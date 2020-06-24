package ru.vershinin.lesson24.pojo;


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


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", client=" + client +
                ", product=" + product +
                '}';
    }

}
