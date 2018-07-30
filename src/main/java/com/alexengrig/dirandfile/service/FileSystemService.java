package com.alexengrig.dirandfile.service;

import com.alexengrig.dirandfile.domain.SnapDirectory;
import com.alexengrig.dirandfile.domain.SnapFile;
import com.alexengrig.dirandfile.util.ComparatorSnapFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Интерфейс файловой системы
 */
@Service
public class FileSystemService {

    @Value("${filesystem.path.root}")
    private String rootPath;

    public SnapDirectory createSnapDirectory(String path) throws IOException {
        if (path == null || path.isEmpty()) { // Если путь пустой
            throw new IOException("Wrong path! Path to directory is empty: " + path);
        }
        File fileDirectory;
        if (path.startsWith("/")) {
            fileDirectory = new File(rootPath, path); // Получили файл директории по относительному пути
        } else {
            fileDirectory = new File(path); // Получили файл директории по абсолютмному пути
        }


        if (!fileDirectory.exists()) { // Если файл не существует
            throw new IOException("Wrong path! Directory does not exist : " + path);
        }
        if (!fileDirectory.isDirectory()) { // Если путь не к директории
            throw new IOException("Wrong path! Path not to directory: " + path);
        }

        File[] files = fileDirectory.listFiles(); // Получили вложенные файлы

        SnapDirectory snapDirectory = new SnapDirectory(path, new Date()); // Создали снимок дир.

        if (files == null || files.length == 0) { // Если вложенных файлов нет
            return snapDirectory; // Вернули пустую директорию
        }

        int numDirs = 0; // Кол-во влож. дир.
        int numFiles = 0; // Кол-во влож. файлов
        long totalSize = 0; // Суммарный размер влож. файлов
        TreeSet<SnapFile> snapFiles = new TreeSet<>(ComparatorSnapFile.defaultComparator); // Набор вложенных файлов

        for (File file : files) { // По каждому вложенному файлу
            String fileName = file.getName(); // Имя файла
            long fileSize = file.length(); // Размер файла
            boolean isDirectory = file.isDirectory(); // Является ли директорией
            if (isDirectory) { // Если директория
                numDirs++; // Увеличели кол-во директорий
            } else {
                numFiles++; // Увеличели кол-во файлов
                totalSize += fileSize; // Увеличели суммарный размер влож. файлов
            }
            SnapFile snapFile = new SnapFile(fileName, fileSize, isDirectory); // Создали сниомк файла
            snapFiles.add(snapFile); // Добавили в набор влож. файлов
        }

        snapDirectory.setNumDirs(numDirs); // Присвоили кол-во влож. дир.
        snapDirectory.setNumFiles(numFiles); // Присвоили кол-во влож. файлов
        snapDirectory.setTotalSize(totalSize); // Присвоили суммарный размер влож. файлов
        snapDirectory.setFiles(snapFiles); // Присвоили набор влож. файлов

        return snapDirectory; // Вернули пустую директорию
    }
}
