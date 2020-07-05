package ru.vershinin.lesson27.connectionManager;

import java.sql.Connection;

/**
 * connectionManager
 *
 * @author Вершинин Пётр
 */
public interface ConnectionManager {
    Connection getConnection();
}
