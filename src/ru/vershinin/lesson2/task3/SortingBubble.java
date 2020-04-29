package ru.vershinin.lesson2.task3;

/**
 * SortingBubble
 *
 * @author Вершинин Пётр
 */
public class SortingBubble implements Sort {


    /**
     * bubble sort О(n²)
     * сравнивает соседние c помощью метода compareTo элементы и меняет местами если они не отсортированы.
     * так же вычисляет время сортировки
     *
     * @param ps обект Person
     */
    @Override
    public void sort(Person[] ps) {
        long time = System.nanoTime();
        boolean sorted = false;
        Person temp;
        Person key;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < ps.length - 1; i++) {
                if (ps[i].compareTo(ps[i + 1]) > 0) {
                    temp = ps[i];
                    key = ps[i + 1];
                    ps[i] = key;
                    ps[i + 1] = temp;
                    sorted = false;
                }
            }
        }

       /* for (Person p : ps) {
            System.out.println(p);
        }*/
        time = System.nanoTime() - time;
        System.out.printf("Время сортировки SortingBubble - %,9.3f ms\n", time/1_000_000.0);
    }

}

