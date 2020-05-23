package ru.vershinin.lesson12.Task1;

import java.util.*;

/**
 * демонстрирует утечку памяти в Java
 * использовать -XX:+UseParallelGC -Xmx256m
 */
public class Main {
    public static void main(String[] args) {
        Map map = new HashMap();
        for (int j = 3200; j <= 50000; j++) {
            Integer[] array = new Integer[10000 * j];
            System.out.println(j);
            if (j % 7 == 0) {
                array = null;
            }
        }
    }
}

