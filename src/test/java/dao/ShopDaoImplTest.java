package dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import ru.vershinin.lesson17.ConnectionManager.ConnectionDB;
import ru.vershinin.lesson17.ConnectionManager.ConnectionManager;
import ru.vershinin.lesson17.dao.ShopDao;
import ru.vershinin.lesson17.dao.ShopDaoImpl;
import ru.vershinin.lesson17.pojo.Client;
import ru.vershinin.lesson17.pojo.Order;
import ru.vershinin.lesson17.pojo.Product;
import ru.vershinin.lesson17.pojo.Shop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;


/**
 * ActionsWithDBImpl
 *
 * @author Вершинин Пётр
 */

@ExtendWith(TestResultLoggerExtension.class)
public class ShopDaoImplTest {

    private ShopDao actionsWithDB;
    private ConnectionManager     connectionManager;
    private Connection            connection;
    @Mock
    private PreparedStatement     preparedStatementMock;
    @Mock
    private ResultSet             resultSetMock;


    @BeforeEach
   void setUp() throws SQLException {
       initMocks(this);
       connectionManager = spy(ConnectionDB.getInstance());
       connection        = mock(Connection.class);
       actionsWithDB     = spy(new ShopDaoImpl(connectionManager));
   }
   @Test
    void addClient() throws SQLException {
       when(connectionManager.getConnection()).thenReturn(connection);
       doReturn(preparedStatementMock).when(connection).prepareStatement(ShopDaoImpl.INSERT_INTO_CLIENT );
       when(resultSetMock.next()).thenReturn(true);

       String fio="jack";
       int phoneNumber=123456;
       Client client=new Client(fio,phoneNumber);

      actionsWithDB.addClient(client);

       verify(connectionManager, times(1)).getConnection();
       verify(connection, atMost(1)).prepareStatement(ShopDaoImpl.INSERT_INTO_CLIENT);
       verify(preparedStatementMock, times(1)).setString(1, client.getFio());
       verify(preparedStatementMock, times(1)).setInt(2, client.getPhoneNumber());
       verify(preparedStatementMock, times(1)).executeQuery();
   }
    @Test
    void addProduct() throws SQLException {
        when(connectionManager.getConnection()).thenReturn(connection);
        doReturn(preparedStatementMock).when(connection).prepareStatement(ShopDaoImpl.INSERT_INTO_PRODUCT );
        when(resultSetMock.next()).thenReturn(true);

        String product_name="pen";
        double price=12.8d;
        boolean present=true;
        Product product=new Product(price,present,product_name);

        actionsWithDB.addProduct(product);

        verify(connectionManager, times(1)).getConnection();
        verify(connection, atMost(1)).prepareStatement(ShopDaoImpl.INSERT_INTO_PRODUCT);
        verify(preparedStatementMock, times(1)).setString(1, product.getProductName());
        verify(preparedStatementMock, times(1)).setDouble(2, product.getPrice());
        verify(preparedStatementMock, times(1)).setBoolean(3, product.isPresent());
        verify(preparedStatementMock, times(1)).executeQuery();
    }
    @Test
    void creatingOrder() throws SQLException {
        when(connectionManager.getConnection()).thenReturn(connection);
        doReturn(preparedStatementMock).when(connection).prepareStatement(ShopDaoImpl.INSERT_INTO_ORDER );
        when(resultSetMock.next()).thenReturn(true);

        Client client=new Client("Mike",123);
        Product product = new Product(12.9, true, "pen");

        actionsWithDB.creatingOrder(client,product);

        verify(connectionManager, times(1)).getConnection();
        verify(connection, atMost(1)).prepareStatement(ShopDaoImpl.INSERT_INTO_ORDER);
        verify(preparedStatementMock, times(1)).setInt(1, client.getId());
        verify(preparedStatementMock, times(1)).setInt(2, product.getId());
        verify(preparedStatementMock, times(1)).executeQuery();
    }
    @Test
    void addOrderToShop() throws SQLException {
        when(connectionManager.getConnection()).thenReturn(connection);
        doReturn(preparedStatementMock).when(connection).prepareStatement(ShopDaoImpl.INSERT_INTO_SHOP );
        when(resultSetMock.next()).thenReturn(true);
        Order order=new Order();
        Shop shop=new Shop();

        actionsWithDB.addOrderToShop(order,shop);

        verify(connectionManager, times(1)).getConnection();
        verify(connection, atMost(1)).prepareStatement(ShopDaoImpl.INSERT_INTO_SHOP);
        verify(preparedStatementMock, times(1)).setInt(1, order.getId());
        verify(preparedStatementMock, times(1)).setInt(2, shop.getNumberOrder());
        verify(preparedStatementMock, times(1)).executeQuery();
    }

    @Test
    void showProduct() {
       List<?> listProduct= actionsWithDB.showProduct();
       assertEquals(2,listProduct.size());
        assertEquals("book",listProduct.get(0));
        assertEquals("pen",listProduct.get(1));
    }
    @Test
    void prepareOrder() {
       List<?> listClient= actionsWithDB.prepareOrder();
       assertEquals(1,listClient.size());
        assertEquals("Tom",listClient.get(0));
    }
    @Test
    void getAllOrder() {
       List<?> listNumberOrder= actionsWithDB.getAllOrder();
       assertEquals(2,listNumberOrder.size());
        assertEquals(1687,listNumberOrder.get(0));
        assertEquals(33,listNumberOrder.get(1));
    }


}
