package ru.tadzh.iss.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.tadzh.iss.demXML.history.XmlListHistory;
import ru.tadzh.iss.demXML.history.XmlDataHistory;
import ru.tadzh.iss.demXML.history.XmlDocHistory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

@Service
public class HistoryServiceImpl implements HistoryService{

    private final RestTemplate restTemplate;

    public HistoryServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    /**
     * Демаршалинг полученного .xml документа
     * @return XmlListHistory
     * @throws JAXBException
     */
    public XmlListHistory getDemXmlHistory() throws JAXBException {
        String url = "http://iss.moex.com/iss/history/engines/stock/markets/shares/boards/tqbr/securities.xml?date=2013-12-20";
        String body = restTemplate.getForObject(url, String.class);
        StringReader reader = new StringReader(body);
        JAXBContext context = JAXBContext.newInstance(XmlDocHistory.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        XmlDocHistory doc = (XmlDocHistory) unmarshaller.unmarshal(reader);
        XmlDataHistory newData = null;
        for (XmlDataHistory data : doc.getXmlDataHistory()) {
            if (data.getXmlListHistory().getHistories().get(0).getTradeDate()!=null){
                newData = data;
                break;
            }
        }
        assert newData != null;
        return newData.getXmlListHistory();
    }
}
