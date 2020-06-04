package ru.vladimir.training.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.vladimir.training.service.SubjectService;

@Controller
public class MainController {

    private final SubjectService subjectService;
    public MainController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/")
    public String greeting(
            Model model
    ) {
        model.addAttribute("subjects", subjectService.findSubjects(null));
        return "greeting";
    }
    //==================================================================================================================
    @GetMapping("/main")
    public String main(
            Model model
    ) {
        model.addAttribute("subjects", subjectService.findSubjects(null));
        return "main";
    }
    //==================================================================================================================
    @GetMapping("/login")
    public String login(
            Model model
    ) {
        model.addAttribute("subjects", subjectService.findSubjects(null));
        return "login";
    }
}
