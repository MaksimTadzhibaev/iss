package ru.tadzh.iss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.tadzh.iss.demXML.history.XmlHistory;
import ru.tadzh.iss.demXML.history.XmlListHistory;
import ru.tadzh.iss.demXML.history.XmlDataHistory;
import ru.tadzh.iss.demXML.history.XmlDocHistory;
import ru.tadzh.iss.entity.History;
import ru.tadzh.iss.entity.Securities;
import ru.tadzh.iss.repository.HistoryRepository;
import ru.tadzh.iss.repository.SecuritiesRepository;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class HistoryServiceImpl implements HistoryService{

    private final RestTemplate restTemplate;
    private final HistoryRepository historyRepository;
    private final SecuritiesRepository securitiesRepository;

    @Autowired
    public HistoryServiceImpl(RestTemplateBuilder restTemplateBuilder,
                              HistoryRepository historyRepository,
                              SecuritiesRepository securitiesRepository) {
        this.restTemplate = restTemplateBuilder.build();
        this.historyRepository = historyRepository;
        this.securitiesRepository = securitiesRepository;
    }

    /**
     * Демаршалинг полученного .xml документа
     * @return XmlListHistory
     * @throws JAXBException
     */
    public XmlListHistory getDemXmlHistory() throws JAXBException {
//        String url = "http://iss.moex.com/iss/history/engines/stock/markets/shares/boards/tqbr/securities.xml?date=2013-12-20";
        String url = "http://iss.moex.com/iss/history/engines/stock/markets/shares/securities.xml";
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

    @Override
    public List<XmlHistory> findAllXmlHistory() throws JAXBException {
        return getDemXmlHistory().getHistories();
    }

    @Override
    public List<History> saveAllXmlHistory() throws JAXBException {
        List<XmlHistory>  listXmlHistory = getDemXmlHistory().getHistories();
        List<History> historyList = new ArrayList<>();
        Map<String, Securities> securitiesMap =
                securitiesRepository.findAll().stream().collect(Collectors.toMap(Securities::getSecId, securities -> securities));
        System.out.println(securitiesMap);
        System.out.println(historyList);
        for (XmlHistory xmlHistory : listXmlHistory) {
            if (securitiesMap.get(xmlHistory.getSecId()) != null) {
             historyList.add(new History(
                     null,
                     xmlHistory.getTradeDate(),
                     xmlHistory.getSecId(),
                     xmlHistory.getNumTrades(),
                     xmlHistory.getOpen(),
                     securitiesMap.get(xmlHistory.getSecId())));
            }
        }
        return historyRepository.saveAll(historyList);
    }
}
