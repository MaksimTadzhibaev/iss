package ru.tadzh.iss.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.tadzh.iss.entity.securities.ListSecurities;
import ru.tadzh.iss.entity.securities.XmlDocSecurities;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

@Service
public class SecuritiesService {
    private final RestTemplate restTemplate;

    public SecuritiesService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public ListSecurities getSecurities() throws JAXBException {
        String url = "http://iss.moex.com/iss/securities.xml";
        String body = restTemplate.getForObject(url, String.class);
        StringReader reader = new StringReader(body);
        JAXBContext context = JAXBContext.newInstance(XmlDocSecurities.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        XmlDocSecurities doc = (XmlDocSecurities) unmarshaller.unmarshal(reader);
        return doc.getXmlDataSecurities().getListSecurities();
    }
}
