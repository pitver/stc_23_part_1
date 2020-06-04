package ru.vershinin.lesson16.dao;

import java.sql.Connection;

/**
 * ActionsWithDB
 *
 * @author Вершинин Пётр
 */
public interface ActionsWithDB {

    void addClient(Connection conn, String name, int phoneNumber);

    void addProduct(Connection conn, String productName, double price, boolean present);

    void creatingOrder(Connection conn, int clientId, int productId);

    void addOrderToShop(Connection conn);

    int getMaxId(Connection conn);

    void showProduct(Connection conn);

    void prepareOrder(Connection conn);

    void getAllOrder(Connection conn);

}
