package ru.vershinin.lesson24.dao;

import ru.vershinin.lesson24.pojo.Client;


import java.util.List;

public interface ClientDao {
    void save(Client client);

    boolean findByClient(Client client);

    List<Client> findAll();

    Client findById(Integer id);

    void editClient(Client client);

    void delete(Client client);
}