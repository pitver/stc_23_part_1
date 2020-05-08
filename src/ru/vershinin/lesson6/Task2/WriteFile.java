package ru.vershinin.lesson6.Task2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * WriteFile
 *
 * @author Вершинин Пётр
 */
public class WriteFile {
    protected static void writeText(String text,String path){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {

                bw.write(text);

            } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }


    }
}
