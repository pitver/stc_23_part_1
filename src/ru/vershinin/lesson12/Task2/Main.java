package ru.vershinin.lesson12.Task2;

/**
 * создание и загрузка в jvm новых классов
 * Main
 *
 * @author Вершинин Пётр
 */
public class Main {
    static javassist.ClassPool cp = javassist.ClassPool.getDefault();

    public static void main(String[] args) throws Exception{
        for (int i = 0; ; i++) {
            Class c = cp.makeClass("eu.plumbr.demo.Generated" + i).toClass();
            System.out.println("eu.plumbr.demo.Generated" + i);
        }
    }
}
