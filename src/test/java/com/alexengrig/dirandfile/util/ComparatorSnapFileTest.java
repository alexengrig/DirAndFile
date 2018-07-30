package com.alexengrig.dirandfile.util;

import com.alexengrig.dirandfile.domain.SnapFile;
import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;

public class ComparatorSnapFileTest {

    private ComparatorSnapFile comparator = ComparatorSnapFile.defaultComparator;

    @Test
    public void compareEqualFiles() {
        SnapFile file1 = new SnapFile("f1i2l3eA1.java", 0, false);
        SnapFile file2 = new SnapFile("f1i2l3ea00001.java", 0, false);
        int compare = comparator.compare(file1, file2);
        assertEquals(0, compare);
    }

    @Test
    public void compareEqualDirectories() {
        SnapFile directory1 = new SnapFile("folder1", 0, true);
        SnapFile directory2 = new SnapFile("folder001", 0, true);
        int compare = comparator.compare(directory1, directory2);
        assertEquals(0, compare);
    }

    @Test
    public void compareDirectoryAndFile() {
        SnapFile file = new SnapFile("simple.txt", 100, false);
        SnapFile directory = new SnapFile("simple", 100, true);
        int compare = comparator.compare(file, directory);
        assertTrue(compare > 0);
    }

    @Test
    public void compare(){
        SnapFile file1 = new SnapFile("f.ttt", 100, false);
        SnapFile file2 = new SnapFile("f.txt", 100, false);
        int compare = comparator.compare(file1, file2);
        assertTrue(compare < 0);
    }

}