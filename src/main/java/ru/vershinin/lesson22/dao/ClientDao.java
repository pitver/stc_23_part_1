package ru.vershinin.lesson22.dao;

import ru.vershinin.lesson22.pojo.Client;
import ru.vershinin.lesson22.pojo.Product;

import java.util.List;

public interface ClientDao {
    void addClient(Client client);

    boolean findClient(String username, String password);

    List<Client> showClient();
}
