package ru.vershinin.lesson8;

import java.io.*;

/**
 * SavedObject
 *
 * @author Вершинин Пётр
 */
public class SavedObject {

    protected static void serialize(Object object, String file) {

        // Сериализация

        try {

            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fos);

            out.writeObject(object);
            out.close();
            fos.close();
            System.out.println("Object has been serialized");

        } catch (IOException ex) {
            System.out.println("IOException is caught");
        }
    }

    protected static Object deSerialize(String file) {
        Object newPerson = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream oin = new ObjectInputStream(fis);

            newPerson = oin.readObject();
            oin.close();
            fis.close();
            System.out.println("Object has been deserialized ");
            System.out.println("a = " + newPerson);

        } catch (IOException ex) {
            System.out.println("IOException is caught");

        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }
        return newPerson;
    }

}
