package com.alexengrig.dirandfile.controller;

import com.alexengrig.dirandfile.FileSystemService;
import com.alexengrig.dirandfile.domain.SnapDirectory;
import com.alexengrig.dirandfile.repository.SnapDirectoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller of dirs and files
 *
 * @author G. Alex
 */
@Controller
@RequestMapping("/dirs_and_files")
public class DirsAndFilesController {

    /**
     * Repository of SnapDirectory
     */
    private SnapDirectoryRepository directoryRepository;

    @Autowired
    public DirsAndFilesController(SnapDirectoryRepository directoryRepository) {
        this.directoryRepository = directoryRepository;
    }

    @GetMapping
    public String main(Model model) {

        addAllDirectories(model);
        return "dirsandfiles";
    }

    @PostMapping
    public String addDirByPath(@RequestParam String path
            , Model model) {

        SnapDirectory directory = FileSystemService.createSnapDirectory(path);
        directoryRepository.save(directory);

        addAllDirectories(model);
        return "dirsandfiles";
    }

    private void addAllDirectories(Model model) {
        Iterable<SnapDirectory> dirs = directoryRepository.findAll();
        model.addAttribute("dirs", dirs);
    }
}
