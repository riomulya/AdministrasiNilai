package com.rio.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MataKuliah {

    private String kodeMataKuliah;
    private String namaMataKuliah;
    private int sks;

    public static Connection connection = null;
    public static PreparedStatement statement = null;
    public static ResultSet resultSet = null;

    // Constructors, getters, and setters
    public MataKuliah(String kodeMataKuliah, String namaMataKuliah, int sks) {
        this.kodeMataKuliah = kodeMataKuliah;
        this.namaMataKuliah = namaMataKuliah;
        this.sks = sks;
    }

    public String getKodeMataKuliah() {
        return kodeMataKuliah;
    }

    public void setKodeMataKuliah(String kodeMataKuliah) {
        this.kodeMataKuliah = kodeMataKuliah;
    }

    public String getNamaMataKuliah() {
        return namaMataKuliah;
    }

    public void setNamaMataKuliah(String namaMataKuliah) {
        this.namaMataKuliah = namaMataKuliah;
    }

    public int getSks() {
        return sks;
    }

    public void setSks(int sks) {
        this.sks = sks;
    }

    public static ArrayList<MataKuliah> getDaftarMataKuliah(String NIM) {
        ArrayList<MataKuliah> mataKuliahList = new ArrayList<>();

        try {
            connection = DatabaseConnection.getConnection();
            String customQuery = "SELECT matakuliah.KodeMataKuliah, matakuliah.NamaMataKuliah, matakuliah.SKS "
                    + "FROM matakuliah "
                    + "INNER JOIN nilaimahasiswa ON matakuliah.KodeMataKuliah = nilaimahasiswa.KodeMataKuliah "
                    + "WHERE nilaimahasiswa.NIM = ?";
            statement = connection.prepareStatement(customQuery);
            statement.setString(1, NIM);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String kodeMataKuliah = resultSet.getString("KodeMataKuliah");
                String namaMataKuliah = resultSet.getString("NamaMataKuliah");
                int sks = resultSet.getInt("SKS");

                MataKuliah mataKuliah = new MataKuliah(kodeMataKuliah, namaMataKuliah, sks);
                mataKuliahList.add(mataKuliah);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mataKuliahList;
    }

    public static boolean HapusMatkul(String kodeMatkul) {
        try {

            connection = DatabaseConnection.getConnection();
            String query = "DELETE FROM matakuliah WHERE KodeMataKuliah = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, kodeMatkul);
            statement.executeUpdate();
            // Untuk mendapatkan jumlah baris yang terpengaruh oleh penghapusan
            int rowsAffected = statement.executeUpdate();

            // Jika ada satu baris yang terpengaruh, penghapusan berhasil
            if (rowsAffected == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean SimpanMatkul(String kodeMatkul, String namaMatkul, int sks) {
        try {
            connection = DatabaseConnection.getConnection();
            String query = "INSERT INTO matakuliah (KodeMataKuliah, NamaMataKuliah, SKS) VALUES (?, ?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, kodeMatkul);
            statement.setString(2, namaMatkul);
            statement.setInt(3, sks);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static MataKuliah LihatMatkul(String kodeMatkul) {
        try {
            connection = DatabaseConnection.getConnection();
            String query = "SELECT * FROM matakuliah WHERE KodeMataKuliah = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, kodeMatkul);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String KodeMatkul = resultSet.getString("KodeMataKuliah");
                String namaMatkul = resultSet.getString("NamaMataKuliah");
                int sks = resultSet.getInt("SKS");

                MataKuliah mataKuliah = new MataKuliah(KodeMatkul, namaMatkul, sks);
                return mataKuliah;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String InsertOrUpdateMatkul(String kodeMatkul, String namaMatkul, int sks) {
        try {
            if (MataKuliah.LihatMatkul(kodeMatkul) != null) {
                // Jika mata kuliah sudah ada, lakukan update
                String updateQuery = "UPDATE matakuliah SET NamaMataKuliah = ?, SKS = ? WHERE KodeMataKuliah = ?";
                PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                updateStatement.setString(1, namaMatkul);
                updateStatement.setInt(2, sks);
                updateStatement.setString(3, kodeMatkul);
                updateStatement.executeUpdate();
                updateStatement.close();
                return "Update";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error Update";
        }
        try {
             // Jika mata kuliah belum ada, lakukan insert
            String insertQuery = "INSERT INTO matakuliah (KodeMataKuliah, NamaMataKuliah, SKS) VALUES (?, ?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
            insertStatement.setString(1, kodeMatkul);
            insertStatement.setString(2, namaMatkul);
            insertStatement.setInt(3, sks);
            insertStatement.executeUpdate();
            insertStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error Insert";
        }
        return "Insert";
    }
}
