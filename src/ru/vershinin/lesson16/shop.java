package ru.vershinin.lesson16;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import java.sql.Connection;
import java.sql.SQLException;

class Shop {
    private static final Logger logger = LogManager.getLogger(Shop.class);

    public static void main(String[] args) {
        Marker markerBusiness = MarkerManager.getMarker("business");
        Marker markerSecurity = MarkerManager.getMarker("security");
        Marker markerSystem = MarkerManager.getMarker("system");




        logger.info(markerSecurity,"start");
        Connection conn= ConnectionDB.connect();
        logger.info(markerSystem,"connect");
        DBInit.Init(conn);

        ///добавляем клиента
          ActionsWithDB.addClient(conn,"luke",121212);
          logger.info(markerBusiness,"addClient");


        //добавляем товары в каталог
        try {
            ActionsWithDB.addProduct(conn,"book",15,true);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        logger.info(markerBusiness,"addProduct");

        //получаем каталог
        ActionsWithDB.showProduct(conn);
        logger.info(markerBusiness,"showProduct");

        //формируем заказ
       ActionsWithDB.creatingOrder(conn,1,1);
        logger.info(markerBusiness,"creatingOrder");

        //подготовка заказа в магазине
        ActionsWithDB.prepareOrder(conn);
        logger.info(markerBusiness,"prepareOrder");

        //получаем список всех заказов
        ActionsWithDB.getAllOrder(conn);
        logger.info(markerBusiness,"getAllOrder");

        ConnectionDB.connectClose();
        logger.info(markerSystem,"connectClose");
        logger.info(markerSecurity,"stop");
    }

}
