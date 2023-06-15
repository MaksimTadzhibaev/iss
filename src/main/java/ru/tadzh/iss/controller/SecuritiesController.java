package ru.tadzh.iss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.tadzh.iss.service.SecuritiesServiceImpl;

import javax.xml.bind.JAXBException;

@RestController
public class SecuritiesController {

    @Autowired
    SecuritiesServiceImpl securitiesServiceImpl;

    @GetMapping(value = "/securities")
    public void getSecurities() throws JAXBException {
        System.out.println(securitiesServiceImpl.getDemXmlSecurities());
    }
}
