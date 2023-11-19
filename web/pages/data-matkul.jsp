<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.net.URLDecoder" %>

<jsp:include page="../components/head.jsp" />

    <link rel="stylesheet" href="../styles/styles.css">
    <link rel="stylesheet" href="../styles/form.css">
    <link rel="stylesheet" href="../styles/select.css">

  </head>

  <body>
<jsp:include page="../components/nav.jsp" />
<%
Cookie[] cookies = request.getCookies();
String kodeMatkul = null;
String namaMatkul = null;
String sks = null;
if (cookies != null) {
 for (Cookie cookie : cookies) {
    if (cookie.getName().equals("matkulInfo")) {
        String encodedMatkulInfo = cookie.getValue();
        String matkulInfo = URLDecoder.decode(encodedMatkulInfo, "UTF-8");
        String[] matkulDetails = matkulInfo.split(",");
        kodeMatkul = matkulDetails[0];
        namaMatkul = matkulDetails[1];
        sks = matkulDetails[2];
    }
 }
}
%>


    <main class="main">
    <div class="login-box">
    <h2>Master Data Matakuliah</h2>
        <form method="POST">
            <div class="user-box">
            <input type="text" name="kodeMatkul" value="<%= kodeMatkul != null ? kodeMatkul : ""  %>" required>
            <label>Kode Mata Kuliah</label>
            </div>
            <div class="user-box">
            <input type="text" name="namaMatkul" value="<%= namaMatkul != null ? namaMatkul : "" %>" required>
            <label>Nama Mata Kuliah</label>
            </div>
            <label for="SKS">Jumlah SKS</label>

            <select name="SKS" id="SKS">
    <option value="1" <%= sks != null && sks.equals("1") ? "selected" : "" %> >1</option>
    <option value="2" <%= sks != null && sks.equals("2") ? "selected" : "" %> >2</option>
    <option value="3" <%= sks != null && sks.equals("3") ? "selected" : "" %> >3</option>
    <option value="4" <%= sks != null && sks.equals("4") ? "selected" : "" %> >4</option>
            </select>


              
            <div id="btn-controller">   
            <a href="javascript:void(0);" onclick="simpanMatkul()">
            <span></span>
            <span></span>
            <span></span>
            <span></span>
            Simpan
            </a><a href="javascript:void(0);" onclick="hapusMatkul()">
            <span></span>
            <span></span>
            <span></span>
            <span></span>
            Hapus
            </a><a href="javascript:void(0);" onclick="lihatMatkul()">
            <span></span>
            <span></span>
            <span></span>
            <span></span>
            Lihat
            </a>
            </div>
        </form>
    </div>
    </main>

    <jsp:include page="../components/footer.jsp" />
  </body>
  <script>
  function hapusMatkul() {
        submitForm("HapusMatkulServlet");
    }
 function lihatMatkul() {
        submitForm("LihatMatkulServlet");
    }

    function simpanMatkul() {

    // Dapatkan nilai dari elemen select untuk SKS
    var sksSelect = document.getElementById("SKS");
    var sksValue = sksSelect ? sksSelect.value : null;
    console.log("SKS:", sksValue);

        var kodeMatkul = document.getElementsByName("kodeMatkul")[0].value;

        // Buat form dinamis
        var form = document.createElement("form");
        form.setAttribute("method", "POST");
        form.setAttribute("action", "<%= request.getContextPath() %>/SimpanMatkulServlet");

        // Tambahkan input untuk kodeMatkul, namaMatkul, dan sks
        var inputKodeMatkul = document.createElement("input");
        inputKodeMatkul.setAttribute("type", "hidden"); 
        inputKodeMatkul.setAttribute("name", "kodeMatkul");
        inputKodeMatkul.setAttribute("value", kodeMatkul);
        form.appendChild(inputKodeMatkul);

        var inputNamaMatkul = document.createElement("input");
        inputNamaMatkul.setAttribute("type", "hidden");
        inputNamaMatkul.setAttribute("name", "namaMatkul");
        inputNamaMatkul.setAttribute("value", document.getElementsByName("namaMatkul")[0].value);
        form.appendChild(inputNamaMatkul);

        var inputSks = document.createElement("input");
        inputSks.setAttribute("type", "hidden");
        inputSks.setAttribute("name", "sks");
        inputSks.setAttribute("value", sksValue);
        form.appendChild(inputSks);

        // Tambahkan form ke dalam body
        document.body.appendChild(form);

        // Submit form
        form.submit();
    }

    function submitForm(action) {
        // Dapatkan nilai input kodeMatkul
        var kodeMatkul = document.getElementsByName("kodeMatkul")[0].value;

        // Buat form dinamis
        var form = document.createElement("form");
        form.setAttribute("method", "POST");
        form.setAttribute("action", "<%= request.getContextPath() %>/"+action);

        // Tambahkan input untuk kodeMatkul dan action
        var inputKodeMatkul = document.createElement("input");
        inputKodeMatkul.setAttribute("type", "hidden");
        inputKodeMatkul.setAttribute("name", "kodeMatkul");
        inputKodeMatkul.setAttribute("value", kodeMatkul);
        form.appendChild(inputKodeMatkul);

        // Tambahkan form ke dalam body
        document.body.appendChild(form);

        // Submit form
        form.submit();
    }
</script>
  </html>
