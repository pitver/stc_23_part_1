package ru.vershinin.lesson13;

import java.time.LocalDate;
import java.util.Objects;

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
