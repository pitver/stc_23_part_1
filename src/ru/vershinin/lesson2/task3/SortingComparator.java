package ru.vershinin.lesson2.task3;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * SortingComparator
 *
 * @author Вершинин Пётр
 */
public class SortingComparator implements Sort {

    /**
     * сравнение объектов Person с применением интерфейса Comparator<E>
     * с реализаций метода compare(), для разных условий+подсчет времени выполнения сортировки
     * @param ps
     */
    @Override
    public  void sort(Person[] ps) {
        long time = System.nanoTime();
        Comparator<Person> pc = new GenderComporator().thenComparing(new AgeComparator()).thenComparing(new NameComparator());
        TreeSet<Person> people=new TreeSet<>(pc);

        for (Person p : ps) {
            people.add(p);
        }
        /*for (Person p1:people) {
            System.out.println(p1);
        }*/
        time = System.nanoTime() - time;
        System.out.printf("Время сортировки Comporator- %,9.3f ms\n", time/1_000_000.0);
    }
}

/**
 * сравнение по имени
 */
class NameComparator implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {
        return o1.getName().compareTo(o2.getName());
    }
}

/**
 * сравнение по возрасту
 */
class AgeComparator implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {
        if (o1.getAge() > o2.getAge()) {
            return -1;
        } else if (o1.getAge() < o2.getAge()) {
            return 1;
        } else {
            return 0;
        }
    }
}

/**
 * сравнение по гендерному признаку
 */
class GenderComporator implements Comparator<Person>{
    @Override
    public int compare(Person o1, Person o2) {
        return o1.getGender().compareTo(o2.getGender());
    }
}

