package ru.vershinin.lesson27.connectionManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.EJB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.lang.Class.forName;

/**
 * ChangeDB
 *
 * @author Вершинин Пётр
 */

@EJB
@Myconnect
public class ConnectionDB implements ConnectionManager {
    private static final Logger loggerSystem = LogManager.getLogger("SystemLog4J2");
    //public static final String DB_URL = "jdbc:postgresql://host.docker.internal:5434/postgres";
    public static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";

    @Override
    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL,
                    "postgres", "root");//соединениесБД
            //"postgres", "qwerty");//соединениесБД
            loggerSystem.info("Соединение с СУБД выполнено.");
            return connection;
        } catch (SQLException e) {
            loggerSystem.fatal("Ошибка SQL !" + e.getSQLState());
        }
        return connection;
    }
}
