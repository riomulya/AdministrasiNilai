    <section class="nav" id="nav">
        <h2>UNIVERSITAS PAMULANG</h2>
        <h3 class="span loader">
        <span class="m">I</span>
        <span class="m">N</span>
        <span class="m">F</span>
        <span class="m">O</span>
        <span class="m">R</span>
        <span class="m">M</span>
        <span class="m">A</span>
        <span class="m">S</span>
        <span class="m">I</span>
        <span class="m">&nbsp;</span>
        <span class="m">N</span>
        <span class="m">I</span>
        <span class="m">L</span>
        <span class="m">A</span>
        <span class="m">I</span>
        </h3>
        <div class="nav-container">
        
        <%   
        String NIM = (String) session.getAttribute("NIM");
        
        if (NIM == null) {
        %>
        <a class="nav-tab" href="<%= request.getContextPath() %>/index.jsp">LOGIN</a>
        <% }else{%>
        <a class="nav-tab" href="<%= request.getContextPath() %>/pages/home.jsp">HOME</a>
        <span class="dropdown nav-tab">
        <a class="nav-tab" href="#">MASTER DATA</a>
        <span class="dropdown-content">
            <a class="nav-tab" href="<%= request.getContextPath() %>/pages/data-mahasiswa.jsp">Mahasiswa</a>
            <a class="nav-tab" href="<%= request.getContextPath() %>/pages/data-matkul.jsp">Mata Kuliah</a>
        </span> 
        </span>
        <a class="nav-tab" href="<%= request.getContextPath() %>/pages/transaksi.jsp">TRANSAKSI</a>
        <a class="nav-tab" href="<%= request.getContextPath() %>/pages/laporan.jsp">LAPORAN</a>
        <a class="nav-tab" href="<%= request.getContextPath() %>/LogoutServlet">LOGOUT</a>
        <% }%>
        </div>
    </section>