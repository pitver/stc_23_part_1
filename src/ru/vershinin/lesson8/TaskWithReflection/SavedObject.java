package ru.vershinin.lesson8.TaskWithReflection;

import ru.vershinin.lesson8.TaskWithReflection.Parent;

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

            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fos);


            out.writeUTF(name);
            out.writeInt(age);
            out.writeLong(serialVersionUID);

            System.out.println("Object has been serialized");

        } catch (IOException ex) {
            System.out.println("IOException is caught");
        }
    }

    protected static Object deSerialize(String file) {
        Object newPerson = null;
        try {
           /* FileInputStream fis = new FileInputStream(file);
            ObjectInputStream oin = new ObjectInputStream(fis);*/
            ObjectInputStream dis = new ObjectInputStream(new FileInputStream(file));

            //newPerson=oin.readObject();
            int i = -1;
            while ((i = dis.read()) != -1) {
                String s = dis.readUTF();
                int age = dis.readInt();
                Long ser = dis.readLong();
                System.out.println(s + " " + age + " " + ser);
            }


            dis.close();
            //fis.close();
            System.out.println("Object has been deserialized ");

        } catch (IOException ex) {
            System.out.println("IOException is caught" + ex);

        }
        System.out.println(newPerson);
        return newPerson;
    }

}
