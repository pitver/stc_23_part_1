package ru.vershinin.lesson12.Task2;

import javassist.ClassPool;


/**
 * генерирация новых классов и загрузка их в Permgen
 * использовать -XX:MaxPermSize=128m
 *
 * @Exception - java.lang.OutOfMemoryError PermGen указывает на то,
 * \что область постоянного поколения в памяти исчерпана
 */
public class Permgen {
    static ClassPool classPool = ClassPool.getDefault();
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 1000000000; i++) {
            Class c = classPool.makeClass("ru.vershinin.lesson12.Task2.Permgen" + i).toClass();
            System.out.println(c.getName());
        }
    }
}