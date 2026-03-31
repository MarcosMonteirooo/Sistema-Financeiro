package br.cefetrj.dao.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static Connection instance;

    public static Connection getConnection() {
        if (instance == null) {
            String url = "jdbc:mysql://localhost:3306/sistema_financeiro";
            String user = "root";
            String password = "";

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                instance = DriverManager.getConnection(url, user, password);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                throw new RuntimeException("Erro ao conectar com o banco de dados");
            }
        }
        return instance;
    }
}

