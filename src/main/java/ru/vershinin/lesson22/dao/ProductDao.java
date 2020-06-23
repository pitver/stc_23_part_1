package ru.vershinin.lesson22.dao;

import ru.vershinin.lesson22.pojo.Product;

import java.util.List;

/**
 * ProductDao
 *
 * @author Вершинин Пётр
 */
public interface ProductDao {
    void addProduct(Product product);

    List<Product> showProduct();

    Product getProductById(Integer id);

    void editProduct(Product product);

    void deleteProduct(Product product);
}
