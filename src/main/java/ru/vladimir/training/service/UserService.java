package ru.vladimir.training.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.vladimir.training.domain.Role;
import ru.vladimir.training.domain.User;
import ru.vladimir.training.repo.UserRepo;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
    //==================================================================================================================
    @Autowired
    private MailSender mailSender;
    //==================================================================================================================
    @Autowired
    private PasswordEncoder passwordEncoder;
    //==================================================================================================================
    @Value("${hostname}")
    private String hostname;
    //==================================================================================================================
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
    //==================================================================================================================
    public boolean addUser(User user) {
        User userFromDb = userRepo.findByUsername(user.getUsername());
        if (userFromDb != null) {
            return false;
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));     // singleton дает возможность создать коллекцию с одним значением
        user.setActivationCode(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        sendMessage(user);
        return true;
    }
    //==================================================================================================================
    private void sendMessage(User user) {
        if (!StringUtils.isEmpty(user.getEmail())) { // StringUtils.isEmpty - функция Spring проверяет на empty и null
            String message = String.format(
                    "Здравствуйте, %s! \n" +
                            "Добро пожаловать на Train-in. Для активации Вашего аккаунта пройдите по ссылке: http://%s/activate/%s",
                    user.getUsername(),
                    hostname,
                    user.getActivationCode()
            );
            mailSender.send(user.getEmail(), "Activation code", message);
        }
    }
    //==================================================================================================================
    public boolean activateUser(String code) {
        User user = userRepo.findByActivationCode(code);
        if (user == null) {
            return false;
        }
        user.setActivationCode(null); // установка в null будет означать,что пользователь подвердил свой почт.ящик
        userRepo.save(user);
        return true;
    }
    //==================================================================================================================
    public List<User> findAll() {
        return userRepo.findAll();
    }
    //==================================================================================================================
    public void saveUser(User user, String username, Map<String, String> form) {
        user.setUsername(username);
        Set<String> roles = Arrays.stream(Role.values()).map(Role::name).collect(Collectors.toSet());
        user.getRoles().clear();
        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userRepo.save(user);
    }
    //==================================================================================================================
    public void updateProfile(User user, String password, String email) {
        String userEmail = user.getEmail();
        boolean isEmailChanged = (email != null && !email.equals(userEmail)) || (userEmail != null && !userEmail.equals(email));
        if (isEmailChanged) {
            user.setEmail(email);
            if (!StringUtils.isEmpty(email)) {
                user.setActivationCode(UUID.randomUUID().toString());
            }
        }
        if (!StringUtils.isEmpty(password)) {
            user.setPassword(password);
        }
        userRepo.save(user);
        if (isEmailChanged) {
            sendMessage(user);
        }
    }
    //==================================================================================================================
    public void changePassword(User user, String newPassword) {
        if (!StringUtils.isEmpty(newPassword)) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepo.save(user);
        }
    }
}
