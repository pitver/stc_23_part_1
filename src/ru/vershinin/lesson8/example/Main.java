package ru.vershinin.lesson8.example;

/**
 * Main
 *
 * @author Вершинин Пётр
 */
public class Main {
    public static void main(String[] args) {
        Message msg = new Message(1,2.0,3.56f,"hello world");
        try {
           byte[] a= msg.Serialize();
           msg.Message(a);

            msg.Display();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
