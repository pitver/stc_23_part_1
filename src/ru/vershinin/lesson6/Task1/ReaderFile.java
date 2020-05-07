package ru.vershinin.lesson6.Task1;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * ReaderFile
 *
 * @author Вершинин Пётр
 */
public class ReaderFile  {
    List<String> tempReadText = new ArrayList<>();

    /**
     * чтение текстового файла+сортировка по алфавиту+исключение повторов(регистр не учитывается)
     *
     * @param path -путь до файла
     * @return отсортированный список
     */
    protected List<String> readFile(String path) {
        try (Scanner scanner = new Scanner(new File(path))) {
            while (scanner.hasNextLine()) {
                String line = scanner.next();
                if (!tempReadText.contains(line.toLowerCase())) {
                    tempReadText.add(line);
                }

            }
        } catch ( FileNotFoundException e) {
            e.printStackTrace();
        }
        Collections.sort(tempReadText);
        return tempReadText;
    }

}