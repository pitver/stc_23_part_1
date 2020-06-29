package ru.vershinin.lesson27.dao.ProductDao;

import ru.vershinin.lesson27.pojo.Product;

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
