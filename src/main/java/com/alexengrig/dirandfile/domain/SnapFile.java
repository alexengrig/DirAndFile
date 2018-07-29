package com.alexengrig.dirandfile.domain;

/**
 * Snapshot of file
 *
 * @author G. Alex
 */
public class SnapFile {

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

}
