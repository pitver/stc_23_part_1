package ru.vershinin.lesson8.TaskWithReflection;

import ru.vershinin.lesson8.TaskWithReflection.Parent;
import ru.vershinin.lesson8.TaskWithReflection.SavedObject;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * ReflectionClass
 *
 * @author Вершинин Пётр
 */
public class Main {
    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException {
        String filename = "file.dat";
        Parent parent = new Parent();
        try {
            SavedObject.serialize(parent,filename);
            SavedObject.deSerialize(filename);
        } catch (InstantiationException e) {
            e.printStackTrace();
        }


    }
/*    private static void readFields(Object obj) throws IllegalAccessException {
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field declaredField : fields) {
            System.out.print(
                    Modifier.toString(declaredField.getModifiers()) + " " +
                            declaredField.getType().getSimpleName() + " " +
                            declaredField.getName() + ": ");
            declaredField.setAccessible(true); // доступ к приватному полю
            System.out.println(declaredField.get(obj));
        }
    }*/


}
