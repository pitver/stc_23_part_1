package ru.vershinin.lesson2.task1;


import java.util.Arrays;
import java.util.List;

/**
 *
 * Writer class. During execution,
 * the class throws an exception and fails,
 * according to the task
 */
public class Writer {

    public static void main(String[] args) {
        WriteData wd = new WriteData();
        String[] data = new String[]{"Hello world", null, "World Hello"};
        List<String> list = Arrays.asList(data);
       // wd.subTask_1();
        //wd.subTask_2(data);
         wd.subTask_3(list);

    }
}
