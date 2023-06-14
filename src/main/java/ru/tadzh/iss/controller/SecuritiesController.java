package ru.tadzh.iss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.tadzh.iss.service.SecuritiesService;

import javax.xml.bind.JAXBException;
import java.io.*;

@RestController
public class SecuritiesController {

    @Autowired
    SecuritiesService securitiesService;

    @GetMapping(value = "/securities")
    public void getSecurities() throws JAXBException {
        System.out.println(securitiesService.getSecurities());
    }
}
