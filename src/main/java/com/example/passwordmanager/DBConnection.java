package com.example.passwordmanager;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public Connection bdLink;

    public Connection getConnection() {
        String bdNombre = "password_manager";
        String bdUsuario = "root";
        String bdPassword = "";
        String bdServer = "localhost";
        String url = "jdbc:mysql://" + bdServer + "/" + bdNombre;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            bdLink = DriverManager.getConnection(url, bdUsuario, bdPassword);
        } catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return bdLink;
    }
}
