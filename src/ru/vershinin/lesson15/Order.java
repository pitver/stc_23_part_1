package ru.vershinin.lesson15;

<<<<<<< HEAD
/**
 * Order
 *
 * @author Вершинин Пётр
 */
=======
>>>>>>> d884f2e20f10f9b7a0de2a7ccc7f24310c0affd7
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
