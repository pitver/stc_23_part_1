package test.ru.vershinin.lesson17.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import ru.vershinin.lesson17.ConnectionManager.ConnectionDB;
import ru.vershinin.lesson17.ConnectionManager.ConnectionManager;
import ru.vershinin.lesson17.dao.ActionsWithDB;
import ru.vershinin.lesson17.dao.ActionsWithDBImpl;
import ru.vershinin.lesson17.pojo.Client;
import test.ru.vershinin.lesson17.TestResultLoggerExtension;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
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
    private PreparedStatement     preparedStatement;
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
       doReturn(preparedStatement).when(connection).prepareStatement(ActionsWithDBImpl.INSERT_INTO_CLIENT );
       when(resultSetMock.next()).thenReturn(true);


       String fio="jack";
       int phoneNumber=123456;
       Client client=new Client(fio,phoneNumber);

      actionsWithDB.addClient(client);

       verify(connectionManager, times(1)).getConnection();
       verify(connection, atMost(1)).prepareStatement(ActionsWithDBImpl.INSERT_INTO_CLIENT);
       verify(preparedStatement, times(1)).setString(1, client.getFio());
       verify(preparedStatement, times(1)).setInt(2, client.getPhoneNumber());
       verify(preparedStatement, times(1)).executeQuery();

   }
}
