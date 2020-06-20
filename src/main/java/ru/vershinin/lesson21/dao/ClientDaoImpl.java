package ru.vershinin.lesson21.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.vershinin.lesson21.ConnectionManager.ConnectionManager;
import ru.vershinin.lesson21.ConnectionManager.Myconnect;
import ru.vershinin.lesson21.pojo.Client;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientDaoImpl implements ClientDao {

    private static final Logger loggerSystem = LogManager.getLogger("SystemLog4J2");
    private static final Logger loggerBusiness = LogManager.getLogger(ClientDaoImpl.class);
    public static final String INSERT_INTO_CLIENT = "INSERT INTO public.client(first_name, last_name," +
            " username, password) VALUES ( ?,?,?,?)";
    public static final String SELECT_CLIENT_LOGIN = "SELECT username,password FROM public.client";

    private final ConnectionManager connectionManager;

    @Inject
    public ClientDaoImpl(@Myconnect ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;

    }
    /**
     * поиск и сравнение по имени и паролю введенных с формы и БД(подготовка к авторизации)
     *
     * @param username - введенный логин
     * @param password - введенный пароль
     * @return-состояние, если
     */
    @Override
    public boolean findClient(String username, String password) {
        try (Connection conn = connectionManager.getConnection();
             ResultSet rs = conn.prepareStatement(SELECT_CLIENT_LOGIN).executeQuery()) {
            while (rs.next()) {
                String usernameDB = rs.getString("username");
                String passwordDB = rs.getString("password");
                if (username.equals(usernameDB)&& password.equals(passwordDB)) {
                    return true;
                }
            }

        } catch (SQLException e) {
            loggerSystem.error(e.getMessage());
        }
        return false;
    }

    /**
     * добавление пользователя в таблицу Client
     *
     * @param client -  экземпляр Client
     */
    public void addClient(Client client) {
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(INSERT_INTO_CLIENT)) {
            st.setString(1, client.getFirstName());
            st.setString(2, client.getLastName());
            st.setString(3, client.getUsername());
            st.setString(4, client.getPassword());
            st.executeQuery();
        } catch (SQLException e) {
            loggerSystem.error(e.getMessage());
        }

    }
}
