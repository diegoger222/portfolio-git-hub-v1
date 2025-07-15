package com.w_backend.demo.vuld;

import java.sql.*;

public class VulnerableSQL {
    public static void main(String[] args) {
        String userInput = "admin"; // Simula entrada del usuario

        try {
            // Conexión falsa (esto no se conectará realmente, es solo para análisis
            // estático)
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/test", "user", "pass");

            Statement stmt = connection.createStatement();
            String sql = "SELECT * FROM users WHERE username = '" + userInput + "'";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println("User found: " + rs.getString("username"));
            }

            rs.close();
            stmt.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
