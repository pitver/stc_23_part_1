package ru.vershinin.lesson6.Task1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Main
 *
 * @author Вершинин Пётр
 */
public class Main {
    public static void main(String[] args) {
        ReaderFile readerFile = new ReaderFile();
        String fileName = "notes.txt";
        List<String> readText = readerFile.readFile(fileName);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("notes4.txt"))) {
            for (String s : readText) {
                bw.write(s);
                bw.write("\n");
            }

        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }


    }


}
