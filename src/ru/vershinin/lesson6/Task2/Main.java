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
        List<String> words = DictionaryRandomWords.readFile(file);
        getFiles(path, 3,2000,words,3);
    }

    /**
     *создание n файлов размером size в каталоге path
     *
     * @param path - путь до файла, без учета разширения файла
     * @param n-количество файлов
     * @param size-размер файла
     * @param words- список слов
     * @param probability- вероятность включения слова из отдельного массива в общий файл
     */
    public static void getFiles(String path, int n,int size,List<String> words,int probability) {
        double chance=(double)1/probability;
        for (int i = 0; i < n; i++) {
            String text = ReadyText.getText(words,chance);//получаем готовый текст
            WriteFile.writeText(text, path + i + ".txt",size);
        }
    }

}
