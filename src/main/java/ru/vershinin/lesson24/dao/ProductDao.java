package ru.vershinin.lesson24.dao;

import ru.vershinin.lesson24.pojo.Product;

import java.util.List;

/**
 * ProductDao
 *
 * @author Вершинин Пётр
 */
public interface ProductDao {
    void save(Product product);

    List<Product> findAll();

    Product findById(Integer id);

    void editProduct(Product product);

    void delete(Product product);
}
