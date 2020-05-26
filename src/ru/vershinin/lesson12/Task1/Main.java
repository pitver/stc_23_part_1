package ru.vershinin.lesson12.Task1;

import java.util.HashMap;
import java.util.Map;

/**
 * из-за отсутсвия метода equals ()
 * элементы будут добавлятся в коллекцию
 * демонстрирует утечку памяти в Java
 * использовать -XX:+UseParallelGC -Xmx256m
 * Main
 *
 * @author Вершинин Пётр
 */
public class Main {
    static class Key {
        Integer id;

        Key(Integer id) {
            this.id = id;
        }

        @Override
        public int hashCode() {
            return id.hashCode();
        }
    }

    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();

        for (int i = 0; i < 100_000_000; i++) {
            if (!map.containsKey(i)) {
                map.put(i, "Number:" + i);
                System.out.println("Number:" + i);
            }
            if (i % 7 == 0) {
                map.remove(i);
                System.out.println("delete Number:" + i);
            }
        }

    }

}

