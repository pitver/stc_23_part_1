package ru.vershinin.lesson16;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.lang.Class.forName;

/**
 * ChangeDB
 *
 * @author Вершинин Пётр
 */
public class ConnectionDB {
    private static final Logger loggerSystem = LogManager.getLogger("SystemLog4J2");


    public static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    public static final String DB_Driver = "org.postgresql.Driver";

    /**
     * закрытие соединения с бд
     */
    public static void connectClose() {

        try {
            Connection conn = DriverManager.getConnection(DB_URL,
                    "postgres", "root");//соединениесБД
            conn.close();
            loggerSystem.info("Соединение с СУБД закрыто.");
        } catch (SQLException e) {
            loggerSystem.error(e.getMessage());
        }
    }


    /**
     * создание соединения с бд
     *
     * @return-  java.sql.Connection;
     *
     */
    public static Connection connect() {
        try {
            forName(DB_Driver); //Проверяем наличие JDBC драйвера для работы с БД
            Connection connection = DriverManager.getConnection(DB_URL,
                    "postgres", "root");//соединениесБД
            loggerSystem.info("Соединение с СУБД выполнено.");
            return connection;
        } catch (ClassNotFoundException e) {
            loggerSystem.fatal("JDBC драйвер для СУБД не найден!"+e.getMessage());
        } catch (SQLException e) {
            loggerSystem.fatal("Ошибка SQL !"+e.getSQLState());
        }
        return null;
    }

}
