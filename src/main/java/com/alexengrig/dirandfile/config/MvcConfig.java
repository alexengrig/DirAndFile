package com.alexengrig.dirandfile.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Конфигурация MVC
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    /**
     * Добавленя ресурса
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**") // Доступ к статик ресурсам
                .addResourceLocations("classpath:/static/");
    }
}
