package ru.vladimir.training.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.vladimir.training.domain.Subject;
import ru.vladimir.training.repo.SubjectRepo;
import ru.vladimir.training.service.SubjectService;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/subject")
public class SubjectController {
    private static final String SUBJECTEXIST = "ДАННЫЙ ПРЕДМЕТ УЖЕ СУЩЕСТВУЕТ";

    @Autowired
    SubjectRepo subjectRepo;

    private final SubjectService subjectService;
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }
    //==================================================================================================================
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String subjectList(Model model) {
        model.addAttribute("subjects", subjectService.findAll());
        return "subjectList";
    }
    //==================================================================================================================
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("update/{subject}")
    public String subjectEditForm(
            @PathVariable Subject subject,
            Model model
    ) {
        model.addAttribute("subjectname", subject.getSubjectname());
        model.addAttribute("subjectid", subject.getId());
        return "updatesubject";
    }
    //==================================================================================================================
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("add")
    public String subjectAddForm(
    ) {
        return "addsubject";
    }
    //==================================================================================================================
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("add")
    public String addSubject(
            @Valid Subject subject,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("subjectname", subject.getSubjectname());
            return "addsubject";
        }
        if (!subjectService.addSubject(subject)) {
            model.addAttribute("subjectnameError", SUBJECTEXIST);
            model.addAttribute("subjectname", subject.getSubjectname());
            return "addsubject";
        }
        return "redirect:/subject";
    }
    //==================================================================================================================
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("update")        // "update/{subject}"
    public String updateSubject(
            @RequestParam("subjectId") Subject oldSubject,
            @Valid Subject subject,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("subjectname", subject.getSubjectname());
            model.addAttribute("subjectid", oldSubject.getId());
            return "updatesubject";
        }
        if (!subjectService.updSubject(subject, oldSubject)) {
            model.addAttribute("subjectnameError", SUBJECTEXIST);
            model.addAttribute("subjectname", subject.getSubjectname());
            model.addAttribute("subjectid", oldSubject.getId());
            return "updatesubject";
        }
        return "redirect:/subject";
    }
    //==================================================================================================================
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping()
    public String deleteSubject(
            @RequestParam("subjectId") Subject subject
    ) {
        subjectService.removeSubject(subject);
        return "redirect:/subject";
    }
}