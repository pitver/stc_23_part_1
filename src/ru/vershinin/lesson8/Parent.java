package ru.vershinin.lesson8;

import java.io.Serializable;

/**
 * Parent
 *
 * @author Вершинин Пётр
 */
public class Parent implements Serializable {
    private final String name="Test";
    private final int age=12;

    @Override
    public String toString() {
        return "Parent{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
