package com.alexengrig.dirandfile.domain;

import com.alexengrig.dirandfile.util.ComparatorSnapFile;
import org.hibernate.annotations.SortComparator;

import javax.persistence.*;
import java.util.*;

/**
 * Снимок директории в определенный момент времени
 *
 * @author G. Alex
 */
@Entity
@Table(name = "dir")
public class SnapDirectory {
    /**
     * Ид
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Путь к директории
     */
    private String path;
    /**
     * Дата снимка
     */
    private Date date;

    /**
     * Кол-во вложенных директорий
     */
    private int numDirs;
    /**
     * Кол-во вложенных файлов
     */
    private int numFiles;
    /**
     * Суммарный размер вложенных файлов
     */
    private long totalSize;

    /**
     * Вложенные файлы
     */
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "dir_id")
    @SortComparator(ComparatorSnapFile.class)
    private Set<SnapFile> files = new TreeSet<>();

    /* Constructors */

    public SnapDirectory() {
    }

    /**
     * Создает снимок директории с путем к директории и датой снимка
     *
     * @param path Путь к директории
     * @param date Дата снимка
     */
    public SnapDirectory(String path, Date date) {
        this.path = path;
        this.date = date;
    }

    /* Getters and Setters */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNumDirs() {
        return numDirs;
    }

    public void setNumDirs(int numDirs) {
        this.numDirs = numDirs;
    }

    public int getNumFiles() {
        return numFiles;
    }

    public void setNumFiles(int numFiles) {
        this.numFiles = numFiles;
    }

    public long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }

    public Set<SnapFile> getFiles() {
        return files;
    }

    public void setFiles(Set<SnapFile> files) {
        this.files = files;
    }

    /* Others */

    @Override
    public String toString() {
        return "SnapDirectory{" +
                "path='" + path + '\'' +
                ", date=" + date +
                ", numDirs=" + numDirs +
                ", numFiles=" + numFiles +
                ", totalSize=" + totalSize +
                ", files=" + files.stream().map(Objects::toString).reduce(String::concat) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SnapDirectory that = (SnapDirectory) o;
        return numDirs == that.numDirs &&
                numFiles == that.numFiles &&
                totalSize == that.totalSize &&
                Objects.equals(id, that.id) &&
                Objects.equals(path, that.path) &&
                Objects.equals(date, that.date) &&
                Objects.equals(files, that.files);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, path, date, numDirs, numFiles, totalSize, files);
    }

}
