package ru.tadzh.iss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.tadzh.iss.demXML.securities.XmlListSecurities;
import ru.tadzh.iss.demXML.securities.XmlDocSecurities;
import ru.tadzh.iss.demXML.securities.XmlSecurities;
import ru.tadzh.iss.entity.Securities;
import ru.tadzh.iss.repository.SecuritiesRepository;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class SecuritiesServiceImpl implements SecuritiesService{
    private final RestTemplate restTemplate;
    private final SecuritiesRepository securitiesRepository;

    @Autowired
    public SecuritiesServiceImpl(RestTemplateBuilder restTemplateBuilder, SecuritiesRepository securitiesRepository) {
        this.restTemplate = restTemplateBuilder.build();
        this.securitiesRepository = securitiesRepository;
    }

    /**
     * Демаршалинг полученного .xml документа
     * @return XmlListSecurities
     * @throws JAXBException
     */
    public XmlListSecurities getDemXmlSecurities() throws JAXBException {
        String url = "http://iss.moex.com/iss/securities.xml";
        String body = restTemplate.getForObject(url, String.class);
        StringReader reader = new StringReader(body);
        JAXBContext context = JAXBContext.newInstance(XmlDocSecurities.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        XmlDocSecurities doc = (XmlDocSecurities) unmarshaller.unmarshal(reader);
        return doc.getXmlDataSecurities().getXmlListSecurities();
    }

    @Override
    public List<XmlSecurities> findAllXmlSecurities() throws JAXBException {
        return getDemXmlSecurities().getSecurities();
    }

    @Override
    public List<Securities> saveAllXmlSecurities() throws JAXBException {
        List<XmlSecurities>  securitiesXmlList = getDemXmlSecurities().getSecurities();
        List<Securities> securitiesList = new ArrayList<>();
        for (XmlSecurities xmlSecurities : securitiesXmlList) {
            securitiesList.add(new Securities(
                    xmlSecurities.getSecId(),
                    xmlSecurities.getRegNumber(),
                    xmlSecurities.getName(),
                    xmlSecurities.getEmitentTitle()));
        }
        return securitiesRepository.saveAll(securitiesList);
    }
}
