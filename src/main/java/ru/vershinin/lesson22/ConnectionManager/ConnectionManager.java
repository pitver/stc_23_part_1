package ru.vershinin.lesson22.ConnectionManager;

import java.sql.Connection;

/**
 * ConnectionManager
 *
 * @author Вершинин Пётр
 */
public interface ConnectionManager {
    Connection getConnection();
}
