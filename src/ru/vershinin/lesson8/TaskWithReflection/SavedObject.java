package ru.vershinin.lesson8.TaskWithReflection;

import java.io.*;
import java.lang.reflect.Field;

/**
 * SavedObject
 *
 * @author Вершинин Пётр
 */
public class SavedObject {

    protected static void serialize(Object object, String file) throws IllegalAccessException, InstantiationException, NoSuchFieldException {


        // Сериализация
        Class clazz = object.getClass();

        Parent parent = (Parent) clazz.newInstance();

        Field fieldNameParent = parent.getClass().getDeclaredField("name");
        Field fieldAgeParent = parent.getClass().getDeclaredField("age");
        Field fieldUIDParent = parent.getClass().getDeclaredField("serialVersionUID");

        fieldNameParent.setAccessible(true);
        fieldAgeParent.setAccessible(true);
        fieldUIDParent.setAccessible(true);

        String name = (String) fieldNameParent.get(parent);
        int age = fieldAgeParent.getInt(parent);
        Long serialVersionUID = fieldUIDParent.getLong(parent);

        try {

            DataOutputStream out = new DataOutputStream(new FileOutputStream(file));

            out.writeUTF(name);
            out.writeInt(age);
            out.writeLong(serialVersionUID);
            out.close();
            System.out.println("Object has been serialized");

        } catch (IOException ex) {
            System.out.println("IOException is caught");
        }
    }

    protected static Object deSerialize(String file) {
        Object newPerson = null;
        try {

            DataInputStream dis = new DataInputStream(new FileInputStream(file));
                String s = dis.readUTF();
                int age = dis.readInt();
                Long ser = dis.readLong();
                System.out.println(s + " " + age + " "+ser );


            dis.close();

            System.out.println("Object has been deserialized ");

        } catch (IOException ex) {
            System.out.println("IOException is caught" + ex);

        }
        return newPerson;
    }

}
