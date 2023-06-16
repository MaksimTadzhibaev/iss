package ru.tadzh.iss.service;

import org.springframework.data.domain.Page;
import ru.tadzh.iss.demXML.securities.XmlListSecurities;
import ru.tadzh.iss.dto.history.HistoryDto;
import ru.tadzh.iss.dto.securities.SecuritiesDto;
import ru.tadzh.iss.dto.securities.SecuritiesListParams;
import ru.tadzh.iss.entity.History;

import javax.xml.bind.JAXBException;
import java.util.List;
import java.util.Optional;

public interface SecuritiesService {

    XmlListSecurities getDemXmlSecurities() throws JAXBException;

    void save(SecuritiesDto securitiesDto);

    List<SecuritiesDto> findAll();

    Page<SecuritiesDto> findAllWithParam(SecuritiesListParams securitiesListParams);

    Optional<SecuritiesDto> findById(String id);

    void deleteById(String id);
}
