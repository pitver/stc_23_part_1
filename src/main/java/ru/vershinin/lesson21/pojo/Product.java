package ru.vershinin.lesson21.pojo;

import java.util.Objects;

public class Product {
    private int id;
   private String productName;
   private double price;
   private boolean present;

    public Product( double price,boolean present, String productName) {
        this.productName = productName;
        this.price = price;
        this.present = present;
    }

    public Product(int id, String productName, double price, boolean present) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.present = present;
    }

    public Product() {
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id &&
                Double.compare(product.price, price) == 0 &&
                present == product.present &&
                Objects.equals(productName, product.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productName, price, present);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", present=" + present +
                '}';
    }
}
