package ru.vershinin.lesson17.dao;

import ru.vershinin.lesson17.pojo.Client;
import ru.vershinin.lesson17.pojo.Order;
import ru.vershinin.lesson17.pojo.Product;
import ru.vershinin.lesson17.pojo.Shop;

import java.util.List;

/**
 * ActionsWithDB
 *
 * @author Вершинин Пётр
 */
public interface ActionsWithDB {

    void addClient(Client client);

    void addProduct(Product product);

    void creatingOrder(Client client, Product product);

    void addOrderToShop(Order order, Shop shop);

    int getMaxId( String nameTable);

    List<?> showProduct();

    List<?> prepareOrder();

    List<?> getAllOrder();

}
