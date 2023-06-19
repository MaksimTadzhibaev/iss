package ru.tadzh.iss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.tadzh.iss.service.SecuritiesService;

import javax.xml.bind.JAXBException;

@Controller
@RequestMapping("/securitiesFromIss")
public class SecuritiesFromIssController {

    @Autowired
    SecuritiesService securitiesService;

    // Отображение информации из ISS по бумаге за определенную дату
    @GetMapping()
    public String listSecuritiesFromXml(Model model) throws JAXBException {
        model.addAttribute("securities", securitiesService.findAllXmlSecurities());
        return "securitiesFromIss";
    }

    // Сохранение информации из ISS в БД по бумаге за определенную дату
    @GetMapping("/saveSecuritiesFromIss")
    public String saveListSecuritiesFromXml() throws JAXBException {
        securitiesService.saveAllXmlSecurities();
        return "redirect:/securitiesFromIss";
    }
}
