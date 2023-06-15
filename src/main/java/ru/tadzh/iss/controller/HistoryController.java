package ru.tadzh.iss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tadzh.iss.service.HistoryServiceImpl;

import javax.xml.bind.JAXBException;

@RestController
public class HistoryController {
    @Autowired
    HistoryServiceImpl historyServiceImpl;

    @GetMapping(value = "/history")
    public void getHistory() throws JAXBException {
        System.out.println(historyServiceImpl.getDemXmlHistory());
    }
}
