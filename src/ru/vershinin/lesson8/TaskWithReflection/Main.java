package ru.vershinin.lesson8.TaskWithReflection;

import org.reflections.Reflections;

import java.util.Set;

/**
 * ReflectionClass
 *
 * @author Вершинин Пётр
 */
public class Main {
    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        String filename = "file.dat";
        Parent parent = new Parent();
        try {
            SavedObject.serialize(parent, filename);
            SavedObject.deSerialize(filename);
            System.out.println("************************************");
        } catch (InstantiationException e) {
            e.printStackTrace();
        }


        Reflections reflections = new Reflections("ru.vershinin.lesson8.TaskWithReflection");
        Set<Class<? extends Parent>> subTypes = reflections.getSubTypesOf(Parent.class);
        for (Class<? extends Parent> s : subTypes) {
            System.out.println(s.getSimpleName());

        }


    }
}
