package ru.vershinin.lesson27.dao.ClientDao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.vershinin.lesson27.connectionManager.ConnectionManager;
import ru.vershinin.lesson27.connectionManager.Myconnect;
import ru.vershinin.lesson27.pojo.Client;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDaoImpl implements ClientDao {

    private static final Logger loggerSystem = LogManager.getLogger("SystemLog4J2");
    private static final Logger loggerBusiness = LogManager.getLogger(ClientDaoImpl.class);
    public static final String INSERT_INTO_CLIENT = "INSERT INTO public.client(first_name, last_name," +
            " username, password,roles) VALUES ( ?,?,?,?,?)";
    //public static final String SELECT_CLIENT_LOGIN = "SELECT username,password FROM public.client";
    public static final String SELECT_CLIENT_BY_SESSION = "SELECT * FROM public.client WHERE username=? and password=?";
    public static final String SELECT_CLIENT = "SELECT * FROM public.client";
    public static final String SELECT_FROM_CLIENT_ID = "SELECT * FROM public.client WHERE id = ?";
    public static final String EDIT_CLIENT = "UPDATE public.client SET first_name=?, last_name=?, username=?,password=? WHERE id = ?";
    public static final String DELETE_CLIENT = "DELETE FROM public.client WHERE id = ?";

    private final ConnectionManager connectionManager;

    @Inject
    public ClientDaoImpl(@Myconnect ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
        loggerBusiness.info("получение connectionManager");

    }

    /**
     * поиск клиента и сравнение по имени и паролю введенных с формы и БД(подготовка к авторизации)
     *
     * @param client-экземпляр client
     * @return - экземпляр Client
     */
    @Override
    public Client findByClient(Client client) {
        try {
            try (Connection connection = connectionManager.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CLIENT_BY_SESSION)) {
                preparedStatement.setString(1, client.getUsername());
                preparedStatement.setString(2, client.getPassword());
                try (ResultSet rs = preparedStatement.executeQuery()) {
                    while (rs.next()) {
                        String usernameDB = rs.getString("username");
                        String passwordDB = rs.getString("password");
                        int id = rs.getInt("id");
                        String firstName = rs.getString("first_name");
                        String lastName = rs.getString("last_name");
                        String roles = rs.getString("roles");
                        if (client.getUsername().equals(usernameDB) && client.getPassword().equals(passwordDB)) {
                            loggerBusiness.info("пользоваель " + client.getUsername() + " найден");
                            return new Client.Builder()
                                    .withId(id)
                                    .withFirstName(firstName)
                                    .withLastName(lastName)
                                    .withUsername(usernameDB)
                                    .withRole(roles)
                                    .build();
                        }
                        loggerBusiness.info("пользоваель " + client.getUsername() + " не найден");
                    }

                } catch (SQLException e) {
                    loggerSystem.error(e.getMessage());
                }

            }
        } catch (SQLException e) {
            loggerSystem.error(e.getMessage());
        }
        return null;
    }

    /**
     * вывод всех клиентов
     *
     * @return - Список Client
     */
    @Override
    public List<Client> findAll() {
        List<Client> clientList = new ArrayList<>();
        try (Connection conn = connectionManager.getConnection();
             ResultSet rs = conn.prepareStatement(SELECT_CLIENT).executeQuery()) {

            while (rs.next()) {
                clientList.add(new Client.Builder()
                        .withUsername(rs.getString("username"))
                        .withId(rs.getInt("id"))
                        .withFirstName(rs.getString("first_name"))
                        .withLastName(rs.getString("last_name"))
                        .withRole(rs.getString("roles"))
                        .build());

            }
            loggerBusiness.info(clientList);
            return clientList;

        } catch (SQLException e) {
            loggerSystem.error(e.getMessage());
        }
        return new ArrayList<>();
    }

    /**
     * поиск клиента по ID
     *
     * @param id - ID клиента
     * @return- Экземпляр Client
     */
    @Override
    public Client findById(Integer id) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_CLIENT_ID)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Client.Builder()
                            .withUsername(resultSet.getString("username"))
                            .withId(resultSet.getInt("id"))
                            .withFirstName(resultSet.getString("first_name"))
                            .withLastName(resultSet.getString("last_name"))
                            .withRole(resultSet.getString("roles"))
                            .build();
                }
            }

        } catch (SQLException e) {
            loggerSystem.error(e.getMessage());
        }
        return null;
    }

    /**
     * редактирование клиента
     *
     * @param client - экземпляр Client
     */
    @Override
    public void editClient(Client client) {
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(EDIT_CLIENT)) {
            st.setString(1, client.getFirstName());
            st.setString(2, client.getLastName());
            st.setString(3, client.getUsername());
            st.setString(4, client.getPassword());
            st.setInt(5, client.getId());
            st.executeQuery();

        } catch (SQLException e) {
            loggerSystem.error(e.getMessage());
        }
    }

    /**
     * удаление клиента
     *
     * @param client - экземпляр Client
     */
    @Override
    public void delete(Client client) {
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(DELETE_CLIENT)) {
            st.setInt(1, client.getId());
            st.executeQuery();

        } catch (SQLException e) {
            loggerSystem.error(e.getMessage());
        }
    }

    /**
     * добавление пользователя в таблицу client
     *
     * @param client -  экземпляр client
     */
    public void save(Client client) {
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(INSERT_INTO_CLIENT)) {
            st.setString(1, client.getFirstName());
            st.setString(2, client.getLastName());
            st.setString(3, client.getUsername());
            st.setString(4, client.getPassword());
            st.setString(5,"user");
            st.executeQuery();
            loggerBusiness.info("поьзователь добавлен ");
        } catch (SQLException e) {
            loggerSystem.error(e.getMessage());
        }

    }
}
