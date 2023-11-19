<%@ page import="java.util.ArrayList" %>
<%@ page import="com.rio.model.NilaiMahasiswa" %>
<%@ page import="com.rio.model.MataKuliah" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:include page="../components/head.jsp" />

    <link rel="stylesheet" href="../styles/styles.css">
    <link rel="stylesheet" href="../styles/home.css">

  </head>

  <body>
  <%   
    String namaMhs = (String) session.getAttribute("namaMhs");
    String nimMhs = (String) session.getAttribute("nimMhs");
    String alamatMhs = (String) session.getAttribute("alamatMhs");
    String jurusanMhs = (String) session.getAttribute("jurusanMhs");
    String kelasMhs = (String) session.getAttribute("kelasMhs");
    String semesterMhs = (String) session.getAttribute("semesterMhs");

  %>
<jsp:include page="../components/nav.jsp" />

<div class="dashboard">

	<div class="profile">
                    <% if(namaMhs != null){%>
                    <h2>Hallo <%= namaMhs %></h2>
                    <p>NIM : <%=nimMhs%></p>
                    <p>Kelas : <%=kelasMhs%></p>
                    <p>Semester : <%=semesterMhs%></p>
                    <p>Alamat : <%=alamatMhs%></p>
                    <p>Jurusan : <%=jurusanMhs%></p>
                    <%}%>
	</div>

	<div class="schedule-table">
            
		<h2>Mata Kuliah</h2>
		<table>
                        <tr>
				<th>Kode Matkul</th>
				<th>Nama Matkul</th>
				<th>SKS</th>
			</tr>
             <% 
            // Mendapatkan daftar mata kuliah dari sesi
            ArrayList<MataKuliah> daftarMataKuliah = (ArrayList<MataKuliah>) session.getAttribute("daftarMataKuliah");
            if (daftarMataKuliah != null && !daftarMataKuliah.isEmpty()) {
                for (MataKuliah mataKuliah : daftarMataKuliah) {
            %>
			
                    <tr>
                        <td><%= mataKuliah.getKodeMataKuliah() %></td>
                        <td><%= mataKuliah.getNamaMataKuliah() %></td>
                        <td><%= mataKuliah.getSks() %></td>
                    </tr>
                       <%
                }
            } else {
        %>
                <tr>
                    <td colspan="3">Tidak ada mata kuliah</td>
                </tr>
        <% } %>
		</table>
	</div>

	<div class="exercise-table">
		<h2>Daftar Nilai</h2>
		<table>
			<tr>
				<th>Mata Kuliah</th>
				<th>Nilai</th>
			</tr>
        <% 
            // Mendapatkan daftar nilai dari sesi
            ArrayList<NilaiMahasiswa> daftarNilai = (ArrayList<NilaiMahasiswa>) session.getAttribute("daftarNilai");
            if (daftarNilai != null && !daftarNilai.isEmpty()) {
                for (NilaiMahasiswa nilai : daftarNilai) {
        %>
			<tr>
                        <td><%= nilai.getKodeMataKuliah() %></td>
                        <td><%= nilai.getNilai() %></td>
                    </tr>
        <%   }
            } else {
        %>
                <tr>
                    <td colspan="2">Tidak ada nilai</td>
                </tr>
        <% } %>
		</table>
	</div>

</div>

    <jsp:include page="../components/footer.jsp" />
  </body>

  </html>