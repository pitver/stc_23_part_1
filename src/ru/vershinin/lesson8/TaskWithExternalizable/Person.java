package ru.vershinin.lesson8.TaskWithExternalizable;

import java.io.*;

/**
 * Parent
 *
 * @author Вершинин Пётр
 */
public class Person {

    protected String name;
    protected int age;


    public Person() {
        System.out.println("Parent:Constructor");
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Parent{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }


}
