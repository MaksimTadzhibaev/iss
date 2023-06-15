package ru.tadzh.iss.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.tadzh.iss.demXML.securities.XmlListSecurities;
import ru.tadzh.iss.demXML.securities.XmlDocSecurities;
import ru.tadzh.iss.dto.securities.SecuritiesDto;
import ru.tadzh.iss.entity.Securities;
import ru.tadzh.iss.repository.SecuritiesRepository;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SecuritiesServiceImpl implements SecuritiesService{
    private final RestTemplate restTemplate;

    private final SecuritiesRepository securitiesRepository;

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
    public void save(SecuritiesDto securitiesDto) {
        Securities securities = new Securities(securitiesDto.getSecId(), securitiesDto.getRegNumber(), securitiesDto.getName(), securitiesDto.getEmitentTitle());
        securitiesRepository.save(securities);
    }

    @Override
    public List<SecuritiesDto> findAll() {
        return securitiesRepository.findAll().stream()
                .map(securitiesDto -> new SecuritiesDto(securitiesDto.getSecId(),
                        securitiesDto.getRegNumber(),
                        securitiesDto.getName(),
                        securitiesDto.getEmitentTitle()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<SecuritiesDto> findById(Long id) {
        return securitiesRepository.findById(id)
                .map(securities -> new SecuritiesDto(
                        securities.getSecId(),
                        securities.getRegNumber(),
                        securities.getName(),
                        securities.getEmitentTitle()));
    }

    @Override
    public void deleteById(Long id) {
        securitiesRepository.deleteById(id);
    }
}
