package ru.vershinin.lesson9;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

class Main {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException,
            NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        DoWorker doWorker = new DoWorker();
        doWorker.doWork();
       MyClassLoader loader = new MyClassLoader();
        Class<?> c = loader.findClass("ru.vershinin.lesson9.SomeClass");
        Object ob = c.newInstance();
        Method md = c.getMethod("doWork");
        md.invoke(ob);
    }
}

