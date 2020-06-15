package ru.vershinin.lesson17.pojo;


import ru.vershinin.lesson17.ConnectionManager.ConnectionDB;
import ru.vershinin.lesson17.ConnectionManager.ConnectionManager;
import ru.vershinin.lesson17.dao.ShopDaoImpl;

/**
 * Order
 *
 * @author Вершинин Пётр
 */

public class Order {
    private int id;
    Client client;
    public Product product;

    public Order() {
    }

    public Order(Client client, Product product) {
        this.client = client;
        this.product = product;
    }

    public int getId() {
        ConnectionManager connectionManager= ConnectionDB.getInstance();
        ShopDaoImpl actionsWithDB= new ShopDaoImpl(connectionManager);
        return actionsWithDB.getMaxId("\"order\"");
    }



    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", client=" + client +
                ", product=" + product +
                '}';
    }

}
