package com.alexengrig.dirandfile.controller;

import com.alexengrig.dirandfile.domain.SnapFile;
import com.alexengrig.dirandfile.service.FileSystemService;
import com.alexengrig.dirandfile.domain.SnapDirectory;
import com.alexengrig.dirandfile.repository.SnapDirectoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

/**
 * Контроллер для работы с директориями и папками
 */
@Controller
@RequestMapping("/dirs_and_files")
public class DirsAndFilesController {

    /**
     * Репозиторий директорий
     */
    private SnapDirectoryRepository directoryRepository;
    /**
     * Сервис файловой системы
     */
    private FileSystemService fileSystemService;

    /**
     * Конструткор
     */
    @Autowired
    public DirsAndFilesController(SnapDirectoryRepository directoryRepository, FileSystemService fileSystemService) {
        this.directoryRepository = directoryRepository;
        this.fileSystemService = fileSystemService;
    }

    /**
     * Главная
     */
    @GetMapping
    public String main(Model model) {
        addAllDirectories(model);
        return "dirsandfiles";
    }

    /**
     * Добавить директорию по путю
     */
    @PostMapping("addDir")
    public String addDirByPath(@RequestParam String path
            , Model model) {
        try {
            SnapDirectory directory = fileSystemService.createSnapDirectory(path);
            directoryRepository.save(directory);
        } catch (IOException e) {
            model.addAttribute("message", e.getMessage());
        }

        addAllDirectories(model);
        return "dirsandfiles"; //"redirect:/dirs_and_files";
    }

    /**
     * Удалить директорию по id
     */
    @PostMapping("deleteDir")
    public String deleteDir(@RequestParam(name = "dirId") SnapDirectory directory
            , Model model) {
        directoryRepository.delete(directory);

        addAllDirectories(model);
        return "dirsandfiles"; //"redirect:/dirs_and_files";
    }

    /**
     * Для перехода на главную
     */
    @GetMapping("addDir")
    public String addDir() {
        return "redirect:/dirs_and_files";
    }

    /**
     * Для перехода на главную
     */
    @GetMapping("deleteDir")
    public String deleteDir() {
        return "redirect:/dirs_and_files";
    }

    private void addAllDirectories(Model model) {
        Iterable<SnapDirectory> dirs = directoryRepository.findByOrderByDateDesc();
        for (SnapDirectory dir : dirs) {
            Set<SnapFile> files = dir.getFiles();
        }
        model.addAttribute("dirs", dirs);
    }
}
