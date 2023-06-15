package ru.tadzh.iss.service;

import ru.tadzh.iss.demXML.history.XmlListHistory;
import ru.tadzh.iss.dto.history.HistoryDto;

import javax.xml.bind.JAXBException;
import java.util.List;
import java.util.Optional;

public interface HistoryService {

    public XmlListHistory getDemXmlHistory() throws JAXBException;

    void save(HistoryDto historyDto);

    List<HistoryDto> findAll();

    Optional<HistoryDto> findById(Long id);

    void deleteById(Long id);
}
