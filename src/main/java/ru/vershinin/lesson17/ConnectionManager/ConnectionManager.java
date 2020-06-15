package ru.vershinin.lesson17.ConnectionManager;

import java.sql.Connection;

/**
 * ConnectionManager
 *
 * @author Вершинин Пётр
 */
public interface ConnectionManager {
    Connection getConnection();
}
