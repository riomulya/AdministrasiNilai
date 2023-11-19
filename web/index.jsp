<jsp:include page="components/head.jsp" />


    <link rel="stylesheet" href="styles/styles.css">
    <link rel="stylesheet" href="styles/login.css">

  </head>

  <body>
  <%   
    String NIM = (String) session.getAttribute("NIM");
    
    if (NIM != null) {
        response.sendRedirect(request.getContextPath() + "/" + "pages/home.jsp");
    }
  %>
<jsp:include page="components/nav.jsp" />

    <main class="main">
    <jsp:include page="pages/login.jsp" />
    </main>
        <jsp:include page="components/footer.jsp" />
  </body>

  </html>