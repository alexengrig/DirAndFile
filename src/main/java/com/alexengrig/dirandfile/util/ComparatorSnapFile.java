package com.alexengrig.dirandfile.util;

import com.alexengrig.dirandfile.domain.SnapFile;
import org.hibernate.validator.constraints.Range;

import java.util.Comparator;

public class ComparatorSnapFile implements Comparator<SnapFile> {

    public static ComparatorSnapFile defaultComparator = new ComparatorSnapFile();

    @Override
    public int compare(SnapFile o1, SnapFile o2) {
        // В начале списка выводятся директории, потом выводятся файлы
        if (o1.isDirectory() ^ o2.isDirectory()) { // Если директория и файл
            return Boolean.compare(!o1.isDirectory(), !o2.isDirectory());
        }

        // Директории и файлы сортируются по алфавиту без учета регистра
        String name1 = o1.getName().toLowerCase(); // Получили имя первого в ниж. рег.
        String name2 = o2.getName().toLowerCase(); // Получили имя второго в ниж. рег.
        Character dot = '.'; // Точка - начало расширения файла

        for (int i = 0, j = 0; i < name1.length() && j < name2.length(); ) {
            char char1 = name1.charAt(i);
            char char2 = name2.charAt(j);
            if (dot.equals(char1) ^ dot.equals(char2)) { // Если есть начало расшрения
                return Boolean.compare(!dot.equals(char1), !dot.equals(char2));
            } else if (Character.isDigit(char1) ^ Character.isDigit(char2)) { // Если символ и цифра
                return Boolean.compare(!Character.isDigit(char1), !Character.isDigit(char2)); // Сравнение признака директории
            } else if (!Character.isDigit(char1) && !Character.isDigit(char2)) { // Если два символа
                if (char1 != char2) { // Если символы не равны
                    return Character.compare(char1, char2); // Сравнение символов
                }
                // След. символы
                i++;
                j++;
            } else { // Если две цифры
                int number1 = getDigit(name1, i);
                int number2 = getDigit(name2, j);
                if (number1 != number2) { // Если не равны
                    return Integer.compare(number1, number2); // Сравнение чисел
                }
                // Пропускаем цифры
                i = skipDigits(name1, i);
                j = skipDigits(name2, j);
            }
        }
        return 0;
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

    private int getDigit(String value, @Range(min = 0) int start) {
        StringBuilder builder = new StringBuilder();
        int i = start;
        while (i < value.length() && Character.isDigit(value.charAt(i))) {
            builder.append(value.charAt(i++));
        }
        return Integer.valueOf(builder.toString());
    }
}
