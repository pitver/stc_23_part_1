package ru.vershinin.lesson15;

import java.sql.Connection;

public class Shop {
    public static void main(String[] args) {
       new Order(new Client("Mike",123456),
                new Product("book",15,true));
        Connection conn= ConnectionDB.connect();
        ///добавляем клиента
        //  ActionsWithDB.addClient(conn,"Mike",123456);


        //добавляем товары в каталог
        /*ActionsWithDB.addProduct(conn,"book",15,true);
        ActionsWithDB.addProduct(conn,"pen",2.7,true);
        ActionsWithDB.addProduct(conn,"notebook",4.8,true);
        ActionsWithDB.addProduct(conn,"ruler",7.9,true);*/


        //получаем каталог
        ActionsWithDB.showProduct(conn);

        //формируем заказ
        //ActionsWithDB.creatingOrder(conn,1,6);

        //подготовка заказа в магазине
        ActionsWithDB.prepareOrder(conn);

        //получаем список всех заказов

        ActionsWithDB.getAllOrder(conn);




        ConnectionDB.connectClose();
    }

}
