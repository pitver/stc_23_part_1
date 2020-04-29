package ru.vershinin.lesson2.task3;

import java.util.Objects;

/**
 * Person
 *
 * @author Вершинин Пётр
 */
public class Person implements Comparable<Person> {

    /**
     * поле возраст тип Integer
     */
    private Integer age;
    /**
     * поле имя тип String
     */
    private String name;
    /**
     * @see Gender
     */
    Gender gender;


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age >= 0 && age <= 100) {
            this.age = age;
        } else {
            System.out.println("возраст должен быть в диапазоне от 0 до 100, лет установлен по умолчанию 25");
            this.age = 25;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Person() {
    }

    /**
     * Конструктор принимает объект Person
     * для @param age-установлено ограгичение от 0 до 100,
     * если выход за границу, то возраст устанавливается по умолчанию =25
     * @param age -возраст
     * @param name- имя
     * @param gender-тип Gender
     */
    public Person(int age, String name, Gender gender) {
        if (age >= 0 && age <= 100) {
            this.age = age;
        } else {
            System.out.println("Для " + name + "возраст должен быть в диапазоне от 0 до 100 лет, установлен по умолчанию 25");
            this.age = 25;
        }
        this.name = name;
        this.gender = gender;
    }

    /**
     *
     * сравнивает вызывающий объект с объектом, переданным в качестве параметра по условиям:
     * первые идут мужчины
     * выше в списке тот, кто более старший
     * имена сортируются по алфавиту
     * @param p объект типа Person
     * @return result возвращает в результате выполнения сравнения целое число
     */
    public int compareTo(Person p) {

        int result = this.gender.compareTo(p.gender);
        if (result == 0) {
            result = this.age.compareTo(p.age);
        }
        if (result == 0) {
            result = this.name.compareTo(p.name);
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                Objects.equals(name, person.name) &&
                gender == person.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, name, gender);
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", sex=" + gender +
                '}';
    }


}
