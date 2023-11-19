package com.rio.controller;

import com.rio.model.Mahasiswa;
import com.rio.model.MataKuliah;
import com.rio.model.NilaiMahasiswa;
import com.rio.model.Users;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve parameters from the form
        String NIM = request.getParameter("uName");
        String password = request.getParameter("pass");

        if (Users.getUser(NIM, password)) {
            // Informasi login valid, simpan dalam sesi
            HttpSession session = request.getSession();
            Mahasiswa mhs = new Mahasiswa();
            mhs = Mahasiswa.getMhs(NIM);

            // Periksa apakah objek mhs tidak null sebelum mengambil atribut
            if (mhs != null) {
                session.setAttribute("NIM", NIM);
                session.setAttribute("namaMhs", mhs.getNamaMhs());
                session.setAttribute("nimMhs", mhs.getNimMhs());
                session.setAttribute("alamatMhs", mhs.getAlamatMhs());
                session.setAttribute("jurusanMhs", mhs.getJurusanMhs());
                session.setAttribute("kelasMhs", mhs.getKelasMhs());
                session.setAttribute("semesterMhs", mhs.getSemesterMhs());

                // Dapatkan daftar nilai dan daftar mata kuliah
                ArrayList<NilaiMahasiswa> daftarNilai = NilaiMahasiswa.getDaftarNilaiMatakuliah(mhs.getNimMhs());
                ArrayList<MataKuliah> daftarMataKuliah = MataKuliah.getDaftarMataKuliah(mhs.getNimMhs());

                // Simpan ke dalam sesi
                session.setAttribute("daftarNilai", daftarNilai);
                session.setAttribute("daftarMataKuliah", daftarMataKuliah);

                response.sendRedirect(request.getContextPath() + "/pages/home.jsp");
            } else {
                // Objek mhs null, berikan tanggapan yang sesuai
                response.getWriter().println("Mahasiswa tidak ditemukan");
                // Atau arahkan ke halaman yang sesuai dengan keadaan
                // response.sendRedirect(request.getContextPath() + "/index.jsp");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
    }
}
