package ru.vershinin.lesson17.pojo;


import ru.vershinin.lesson17.ConnectionManager.ConnectionDB;
import ru.vershinin.lesson17.ConnectionManager.ConnectionManager;
import ru.vershinin.lesson17.dao.ActionsWithDBImpl;

/**
 * Order
 *
 * @author Вершинин Пётр
 */

public class Order {
    private final int id;
    Client client;
    public Product product;

    public Order(int id,Client client, Product product) {
        this.id=id;
        this.client = client;
        this.product = product;
    }

    public int getId() {
        ConnectionManager connectionManager= ConnectionDB.getInstance();
        ActionsWithDBImpl actionsWithDB= new ActionsWithDBImpl(connectionManager);
        return actionsWithDB.getMaxId("order");
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
