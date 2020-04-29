package ru.vershinin.lesson2.task3;

/**
 * SortingInsert
 *
 * @author Вершинин Пётр
 */
public class SortingInsert implements Sort {

    /**
     * sortingInsert О(n²)
     * просматривает данные в виде двух половинок.
     * Левая половина отсортированных элементов, правая которые нужно отсортировать.
     * В каждой итерации, один элемент из правой половины берется и добавляется в левую половину так,
     * что левая половина по-прежнему остается отсортированной
     * так же вычисляет время сортировки
     *
     * @param ps
     *
     */
    @Override
    public void sort(Person[] ps) {
        long time = System.nanoTime();
        for (int i = 0; i < ps.length - 1; i++) {
            Person key = ps[i];
            int j = i - 1;
            while ((j >= 0) && (key.compareTo(ps[j]) < 0)) {
                ps[j + 1] = ps[j];
                j--;
            }
            ps[j + 1] = key;
        }
        /*for (Person p:ps) {
            System.out.println(p);
        }*/
        time = System.nanoTime() - time;
        System.out.printf("Время сортировки SortingInsert - %,9.3f ms\n", time/1_000_000.0);
    }



}
