package ru.vershinin.lesson17.dao;

import java.sql.Connection;

/**
 * ActionsWithDB
 *
 * @author Вершинин Пётр
 */
public interface ActionsWithDB {

    long addClient( String name, int phoneNumber);

    void addProduct(Connection conn, String productName, double price, boolean present);

    void creatingOrder(Connection conn, int clientId, int productId);

    void addOrderToShop(Connection conn);

    int getMaxId(Connection conn);

    void showProduct(Connection conn);

    void prepareOrder(Connection conn);

    void getAllOrder(Connection conn);

}
