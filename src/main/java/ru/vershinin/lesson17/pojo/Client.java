package ru.vershinin.lesson17.pojo;


/**
 * Client
 *
 * @author Вершинин Пётр
 */

import ru.vershinin.lesson17.ConnectionManager.ConnectionDB;
import ru.vershinin.lesson17.ConnectionManager.ConnectionManager;
import ru.vershinin.lesson17.dao.ShopDaoImpl;

import java.util.Objects;

public class Client {
    private int id;
    private String fio;
    private int phoneNumber;


    public Client( String fio, int phoneNumber) {
        this.fio = fio;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        ConnectionManager connectionManager= ConnectionDB.getInstance();
        ShopDaoImpl actionsWithDB= new ShopDaoImpl(connectionManager);
        return actionsWithDB.getMaxId("client");
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id == client.id &&
                phoneNumber == client.phoneNumber &&
                Objects.equals(fio, client.fio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fio, phoneNumber);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", fio='" + fio + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }
}

