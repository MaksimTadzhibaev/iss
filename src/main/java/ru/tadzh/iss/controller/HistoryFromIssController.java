package ru.tadzh.iss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.tadzh.iss.service.HistoryService;

import javax.xml.bind.JAXBException;

@Controller
@RequestMapping("/historyFromIss")
public class HistoryFromIssController {
    @Autowired
    HistoryService historyService;

    @GetMapping()
    public String listHistoryFromXml(Model model) throws JAXBException {
        model.addAttribute("history", historyService.findAllXmlHistory());
        return "historyFromIss";
    }

    @GetMapping("/saveHistoryFromIss")
    public String saveListHistoryFromXml() throws JAXBException {
        historyService.saveAllXmlHistory();
        return "redirect:/historyFromIss";
    }
}
