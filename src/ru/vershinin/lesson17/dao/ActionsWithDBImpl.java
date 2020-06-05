package ru.vershinin.lesson17.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.vershinin.lesson17.ConnectionManager.ConnectionDB;
import ru.vershinin.lesson17.ConnectionManager.ConnectionManager;
import ru.vershinin.lesson17.pojo.Client;
import ru.vershinin.lesson17.pojo.Order;
import ru.vershinin.lesson17.pojo.Product;
import ru.vershinin.lesson17.pojo.Shop;

import java.sql.*;
import java.util.Random;

/**
 * ActionsWithDB
 *
 * @author Вершинин Пётр
 */
public class ActionsWithDBImpl implements ActionsWithDB {

    private static final Logger loggerSystem = LogManager.getLogger("SystemLog4J2");
    private static final Logger loggerBusiness = LogManager.getLogger(ActionsWithDBImpl.class);
    public static final String INSERT_INTO_CLIENT = "INSERT INTO public.client(fio, phonenumber) VALUES ( ?, ?)";
    public static final String INSERT_INTO_PRODUCT = "INSERT INTO public.product(product_name, price,present) VALUES ( ?, ?, ?)";
    public static final String INSERT_INTO_ORDER = "INSERT INTO public.order(client_id, product_id) VALUES ( ?, ?)";
    public static final String INSERT_INTO_SHOP = "INSERT INTO public.shop(order_id, number_order) VALUES ( ?, ?)";
    public static final String SELECT_PRODUCT = "SELECT *FROM product";
    public static final String SELECT_PREPARE_ORDER = "select o.id, cl.fio, cl.phonenumber,p.product_name,p.price,p.present\n" +
            "FROM client cl\n" +
            "         LEFT JOIN \"order\" o ON cl.id = \"o\".client_id\n" +
            "         LEFT JOIN \"product\" p on p.id = \"o\".product_id\n" +
            "WHERE o.id = (SELECT id FROM \"order\" WHERE id = (SELECT max(id) FROM \"order\"))";
    public static final String SELECT_GET_ALL_ORDER = "select sh.number_order,c.fio, c.phonenumber,p.product_name,p.price,p.present\n" +
            "FROM shop sh\n" +
            "LEFT JOIN \"order\" o on sh.order_id = o.id\n" +
            "LEFT JOIN client c on o.client_id = c.id\n" +
            "LEFT JOIN product p on o.product_id = p.id";

    public ActionsWithDBImpl() {
    }
    private ConnectionManager connectionManager;// - ? для чего

    public ActionsWithDBImpl(ConnectionManager connectionManager) {// - ? для чего
        this.connectionManager = connectionManager;

    }


    /**
     * добавление пользователя в таблицу Client
     *
     * @param client -  экземпляр Client
     */
    public void addClient(Client client) {

        try (Connection conn = connectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(INSERT_INTO_CLIENT)) {
            st.setString(1, client.getFio());
            st.setInt(2, client.getPhoneNumber());
            st.executeQuery();

        } catch (SQLException e) {
            loggerSystem.error(e.getMessage());
        }

    }

    /**
     * добавление товара в таблицу product
     *
     * @param product- экземпляр Product
     */
    public void addProduct(Product product) {


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
     * добавление заказа в таблицу order
     *
     * @param client  - экземпляр Client
     * @param product - экземпляр Product
     */
    public void creatingOrder(Client client,Product product) {
        try (Connection conn = connectionManager.getConnection();
                PreparedStatement st = conn.prepareStatement(INSERT_INTO_ORDER)) {
            st.setInt(1, client.getId());
            st.setInt(2, product.getId());
            st.executeQuery();
        } catch (SQLException e) {
            loggerSystem.error(e.getMessage());
        }
    }

    /**
     * добавление заказа в архив магазина, таблица shop
     * номер заказа присваивается рандомно
     *
     * @param order - экземпляр Order
     * @param shop - экземпляр Shop
     *
     */
    public void addOrderToShop(Order order, Shop shop) {
        try (Connection conn = connectionManager.getConnection();
                PreparedStatement st = conn.prepareStatement(INSERT_INTO_SHOP)) {
            st.setInt(1, order.getId());
            st.setInt(2,shop.getNumberOrder() );
            st.executeQuery();
        } catch (SQLException e) {
            loggerSystem.error(e.getMessage());
        }
    }

    /**
     * поиск id последней записи в таблицах
     *
     * @param nameTable -имя таблицы String;
     */
    public int getMaxId(String nameTable) {
        int count = 0;

        String sql= "SELECT id From "+ nameTable+" WHERE id=(SELECT max(id) FROM \"order\")";
        try (Connection conn = connectionManager.getConnection();
                ResultSet rs = conn.prepareStatement(sql).executeQuery()) {

            while (rs.next()) {
                count = rs.getInt("id");
            }
        } catch (SQLException e) {
            loggerSystem.error(e.getMessage());
        }
        return count;
    }

    /**
     * отображение каталога товаров
     *
     */
    public void showProduct() {
        try (Connection conn = connectionManager.getConnection();
                ResultSet rs = conn.prepareStatement(SELECT_PRODUCT).executeQuery()) {
            loggerBusiness.info("     ************каталог товаров************");
            loggerBusiness.info(String.format("%-11s%-20s%-11s%-11s%n", "№", "Наименование", "Цена", "Наличие"));
            while (rs.next()) {
                int id = rs.getInt("id");
                double price = rs.getDouble("price");
                String productName = rs.getString("product_name");
                boolean present = rs.getBoolean("present");
                loggerBusiness.info(String.format("%-11d%-20s%-11.2f%-13s%n", id, productName, price, present ? "да" : "нет"));
            }
        } catch (SQLException e) {
            loggerSystem.error(e.getMessage());
        }
    }

    /**
     * отображение сформированного заказа
     *
     */
    public void prepareOrder() {
        try (Connection conn = connectionManager.getConnection();
                ResultSet rs = conn.prepareStatement(SELECT_PREPARE_ORDER).executeQuery()) {

            loggerBusiness.info("     ************Заказ************");
            loggerBusiness.info(String.format("%-9s%-10s%-11s%-14s%-11s%-11s%n", "№Заказа", "ФИО", "телефон", "Наименование", "Цена", "Наличие"));
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("fio");
                int phoneNumber = rs.getInt("phonenumber");
                double price = rs.getDouble("price");
                String productName = rs.getString("product_name");
                boolean present = rs.getBoolean("present");

                loggerBusiness.info(String.format("%-9d%-10s%-11d%-14s%-11.2f%-13s%n", id, name, phoneNumber, productName, price, present ? "да" : "нет"));

            }
        } catch (SQLException e) {
            loggerSystem.error(e.getMessage());
        }
    }

    /**
     * отображение истории заказов
     *
     */
    public void getAllOrder() {

        try (Connection conn = connectionManager.getConnection();
                ResultSet rs = conn.prepareStatement(SELECT_GET_ALL_ORDER).executeQuery()) {

            loggerBusiness.info("     ************История заказов************");
            loggerBusiness.info(String.format("%-9s%-10s%-11s%-14s%-11s%-11s%n", "№Заказа", "ФИО", "телефон", "Наименование", "Цена", "Наличие"));
            while (rs.next()) {
                int number_order = rs.getInt("number_order");
                String name = rs.getString("fio");
                int phoneNumber = rs.getInt("phonenumber");
                double price = rs.getDouble("price");
                String productName = rs.getString("product_name");
                boolean present = rs.getBoolean("present");

                loggerBusiness.info(String.format("%-9d%-10s%-11d%-14s%-11.2f%-13s%n", number_order, name, phoneNumber, productName, price, present ? "да" : "нет"));
            }
        } catch (SQLException e) {
            loggerSystem.error(e.getMessage());
        }
    }

}



