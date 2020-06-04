package ru.vershinin.lesson17.dao;

import ru.vershinin.lesson17.pojo.Client;

import java.sql.Connection;

/**
 * ActionsWithDB
 *
 * @author Вершинин Пётр
 */
public interface ActionsWithDB {

    long addClient(Client client);

    void addProduct(Connection conn, String productName, double price, boolean present);

    void creatingOrder(Connection conn, int clientId, int productId);

    void addOrderToShop(Connection conn);

    int getMaxId( String nameTable);

    void showProduct(Connection conn);

    void prepareOrder(Connection conn);

    void getAllOrder(Connection conn);

}
