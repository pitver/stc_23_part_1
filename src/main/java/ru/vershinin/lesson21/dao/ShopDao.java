package ru.vershinin.lesson21.dao;

import ru.vershinin.lesson21.pojo.Client;
import ru.vershinin.lesson21.pojo.Order;
import ru.vershinin.lesson21.pojo.Product;
import ru.vershinin.lesson21.pojo.Shop;

import java.util.List;

/**
 * ActionsWithDB
 *
 * @author Вершинин Пётр
 */
public interface ShopDao {

    void addClient(Client client);

    void addProduct(Product product);

    void creatingOrder(Client client, Product product);

    void addOrderToShop(Order order, Shop shop);

    int getMaxId( String nameTable);

    List<Product> showProduct();

    List<?> prepareOrder();

    List<?> getAllOrder();

    boolean findClient(String username,String password);

}
