package ru.vershinin.lesson24.dao.ShopDao;

import ru.vershinin.lesson24.pojo.Client;
import ru.vershinin.lesson24.pojo.Order;
import ru.vershinin.lesson24.pojo.Product;
import ru.vershinin.lesson24.pojo.Shop;

import java.util.List;

/**
 * ShopDao
 *
 * @author Вершинин Пётр
 */
public interface ShopDao {

    void save(Client client, Product product);

    List<Order> findOrderByClientId(Integer id);

    void OrderFulfillmentController();

    void addOrderToShop(Order order, Shop shop);

    int getMaxId(String nameTable);

    List<?> getAllOrder();


}
