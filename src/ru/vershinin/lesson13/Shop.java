package ru.vershinin.lesson13;

public class Shop {
    public static void main(String[] args) {
        Order order=new Order(new Client("Mike",123456),
                new Product("book",15,true));
        System.out.println(order);
    }

}
