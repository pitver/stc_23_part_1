package ru.vershinin.lesson22.dao;

import ru.vershinin.lesson22.pojo.Client;

public interface ClientDao {
    void addClient(Client client);

    boolean findClient(String username, String password);
}
