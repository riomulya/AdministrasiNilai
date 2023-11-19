package com.rio.controller;

import com.rio.model.MataKuliah;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(name = "HapusMatkulServlet", urlPatterns = {"/HapusMatkulServlet"})
public class HapusMatkulServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Ambil data dari form
        String kodeMatkul = request.getParameter("kodeMatkul");

        MataKuliah.HapusMatkul(kodeMatkul);
        
        // Redirect kembali ke halaman data-matkul.jsp atau tampilkan pesan sukses
        response.sendRedirect(request.getContextPath()+"/pages/data-matkul.jsp");
    }
}