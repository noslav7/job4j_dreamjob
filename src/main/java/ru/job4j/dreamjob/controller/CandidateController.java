package ru.job4j.dreamjob.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.dreamjob.dto.FileDto;
import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.service.CandidateService;
import ru.job4j.dreamjob.service.CityService;
import ru.job4j.dreamjob.service.SimpleCandidateService;

import java.io.IOException;

@Controller
@RequestMapping("/candidates") /* Работать с кандидатами будем по URI /candidates/** */
@ThreadSafe
public class CandidateController {
    private final CandidateService candidateService;
    private final CityService cityService;

    public CandidateController(CandidateService candidateService, CityService cityService) {
        this.candidateService = candidateService;
        this.cityService = cityService;
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("candidates", candidateService.findAll());
        return "candidates/list";
    }

    @GetMapping("/create")
    public String getCreationPage(Model model) {
        model.addAttribute("candidates", cityService.findAll());
        return "candidates/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Candidate candidate,
                         @RequestParam MultipartFile file, Model model) {
        try {
            candidateService.save(candidate, new FileDto(file.getOriginalFilename(), file.getBytes()));
            return "redirect:/vacancies";
        } catch (Exception exception) {
            model.addAttribute("message", exception.getMessage());
            return "errors/404";
        }
    }

    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable int id) {
        var candidateOptional = candidateService.findById(id);
        if (candidateOptional.isEmpty()) {
            model.addAttribute("message", "Вакансия с указанным идентификатором не найдена");
            return "errors/404";
        }
        model.addAttribute("cities", cityService.findAll());
        model.addAttribute("candidate", candidateOptional.get());
        return "candidates/one";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Candidate candidate,
                         @RequestParam MultipartFile file, Model model) {
        boolean isUpdated = false;
        try {
            isUpdated = candidateService.update(candidate,
                    new FileDto(file.getOriginalFilename(), file.getBytes()));
            if (!isUpdated) {
                model.addAttribute("message", "Кандидат с указанным идентификатором не найден");
                return "errors/404";
            }
            return "redirect:/candidates";
        } catch (Exception exception) {
            model.addAttribute("message", exception.getMessage());
            return "errors/404";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable int id) {
        var isDeleted = candidateService.deleteById(id);
        if (!isDeleted) {
            model.addAttribute("message", "Кандидат с указанным идентификатором не найден");
            return "errors/404";
        }
        return "redirect:/candidates";
    }
}
