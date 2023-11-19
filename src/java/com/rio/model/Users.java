/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rio.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Administrator
 */
public class Users {
    public static Connection connection = null;
    public static PreparedStatement statement = null;
    public static ResultSet resultSet = null;
    
    public static Boolean isLogin = null;

    public static String getErrorMsg() {
        if (connection == null) {
            return "Terjadi kesalahan koneksi";
        }
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                return "Terjadi kesalahan saat menutup ResultSet: " + e.getMessage();
            }
        }

        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                return "Terjadi kesalahan saat menutup PreparedStatement: " + e.getMessage();
            }
        }
        return ""; // Tidak ada kesalahan
    }

    public static boolean getUser(String NIM, String password) {
        try {
            connection = DatabaseConnection.getConnection();
            String customQuery = "SELECT count(*) FROM users WHERE NIM = ? and Password = ?";
            statement = connection.prepareStatement(customQuery);
            statement.setString(1, NIM); // Menggunakan PreparedStatement untuk menghindari SQL Injection
            statement.setString(2, password);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                if (resultSet.getInt("count(*)") == 1) {
                    isLogin = true;
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        isLogin = false;
        return false;
    }
}
