
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


    <main class="main">
    <div class="login-box">
    <h2>Login</h2>
        <form action="LoginServlet" method="post">
            <div class="user-box">
            <input type="text" name="uName" required="">
            <label>Username</label>
            </div>
            <div class="user-box">
            <input type="password" name="pass" required="">
            <label>Password</label>
            </div>
            <button type="submit">
            <span></span>
            <span></span>
            <span></span>
            <span></span>
            Submit
            </button>
        </form>
    </div>
    </main>
