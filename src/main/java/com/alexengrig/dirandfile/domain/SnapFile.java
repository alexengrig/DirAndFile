package com.alexengrig.dirandfile.domain;

import javax.persistence.*;
import java.util.Objects;

/**
 * Snapshot of file
 *
 * @author G. Alex
 */
@Entity
@Table(name = "file")
public class SnapFile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * File name
     */
    private String name;
    /**
     * File size
     */
    private long size;
    /**
     * File is a directory
     */
    private boolean isDirectory;

    /* Constructors */

    public SnapFile() {
    }

    /**
     * Create snapshot of file, with file name, file size and mark file is directory
     *
     * @param name        File name
     * @param size        File size
     * @param isDirectory Mark that file is directory
     */
    public SnapFile(String name, long size, boolean isDirectory) {
        this.name = name;
        this.size = size;
        this.isDirectory = isDirectory;
    }

    /* Getters and Setters */

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

}
