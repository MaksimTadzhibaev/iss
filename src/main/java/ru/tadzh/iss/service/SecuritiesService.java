package ru.tadzh.iss.service;

import ru.tadzh.iss.demXML.securities.XmlListSecurities;
import ru.tadzh.iss.demXML.securities.XmlSecurities;
import ru.tadzh.iss.entity.Securities;

import javax.xml.bind.JAXBException;
import java.util.List;

public interface SecuritiesService {

    XmlListSecurities getDemXmlSecurities() throws JAXBException;
    List<XmlSecurities> findAllXmlSecurities() throws JAXBException;
    List<Securities> saveAllXmlSecurities() throws JAXBException;



}
