package ru.vershinin.lesson21.dao;

import ru.vershinin.lesson21.pojo.Client;

public interface ClientDao {
    void addClient(Client client);
    boolean findClient(String username,String password);
}
