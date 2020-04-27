package ru.vershinin.lesson2.task3;

import ru.vershinin.lesson3.RepetException;

import java.util.*;

/**
 * Main
 *
 * @author Вершинин Пётр
 */
public class Main {
    public static void main(String[] args) {

        SortingComposite sb = new SortingComposite();
        SortingComparator sc = new SortingComparator();

        Person[] person = new Person[10000];
       /* person[0]=new Person(12,"Mike",Gender.MALE);
        person[1]=new Person(18,"Mike",Gender.MALE);
        person[2]=new Person(25,"Mike",Gender.MALE);
        person[3]=new Person(12,"Mike",Gender.MALE);
        person[4]=new Person(12,"Mike",Gender.MALE);
        person[5]=new Person(14,"Mike",Gender.MALE);
        person[6]=new Person(12,"Mike",Gender.MALE);*/
        for (int i = 0; i < 10000; i++) {
            int ageRand = Utils.getRandomAge(0,100);
            String nameRand = Utils.getRandomName();
            person[i] = new Person(ageRand, nameRand, Utils.getRandomGender(0, 100));

        }
        searchRepet(person);
        sc.sort(person);
        //sb.sort(person);
        //sb.alphabeticalSorting(person);
        //sb.sexSorting(person);


    }

    /**
     * сравнивает по очередности в массиве Person объекты по имени и возрасту людей и при совпадении
     *  выбрасывает в программе пользовательское исключение RepetException
     * @param data
     */
    private static void searchRepet(Person[] data) {
        List<Person> duplicates = new ArrayList<>();
        for (int i = 0; i <= data.length; i++) {
            try {
                for (int j = i + 1; j < data.length; j++) {

                    if ((data[i].getAge() == data[j].getAge()) &
                            (data[i].getName().equals(data[j].getName()))) {
                        duplicates.add(data[i]);
                        throw new RepetException("совпадение по имени и по возрасту"+data[i]);
                        //break;
                    }
                }
            }catch (RepetException e){
                System.out.println(e.getMessage());
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if(duplicates.isEmpty()){
        System.out.println("Повторов по условию задачи не найлено");
        };
    }

}
