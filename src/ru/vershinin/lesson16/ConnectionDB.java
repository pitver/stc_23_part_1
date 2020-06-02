package ru.vershinin.lesson16;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * ChangeDB
 *
 * @author Вершинин Пётр
 */
public class ConnectionDB {
    private static final Logger logger = LogManager.getLogger(ConnectionDB.class);
   static Marker markerBusiness = MarkerManager.getMarker("business");
   static Marker markerSecurity = MarkerManager.getMarker("security");
   static Marker markerSystem = MarkerManager.getMarker("system");

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
            logger.info(markerSystem,"Соединение с СУБД закрыто.");
        } catch (SQLException e) {
            logger.error(markerSystem,e.getMessage());
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
            Class.forName(DB_Driver); //Проверяем наличие JDBC драйвера для работы с БД
            Connection connection = DriverManager.getConnection(DB_URL,
                    "postgres", "root");//соединениесБД
            logger.info(markerSystem,"Соединение с СУБД выполнено.");
            return connection;
        } catch (ClassNotFoundException e) {
            logger.fatal(markerSystem,"JDBC драйвер для СУБД не найден!"+e.getMessage());
        } catch (SQLException e) {
            logger.fatal(markerSystem,"Ошибка SQL !"+e.getSQLState());
        }
        return null;
    }

}
