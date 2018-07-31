package com.alexengrig.dirandfile.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Контроллер приветствия
 *
 * @author G. Alex
 */
@Controller
@RequestMapping({"/", "/greeting", "/home"})
public class GreetingController {
    /**
     * Главная
     */
    @GetMapping
    public String main() {
        return "greeting";
    }

}
