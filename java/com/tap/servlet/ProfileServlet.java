package com.tap.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import com.tap.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("loggedInUser") == null) {
            response.sendRedirect("login.html");
            return;
        }

        User user = (User) session.getAttribute("loggedInUser");

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");

        out.println("<title>My Profile - Food Delivery</title>");

        out.println("<style>");

        out.println("* {");
        out.println("box-sizing: border-box;");
        out.println("}");

        out.println("body {");
        out.println("margin: 0;");
        out.println("font-family: Arial, sans-serif;");
        out.println("background: linear-gradient(135deg, #fff1eb, #ffe0f0, #e8e0ff);");
        out.println("min-height: 100vh;");
        out.println("}");

        /* NAVBAR */

        out.println(".navbar {");
        out.println("height: 70px;");
        out.println("background: linear-gradient(90deg, #ff512f, #dd2476);");
        out.println("display: flex;");
        out.println("align-items: center;");
        out.println("justify-content: space-between;");
        out.println("padding: 0 60px;");
        out.println("color: white;");
        out.println("box-shadow: 0 5px 20px rgba(0,0,0,0.15);");
        out.println("}");

        out.println(".logo {");
        out.println("font-size: 25px;");
        out.println("font-weight: bold;");
        out.println("}");

        out.println(".nav-links {");
        out.println("display: flex;");
        out.println("gap: 30px;");
        out.println("}");

        out.println(".nav-links a {");
        out.println("color: white;");
        out.println("text-decoration: none;");
        out.println("font-weight: bold;");
        out.println("font-size: 15px;");
        out.println("}");

        out.println(".nav-links a:hover {");
        out.println("color: #ffe082;");
        out.println("}");

        /* PROFILE CONTAINER */

        out.println(".container {");
        out.println("width: 90%;");
        out.println("max-width: 900px;");
        out.println("margin: 50px auto;");
        out.println("}");

        out.println(".profile-card {");
        out.println("background: white;");
        out.println("border-radius: 25px;");
        out.println("overflow: hidden;");
        out.println("box-shadow: 0 20px 50px rgba(0,0,0,0.15);");
        out.println("}");

        /* HEADER */

        out.println(".profile-header {");
        out.println("background: linear-gradient(135deg, #ff512f, #dd2476, #7b2ff7);");
        out.println("padding: 40px;");
        out.println("text-align: center;");
        out.println("color: white;");
        out.println("}");

        out.println(".profile-icon {");
        out.println("width: 110px;");
        out.println("height: 110px;");
        out.println("margin: 0 auto 15px;");
        out.println("border-radius: 50%;");
        out.println("background: white;");
        out.println("display: flex;");
        out.println("align-items: center;");
        out.println("justify-content: center;");
        out.println("font-size: 55px;");
        out.println("box-shadow: 0 8px 25px rgba(0,0,0,0.2);");
        out.println("}");

        out.println(".profile-header h1 {");
        out.println("margin: 10px 0 5px;");
        out.println("font-size: 32px;");
        out.println("}");

        out.println(".profile-header p {");
        out.println("margin: 0;");
        out.println("opacity: 0.9;");
        out.println("}");

        /* DETAILS */

        out.println(".details {");
        out.println("padding: 35px 45px;");
        out.println("}");

        out.println(".row {");
        out.println("display: flex;");
        out.println("align-items: center;");
        out.println("padding: 18px;");
        out.println("margin-bottom: 15px;");
        out.println("background: #fff7fa;");
        out.println("border-radius: 15px;");
        out.println("border-left: 5px solid #dd2476;");
        out.println("}");

        out.println(".icon {");
        out.println("font-size: 25px;");
        out.println("width: 55px;");
        out.println("}");

        out.println(".info {");
        out.println("flex: 1;");
        out.println("}");

        out.println(".label {");
        out.println("font-size: 13px;");
        out.println("color: #888;");
        out.println("margin-bottom: 5px;");
        out.println("}");

        out.println(".value {");
        out.println("font-size: 16px;");
        out.println("font-weight: bold;");
        out.println("color: #333;");
        out.println("word-break: break-word;");
        out.println("}");

        /* BUTTON */

        out.println(".back-btn {");
        out.println("display: block;");
        out.println("width: 220px;");
        out.println("margin: 10px auto 0;");
        out.println("padding: 14px;");
        out.println("text-align: center;");
        out.println("background: linear-gradient(135deg, #ff512f, #dd2476);");
        out.println("color: white;");
        out.println("text-decoration: none;");
        out.println("border-radius: 30px;");
        out.println("font-weight: bold;");
        out.println("transition: 0.3s;");
        out.println("}");

        out.println(".back-btn:hover {");
        out.println("transform: translateY(-3px);");
        out.println("box-shadow: 0 8px 20px rgba(221,36,118,0.3);");
        out.println("}");

        /* FOOTER */

        out.println(".footer {");
        out.println("text-align: center;");
        out.println("padding: 25px;");
        out.println("color: #777;");
        out.println("font-size: 14px;");
        out.println("}");

        out.println("</style>");

        out.println("</head>");

        out.println("<body>");

        /* NAVBAR */

        out.println("<div class='navbar'>");

        out.println("<div class='logo'>🍴 FoodieExpress</div>");

        out.println("<div class='nav-links'>");

        out.println("<a href='home.html'>Home</a>");
        out.println("<a href='profile'>Profile</a>");
        out.println("<a href='cart.html'>Cart</a>");
        out.println("<a href='order-history.html'>Orders</a>");
        out.println("<a href='logout'>Logout</a>");

        out.println("</div>");

        out.println("</div>");

        /* PROFILE */

        out.println("<div class='container'>");

        out.println("<div class='profile-card'>");

        out.println("<div class='profile-header'>");

        out.println("<div class='profile-icon'>👤</div>");

        out.println("<h1>My Profile</h1>");

        out.println("<p>Welcome to your FoodieExpress account</p>");

        out.println("</div>");

        out.println("<div class='details'>");

        /* NAME */

        out.println("<div class='row'>");

        out.println("<div class='icon'>👤</div>");

        out.println("<div class='info'>");

        out.println("<div class='label'>FULL NAME</div>");

        out.println("<div class='value'>" + user.getName() + "</div>");

        out.println("</div>");

        out.println("</div>");

        /* EMAIL */

        out.println("<div class='row'>");

        out.println("<div class='icon'>📧</div>");

        out.println("<div class='info'>");

        out.println("<div class='label'>EMAIL ADDRESS</div>");

        out.println("<div class='value'>" + user.getEmail() + "</div>");

        out.println("</div>");

        out.println("</div>");

        /* PHONE */

        out.println("<div class='row'>");

        out.println("<div class='icon'>📱</div>");

        out.println("<div class='info'>");

        out.println("<div class='label'>PHONE NUMBER</div>");

        out.println("<div class='value'>" + user.getPhone() + "</div>");

        out.println("</div>");

        out.println("</div>");

        /* ADDRESS */

        out.println("<div class='row'>");

        out.println("<div class='icon'>📍</div>");

        out.println("<div class='info'>");

        out.println("<div class='label'>DELIVERY ADDRESS</div>");

        out.println("<div class='value'>" + user.getAdress() + "</div>");

        out.println("</div>");

        out.println("</div>");

        /* ROLE */

        out.println("<div class='row'>");

        out.println("<div class='icon'>⭐</div>");

        out.println("<div class='info'>");

        out.println("<div class='label'>ACCOUNT TYPE</div>");

        out.println("<div class='value'>" + user.getRole() + "</div>");

        out.println("</div>");

        out.println("</div>");

        out.println("<a class='back-btn' href='home.html'>← Back to Home</a>");

        out.println("</div>");

        out.println("</div>");

        out.println("</div>");

        out.println("<div class='footer'>");

        out.println("🍕 Delicious food, delivered with love ❤️");

        out.println("</div>");

        out.println("</body>");

        out.println("</html>");
    }
}