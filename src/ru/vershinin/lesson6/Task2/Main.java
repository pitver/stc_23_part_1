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
        getFiles(path, 3);
    }
    public static void getFiles(String path, int n) {
        for (int i = 0; i < n; i++) {
            String text = GetReadyText.getText();//получаем готовый текст
            WriteFile.writeText(text, path + i + ".txt");

        }

    }

}
