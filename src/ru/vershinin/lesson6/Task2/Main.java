package ru.vershinin.lesson6.Task2;

import java.io.File;
import java.util.*;

/**
 * Main
 *
 * @author Вершинин Пётр
 */
public class Main {
    public static void main(String[] args) {
        String path = "text";
        File file = new File("wordlist.txt");
        //получаем список слов из словаря "wordlist.txt"
        List<String> words = GetDictionary.readFile(file);
        getFiles(path, 3,14000,words);

    }
    public static void getFiles(String path, int n,int size,List<String> words) {
        for (int i = 0; i < n; i++) {
            String text = GetReadyText.getText(words);//получаем готовый текст
            WriteFile.writeText(text, path + i + ".txt",size);

        }

    }

}
