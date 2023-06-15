package ru.tadzh.iss.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.tadzh.iss.entity.history.ListHistory;
import ru.tadzh.iss.entity.history.XmlDataHistory;
import ru.tadzh.iss.entity.history.XmlDocHistory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

@Service
public class HistoryService {

    private final RestTemplate restTemplate;

    public HistoryService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public ListHistory getHistory() throws JAXBException {
        String url = "http://iss.moex.com/iss/history/engines/stock/markets/shares/boards/tqbr/securities.xml?date=2013-12-20";
        String body = restTemplate.getForObject(url, String.class);
        StringReader reader = new StringReader(body);
        JAXBContext context = JAXBContext.newInstance(XmlDocHistory.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        XmlDocHistory doc = (XmlDocHistory) unmarshaller.unmarshal(reader);
        XmlDataHistory newData = null;
        for (XmlDataHistory data : doc.getXmlDataHistory()) {
            if (data.getListHistory().getHistories().get(0).getTradeDate()!=null){
                newData = data;
                break;
            }
        }
        assert newData != null;
        return newData.getListHistory();
    }
}
