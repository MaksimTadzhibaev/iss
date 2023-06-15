package ru.tadzh.iss.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.tadzh.iss.demXML.history.XmlListHistory;
import ru.tadzh.iss.demXML.history.XmlDataHistory;
import ru.tadzh.iss.demXML.history.XmlDocHistory;
import ru.tadzh.iss.dto.history.HistoryDto;
import ru.tadzh.iss.dto.securities.SecuritiesDto;
import ru.tadzh.iss.entity.History;
import ru.tadzh.iss.entity.Securities;
import ru.tadzh.iss.repository.HistoryRepository;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HistoryServiceImpl implements HistoryService{

    private final RestTemplate restTemplate;
    private final HistoryRepository historyRepository;

    public HistoryServiceImpl(RestTemplateBuilder restTemplateBuilder,
                              HistoryRepository historyRepository) {
        this.restTemplate = restTemplateBuilder.build();
        this.historyRepository = historyRepository;
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

    @Override
    public void save(HistoryDto historyDto) {
        SecuritiesDto securitiesDto = historyDto.getSecuritiesDto();
        History history = new History(
                historyDto.getId(),
                historyDto.getTradeDate(),
                historyDto.getSecId(),
                historyDto.getNumTrades(),
                historyDto.getOpen(),
                new Securities(securitiesDto.getSecId(), securitiesDto.getRegNumber(), securitiesDto.getName(), securitiesDto.getEmitentTitle()));
        historyRepository.save(history);
    }

    @Override
    public List<HistoryDto> findAll() {
        return historyRepository.findAll().stream()
                .map(historyDto -> new HistoryDto(historyDto.getId(),
                        historyDto.getTradeDate(),
                        historyDto.getSecId(),
                        historyDto.getNumTrades(),
                        historyDto.getOpen(),
                        new SecuritiesDto(historyDto.getSecurities().getSecId(), historyDto.getSecurities().getRegNumber(), historyDto.getSecurities().getName(), historyDto.getSecurities().getEmitentTitle())))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<HistoryDto> findById(Long id) {
        return historyRepository.findById(id)
                .map(history -> new HistoryDto(
                        history.getId(),
                        history.getTradeDate(),
                        history.getSecId(),
                        history.getNumTrades(),
                        history.getOpen(),
                        new SecuritiesDto(history.getSecurities().getSecId(), history.getSecurities().getRegNumber(), history.getSecurities().getName(), history.getSecurities().getEmitentTitle())));
    }

    @Override
    public void deleteById(Long id) {
        historyRepository.deleteById(id);
    }
}
