package ru.vershinin.lesson21.dao;

import ru.vershinin.lesson21.pojo.Client;
import ru.vershinin.lesson21.pojo.Order;
import ru.vershinin.lesson21.pojo.Product;
import ru.vershinin.lesson21.pojo.Shop;
import ru.vershinin.lesson21.servlet.EditProduct;

import java.util.List;

/**
 * ActionsWithDB
 *
 * @author Вершинин Пётр
 */
public interface ShopDao {



    void addProduct(Product product);

    void creatingOrder(Client client, Product product);

    void addOrderToShop(Order order, Shop shop);

    int getMaxId( String nameTable);

    List<Product> showProduct();

    List<?> prepareOrder();

    List<?> getAllOrder();



    Product getProductById(Integer id);

    void editProduct(Product product);
    void deleteProduct(Product product);

}
