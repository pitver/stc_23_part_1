package ru.vershinin.lesson15;

import java.sql.*;
import java.time.LocalDate;
import java.util.Random;

/**
 * ActionsWithDB
 *
 * @author Вершинин Пётр
 */
public class ActionsWithDB {

    protected static void addClient(Connection conn, String name, int phoneNumber) {
        String sql = "INSERT INTO public.client(fio, phonenumber)"
                + "VALUES ( ?, ?)";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, name);
            st.setInt(2, phoneNumber);
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    protected static void addProduct(Connection conn, String productName, double price, boolean present) {
        String sql = "INSERT INTO public.product(product_name, price,present)"
                + "VALUES ( ?, ?, ?)";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, productName);
            st.setDouble(2, price);
            st.setBoolean(3, present);
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

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

    private static int getMaxId(Connection conn) {
        int count = 0;
        try (ResultSet rs = conn.prepareStatement("SELECT id From \"order\"" +
                " WHERE id=(SELECT max(id) FROM \"order\")").executeQuery()) {
            if (rs == null) {
                return 1;
            }
            while (rs.next()) {
                count = rs.getInt("id");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }

    protected static void GetListOrder(Connection conn, int numberOrder, int orderId) {
        String sql = "INSERT INTO public.shop(number_order,order_id)"
                + "VALUES ( ?, ?)";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, numberOrder);
            st.setInt(2, orderId);
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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

    protected static void getAllOrder(Connection conn) {

        try (ResultSet rs = conn.prepareStatement("select o.id,\n" +
                "       cl.fio,\n" +
                "       cl.phonenumber,\n" +
                "       p.product_name,\n" +
                "       p.price,\n" +
                "       p.present\n" +
                "FROM client cl\n" +
                "         LEFT JOIN \"order\" o ON cl.id = \"o\".client_id\n" +
                "         LEFT JOIN \"product\" p on p.id = \"o\".product_id").executeQuery()) {
            System.out.println("     ************История заказов************");
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

}



