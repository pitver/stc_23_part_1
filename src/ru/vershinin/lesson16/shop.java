package ru.vershinin.lesson16;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.vershinin.lesson16.ConnectionManager.ConnectionDB;
import ru.vershinin.lesson16.ConnectionManager.ConnectionManager;
import ru.vershinin.lesson16.dao.ActionsWithDBImpl;

import java.sql.Connection;
import java.sql.SQLException;

class Shop {
    private static final Logger loggerBusiness = LogManager.getLogger(Shop.class);
    private static final Logger loggerSystem = LogManager.getLogger("SystemLog4J2");
    private static final Logger loggerSecurity = LogManager.getLogger("SecurityLog4J2");

    public static void main(String[] args) {
        ConnectionManager connectionManager=ConnectionDB.getInstance();
        ActionsWithDBImpl actionsWithDB= new ActionsWithDBImpl();




        loggerSecurity.info("start");
        Connection conn= connectionManager.getConnection();
        loggerBusiness.info("connect");
        DBInit.Init(conn);

        ///добавляем клиента
          actionsWithDB.addClient(conn,"luke",121212);
          loggerBusiness.info("addClient");


        //добавляем товары в каталог
            actionsWithDB.addProduct(conn,"book",15,true);
        loggerBusiness.info("addProduct");

        //получаем каталог
        actionsWithDB.showProduct(conn);
        loggerBusiness.info("showProduct");

        //формируем заказ
        actionsWithDB.creatingOrder(conn,1,1);
        loggerBusiness.info("creatingOrder");

        //запись в архив магазина(таблица shop)
        actionsWithDB.addOrderToShop(conn);
        loggerBusiness.info("creatingShop");

        //подготовка заказа в магазине
        actionsWithDB.prepareOrder(conn);
        loggerBusiness.info("prepareOrder");

        //получаем список всех заказов
        actionsWithDB.getAllOrder(conn);
        loggerBusiness.info("getAllOrder");

        ConnectionDB.connectClose();
        loggerBusiness.info("connectClose");
        loggerSecurity.info("stop");
    }

}
