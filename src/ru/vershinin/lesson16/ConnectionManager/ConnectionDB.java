package ru.vershinin.lesson16.ConnectionManager;

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
public class ConnectionDB implements ConnectionManager  {
    private static final Logger loggerSystem = LogManager.getLogger("SystemLog4J2");
    public static final ConnectionManager INSTANCE = new ConnectionDB();
    public static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";

    private ConnectionDB(){}

    public static ConnectionManager getInstance() {
        return INSTANCE;
    }
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


    @Override
    public Connection getConnection() {
        Connection connection=null;
        try {connection = DriverManager.getConnection(DB_URL,
                    "postgres", "root");//соединениесБД
            loggerSystem.info("Соединение с СУБД выполнено.");
            return connection;
        } catch (SQLException e) {
            loggerSystem.fatal("Ошибка SQL !"+e.getSQLState());
        }
        return connection;
    }
}
