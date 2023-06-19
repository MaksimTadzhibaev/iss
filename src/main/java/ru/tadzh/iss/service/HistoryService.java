package ru.tadzh.iss.service;

import ru.tadzh.iss.demXML.history.XmlHistory;
import ru.tadzh.iss.demXML.history.XmlListHistory;
import ru.tadzh.iss.entity.History;

import javax.xml.bind.JAXBException;
import java.util.List;

public interface HistoryService {
    public XmlListHistory getDemXmlHistory() throws JAXBException;
    List<XmlHistory> findAllXmlHistory() throws JAXBException;
    List<History> saveAllXmlHistory() throws JAXBException;
}