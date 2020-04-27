package ru.vershinin.lesson2.task3;

/**
 * SortingComposite
 * bubble+insert
 *
 * @author Вершинин Пётр
 */
public class SortingComposite implements Sort {


    /**
     * bubble sort О(n²)
     * сравнивает соседние элементы и меняет местами если они не отсортированы.
     *
     * @param ps
     */
    @Override
    public void sort(Person[] ps) {
        boolean sorted = false;
        int temp;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < ps.length - 1; i++) {
                if (ps[i].getAge() < ps[i + 1].getAge()) {
                    temp = ps[i].getAge();
                    ps[i].setAge(ps[i + 1].getAge());
                    ps[i + 1].setAge(temp);
                    sorted = false;
                }
            }
        }
        alphabeticalSorting(ps);
        for (Person p:ps) {
            System.out.println(p);
        }
    }


    public void alphabeticalSorting(Person[] ps) {
        insertSorting(ps, true);

    }

    public void sexSorting(Person[] ps) {
        insertSorting(ps, false);
        sort(ps);

    }

    /**
     * sortingInsert О(n²)
     * просматривает данные в виде двух половинок.
     * Левая половина отсортированных элементов, правая которые нужно отсортировать.
     * В каждой итерации, один элемент из правой половины берется и добавляется в левую половину так,
     * что левая половина по-прежнему остается отсортированной
     *
     * @param ps
     * @param typeSort
     */
    protected void insertSorting(Person[] ps, boolean typeSort) {
        for (int i = 0; i < ps.length - 1; i++) {
            Person key = ps[i];
            int j = i - 1;
            while ((j >= 0) && (key.compareTo(ps[j], typeSort) < 0)) {
                ps[j + 1] = ps[j];
                j--;
            }
            ps[j + 1] = key;
        }
    }


}

