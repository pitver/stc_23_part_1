package ru.vershinin.lesson15;

import java.sql.Connection;
import java.sql.SQLException;

class Shop {
    public static void main(String[] args) {
       new Order(new Client("Mike",123456),
                new Product("book",15,true));
        Connection conn= ConnectionDB.connect();
        ///добавляем клиента
          ActionsWithDB.addClient(conn,"Mike",123456);


        //добавляем товары в каталог
        try {
            ActionsWithDB.addProduct(conn,"book",15,true);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        //получаем каталог
        ActionsWithDB.showProduct(conn);

        //формируем заказ
        ActionsWithDB.creatingOrder(conn,1,1);

        //подготовка заказа в магазине
        ActionsWithDB.prepareOrder(conn);

        //получаем список всех заказов

        ActionsWithDB.getAllOrder(conn);




        ConnectionDB.connectClose();
    }

}
