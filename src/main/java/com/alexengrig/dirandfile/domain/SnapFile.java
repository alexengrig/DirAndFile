package com.alexengrig.dirandfile.domain;

import com.alexengrig.dirandfile.util.ComparatorSnapFile;

import javax.persistence.*;
import java.util.Objects;

/**
 * Снимок файла
 *
 * @author G. Alex
 */
@Entity
@Table(name = "file")
public class SnapFile implements Comparable<SnapFile> {
    /**
     * Ид
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Имя файла
     */
    private String name;
    /**
     * Размер файла
     */
    private long size;
    /**
     * Файл является директорией
     */
    private boolean isDirectory;

    /* Constructors */

    public SnapFile() {
    }

    /**
     * Создает снимок файла с именем и размером файла, пометка о том что файл яляется директорией
     *
     * @param name        Имя файла
     * @param size        Размер файла
     * @param isDirectory Пометка о том что файл является директорией
     */
    public SnapFile(String name, long size, boolean isDirectory) {
        this.name = name;
        this.size = size;
        this.isDirectory = isDirectory;
    }

    /* Getters and Setters */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public boolean isDirectory() {
        return isDirectory;
    }

    public void setDirectory(boolean directory) {
        isDirectory = directory;
    }

    /**
     * Получить размер файла в строковом представлении с единицией измерения
     */
    public String getSizeString() {
        String[] types = {"b", "Kb", "Mb", "Gb", "Pb"};
        double fsize = size;
        int delimiter = 1024;
        int e;
        for (e = 0; e < types.length; e++) {
            if (fsize / delimiter < 1) {
                break;
            } else {
                fsize /= delimiter;
            }
        }
        return String.format("%.2f%s", fsize, types[e]);
    }

    /* Others */

    @Override
    public String toString() {
        return "SnapFile{" +
                "name='" + name + '\'' +
                ", size=" + size +
                ", isDirectory=" + isDirectory +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SnapFile snapFile = (SnapFile) o;
        return size == snapFile.size &&
                isDirectory == snapFile.isDirectory &&
                Objects.equals(name, snapFile.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, size, isDirectory);
    }

    /* Comparable */

    @Override
    public int compareTo(SnapFile o) {
        return ComparatorSnapFile.defaultComparator.compare(this, o);
    }
}
