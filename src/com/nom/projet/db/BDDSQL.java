package com.nom.projet.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDDSQL {
    //Attribut paramètre BDD
    static final String DB_URL = "jdbc:mysql://localhost/cda";
    static final String USERNAME = "root";
    static final String PASSWORD = "";
    //Connexion à la BDD
    private static Connection connexion;
    static {
        try {
            connexion = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection(){
        return connexion;
    }
}
