package com.rio.controller;

import com.rio.model.MataKuliah;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.net.URLEncoder;

@WebServlet(name = "LihatMatkulServlet", urlPatterns = {"/LihatMatkulServlet"})
public class LihatMatkulServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Ambil parameter kodeMatkul dari form
        String kodeMatkul = request.getParameter("kodeMatkul");

        
        MataKuliah mataKuliah = MataKuliah.LihatMatkul(kodeMatkul);

        if (mataKuliah != null) {

            // Buat cookie
            String matkulInfo = kodeMatkul + "," + mataKuliah.getNamaMataKuliah() + "," + mataKuliah.getSks();
            String encodedMatkulInfo = URLEncoder.encode(matkulInfo, "UTF-8");
            Cookie matkulCookie = new Cookie("matkulInfo", encodedMatkulInfo);

            // Tambahkan cookie ke respons
            response.addCookie(matkulCookie);

            // Mengarahkan pengguna ke halaman data-matkul.jsp
        }
        response.sendRedirect(request.getContextPath() + "/pages/data-matkul.jsp");
    }
}
