package ru.vershinin.lesson5;

import java.util.Comparator;
import java.util.Objects;

/**
 * Person
 *
 * @author Вершинин Пётр
 */
public class Person  implements Comparable<Person> {
    /**
     * имя владельца животного,String
     */
    private String name;
    /**
     * возраст владельца животного,int
     */
    private int age;
    /**
     * пол владельца животного
     * @see Gender
     */
    Gender gender;

    public Person() {
    }

    /**
     * Конструктор объекта Person
     *
     * @param name
     * @param age
     * @param gender
     */
    public Person(String name, int age, Gender gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
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
        return Objects.hash(name, age, gender);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }
    @Override
    public int compareTo(Person o) {
        return this.getName().compareTo(o.getName());
    }




}
