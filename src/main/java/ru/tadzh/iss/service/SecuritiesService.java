package ru.tadzh.iss.service;

import ru.tadzh.iss.demXML.securities.XmlListSecurities;
import ru.tadzh.iss.dto.securities.SecuritiesDto;

import javax.xml.bind.JAXBException;
import java.util.List;
import java.util.Optional;

public interface SecuritiesService {

    XmlListSecurities getDemXmlSecurities() throws JAXBException;

    void save(SecuritiesDto securitiesDto);

    List<SecuritiesDto> findAll();

    Optional<SecuritiesDto> findById(Long id);

    void deleteById(Long id);
}
