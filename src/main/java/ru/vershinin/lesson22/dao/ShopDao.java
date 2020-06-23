package ru.vershinin.lesson22.dao;

import ru.vershinin.lesson22.pojo.Client;
import ru.vershinin.lesson22.pojo.Order;
import ru.vershinin.lesson22.pojo.Product;
import ru.vershinin.lesson22.pojo.Shop;

import java.util.List;

/**
 * ShopDao
 *
 * @author Вершинин Пётр
 */
public interface ShopDao {

    void creatingOrder(Client client, Product product);

    void addOrderToShop(Order order, Shop shop);

    int getMaxId(String nameTable);

    List<?> prepareOrder();

    List<?> getAllOrder();


}
