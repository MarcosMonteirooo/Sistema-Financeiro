package br.cefetrj.dao.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static Connection instance;

    public static Connection getConnection() {
        if (instance == null) {
            String url = "jdbc:mysql://localhost:3306/nome do banco de dados";
            String user = "root";
            String password = "root";
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                instance = DriverManager.getConnection(url, user, password);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return instance;
    }
}

