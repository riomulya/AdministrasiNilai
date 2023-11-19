package com.rio.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Administrator
 */
public class Mahasiswa {

    private String namaMhs;

    public String getNamaMhs() {
        return namaMhs;
    }

    public void setNamaMhs(String namaMhs) {
        this.namaMhs = namaMhs;
    }

    public String getNimMhs() {
        return nimMhs;
    }

    public void setNimMhs(String nimMhs) {
        this.nimMhs = nimMhs;
    }

    public String getAlamatMhs() {
        return alamatMhs;
    }

    public void setAlamatMhs(String alamatMhs) {
        this.alamatMhs = alamatMhs;
    }

    public String getJurusanMhs() {
        return jurusanMhs;
    }

    public void setJurusanMhs(String jurusanMhs) {
        this.jurusanMhs = jurusanMhs;
    }
    private String nimMhs;
    private String alamatMhs;
    private String jurusanMhs;
    private String semesterMhs;

    public String getSemesterMhs() {
        return semesterMhs;
    }

    public void setSemesterMhs(String semesterMhs) {
        this.semesterMhs = semesterMhs;
    }

    public String getKelasMhs() {
        return kelasMhs;
    }

    public void setKelasMhs(String kelasMhs) {
        this.kelasMhs = kelasMhs;
    }
    private String kelasMhs;

    public static Connection connection = null;
    public static PreparedStatement statement = null;
    public static ResultSet resultSet = null;

    public static Boolean isLogin = null;

    public Mahasiswa(String nama, String nim, String alamat, String jurusan,String kelas,String semseter) {
        this.namaMhs = nama;
        this.nimMhs = nim;
        this.alamatMhs = alamat;
        this.jurusanMhs = jurusan;
        this.kelasMhs = kelas;
        this.semesterMhs = semseter;
    }

    public Mahasiswa() {
    }

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

    public static Mahasiswa getMhs(String NIM) {
        try {
            connection = DatabaseConnection.getConnection();
            String customQuery = "SELECT * FROM mahasiswa WHERE NIM = ?";
            statement = connection.prepareStatement(customQuery);
            statement.setString(1, NIM);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Ambil data mahasiswa dari hasil query
                String nimMhs = resultSet.getString("NIM");
                String namaMhs = resultSet.getString("Nama");
                String alamatMhs = resultSet.getString("Alamat");
                String jurusanMhs = resultSet.getString("Jurusan");
                String semesterMhs = resultSet.getString("Semester");
                String kelasMhs = resultSet.getString("Kelas");

                // Buat objek Mahasiswa dari data yang didapat
                Mahasiswa mahasiswa = new Mahasiswa(namaMhs, nimMhs, alamatMhs, jurusanMhs,kelasMhs,semesterMhs);
                return mahasiswa;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
