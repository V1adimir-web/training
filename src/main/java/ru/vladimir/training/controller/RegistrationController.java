package ru.vladimir.training.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import ru.vladimir.training.domain.User;
import ru.vladimir.training.domain.dto.CaptchaResponseDto;
import ru.vladimir.training.service.UserService;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    private final static String CAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";

    @Autowired
    private UserService userService;
    //==================================================================================================================
    @Value("${recaptcha.secret}")
    private String secret;      // для капчи
    //==================================================================================================================
    @Autowired
    private RestTemplate restTemplate;      // для капчи
    //==================================================================================================================
    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }
    //==================================================================================================================
    @PostMapping("/registration")
    public String addUser(
            @RequestParam("password2") String passwordConfirm,
            @RequestParam("g-recaptcha-response") String captchaResponse,   // для капчи
            @Valid User user,
            BindingResult bindingResult,
            Model model
    ) {
        // для капчи =============
        String url = String.format(CAPTCHA_URL, secret, captchaResponse);
        // 2-м параметром метод ожидает объект, а т.к. нам передавать ничего не нужно во 2-м параметре,то передаем путой list
        CaptchaResponseDto response = restTemplate.postForObject(url, Collections.emptyList(), CaptchaResponseDto.class);
        if (!response.isSuccess()) {
            model.addAttribute("captchaError", "Fill captcha");
        }
        // =======================
        boolean isConfirmEmpty = StringUtils.isEmpty(passwordConfirm);
        if (isConfirmEmpty) {
            model.addAttribute("password2Error", "Password confirmation cannot be empty");
        }
        if (user.getPassword() != null && !user.getPassword().equals(passwordConfirm)) {
            model.addAttribute("passwordError", "Passwords are different!");
        }
        if (isConfirmEmpty || bindingResult.hasErrors() || !response.isSuccess()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            return "registration";
        }
        if (!userService.addUser(user)) {
            model.addAttribute("usernameError", "User exists!");
            return "registration";
        }
        model.addAttribute("registered", "Аккаунт создан. Для завершения регистрации пройдите по ссылке в письме, высланном на Ваш email");
        return "login"; //"redirect:/login";
    }
    //==================================================================================================================
    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code) {
        boolean isActivated = userService.activateUser(code);
        if (isActivated) {
            model.addAttribute("messageType", "success");
            model.addAttribute("message", "Ваш аккаунт успешно активирован!");
        } else {
            model.addAttribute("messageType", "danger");
            model.addAttribute("message", "Код активации не найден. Возможно вы уже прошли автивацию.");
        }
        return "login";
    }
}
