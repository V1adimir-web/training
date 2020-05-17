package ru.vladimir.training.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vladimir.training.domain.Role;
import ru.vladimir.training.domain.User;
import ru.vladimir.training.service.UserService;

import java.util.Map;

@Controller
@RequestMapping("/user")    // указываем @RequestMapping("/user") для класса, чтобы в каждом методе не подписывать путь /user
public class UserController {
    @Autowired
    private UserService userService;
    //==================================================================================================================
    @PreAuthorize("hasAuthority('ADMIN')")  // эта аннотацию будет проверять перед выполнением каждого метода наличие у поль-ля прав админа
    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userService.findAll());
        return "userList";
    }
    //==================================================================================================================
    @PreAuthorize("hasAuthority('ADMIN')")  // эта аннотацию будет проверять перед выполнением каждого метода наличие у поль-ля прав админа
    @GetMapping("{user}")   // фигурные скобки означают, что Spring ожидает кроме /user еще его id
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }
    //==================================================================================================================
    @PreAuthorize("hasAuthority('ADMIN')")  // эта аннотацию будет проверять перед выполнением каждого метода наличие у поль-ля прав админа
    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user)
    {
        userService.saveUser(user, username, form);
        return "redirect:/user";
    }
    //==================================================================================================================
    @GetMapping("profile")
    public String getProfile(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());
        return "profile";
    }
    //==================================================================================================================
    @PostMapping("profile")
    public String updateProfile(
            @AuthenticationPrincipal User user,
            @RequestParam String password,
            @RequestParam String email
    ) {
        userService.updateProfile(user, password, email);
        return "redirect:/user/profile";
    }
}
