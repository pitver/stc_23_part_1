package ru.vershinin.lesson21.ConnectionManager;

import java.sql.Connection;

/**
 * ConnectionManager
 *
 * @author Вершинин Пётр
 */
public interface ConnectionManager {
    Connection getConnection();
}
