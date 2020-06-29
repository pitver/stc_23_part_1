package ru.vershinin.lesson27.dao.ClientDao;

import ru.vershinin.lesson27.pojo.Client;


import java.util.List;

public interface ClientDao {
    void save(Client client);

    Client findByClient(Client client);

    List<Client> findAll();

    Client findById(Integer id);

    void editClient(Client client);

    void delete(Client client);
}
