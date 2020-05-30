package ru.vladimir.training.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Value("${upload.path}")
    private String uploadPath;
    //=== для капчи: ===================================================================================================
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
    //==================================================================================================================
    public void addViewControllers(ViewControllerRegistry registry) {
        //registry.addViewController("/home").setViewName("home");
        //registry.addViewController("/").setViewName("home");
        //registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/login").setViewName("login");
    }
    //==================================================================================================================
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**")
                .addResourceLocations("file:///" + uploadPath + "/");    // "file://" указывает что это протокол file,т.е. место в файловой системе

        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");    // classpath: означает, что Spring будет искать ресурс в корне проекта в директории с классами
        // и в директории с ресурсами

    }
}
