package ru.vladimir.training.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.vladimir.training.domain.Task;
import ru.vladimir.training.service.TaskService;
import ru.vladimir.training.service.TopicService;

import javax.validation.Valid;
import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/task")
public class TaskController {
    private static final String TASKEXIST = "ДАННАЯ ЗАДАЧА УЖЕ СУЩЕСТВУЕТ";
    //==================================================================================================================
    private final TaskService taskService;
    private final TopicService topicService;
    public TaskController(TaskService taskService, TopicService topicService) {
        this.taskService = taskService;
        this.topicService = topicService;
    }
    //==================================================================================================================
    @GetMapping
    public String taskList(
            Model model
    ) {
        model.addAttribute("tasks", taskService.findAll());
        return "task/taskList";
    }
    //==================================================================================================================
    @GetMapping("update/{task}")
    public String taskEditForm(
            @PathVariable Task task,
            Model model
            ) {
        model.addAttribute("topictitle", task.getTopic().getTitle());
        model.addAttribute("topics", topicService.findTopics(null));
        model.addAttribute("content", task.getContent());
        model.addAttribute("rightanswer", task.getRightanswer());
        model.addAttribute("taskid", task.getId());
        return "task/updatetask";
    }
    //==================================================================================================================
    @GetMapping("add")
    public String taskAddForm(
            Model model
    ) {
        model.addAttribute("topics", topicService.findTopics(null));
        return "task/addtask";
    }
    //==================================================================================================================
    @PostMapping("add")
    public String add(
            @RequestParam("topictitle") String topictitle,
            @Valid Task task,
            BindingResult bindingResult,
            Model model
    ) {
        model.addAttribute("topictitle", topictitle);
        model.addAttribute("topics", topicService.findTopics(null));
        model.addAttribute("content", task.getContent());
        model.addAttribute("rightanswer", task.getRightanswer());
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            return "task/addtask";
        }
        if (!taskService.addTask(task, topictitle)) {
            model.addAttribute("contentError", TASKEXIST);
            return "task/addtask";
        }
        return "redirect:/task";
    }
    //==================================================================================================================
    @PostMapping("update")
    public String upd(
            @RequestParam("taskId") Task oldTask,
            @RequestParam("topictitle") String topictitle,
            @Valid Task task,
            BindingResult bindingResult,
            Model model
    ) {
        model.addAttribute("topictitle", topictitle);
        model.addAttribute("topics", topicService.findTopics(null));
        model.addAttribute("content", task.getContent());
        model.addAttribute("rightanswer", task.getRightanswer());
        model.addAttribute("taskid", oldTask.getId());
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            return "task/updatetask";
        }
        if (!taskService.updTask(task, oldTask, topictitle)) {
            model.addAttribute("contentError", TASKEXIST);
            return "task/updatetask";
        }
        return "redirect:/task";
    }
    //==================================================================================================================
    @PostMapping()
    public String del(
            @RequestParam("taskId") Task task
    ) {
        taskService.removeTask(task);
        return "redirect:/task";
    }
}
