package ru.innopolis.stc12.lab02.repository.connectionManager;

import java.sql.Connection;

public interface ConnectionManager {
    public Connection getConnection();
}