package ru.vladimir.training.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.vladimir.training.service.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)  // для того, чтобы поль-ль не имеющий прав на просмотр опред-х страниц не мог их открыть
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;
    //==================================================================================================================
    @Autowired
    private PasswordEncoder passwordEncoder;
    //==================================================================================================================
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder(8); // параметр strength - определяет надежность ключа шифрования
    }
    //==================================================================================================================
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/registration", "/static/**", "/activate/*").permitAll()   // для главной стр. и стр. регистрации разрешаем полный доступ, ресурсы в static не должны требовать авторизации
                .anyRequest().authenticated()   // для всех остальных страниц требуем авторизации
                .and()
                // подключаем форму авторизации
                .formLogin()
                // указываем, что форма авторизации находится по адресу /login
                .loginPage("/login")
                .permitAll()    // разрешаем всем доступ к форме авторизации
                .and()
                // подключаем rememberMe
                .rememberMe()
                .and()
                // подключаем logout
                .logout()
                .permitAll(); // разрешаем всем доступ к logout
    }
    //==================================================================================================================
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(passwordEncoder);
    }
}
