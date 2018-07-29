package com.alexengrig.dirandfile.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller of greeting
 *
 * @author G. Alex
 */
@Controller
@RequestMapping({"/", "/greeting", "/home"})
public class GreetingController {

    @GetMapping
    public String main() {
        return "greeting";
    }

}
