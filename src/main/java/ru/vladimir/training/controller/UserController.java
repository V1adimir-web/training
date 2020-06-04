package ru.vladimir.training.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
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
    public String getProfile(
            Model model,
            @AuthenticationPrincipal User user
    ) {
        model.addAttribute("firstname", user.getFirstname());
        model.addAttribute("lastname", user.getLastname());
        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("lastdatechange", user.getLastDateChange());
        return "profile";
    }
    //==================================================================================================================
    @GetMapping("profile/changepass")
    public String changePasswordForm(
            Model model,
            @AuthenticationPrincipal User user
    ) {
        model.addAttribute("username", user.getUsername());
        return "profilechangepass";
    }
    //==================================================================================================================
    @PostMapping("profile/changepass")
    public String changePassword(
            @RequestParam("newpassword") String passwordNew,
            @RequestParam("newpassword2") String passwordNew2,
            @AuthenticationPrincipal User user,
            Model model
    ) {
        boolean isErrorFinded = false;
        if (StringUtils.isEmpty(passwordNew.trim())) {
            model.addAttribute("newpasswordError", "Необходимо ввести новый пароль");
            isErrorFinded = true;
        }
        if (StringUtils.isEmpty(passwordNew2.trim())) {
            model.addAttribute("newpassword2Error", "Необходимо ввести новый пароль еще раз");
            isErrorFinded = true;
        }
        if (!passwordNew.equals(passwordNew2)) {
            model.addAttribute("newpasswordError", "Пароли не совпадают");
            isErrorFinded = true;
        }
        if (isErrorFinded) { return "profilechangepass"; }
        userService.changePassword(user, passwordNew);
        model.addAttribute("message", "Пароль успешно изменен. При следующем входе в Ваш аккаунт необходимо использовать новый пароль");
        return "profile"; //"redirect:/user/profile";
    }
}
