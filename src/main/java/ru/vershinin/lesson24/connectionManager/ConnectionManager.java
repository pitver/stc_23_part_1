package ru.vershinin.lesson24.connectionManager;

import java.sql.Connection;

/**
 * connectionManager
 *
 * @author Вершинин Пётр
 */
public interface ConnectionManager {
    Connection getConnection();
}
