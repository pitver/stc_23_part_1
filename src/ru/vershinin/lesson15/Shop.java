package ru.vershinin.lesson15;

public class Shop {
    public static void main(String[] args) {
       new Order(new Client("Mike",123456),
                new Product("book",15,true));
        
    }

}
