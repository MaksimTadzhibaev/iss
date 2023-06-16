package ru.tadzh.iss.service;

import org.springframework.data.domain.Page;
import ru.tadzh.iss.demXML.history.XmlHistory;
import ru.tadzh.iss.demXML.history.XmlListHistory;
import ru.tadzh.iss.dto.history.HistoryDto;
import ru.tadzh.iss.dto.history.HistoryListParams;
import ru.tadzh.iss.entity.History;

import javax.xml.bind.JAXBException;
import java.util.List;
import java.util.Optional;

public interface HistoryService {
    public XmlListHistory getDemXmlHistory() throws JAXBException;

    void save(HistoryDto historyDto);

    List<HistoryDto> findAll();

    List<XmlHistory> findAllXml() throws JAXBException;

    List<History> saveAllXml(List<XmlHistory> xmlHistoryList);

    Page<HistoryDto> findAllWithParam(HistoryListParams historyListParams);

    Optional<HistoryDto> findById(Long id);

    void deleteById(Long id);
}
