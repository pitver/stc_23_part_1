package ru.vershinin.lesson16;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * ChangeDB
 *
 * @author Вершинин Пётр
 */
public class ConnectionDB {

    public static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    public static final String DB_Driver = "org.postgresql.Driver";
    public static void connectClose() {

        try {
            Connection conn = DriverManager.getConnection(DB_URL,
                    "postgres", "root");//соединениесБД
            conn.close();
            System.out.println("Соединение с СУБД закрыто.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static Connection connect() {
        try {
            Class.forName(DB_Driver); //Проверяем наличие JDBC драйвера для работы с БД
            Connection connection = DriverManager.getConnection(DB_URL,
                    "postgres", "root");//соединениесБД
            System.out.println("Соединение с СУБД выполнено.");
            return connection;
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // обработка ошибки  Class.forName
            System.out.println("JDBC драйвер для СУБД не найден!");
        } catch (SQLException e) {
            e.printStackTrace(); // обработка ошибок  DriverManager.getConnection
            System.out.println("Ошибка SQL !");
        }
        return null;
    }

}
