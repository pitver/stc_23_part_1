package ru.vershinin.lesson9;

import java.util.Scanner;

public class DoWorker implements Worker {
    @Override
    public void doWork() {
        String str = "10 20 40 60";
        Scanner scanner = new Scanner(str);
        SomeClass someClass = new SomeClass();
        someClass.doWork(scanner);

    }
}
