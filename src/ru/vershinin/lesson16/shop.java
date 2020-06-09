package ru.vershinin.lesson16;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

class Shop {
    private static final Logger loggerBusiness = LogManager.getLogger(Shop.class);
    private static final Logger loggerSystem = LogManager.getLogger("SystemLog4J2");
    private static final Logger loggerSecurity = LogManager.getLogger("SecurityLog4J2");

    public static void main(String[] args) {





        loggerSecurity.info("start");
        Connection conn= ConnectionDB.connect();
        loggerBusiness.info("connect");
        DBInit.Init(conn);

        ///добавляем клиента
          ActionsWithDB.addClient(conn,"luke",121212);
          loggerBusiness.info("addClient");


        //добавляем товары в каталог
        try {
            ActionsWithDB.addProduct(conn,"book",15,true);
        } catch (SQLException e) {
            loggerSystem.info(e.getMessage());
        }
        loggerBusiness.info("addProduct");

        //получаем каталог
        ActionsWithDB.showProduct(conn);
        loggerBusiness.info("showProduct");

        //формируем заказ
       ActionsWithDB.creatingOrder(conn,1,1);
        loggerBusiness.info("creatingOrder");

        //подготовка заказа в магазине
        ActionsWithDB.prepareOrder(conn);
        loggerBusiness.info("prepareOrder");

        //получаем список всех заказов
        ActionsWithDB.getAllOrder(conn);
        loggerBusiness.info("getAllOrder");

        ConnectionDB.connectClose();
        loggerBusiness.info("connectClose");
        loggerSecurity.info("stop");
    }

}
