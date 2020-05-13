package ru.vershinin.lesson6.Task2;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * GetDictionary
 *
 * @author Вершинин Пётр
 */
public class DictionaryRandomWords {

    /**
     * чтение текстового файла
     *
     * @param path -путь до файла
     * @return список слов
     */

    protected static List<String> readFile(File path) {
        List<String> tempReadText = new ArrayList<>();
        try {
            FileReader fr = new FileReader(path);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                tempReadText.add(line);
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempReadText;
    }
}

