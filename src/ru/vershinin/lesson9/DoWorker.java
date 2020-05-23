package ru.vershinin.lesson9;

import java.io.BufferedReader;
import java.util.Scanner;

/**
 * DoWorker
 */
public class DoWorker implements Worker {
    /**
     * получает введеный от пользователя текст
     */
    @Override
    public void doWork() {
        System.out.println("введите тело метода, для выхода нажмите space затем Enter");
        boolean check = true;
        StringBuffer strBuffer = new StringBuffer();
        while (check) {
            Scanner scanner = new Scanner(System.in);
            String text = scanner.nextLine();
            strBuffer.append(text);
            System.out.println(strBuffer.toString());
            if (text.equals(" ")) {
                System.out.println("out");
                try {
                    FilePreparer.prepare(strBuffer);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                check = false;
            }
        }

    }
}
