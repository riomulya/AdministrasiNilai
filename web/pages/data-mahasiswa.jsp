
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<jsp:include page="../components/head.jsp" />

    <link rel="stylesheet" href="../styles/styles.css">
    <link rel="stylesheet" href="../styles/form.css">
  </head>

  <body>
<jsp:include page="../components/nav.jsp" />

    <main class="main">
    <div class="login-box">
    <h2>Master Data Mahasiswa</h2>
        <form>
            <div class="user-box">
            <input type="text" name="" required="">
            <label>NIM</label>
            </div>
            <div class="user-box">
            <input type="text" name="" required="">
            <label>NAMA</label>
            </div>
            <div class="user-box">
            <input type="text" name="" required="">
            <label>KELAS</label>
            </div>
            <a href="#">
            <span></span>
            <span></span>
            <span></span>
            <span></span>
            Simpan
            </a><a href="#">
            <span></span>
            <span></span>
            <span></span>
            <span></span>
            Hapus
            </a><a href="#">
            <span></span>
            <span></span>
            <span></span>
            <span></span>
            Lihat
            </a>
        </form>
    </div>
    </main>

    <jsp:include page="../components/footer.jsp" />
  </body>

  </html>