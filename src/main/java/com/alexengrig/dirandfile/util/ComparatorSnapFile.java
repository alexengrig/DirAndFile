package com.alexengrig.dirandfile.util;

import com.alexengrig.dirandfile.domain.SnapFile;
import org.hibernate.validator.constraints.Range;

import java.util.Comparator;

public class ComparatorSnapFile implements Comparator<SnapFile> {
    private static final Character DOT = '.';
    public static ComparatorSnapFile defaultComparator = new ComparatorSnapFile();

    @Override
    public int compare(SnapFile o1, SnapFile o2) {
        return Comparator.comparing(SnapFile::isDirectory, this::compareType)
                .thenComparing(SnapFile::getName, this::compareName)
                .compare(o1, o2);
    }

    private int compareType(boolean l, boolean r) {
        return l ^ r ? Boolean.compare(!l, !r) : 0;
    }

    private int compareName(String l, String r) {
        if (l.equals(r)) return 0;
        for (int i = 0, j = 0; i < l.length() && j < r.length(); ) {
            char char1 = l.charAt(i);
            char char2 = r.charAt(j);
            if (isOneDot(char1, char2)) return compareByDot(char1, char2);
            else if (isOneNumber(char1, char2)) return compareByNumber(char1, char2);
            else if (isTwoSymbols(char1, char2)) {
                if (char1 != char2) return Character.compare(char1, char2);
                i++;
                j++;
            } else {
                int number1 = getDigit(l, i);
                int number2 = getDigit(r, j);
                if (number1 != number2) return Integer.compare(number1, number2);
                i = skipDigits(l, i);
                j = skipDigits(r, j);
            }
        }
        return 0;
    }

    private int compareByDot(char l, char r) {
        return Boolean.compare(!DOT.equals(l), !DOT.equals(r));
    }

    private int compareByNumber(char l, char r) {
        return Boolean.compare(!Character.isDigit(l), !Character.isDigit(r));
    }

    private boolean isOneDot(char l, char r) {
        return DOT.equals(l) ^ DOT.equals(r);
    }

    private boolean isOneNumber(char l, char r) {
        return Character.isDigit(l) ^ Character.isDigit(r);
    }

    private boolean isTwoSymbols(char l, char r) {
        return !Character.isDigit(l) && !Character.isDigit(r);
    }

    private int getDigit(String value, @Range(min = 0) int start) {
        StringBuilder builder = new StringBuilder();
        int i = start;
        while (i < value.length() && Character.isDigit(value.charAt(i))) {
            builder.append(value.charAt(i++));
        }
        return Integer.parseInt(builder.toString());
    }

    private int skipDigits(String value, @Range(min = 0) int start) {
        int i = start;
        for (; i < value.length(); i++) {
            if (!Character.isDigit(value.charAt(i))) {
                break;
            }
        }
        return i;
    }
}
