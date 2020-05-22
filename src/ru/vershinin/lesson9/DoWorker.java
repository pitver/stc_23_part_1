package ru.vershinin.lesson9;

import java.util.Scanner;

public class DoWorker implements Worker {
    @Override
    public void doWork() {

        Scanner scanner = new Scanner(System.in);
        try {
            FilePreparer.prepare(scanner);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
