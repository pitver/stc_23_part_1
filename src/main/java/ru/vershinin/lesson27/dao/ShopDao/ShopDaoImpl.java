package ru.vershinin.lesson27.dao.ShopDao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.vershinin.lesson27.connectionManager.ConnectionManager;
import ru.vershinin.lesson27.connectionManager.Myconnect;
import ru.vershinin.lesson27.controller.fasade.OrderServiceFacadeImpl;
import ru.vershinin.lesson27.pojo.Client;
import ru.vershinin.lesson27.pojo.Order;
import ru.vershinin.lesson27.pojo.Product;
import ru.vershinin.lesson27.pojo.Shop;

import javax.ejb.EJB;
import javax.inject.Inject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * ShopDaoImpl
 *
 * @author Вершинин Пётр
 */

@EJB
public class ShopDaoImpl implements ShopDao {

    private static final Logger loggerSystem = LogManager.getLogger("SystemLog4J2");
    private static final Logger loggerBusiness = LogManager.getLogger(ShopDaoImpl.class);

    public static final String INSERT_INTO_ORDER = "INSERT INTO public.order(client_id, product_id,number_order) VALUES ( ?, ?, ?)";
    public static final String INSERT_INTO_SHOP = "INSERT INTO public.shop(order_id, number_order) VALUES ( ?, ?)";

    public static final String SELECT_PREPARE_ORDER = "select o.id,o.number_order, cl.first_name, " +
            "cl.last_name,cl.username,p.product_name,p.price,p.present " +
            "FROM client cl " +
            "LEFT JOIN \"order\" o ON cl.id = \"o\".client_id " +
            "LEFT JOIN \"product\" p on p.id = \"o\".product_id WHERE cl.id = ?";


    public static final String SELECT_GET_ALL_ORDER = "select sh.number_order,c.fio, c.phonenumber,p.product_name,p.price,p.present\n" +
            "FROM shop sh\n" +
            "LEFT JOIN \"order\" o on sh.order_id = o.id\n" +
            "LEFT JOIN client c on o.client_id = c.id\n" +
            "LEFT JOIN product p on o.product_id = p.id";


    private final ConnectionManager connectionManager;

    @Inject
    public ShopDaoImpl(@Myconnect ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;

    }

    /**
     * добавление заказа в таблицу order
     *
     * @param client  - экземпляр client
     * @param product - экземпляр product
     */
    @Override
    public void save(Client client, Product product) {
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(INSERT_INTO_ORDER)) {


            st.setInt(1, client.getId());
            st.setInt(2, product.getId());
            Random rd = new Random();
            st.setInt(3, rd.nextInt(9999));
            st.executeQuery();
        } catch (SQLException e) {
            loggerSystem.error(e.getMessage());
        }
    }


    /**
     * поиск закзов у конкретного клиента
     *
     * @param id - ID Client-получаем из текущей сессии
     * @return -List Order
     */
    @Override
    public List<Order> findOrderByClientId(Integer id) {
        List<Order> listOrder = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PREPARE_ORDER)) {
            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    int idOrder = rs.getInt("id");
                    String usernameDB = rs.getString("username");
                    int numberOrder = rs.getInt("number_order");
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    Double price = rs.getDouble("price");
                    String productName = rs.getString("product_name");
                    Boolean present = rs.getBoolean("present");

                    listOrder.add(new Order(new Client.Builder()
                            .withUsername(usernameDB)
                            .withFirstName(firstName)
                            .withLastName(lastName)
                            .build(), new Product(price, present, productName), numberOrder, idOrder));
                }
            } catch (SQLException e) {
                loggerSystem.error(e.getMessage());
            }

        } catch (SQLException e) {
            loggerSystem.error(e.getMessage());
        }
        return listOrder;
    }

    /**
     * вызов имитации паттерна фасад
     */
    @Override
    public void OrderFulfillmentController() {
        OrderServiceFacadeImpl facade = new OrderServiceFacadeImpl();
        facade.placeOrder();
        System.out.println("OrderFulfillmentController: Order fulfillment completed. ");

    }

//********************************* временно б/у ********************

    /**
     * отображение истории заказов
     */
    public List<?> getAllOrder() {
        List<Integer> listNumberOrder = new ArrayList<>();

        try (Connection conn = connectionManager.getConnection();
             ResultSet rs = conn.prepareStatement(SELECT_GET_ALL_ORDER).executeQuery()) {

            loggerBusiness.info("     ************История заказов************");
            loggerBusiness.info(String.format("%-9s%-10s%-11s%-14s%-11s%-11s%n", "№Заказа", "ФИО", "телефон", "Наименование", "Цена", "Наличие"));
            while (rs.next()) {
                int number_order = rs.getInt("number_order");
                listNumberOrder.add(number_order);
                String name = rs.getString("fio");
                int phoneNumber = rs.getInt("phonenumber");
                double price = rs.getDouble("price");
                String productName = rs.getString("product_name");
                boolean present = rs.getBoolean("present");

                loggerBusiness.info(String.format("%-9d%-10s%-11d%-14s%-11.2f%-13s%n", number_order, name, phoneNumber, productName, price, present ? "да" : "нет"));
            }
        } catch (SQLException e) {
            loggerSystem.error(e.getMessage());
        }
        return listNumberOrder;
    }

    /**
     * поиск id последней записи в таблицах
     *
     * @param nameTable -имя таблицы String;
     */
    public int getMaxId(String nameTable) {
        int count = 0;
        String sql = "SELECT id From " + nameTable + " WHERE id=(SELECT max(id) FROM + nameTable +)";
        try (Connection conn = connectionManager.getConnection();
             ResultSet rs = conn.prepareStatement(sql).executeQuery()) {
            while (rs.next()) {
                count = rs.getInt("id");
            }
        } catch (SQLException e) {
            loggerSystem.error(e.getMessage());
        }
        return count;
    }


    /**
     * добавление заказа в архив магазина, таблица shop
     * номер заказа присваивается рандомно
     *
     * @param order - экземпляр Order
     * @param shop  - экземпляр Shop
     */
    public void addOrderToShop(Order order, Shop shop) {
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(INSERT_INTO_SHOP)) {
            st.setInt(1, getMaxId("order"));
            st.setInt(2, shop.getNumberOrder());
            st.executeQuery();
        } catch (SQLException e) {
            loggerSystem.error(e.getMessage());
        }
    }


}



