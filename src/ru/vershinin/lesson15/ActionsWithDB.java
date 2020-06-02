package ru.vershinin.lesson15;

import java.sql.*;
import java.util.Random;

/**
 * ActionsWithDB
 *
 * @author Вершинин Пётр
 */
public class ActionsWithDB {

    /**
     * добавление пользователя в таблицу Client
     * согласно условию используется батчинг и ручное управление транзакциями
     * в случае неудачной записи используется rollback
     *
     * @param conn -java.sql.Connection;
     * @param name-имя клиента String
     * @param phoneNumber-телефонный номер int
     */
    protected static void addClient(Connection conn, String name, int phoneNumber) {
        String sql = "INSERT INTO public.client(fio, phonenumber)"
                + "VALUES ( ?, ?)";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false);
            st.setString(1, name);
            st.setInt(2, phoneNumber);
            st.addBatch();

            st.setString(1, "Nike");
            st.setInt(2, 654321);
            st.addBatch();

            st.setString(1, "Tim");
            st.setInt(2, 987456);
            st.addBatch();

            if(st.executeBatch().length==3){
                conn.commit();
            }else {
             conn.rollback();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    /**
     *
     *  добавление товара в таблицу product
     *   согласно условию использются  2 точки сохранения и ручное управление транзакциями
     *   в случае неудачной записи 2 точки сохранения используется rollback на первую точку
     *
     * @param conn -java.sql.Connection;
     * @param productName - наименование продукта String
     * @param price -цена doouble
     * @param present-наличие на складе boolean
     * @exception SQLException if a database access error occurs,
     *  setAutoCommit(true) is called while participating in a distributed transaction,
     *  or this method is called on a closed connection
     */
    protected static void addProduct(Connection conn, String productName,
                                     double price, boolean present) throws SQLException {
        String sql = "INSERT INTO public.product(product_name, price,present)"
                + "VALUES ( ?, ?, ?)";
        conn.setAutoCommit(false);
        try (PreparedStatement st = conn.prepareStatement(sql)) {

            st.setString(1, productName);
            st.setDouble(2, price);
            st.setBoolean(3, present);
            st.execute();

            st.setString(1, "notebook");
            st.setDouble(2, 12.7);
            st.setBoolean(3, true);
            st.execute();

            // Создание Savepoint
            Savepoint first_savepoint = conn.setSavepoint("first_savepoint");

            st.setString(1, "ruler");
            st.setDouble(2, 4.7);
            st.setBoolean(3, true);

            // Создание Savepoint
            Savepoint second_savepoint = conn.setSavepoint("second_savepoint");
            st.execute();

            st.setString(1, "pen");
            st.setDouble(2, 1.9);
            st.setBoolean(3, true);
            st.execute();

            conn.releaseSavepoint(second_savepoint);
            // Rollback к savepoint
            conn.rollback(first_savepoint);

            // Commit транзакции
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     *  добавление заказа в таблицу order
     *
     * @param conn -java.sql.Connection;
     * @param clientId - id клиента int
     * @param productId -id товара int
     */
    protected static void creatingOrder(Connection conn, int clientId, int productId) {
        String sql = "INSERT INTO public.order(client_id, product_id)"
                + "VALUES ( ?, ?)";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, clientId);
            st.setInt(2, productId);
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //запись в архив магазина(таблица shop)
        addOrderToShop(conn);

    }

    /**
     *  добавление заказа в архив магазина, таблица shop
     *  номер заказу присваивается рандомно
     *
     * @param conn -java.sql.Connection;
     */
    private static void addOrderToShop(Connection conn) {
        String genInfo = "INSERT INTO public.shop(order_id, number_order)"
                + "VALUES ( ?, ?)";
        try (PreparedStatement st1 = conn.prepareStatement(genInfo)) {
            st1.setInt(1, getMaxId(conn));
            Random rd = new Random();
            st1.setInt(2, rd.nextInt(99999));
            st1.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * поиск id последней записи в таблице order
     *
     * @param conn -java.sql.Connection;
     */
    private static int getMaxId(Connection conn) {
        int count = 0;
        try (ResultSet rs = conn.prepareStatement("SELECT id From \"order\"" +
                " WHERE id=(SELECT max(id) FROM \"order\")").executeQuery()) {

            while (rs.next()) {
                count = rs.getInt("id");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }


    /**
     * отображение каталога товаров
     *
     * @param conn-java.sql.Connection;
     */
    protected static void showProduct(Connection conn) {
        try (ResultSet rs = conn.prepareStatement("SELECT *FROM product").executeQuery()) {
            System.out.println("     ************каталог товаров************");
            System.out.println(String.format("%-11s%-20s%-11s%-11s%n", "№", "Наименование", "Цена", "Наличие"));
            while (rs.next()) {
                int id = rs.getInt("id");
                double price = rs.getDouble("price");
                String productName = rs.getString("product_name");
                boolean present = rs.getBoolean("present");
                System.out.println(String.format("%-11d%-20s%-11.2f%-13s%n", id, productName, price, present ? "да" : "нет"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * отображение сформированного заказа
     *
     * @param conn-java.sql.Connection;
     */
    protected static void prepareOrder(Connection conn) {
        try (ResultSet rs = conn.prepareStatement("select o.id,\n" +
                "       cl.fio,\n" +
                "       cl.phonenumber,\n" +
                "       p.product_name,\n" +
                "       p.price,\n" +
                "       p.present\n" +
                "FROM client cl\n" +
                "         LEFT JOIN \"order\" o ON cl.id = \"o\".client_id\n" +
                "         LEFT JOIN \"product\" p on p.id = \"o\".product_id\n" +
                "WHERE o.id = (SELECT id FROM \"order\" WHERE id = (SELECT max(id) FROM \"order\"))").executeQuery()) {

            System.out.println("     ************Заказ************");
            System.out.println(String.format("%-9s%-10s%-11s%-14s%-11s%-11s%n", "№Заказа", "ФИО", "телефон", "Наименование", "Цена", "Наличие"));
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("fio");
                int phoneNumber = rs.getInt("phonenumber");
                double price = rs.getDouble("price");
                String productName = rs.getString("product_name");
                boolean present = rs.getBoolean("present");

                System.out.println(String.format("%-9d%-10s%-11d%-14s%-11.2f%-13s%n", id, name, phoneNumber, productName, price, present ? "да" : "нет"));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * отображение истории заказов
     *
     * @param conn-java.sql.Connection;
     */
    protected static void getAllOrder(Connection conn) {

        try (ResultSet rs = conn.prepareStatement("select\n" +
                "    sh.number_order,\n" +
                "    c.fio,\n" +
                "    c.phonenumber,\n" +
                "    p.product_name,\n" +
                "    p.price,\n" +
                "    p.present\n" +
                "\n" +
                "from shop sh\n" +
                "LEFT JOIN \"order\" o on sh.order_id = o.id\n" +
                "LEFT JOIN client c on o.client_id = c.id\n" +
                "LEFT JOIN product p on o.product_id = p.id").executeQuery()) {
            System.out.println("     ************История заказов************");
            System.out.println(String.format("%-9s%-10s%-11s%-14s%-11s%-11s%n", "№Заказа", "ФИО", "телефон", "Наименование", "Цена", "Наличие"));
            while (rs.next()) {
                int number_order = rs.getInt("number_order");
                String name = rs.getString("fio");
                int phoneNumber = rs.getInt("phonenumber");
                double price = rs.getDouble("price");
                String productName = rs.getString("product_name");
                boolean present = rs.getBoolean("present");

                System.out.println(String.format("%-9d%-10s%-11d%-14s%-11.2f%-13s%n", number_order, name, phoneNumber, productName, price, present ? "да" : "нет"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}



