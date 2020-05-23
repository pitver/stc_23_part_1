package ru.vershinin.lesson12.Task2;

import javassist.ClassPool;


/**
 * генерирация новых классов и загрузка их в Permgen
 *
 * @Exception - java.lang.OutOfMemoryError
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