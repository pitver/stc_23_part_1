package ru.vershinin.lesson17;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.vershinin.lesson17.ConnectionManager.ConnectionDB;
import ru.vershinin.lesson17.ConnectionManager.ConnectionManager;
import ru.vershinin.lesson17.dao.ActionsWithDBImpl;
import ru.vershinin.lesson17.pojo.Client;
import ru.vershinin.lesson17.pojo.Order;
import ru.vershinin.lesson17.pojo.Product;
import ru.vershinin.lesson17.pojo.Shop;

import java.sql.Connection;

class Main {
    private static final Logger loggerBusiness = LogManager.getLogger(Main.class);
    private static final Logger loggerSystem = LogManager.getLogger("SystemLog4J2");
    private static final Logger loggerSecurity = LogManager.getLogger("SecurityLog4J2");

    public static void main(String[] args) {
        ConnectionManager connectionManager=ConnectionDB.getInstance();
        ActionsWithDBImpl actionsWithDB= new ActionsWithDBImpl(connectionManager);

        Client client= new Client("luke",123456789);
        Product product = new Product(12.8,true,"pen");
        Order order = new Order();
        Shop shop=new Shop();


        Connection conn= connectionManager.getConnection();
        loggerBusiness.info("connect");
        DBInit.Init(conn);
        loggerSecurity.info("start");

        ///добавляем клиента
          actionsWithDB.addClient(client);
          loggerBusiness.info("addClient");


        //добавляем товары в каталог
            actionsWithDB.addProduct(product);
        loggerBusiness.info("addProduct");

        //получаем каталог
        actionsWithDB.showProduct();
        loggerBusiness.info("showProduct");

        //формируем заказ
        actionsWithDB.creatingOrder(client,product);
        loggerBusiness.info("creatingOrder");

        //запись в архив магазина(таблица shop)
        actionsWithDB.addOrderToShop(order,shop);
        loggerBusiness.info("creatingShop");

        //подготовка заказа в магазине
        actionsWithDB.prepareOrder();
        loggerBusiness.info("prepareOrder");

        //получаем список всех заказов
        actionsWithDB.getAllOrder();
        loggerBusiness.info("getAllOrder");

        loggerSecurity.info("stop");
    }

}
