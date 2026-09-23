package com.tap.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.tap.DAO.OrderItemDAO;
import com.tap.DAO.OrdersDAO;
import com.tap.DAO.UserDAO;

import com.tap.DAOImpl.OrderItemDAOImpl;
import com.tap.DAOImpl.OrdersDAOImpl;
import com.tap.DAOImpl.UserDAOImpl;

import com.tap.model.CartItem;
import com.tap.model.OrderItem;
import com.tap.model.Orders;
import com.tap.model.User;

import com.tap.util.DBConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/placeOrder")
public class PlaceOrderServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private OrdersDAO ordersDAO;
    private OrderItemDAO orderItemDAO;
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {

        ordersDAO = new OrdersDAOImpl();
        orderItemDAO = new OrderItemDAOImpl();
        userDAO = new UserDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        // =========================================
        // GET EXISTING SESSION
        // =========================================

        HttpSession session = request.getSession(false);

        if (session == null) {

            response.sendRedirect(
                    request.getContextPath() + "/login.html"
            );

            return;
        }

        // =========================================
        // GET USER ID FROM SESSION
        // =========================================

        Integer userId =
                (Integer) session.getAttribute("userId");

        // =========================================
        // FALLBACK 1 - LOGGED IN USER
        // =========================================

        if (userId == null) {

            User loggedInUser =
                    (User) session.getAttribute("loggedInUser");

            if (loggedInUser != null) {

                userId = loggedInUser.getId();

                session.setAttribute(
                        "userId",
                        userId
                );
            }
        }

        // =========================================
        // FALLBACK 2 - USER EMAIL
        // =========================================

        if (userId == null) {

            String userEmail =
                    (String) session.getAttribute("userEmail");

            if (userEmail != null &&
                !userEmail.trim().isEmpty()) {

                User user =
                        userDAO.getUserByEmail(userEmail);

                if (user != null) {

                    userId = user.getId();

                    session.setAttribute(
                            "userId",
                            userId
                    );

                    session.setAttribute(
                            "loggedInUser",
                            user
                    );

                    System.out.println(
                            "USER ID RECOVERED USING EMAIL: "
                            + userId
                    );
                }
            }
        }

        // =========================================
        // FINAL LOGIN CHECK
        // =========================================

        if (userId == null) {

            System.out.println(
                    "USER ID NOT FOUND - REDIRECTING TO LOGIN"
            );

            response.sendRedirect(
                    request.getContextPath() + "/login.html"
            );

            return;
        }

        // =========================================
        // DEBUG INFORMATION
        // =========================================

        System.out.println("=================================");
        System.out.println("PLACE ORDER SERVLET");
        System.out.println("SESSION ID : " + session.getId());
        System.out.println("USER ID    : " + userId);
        System.out.println("USER EMAIL : " + session.getAttribute("userEmail"));
        System.out.println("LOGGED USER: " + session.getAttribute("loggedInUser"));
        System.out.println("=================================");

        // =========================================
        // GET CART
        // =========================================

        List<CartItem> cart =
                (List<CartItem>) session.getAttribute("cart");

        if (cart == null || cart.isEmpty()) {

            response.setContentType("text/html");

            PrintWriter out =
                    response.getWriter();

            out.println("<h2>Your cart is empty!</h2>");

            out.println(
                    "<a href='" +
                    request.getContextPath() +
                    "/home.html'>Continue Shopping</a>"
            );

            return;
        }

        // =========================================
        // GET PAYMENT METHOD
        // =========================================

        String paymentMethod =
                request.getParameter("paymentMethod");

        if (paymentMethod == null ||
            paymentMethod.trim().isEmpty()) {

            paymentMethod = "Cash on Delivery";
        }

        // =========================================
        // CALCULATE TOTAL
        // =========================================

        double totalAmount = 0;

        for (CartItem item : cart) {

            totalAmount =
                    totalAmount + item.getTotal();
        }

        // =========================================
        // GET RESTAURANT ID
        // =========================================

        int restaurantId = 0;

        try {

            String query =
                    "SELECT restaurant_id "
                    + "FROM menu "
                    + "WHERE menu_id = ?";

            java.sql.Connection connection =
                    DBConnection.getConnection();

            java.sql.PreparedStatement pstmt =
                    connection.prepareStatement(query);

            pstmt.setInt(
                    1,
                    cart.get(0).getMenuId()
            );

            java.sql.ResultSet rs =
                    pstmt.executeQuery();

            if (rs.next()) {

                restaurantId =
                        rs.getInt("restaurant_id");
            }

            rs.close();
            pstmt.close();
            connection.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

        // =========================================
        // CHECK RESTAURANT
        // =========================================

        if (restaurantId == 0) {

            response.setContentType("text/html");

            PrintWriter out =
                    response.getWriter();

            out.println(
                    "<h2>Restaurant not found.</h2>"
            );

            out.println(
                    "<a href='" +
                    request.getContextPath() +
                    "/cart'>Back to Cart</a>"
            );

            return;
        }

        // =========================================
        // CREATE ORDER
        // =========================================

        Orders orders =
                new Orders(
                        userId,
                        restaurantId,
                        totalAmount,
                        "PLACED",
                        paymentMethod
                );

        // =========================================
        // INSERT ORDER
        // =========================================

        int orderId =
                ordersDAO.addOrders(orders);

        // =========================================
        // CHECK ORDER INSERTION
        // =========================================

        if (orderId == 0) {

            response.setContentType("text/html");

            PrintWriter out =
                    response.getWriter();

            out.println(
                    "<h2>Order could not be placed.</h2>"
            );

            out.println(
                    "<p>Please try again.</p>"
            );

            out.println(
                    "<a href='" +
                    request.getContextPath() +
                    "/cart'>Back to Cart</a>"
            );

            return;
        }

        // =========================================
        // INSERT ORDER ITEMS
        // =========================================

        for (CartItem item : cart) {

            OrderItem orderItem =
                    new OrderItem(
                            orderId,
                            item.getMenuId(),
                            item.getQuantity(),
                            item.getTotal()
                    );

            orderItemDAO.addOrderItem(orderItem);
        }

        // =========================================
        // CLEAR CART
        // =========================================

        session.removeAttribute("cart");

        // =========================================
        // SAVE LAST ORDER ID
        // =========================================

        session.setAttribute(
                "lastOrderId",
                orderId
        );

        // =========================================
        // SUCCESS PAGE
        // =========================================

        response.setContentType(
                "text/html;charset=UTF-8"
        );

        PrintWriter out =
                response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");

        out.println("<meta charset='UTF-8'>");

        out.println(
                "<title>Order Successful</title>"
        );

        out.println("<style>");

        out.println("* {");
        out.println("box-sizing: border-box;");
        out.println("}");

        out.println("body {");
        out.println("margin: 0;");
        out.println("font-family: Arial, sans-serif;");
        out.println(
                "background: linear-gradient(135deg, #fff1eb, #ffe4f0, #eee5ff);"
        );
        out.println("min-height: 100vh;");
        out.println("display: flex;");
        out.println("align-items: center;");
        out.println("justify-content: center;");
        out.println("}");

        out.println(".success-box {");
        out.println("background: white;");
        out.println("padding: 50px;");
        out.println("border-radius: 25px;");
        out.println("text-align: center;");
        out.println(
                "box-shadow: 0 15px 40px rgba(0,0,0,0.15);"
        );
        out.println("width: 450px;");
        out.println("}");

        out.println(".success {");
        out.println("font-size: 60px;");
        out.println("color: #16a34a;");
        out.println("}");

        out.println("h1 {");
        out.println("color: #16a34a;");
        out.println("}");

        out.println(".order-id {");
        out.println("font-size: 20px;");
        out.println("font-weight: bold;");
        out.println("color: #555;");
        out.println("margin: 15px;");
        out.println("}");

        out.println(".amount {");
        out.println("font-size: 28px;");
        out.println("font-weight: bold;");
        out.println("color: #dd2476;");
        out.println("margin: 20px;");
        out.println("}");

        out.println(".payment {");
        out.println("color: #555;");
        out.println("}");

        out.println(".button {");
        out.println("display: inline-block;");
        out.println("margin-top: 25px;");
        out.println("padding: 14px 25px;");
        out.println(
                "background: linear-gradient(135deg, #ff512f, #dd2476);"
        );
        out.println("color: white;");
        out.println("text-decoration: none;");
        out.println("border-radius: 25px;");
        out.println("font-weight: bold;");
        out.println("margin-right: 10px;");
        out.println("}");

        out.println("</style>");

        out.println("</head>");

        out.println("<body>");

        out.println("<div class='success-box'>");

        out.println(
                "<div class='success'>✓</div>"
        );

        out.println(
                "<h1>Order Placed Successfully!</h1>"
        );

        out.println(
                "<p>Your order has been received.</p>"
        );

        out.println("<div class='order-id'>");

        out.println(
                "Order ID: #" + orderId
        );

        out.println("</div>");

        out.println("<div class='amount'>");

        out.println(
                "₹" + String.format("%.2f", totalAmount)
        );

        out.println("</div>");

        out.println("<p class='payment'>");

        out.println(
                "Payment Method: " + paymentMethod
        );

        out.println("</p>");

        out.println(
                "<a class='button' href='" +
                request.getContextPath() +
                "/orderHistory'>"
        );

        out.println("View My Orders");

        out.println("</a>");

        out.println(
                "<a class='button' href='" +
                request.getContextPath() +
                "/home.html'>"
        );

        out.println("Continue Shopping");

        out.println("</a>");

        out.println("</div>");

        out.println("</body>");

        out.println("</html>");
    }
}