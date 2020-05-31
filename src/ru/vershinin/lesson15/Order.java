package ru.vershinin.lesson15;

public class Order {
    Client client;
    Product product;

    public Order(Client client, Product product) {
        this.client = client;
        this.product = product;
    }

    @Override
    public String toString() {
        return "Order{" +
                "client=" + client +
                ", product=" + product +
                '}';
    }
}
