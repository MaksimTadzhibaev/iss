package ru.tadzh.iss.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.tadzh.iss.NotFoundException;
import ru.tadzh.iss.dto.history.HistoryDto;
import ru.tadzh.iss.dto.history.HistoryListParams;
import ru.tadzh.iss.dto.securities.SecuritiesDto;
import ru.tadzh.iss.dto.securities.SecuritiesListParams;
import ru.tadzh.iss.service.SecuritiesService;

import javax.xml.bind.JAXBException;

@Controller
@RequestMapping("/securities")
public class SecuritiesController {

    @Autowired
    SecuritiesService securitiesService;

    @GetMapping(value = "/getSecuritiesFromIss")
    public void getSecurities() throws JAXBException {
        System.out.println(securitiesService.getDemXmlSecurities());
    }


    //    Отображение страницы содержащей Историю

    @GetMapping
    public String listPageSecurities(Model model, SecuritiesListParams securitiesListParams) {
        model.addAttribute("securities", securitiesService.findAllWithParam(securitiesListParams));
        return "securities";
    }

    //    Создание новой Истории

    @GetMapping("/new")
    public String newSecurities(Model model) {
        model.addAttribute("securities", new SecuritiesDto());
        return "securities_form";
    }

    //    Редактирование Истории
    @GetMapping("/{id}")
    public String editSecurities(@PathVariable("id") String id, Model model) {
        model.addAttribute("securities", securitiesService.findById(id)
                .orElseThrow(() -> new NotFoundException("Securities not found")));
        return "securities_form";
    }

    //    Сохранение новой или сохранение изменений в Истории

    @PostMapping
    public String updateSecurities(@Valid @ModelAttribute("securities") SecuritiesDto securities, BindingResult result) {
//        model.addAttribute("securities", new SecuritiesDto());
        if (result.hasErrors()) {
            return "securities_form";
        }
        System.out.println(securities);
        securitiesService.save(securities);
        return "redirect:/securities";
    }

    //    Удаление Истории

    @DeleteMapping("/{id}")
    public String deleteSecurities(@PathVariable("id") String id) {
        securitiesService.deleteById(id);
        return "redirect:/securities";
    }
}
