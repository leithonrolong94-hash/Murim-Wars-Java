package com.murimwars;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL =
            "jdbc:mysql://localhost:3306/murim_wars_ev01";

    private static final String USUARIO = "root";
   private static final String CLAVE = "Leithon2004";

    public static Connection conectar() {
        try {
            Connection conexion = DriverManager.getConnection(
                    URL, USUARIO, CLAVE
            );

            System.out.println("Conexión exitosa con MySQL.");
            return conexion;

        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
            return null;
        }
    }
}