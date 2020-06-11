package ru.vershinin.lesson17.ConnectionManager;

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
    private static final ConnectionManager INSTANCE = new ConnectionDB();// - ? для чего
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";

    private ConnectionDB(){}

    public static ConnectionManager getInstance() {// - ? для чего
        return INSTANCE;
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
