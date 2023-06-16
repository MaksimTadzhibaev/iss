package ru.tadzh.iss.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.tadzh.iss.dto.history.HistoryDto;
import ru.tadzh.iss.dto.history.HistoryListParams;
import ru.tadzh.iss.dto.securities.SecuritiesDto;
import ru.tadzh.iss.service.HistoryService;
import ru.tadzh.iss.service.SecuritiesService;

import javax.xml.bind.JAXBException;
import ru.tadzh.iss.NotFoundException;

import java.util.List;

@Controller
@RequestMapping("/history")
public class HistoryController {
    @Autowired
    HistoryService historyService;
    @Autowired
    SecuritiesService securitiesService;

    @GetMapping(value = "/getHistoryFromIss")
    public void getHistory() throws JAXBException {
        System.out.println(historyService.getDemXmlHistory());
    }

    //    Отображение страницы содержащей Историю

    @GetMapping
    public String listPageHistory(Model model, HistoryListParams historyListParams) {
        model.addAttribute("history", historyService.findAllWithParam(historyListParams));
        return "history";
    }

    //    Создание новой Истории

    @GetMapping("/new")
    public String newHistory(Model model) {
        model.addAttribute("history", new HistoryDto());
        model.addAttribute("securities", securitiesService.findAll());
        return "history_form";
    }

    //    Редактирование Истории
    @GetMapping("/{id}")
    public String editHistory(@PathVariable("id") Long id, Model model) {
        model.addAttribute("history", historyService.findById(id)
                .orElseThrow(() -> new NotFoundException("History not found")));
        model.addAttribute("securities", securitiesService.findAll());
        return "history_form";
    }

    //    Сохранение новой или сохранение изменений в Истории

    @PostMapping
    public String updateHistory(@Valid @ModelAttribute("history") HistoryDto history, BindingResult result, Model model) {
        List<SecuritiesDto> allList = securitiesService.findAll();
        if (history.getSecuritiesDto()==null){
            for (SecuritiesDto sec : allList) {
                if (sec.getSecId().equals(history.getSecId())){
                    history.setSecuritiesDto(sec);
                    break;
                }
                else
                    model.addAttribute("securities", allList);
                    return "history_form";
            }
        }
        if (result.hasErrors()) {
            model.addAttribute("securities", allList);
            return "history_form";
        }
        historyService.save(history);
        return "redirect:/history";
    }

    //  Удаление Истории

    @DeleteMapping("/{id}")
    public String deleteHistory(@PathVariable("id") Long id) {
        historyService.deleteById(id);
        return "redirect:/history";
    }
}
