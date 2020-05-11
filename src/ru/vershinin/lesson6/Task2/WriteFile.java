package ru.vershinin.lesson6.Task2;

import java.io.*;

/**
 * WriteFile
 *
 * @author Вершинин Пётр
 */
public class WriteFile {
    protected static void writeText(String text,String path,int lengthFile){

        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(path, "rw");
            raf.writeBytes(text);
            raf.setLength(lengthFile);
            System.out.println("" + raf.length());


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {


            bw.write(text);

            } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }*/


    }
}
