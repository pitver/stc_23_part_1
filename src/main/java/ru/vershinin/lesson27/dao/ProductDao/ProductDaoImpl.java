package ru.vershinin.lesson27.dao.ProductDao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.vershinin.lesson27.connectionManager.ConnectionManager;
import ru.vershinin.lesson27.connectionManager.Myconnect;
import ru.vershinin.lesson27.pojo.Product;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * ProductDaoImpl
 *
 * @author Вершинин Пётр
 */
public class ProductDaoImpl implements ProductDao {

    private static final Logger loggerSystem = LogManager.getLogger("SystemLog4J2");
    private static final Logger loggerBusiness = LogManager.getLogger(ProductDaoImpl.class);


    public static final String INSERT_INTO_PRODUCT = "INSERT INTO public.product(product_name, price,present) VALUES ( ?, ?, ?)";
    public static final String SELECT_FROM_PRODUCT_ID = "SELECT * FROM public.product WHERE id = ?";
    public static final String EDIT_PRODUCT = "UPDATE public.product SET price=?, present=?, product_name=? WHERE id = ?";
    public static final String DELETE_PRODUCT = "DELETE FROM public.product WHERE id = ?";
    public static final String SELECT_PRODUCT = "SELECT *FROM product";
    private final ConnectionManager connectionManager;

    @Inject
    public ProductDaoImpl(@Myconnect ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;

    }

    /**
     * добавление товара в таблицу product
     *
     * @param product- экземпляр product
     */
    @Override
    public void save(Product product) {
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(INSERT_INTO_PRODUCT)) {
            st.setString(1, product.getProductName());
            st.setDouble(2, product.getPrice());
            st.setBoolean(3, product.isPresent());
            st.executeQuery();
        } catch (SQLException e) {
            loggerSystem.error(e.getMessage());
        }
    }

    /**
     * отображение каталога товаров
     *
     * @return -List Product
     */
    @Override
    public List<Product> findAll() {
        List<Product> listProductName = new ArrayList<>();
        try (Connection conn = connectionManager.getConnection();
             ResultSet rs = conn.prepareStatement(SELECT_PRODUCT).executeQuery()) {
            loggerBusiness.info("     ************каталог товаров************");
            loggerBusiness.info(String.format("%-11s%-20s%-11s%-11s%n", "№", "Наименование", "Цена", "Наличие"));
            while (rs.next()) {
                int id = rs.getInt("id");
                double price = rs.getDouble("price");
                String productName = rs.getString("product_name");
                boolean present = rs.getBoolean("present");
                listProductName.add(new Product(id, productName, price, present));
                loggerBusiness.info(String.format("%-11d%-20s%-11.2f%-13s%n", id, productName, price, present ? "да" : "нет"));
            }
            return listProductName;
        } catch (SQLException e) {
            loggerSystem.error(e.getMessage());
        }
        return new ArrayList<>();
    }

    /**
     * поиск продукта по id(для редактирования и удаления)
     *
     * @param id - id товара
     * @return - new product
     */
    @Override
    public Product findById(Integer id) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_PRODUCT_ID)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Product(
                            resultSet.getInt("id"),
                            resultSet.getString("product_name"),
                            resultSet.getDouble("price"),
                            resultSet.getBoolean("present"));
                }
            }
        } catch (SQLException e) {
            loggerSystem.error(e.getMessage());
        }
        return null;
    }

    /**
     * редактирование продукта
     *
     * @param product - экземпляр product
     */
    @Override
    public void editProduct(Product product) {
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(EDIT_PRODUCT)) {
            st.setDouble(1, product.getPrice());
            st.setBoolean(2, product.isPresent());
            st.setString(3, product.getProductName());
            st.setInt(4, product.getId());
            st.executeQuery();

        } catch (SQLException e) {
            loggerSystem.error(e.getMessage());
        }
    }

    /**
     * удаление продукта
     *
     * @param product - экземпляр product
     */
    @Override
    public void delete(Product product) {
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(DELETE_PRODUCT)) {
            st.setInt(1, product.getId());
            st.executeQuery();

        } catch (SQLException e) {
            loggerSystem.error(e.getMessage());
        }
    }


}
