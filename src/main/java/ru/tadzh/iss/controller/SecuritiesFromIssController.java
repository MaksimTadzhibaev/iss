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

    @GetMapping(value = "/getSecuritiesFromIss")
    public void getSecurities() throws JAXBException {
        System.out.println(securitiesService.getDemXmlSecurities());
    }

    @GetMapping()
    public String listSecuritiesFromXml(Model model) throws JAXBException {
        model.addAttribute("securities", securitiesService.findAllXmlSecurities());
        return "securitiesFromIss";
    }

    @GetMapping("/saveSecuritiesFromIss")
    public String saveListSecuritiesFromXml() throws JAXBException {
        securitiesService.saveAllXmlSecurities();
        return "redirect:/securitiesFromIss";
    }
}
