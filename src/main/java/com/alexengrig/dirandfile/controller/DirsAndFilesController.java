package com.alexengrig.dirandfile.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dirs_and_files")
public class DirsAndFilesController {

    @GetMapping
    public String main() {
        return "dirsandfiles";
    }
}
