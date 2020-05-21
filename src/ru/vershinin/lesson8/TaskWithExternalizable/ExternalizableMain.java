package ru.vershinin.lesson8.TaskWithExternalizable;

import ru.vershinin.lesson8.TaskWithExternalizable.Child;

import java.io.*;

/**
 * ReflectionClass
 *
 * @author Вершинин Пётр
 */
public class ExternalizableMain {
    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException {
        String filename = "file.dat";
        Child child = new Child("teacher", 8000, "Nike", 42);

        // Сериализация
        System.out.println("Before serializing");
        System.out.println("profession: "+child.getProfession() );
        System.out.println("salary: "+child.getSalary() );
        System.out.println("Name: " + child.getName());
        System.out.println("Age: " +child.getAge() );
        System.out.println("************");

        try {

            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fos);

            out.writeObject(child);
            out.close();
            fos.close();


        } catch (IOException ex) {
            System.out.println("IOException is caught");
        }





        //Deserialize
        child = null;
        try
        {
            FileInputStream fileIn =new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            child = (Child) in.readObject();
            in.close();
            fileIn.close();
        }catch(IOException i)
        {
            i.printStackTrace();
            return;
        }catch(ClassNotFoundException c)
        {
            System.out.println("Employee class not found");
            c.printStackTrace();
            return;
        }
        System.out.println("After serializing");
        System.out.println("profession: "+child.getProfession() );
        System.out.println("salary: "+child.getSalary() );
        System.out.println("Name: " + child.getName());
        System.out.println("Age: " +child.getAge() );
        System.out.println("************");

    }


}
