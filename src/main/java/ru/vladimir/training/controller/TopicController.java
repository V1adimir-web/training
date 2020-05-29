package ru.vladimir.training.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.vladimir.training.domain.Topic;
import ru.vladimir.training.repo.TopicRepo;
import ru.vladimir.training.service.SubjectService;
import ru.vladimir.training.service.TopicService;

import javax.validation.Valid;
import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/topic")
public class TopicController {
    private static final String TOPICEXIST = "ДАННАЯ ТЕМА УЖЕ СУЩЕСТВУЕТ";

    @Autowired
    SubjectService subjectService;
    //==================================================================================================================
    @Autowired
    TopicService topicService;
    //==================================================================================================================
    @GetMapping
    public String topicList(Model model) {
        model.addAttribute("topics", topicService.findAll());
        return "topicList";
    }
    //==================================================================================================================
    @GetMapping("update/{topic}")
    public String topicEditForm(
            @PathVariable Topic topic,
            Model model
            ) {
        model.addAttribute("title", topic.getTitle());
        model.addAttribute("topicid", topic.getId());
        model.addAttribute("subjects", subjectService.findAll());
        model.addAttribute("subjectname", topic.getSubject().getSubjectname());
        return "updatetopic";
    }
    //==================================================================================================================
    @GetMapping("add")
    public String topicAddForm(
            Model model
    ) {
        model.addAttribute("subjects", subjectService.findAll());
        return "addtopic";
    }
    //==================================================================================================================
    @PostMapping("add")
    public String add(
            @RequestParam("subjectname") String subjectname,
            @Valid Topic topic,
            BindingResult bindingResult,
            Model model
    ) {
        model.addAttribute("title", topic.getTitle());
        model.addAttribute("subjectname", subjectname);
        model.addAttribute("subjects", subjectService.findAll());
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            return "addtopic";
        }
        if (!topicService.addTopic(topic, subjectname)) {
            model.addAttribute("titleError", TOPICEXIST);
            return "addtopic";
        }
        return "redirect:/topic";
    }
    //==================================================================================================================
    @PostMapping("update")
    public String upd(
            @RequestParam("topicId") Topic oldTopic,
            @RequestParam("subjectname") String subjectname,
            @Valid Topic topic,
            BindingResult bindingResult,
            Model model
    ) {
        model.addAttribute("title", topic.getTitle());
        model.addAttribute("topicid", oldTopic.getId());
        model.addAttribute("subjects", subjectService.findAll());
        model.addAttribute("subjectname", subjectname);
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            return "updatetopic";
        }
        if (!topicService.updTopic(topic, oldTopic, subjectname)) {
            model.addAttribute("titleError", TOPICEXIST);
            return "updatetopic";
        }
        return "redirect:/topic";
    }
    //==================================================================================================================
    @PostMapping()
    public String del(
            @RequestParam("topicId") Topic topic
    ) {
        topicService.removeTopic(topic);
        return "redirect:/topic";
    }
}
