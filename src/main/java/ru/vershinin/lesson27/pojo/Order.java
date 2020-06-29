package ru.vershinin.lesson27.pojo;


/**
 * Order
 *
 * @author Вершинин Пётр
 */

public class Order {
    private int id;
    Client client;
    public Product product;
    private int numberOrder;

    public Client getClient() {
        return client;
    }


    public void setClient(Client client) {
        this.client = client;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getNumberOrder() {
        return numberOrder;
    }

    public void setNumberOrder(int numberOrder) {
        this.numberOrder = numberOrder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Order() {
    }

    public Order(Client client, Product product, int numberOrder, int id) {
        this.client = client;
        this.product = product;
        this.numberOrder = numberOrder;
        this.id = id;
    }


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", client=" + client +
                ", product=" + product +
                ", numberOrder=" + numberOrder +
                '}';
    }
}
