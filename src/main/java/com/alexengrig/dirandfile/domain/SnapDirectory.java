package com.alexengrig.dirandfile.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

/**
 * Snapshot of directory
 *
 * @author G. Alex
 */
@Entity
@Table(name = "dir")
public class SnapDirectory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Path to directory
     */
    private String path;
    /**
     * Date of snapshot
     */
    private Date date;

    /**
     * Number of directories
     */
    private int numDirs;
    /**
     * Number of files
     */
    private int numFiles;
    /**
     * Total size of nested files
     */
    private long totalSize;

    /**
     * Nested files
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "dir_id")
    private Set<SnapFile> files;

    /* Constructors */

    public SnapDirectory() {
    }

    /**
     * Create snapshot of directory, with path to directory and date of snapshot
     *
     * @param path Path to directory
     * @param date Date of snapshot
     */
    public SnapDirectory(String path, Date date) {
        this.path = path;
        this.date = date;
    }

    /* Getters and Setters */

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
