package com.rio.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NilaiMahasiswa {

    private String NIM;
    private String KodeMataKuliah;
    private int Nilai;
    
    public static Connection connection = null;
    public static PreparedStatement statement = null;
    public static ResultSet resultSet = null;

    public NilaiMahasiswa(String NIM, String KodeMataKuliah, int Nilai) {
        this.NIM = NIM;
        this.KodeMataKuliah = KodeMataKuliah;
        this.Nilai = Nilai;
    }

    public NilaiMahasiswa() {
    }

    public String getNIM() {
        return NIM;
    }

    public void setNIM(String NIM) {
        this.NIM = NIM;
    }

    public String getKodeMataKuliah() {
        return KodeMataKuliah;
    }

    public void setKodeMataKuliah(String KodeMataKuliah) {
        this.KodeMataKuliah = KodeMataKuliah;
    }

    public int getNilai() {
        return Nilai;
    }

    public void setNilai(int Nilai) {
        this.Nilai = Nilai;
    }

    public static ArrayList<NilaiMahasiswa> getDaftarNilaiMatakuliah(String NIM) {
        ArrayList<NilaiMahasiswa> nilaiList = new ArrayList<>();

        try {
            connection = DatabaseConnection.getConnection();
            String customQuery = "SELECT matakuliah.NamaMataKuliah, nilaimahasiswa.Nilai " +
                                "FROM matakuliah " +
                                "INNER JOIN nilaimahasiswa ON matakuliah.KodeMataKuliah = nilaimahasiswa.KodeMataKuliah " +
                                "WHERE nilaimahasiswa.NIM = ?";
            statement = connection.prepareStatement(customQuery);
            statement.setString(1, NIM);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String namaMataKuliah = resultSet.getString("NamaMataKuliah");
                int nilai = resultSet.getInt("Nilai");

                NilaiMahasiswa nilaiMahasiswa = new NilaiMahasiswa(NIM, namaMataKuliah, nilai);
                nilaiList.add(nilaiMahasiswa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return nilaiList;
    }
}
