package dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import ru.vershinin.lesson17.ConnectionManager.ConnectionDB;
import ru.vershinin.lesson17.ConnectionManager.ConnectionManager;
import ru.vershinin.lesson17.dao.ActionsWithDB;
import ru.vershinin.lesson17.dao.ActionsWithDBImpl;
import ru.vershinin.lesson17.pojo.Client;
import ru.vershinin.lesson17.pojo.Order;
import ru.vershinin.lesson17.pojo.Product;
import ru.vershinin.lesson17.pojo.Shop;
import test.ru.vershinin.lesson17.TestResultLoggerExtension;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;


/**
 * ActionsWithDBImpl
 *
 * @author Вершинин Пётр
 */

@ExtendWith(TestResultLoggerExtension.class)
public class ActionsWithDBImplTest {

    private ActionsWithDB         actionsWithDB;
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
       actionsWithDB     = spy(new ActionsWithDBImpl(connectionManager));
   }
   @Test
    void addClient() throws SQLException {
       when(connectionManager.getConnection()).thenReturn(connection);
       doReturn(preparedStatementMock).when(connection).prepareStatement(ActionsWithDBImpl.INSERT_INTO_CLIENT );
       when(resultSetMock.next()).thenReturn(true);

       String fio="jack";
       int phoneNumber=123456;
       Client client=new Client(fio,phoneNumber);

      actionsWithDB.addClient(client);

       verify(connectionManager, times(1)).getConnection();
       verify(connection, atMost(1)).prepareStatement(ActionsWithDBImpl.INSERT_INTO_CLIENT);
       verify(preparedStatementMock, times(1)).setString(1, client.getFio());
       verify(preparedStatementMock, times(1)).setInt(2, client.getPhoneNumber());
       verify(preparedStatementMock, times(1)).executeQuery();
   }
    @Test
    void addProduct() throws SQLException {
        when(connectionManager.getConnection()).thenReturn(connection);
        doReturn(preparedStatementMock).when(connection).prepareStatement(ActionsWithDBImpl.INSERT_INTO_PRODUCT );
        when(resultSetMock.next()).thenReturn(true);

        String product_name="pen";
        double price=12.8d;
        boolean present=true;
        Product product=new Product(price,present,product_name);

        actionsWithDB.addProduct(product);

        verify(connectionManager, times(1)).getConnection();
        verify(connection, atMost(1)).prepareStatement(ActionsWithDBImpl.INSERT_INTO_PRODUCT);
        verify(preparedStatementMock, times(1)).setString(1, product.getProductName());
        verify(preparedStatementMock, times(1)).setDouble(2, product.getPrice());
        verify(preparedStatementMock, times(1)).setBoolean(3, product.isPresent());
        verify(preparedStatementMock, times(1)).executeQuery();
    }
    @Test
    void creatingOrder() throws SQLException {
        when(connectionManager.getConnection()).thenReturn(connection);
        doReturn(preparedStatementMock).when(connection).prepareStatement(ActionsWithDBImpl.INSERT_INTO_ORDER );
        when(resultSetMock.next()).thenReturn(true);

        Client client=new Client("Mike",123);
        Product product = new Product(12.9, true, "pen");

        actionsWithDB.creatingOrder(client,product);

        verify(connectionManager, times(1)).getConnection();
        verify(connection, atMost(1)).prepareStatement(ActionsWithDBImpl.INSERT_INTO_ORDER);
        verify(preparedStatementMock, times(1)).setInt(1, client.getId());
        verify(preparedStatementMock, times(1)).setInt(2, product.getId());
        verify(preparedStatementMock, times(1)).executeQuery();
    }
    @Test
    void addOrderToShop() throws SQLException {
        when(connectionManager.getConnection()).thenReturn(connection);
        doReturn(preparedStatementMock).when(connection).prepareStatement(ActionsWithDBImpl.INSERT_INTO_SHOP );
        when(resultSetMock.next()).thenReturn(true);
        Order order=new Order();
        Shop shop=new Shop();

        actionsWithDB.addOrderToShop(order,shop);

        verify(connectionManager, times(1)).getConnection();
        verify(connection, atMost(1)).prepareStatement(ActionsWithDBImpl.INSERT_INTO_SHOP);
        verify(preparedStatementMock, times(1)).setInt(1, order.getId());
        verify(preparedStatementMock, times(1)).setInt(2, shop.getNumberOrder());
        verify(preparedStatementMock, times(1)).executeQuery();
    }

    @Test
    void showProduct() throws SQLException {
        when(connectionManager.getConnection()).thenReturn(connection);
        doReturn(preparedStatementMock).when(connection).prepareStatement(ActionsWithDBImpl.SELECT_PRODUCT );
        when(resultSetMock.next()).thenReturn(true);

        actionsWithDB.showProduct();


       verify(connectionManager,times(1)).getConnection();
        verify(connection, atMost(1)).prepareStatement(ActionsWithDBImpl.SELECT_PRODUCT);
        verify(preparedStatementMock).executeQuery();
        verify(resultSetMock).next();
        verify(resultSetMock.getInt("id"));

    }
}
