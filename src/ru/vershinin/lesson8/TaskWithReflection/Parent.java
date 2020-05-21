package ru.vershinin.lesson8.TaskWithReflection;

import java.io.Serializable;

/**
 * Parent
 *
 * @author Вершинин Пётр
 */
public class Parent implements Serializable {
    private static final long serialVersionUID = 12345L;
    private final String name="Test";
    private final int age=12;
   // Child child= new Child();

    @Override
    public String toString() {
        return "Parent{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
