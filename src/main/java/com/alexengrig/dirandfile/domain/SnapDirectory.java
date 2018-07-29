package com.alexengrig.dirandfile.domain;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

/**
 * Snapshot of directory
 *
 * @author G. Alex
 */
public class SnapDirectory {

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

    private Set<SnapFile> files;

    /* Constructors */

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

}
