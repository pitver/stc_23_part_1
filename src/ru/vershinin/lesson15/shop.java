package ru.vershinin.lesson15;

import java.sql.Connection;
import java.sql.SQLException;

class Shop {
    public static void main(String[] args) {

        Connection conn= ConnectionDB.connect();
        DBInit.Init(conn);

        ///добавляем клиента
          ActionsWithDB.addClient(conn,"luke",121212);

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
