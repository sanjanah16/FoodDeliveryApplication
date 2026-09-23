
package com.tap.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.tap.DAO.OrdersDAO;
import com.tap.DAOImpl.OrdersDAOImpl;
import com.tap.model.Orders;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/orderHistory")
public class OrderHistoryServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private OrdersDAO ordersDAO;

    @Override
    public void init() throws ServletException {

        ordersDAO = new OrdersDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();

        // Get logged-in user ID
        Integer userId =
                (Integer) session.getAttribute("userId");

        // If user is not logged in
        if (userId == null) {

            response.sendRedirect("login.html");

            return;
        }

        // Get all orders of logged-in user
        List<Orders> orders =
                ordersDAO.getOrdersByUserId(userId);

        // HTML START
        out.println("<!DOCTYPE html>");

        out.println("<html>");

        out.println("<head>");

        out.println("<meta charset='UTF-8'>");

        out.println("<title>My Orders</title>");

        out.println("<style>");

        out.println("* {");
        out.println("box-sizing: border-box;");
        out.println("}");

        out.println("body {");
        out.println("margin: 0;");
        out.println("font-family: Arial, sans-serif;");
        out.println("background: linear-gradient(135deg, #fff1eb, #ffe4f0, #eee5ff);");
        out.println("min-height: 100vh;");
        out.println("}");

        /* NAVBAR */

        out.println(".navbar {");
        out.println("background: linear-gradient(135deg, #ff512f, #dd2476);");
        out.println("padding: 18px 50px;");
        out.println("display: flex;");
        out.println("justify-content: space-between;");
        out.println("align-items: center;");
        out.println("}");

        out.println(".logo {");
        out.println("color: white;");
        out.println("font-size: 24px;");
        out.println("font-weight: bold;");
        out.println("}");

        out.println(".nav-links {");
        out.println("display: flex;");
        out.println("gap: 25px;");
        out.println("}");

        out.println(".nav-links a {");
        out.println("color: white;");
        out.println("text-decoration: none;");
        out.println("font-weight: bold;");
        out.println("}");

        out.println(".nav-links a:hover {");
        out.println("text-decoration: underline;");
        out.println("}");

        /* CONTAINER */

        out.println(".container {");
        out.println("width: 90%;");
        out.println("max-width: 1100px;");
        out.println("margin: 40px auto;");
        out.println("}");

        out.println("h1 {");
        out.println("text-align: center;");
        out.println("color: #333;");
        out.println("margin-bottom: 10px;");
        out.println("}");

        out.println(".subtitle {");
        out.println("text-align: center;");
        out.println("color: #666;");
        out.println("margin-bottom: 35px;");
        out.println("}");

        /* ORDER CARD */

        out.println(".order-card {");
        out.println("background: white;");
        out.println("border-radius: 20px;");
        out.println("padding: 25px;");
        out.println("margin-bottom: 25px;");
        out.println("box-shadow: 0 10px 30px rgba(0,0,0,0.12);");
        out.println("}");

        out.println(".order-header {");
        out.println("display: flex;");
        out.println("justify-content: space-between;");
        out.println("align-items: center;");
        out.println("border-bottom: 1px solid #eee;");
        out.println("padding-bottom: 15px;");
        out.println("margin-bottom: 20px;");
        out.println("}");

        out.println(".order-id {");
        out.println("font-size: 20px;");
        out.println("font-weight: bold;");
        out.println("color: #dd2476;");
        out.println("}");

        out.println(".status {");
        out.println("background: #dcfce7;");
        out.println("color: #15803d;");
        out.println("padding: 8px 16px;");
        out.println("border-radius: 20px;");
        out.println("font-weight: bold;");
        out.println("}");

        out.println(".details {");
        out.println("display: grid;");
        out.println("grid-template-columns: repeat(2, 1fr);");
        out.println("gap: 15px;");
        out.println("}");

        out.println(".detail {");
        out.println("background: #fff7f5;");
        out.println("padding: 15px;");
        out.println("border-radius: 12px;");
        out.println("}");

        out.println(".label {");
        out.println("font-size: 13px;");
        out.println("color: #777;");
        out.println("margin-bottom: 5px;");
        out.println("}");

        out.println(".value {");
        out.println("font-size: 17px;");
        out.println("font-weight: bold;");
        out.println("color: #333;");
        out.println("}");

        out.println(".total {");
        out.println("font-size: 24px;");
        out.println("color: #dd2476;");
        out.println("}");

        /* EMPTY ORDERS */

        out.println(".empty {");
        out.println("background: white;");
        out.println("padding: 50px;");
        out.println("border-radius: 20px;");
        out.println("text-align: center;");
        out.println("box-shadow: 0 10px 30px rgba(0,0,0,0.1);");
        out.println("}");

        out.println(".empty-icon {");
        out.println("font-size: 60px;");
        out.println("}");

        out.println(".button {");
        out.println("display: inline-block;");
        out.println("margin-top: 20px;");
        out.println("padding: 13px 25px;");
        out.println("background: linear-gradient(135deg, #ff512f, #dd2476);");
        out.println("color: white;");
        out.println("text-decoration: none;");
        out.println("border-radius: 25px;");
        out.println("font-weight: bold;");
        out.println("}");

        /* MOBILE */

        out.println("@media(max-width:600px) {");

        out.println(".navbar {");
        out.println("padding: 15px;");
        out.println("}");

        out.println(".nav-links {");
        out.println("gap: 10px;");
        out.println("}");

        out.println(".details {");
        out.println("grid-template-columns: 1fr;");
        out.println("}");

        out.println(".order-header {");
        out.println("flex-direction: column;");
        out.println("gap: 10px;");
        out.println("align-items: flex-start;");
        out.println("}");

        out.println("}");

        out.println("</style>");

        out.println("</head>");

        out.println("<body>");

        // NAVBAR

        out.println("<div class='navbar'>");

        out.println("<div class='logo'>FoodieExpress</div>");

        out.println("<div class='nav-links'>");

        out.println("<a href='home.html'>Home</a>");

        out.println("<a href='profile'>Profile</a>");

        out.println("<a href='cart'>Cart</a>");

        out.println("<a href='orderHistory'>Orders</a>");

        out.println("<a href='logout'>Logout</a>");

        out.println("</div>");

        out.println("</div>");

        // MAIN CONTAINER

        out.println("<div class='container'>");

        out.println("<h1>My Orders</h1>");

        out.println("<p class='subtitle'>Here are all your previous orders</p>");

        // CHECK ORDERS

        if (orders == null || orders.isEmpty()) {

            out.println("<div class='empty'>");

            out.println("<div class='empty-icon'>🍽️</div>");

            out.println("<h2>No Orders Yet</h2>");

            out.println("<p>You haven't placed any orders yet.</p>");

            out.println("<a class='button' href='home.html'>");

            out.println("Start Ordering");

            out.println("</a>");

            out.println("</div>");

        } else {

            // DISPLAY ALL ORDERS

            for (Orders order : orders) {

                out.println("<div class='order-card'>");

                out.println("<div class='order-header'>");

                out.println("<div class='order-id'>");

                out.println("Order #" + order.getOrderId());

                out.println("</div>");

                out.println("<div class='status'>");

                out.println(order.getStatus());

                out.println("</div>");

                out.println("</div>");

                out.println("<div class='details'>");

                // RESTAURANT

                out.println("<div class='detail'>");

                out.println("<div class='label'>Restaurant ID</div>");

                out.println("<div class='value'>");

                out.println(order.getRestaurantId());

                out.println("</div>");

                out.println("</div>");

                // DATE

                out.println("<div class='detail'>");

                out.println("<div class='label'>Order Date</div>");

                out.println("<div class='value'>");

                out.println(order.getOrderDate());

                out.println("</div>");

                out.println("</div>");

                // PAYMENT

                out.println("<div class='detail'>");

                out.println("<div class='label'>Payment Method</div>");

                out.println("<div class='value'>");

                out.println(order.getPaymentMethod());

                out.println("</div>");

                out.println("</div>");

                // TOTAL

                out.println("<div class='detail'>");

                out.println("<div class='label'>Total Amount</div>");

                out.println("<div class='value total'>");

                out.println("₹" + String.format("%.2f",
                        order.getTotalAmount()));

                out.println("</div>");

                out.println("</div>");

                out.println("</div>");

                out.println("</div>");
            }
        }

        out.println("</div>");

        out.println("</body>");

        out.println("</html>");
    }
}

