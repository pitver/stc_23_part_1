package ru.vershinin.lesson8;

import ru.vershinin.lesson8.Parent;
import ru.vershinin.lesson8.SavedObject;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * ReflectionClass
 *
 * @author Вершинин Пётр
 */
public class ReflectionClass {
    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException {
        String filename = "file.dat";
        System.out.println();
        Parent parent = new Parent();
        String name = null;
        //получаем список всех полей
        readFields(parent);
        try {
            Field field = parent.getClass().getDeclaredField("name");
            field.setAccessible(true);
            System.out.println(name = (String) field.get(parent));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        SavedObject.serialize(parent, filename);
        Object o = SavedObject.deSerialize(filename);
        System.out.println(o);

    }
    private static void readFields(Object obj) throws IllegalAccessException {
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field declaredField : fields) {
            System.out.print(
                    Modifier.toString(declaredField.getModifiers()) + " " +
                            declaredField.getType().getSimpleName() + " " +
                            declaredField.getName() + ": ");
            declaredField.setAccessible(true); // доступ к приватному полю
            System.out.println(declaredField.get(obj));
        }
    }


}
