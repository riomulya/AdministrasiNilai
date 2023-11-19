package com.rio.controller;

import com.rio.model.MataKuliah;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "SimpanMatkulServlet", urlPatterns = {"/SimpanMatkulServlet"})
public class SimpanMatkulServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Ambil data dari form
        String kodeMatkul = request.getParameter("kodeMatkul");
        String namaMatkul = request.getParameter("namaMatkul");
        int sks = Integer.parseInt(request.getParameter("sks"));

        String pesanSimpanMatkul = MataKuliah.InsertOrUpdateMatkul(kodeMatkul, namaMatkul, sks);
        request.setAttribute("pesanSimpanMatkul", pesanSimpanMatkul);
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("matkulInfo")) {
                    // Set max age menjadi 0 untuk menghapus cookie
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }
        response.sendRedirect(request.getContextPath() + "/pages/data-matkul.jsp");
    }
}
