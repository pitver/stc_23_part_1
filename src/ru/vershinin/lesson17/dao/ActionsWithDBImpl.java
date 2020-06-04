package ru.vershinin.lesson17.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.vershinin.lesson17.ConnectionManager.ConnectionDB;
import ru.vershinin.lesson17.ConnectionManager.ConnectionManager;
import ru.vershinin.lesson17.pojo.Client;

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
     * согласно условию используется батчинг и ручное управление транзакциями
     * в случае неудачной записи используется rollback
     *
     * @param client - получает экземпляр Client
     */
    public long addClient(Client client) {

        try (Connection conn = connectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(INSERT_INTO_CLIENT)) {
            st.setString(1, client.getFio());
            st.setInt(2, client.getPhoneNumber());
            st.executeQuery();

        } catch (SQLException e) {
            loggerSystem.error(e.getMessage());
        }
        return 0L;
    }

    /**
     * добавление товара в таблицу product
     * согласно условию использются  2 точки сохранения и ручное управление транзакциями
     * в случае неудачной записи 2 точки сохранения используется rollback на первую точку
     *
     * @param conn            -java.sql.Connection;
     * @param productName     - наименование продукта String
     * @param price           -цена doouble
     * @param present-наличие на складе boolean
     * @throws SQLException if a database access error occurs,
     *                      setAutoCommit(true) is called while participating in a distributed transaction,
     *                      or this method is called on a closed connection
     */
    public void addProduct(Connection conn, String productName, double price, boolean present) {


        try (PreparedStatement st = conn.prepareStatement(INSERT_INTO_PRODUCT)) {

            st.setString(1, productName);
            st.setDouble(2, price);
            st.setBoolean(3, present);
            st.executeQuery();

        } catch (SQLException e) {
            loggerSystem.error(e.getMessage());
        }

    }

    /**
     * добавление заказа в таблицу order
     *
     * @param conn      -java.sql.Connection;
     * @param clientId  - id клиента int
     * @param productId -id товара int
     */
    public void creatingOrder(Connection conn, int clientId, int productId) {
        try (PreparedStatement st = conn.prepareStatement(INSERT_INTO_ORDER)) {
            st.setInt(1, clientId);
            st.setInt(2, productId);
            st.execute();
        } catch (SQLException e) {
            loggerSystem.error(e.getMessage());
        }
    }

    /**
     * добавление заказа в архив магазина, таблица shop
     * номер заказу присваивается рандомно
     *
     * @param conn -java.sql.Connection;
     */
    public void addOrderToShop(Connection conn) {
        try (PreparedStatement st1 = conn.prepareStatement(INSERT_INTO_SHOP)) {
            st1.setInt(1, getMaxId("order"));
            Random rd = new Random();
            st1.setInt(2, rd.nextInt(99999));
            st1.execute();
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

        String sql= "SELECT id From "+ nameTable+"WHERE id=(SELECT max(id) FROM \"order\")";
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
     * @param conn-java.sql.Connection;
     */
    public void showProduct(Connection conn) {
        try (ResultSet rs = conn.prepareStatement(SELECT_PRODUCT).executeQuery()) {
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
     * @param conn-java.sql.Connection;
     */
    public void prepareOrder(Connection conn) {
        try (ResultSet rs = conn.prepareStatement(SELECT_PREPARE_ORDER).executeQuery()) {

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
     * @param conn-java.sql.Connection;
     */
    public void getAllOrder(Connection conn) {

        try (ResultSet rs = conn.prepareStatement(SELECT_GET_ALL_ORDER).executeQuery()) {

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



