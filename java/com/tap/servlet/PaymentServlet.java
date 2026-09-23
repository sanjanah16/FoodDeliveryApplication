
package com.tap.servlet;

import java.io.IOException;
import java.util.List;

import com.tap.DAO.MenuDAO;
import com.tap.DAO.OrderItemDAO;
import com.tap.DAO.OrdersDAO;
import com.tap.DAOImpl.MenuDAOImpl;
import com.tap.DAOImpl.OrderItemDAOImpl;
import com.tap.DAOImpl.OrdersDAOImpl;
import com.tap.model.CartItem;
import com.tap.model.Menu;
import com.tap.model.OrderItem;
import com.tap.model.Orders;
import com.tap.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "PaymentServlet", urlPatterns = {"/payment"})
public class PaymentServlet extends HttpServlet {

    private OrdersDAO ordersDAO;
    private OrderItemDAO orderItemDAO;
    private MenuDAO menuDAO;

    @Override
    public void init() throws ServletException {

        ordersDAO = new OrdersDAOImpl();
        orderItemDAO = new OrderItemDAOImpl();
        menuDAO = new MenuDAOImpl();

    }

    @Override
    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        // Get payment method
        String paymentMethod =
                request.getParameter("paymentMethod");

        // Get existing session
        HttpSession session =
                request.getSession(false);

        // Check session
        if (session == null) {

            response.setContentType("text/html");

            response.getWriter().println(
                "<h2 style='color:red;text-align:center;'>"
                + "Session Expired"
                + "</h2>"
            );

            response.getWriter().println(
                "<p style='text-align:center;'>"
                + "Please login again."
                + "</p>"
            );

            response.getWriter().println(
                "<p style='text-align:center;'>"
                + "<a href='login.html'>Login</a>"
                + "</p>"
            );

            return;
        }

        // Get logged-in user
        User user =
                (User) session.getAttribute("loggedInUser");

        // If complete user object is missing,
        // try using stored userId
        if (user == null) {

            Object userIdObject =
                    session.getAttribute("userId");

            if (userIdObject != null) {

                int userId =
                        (Integer) userIdObject;

                user =
                        userDAOGetUser(userId);
            }
        }

        // Check user again
        if (user == null) {

            response.setContentType("text/html");

            response.getWriter().println(
                "<h2 style='color:red;text-align:center;'>"
                + "User Session is NULL"
                + "</h2>"
            );

            response.getWriter().println(
                "<p style='text-align:center;'>"
                + "Please login again."
                + "</p>"
            );

            response.getWriter().println(
                "<p style='text-align:center;'>"
                + "<a href='login.html'>Login Again</a>"
                + "</p>"
            );

            return;
        }

        // Get cart
        List<CartItem> cart =
                (List<CartItem>) session.getAttribute("cart");

        // Check cart
        if (cart == null || cart.isEmpty()) {

            response.sendRedirect(
                response.encodeRedirectURL(
                    request.getContextPath()
                    + "/order-history"
                )
            );

            return;
        }

        // Calculate total amount
        double totalAmount = 0;

        for (CartItem item : cart) {

            totalAmount =
                    totalAmount + item.getTotal();
        }

        // Get first cart item
        CartItem firstItem =
                cart.get(0);

        // Get menu details
        Menu menu =
                menuDAO.getMenu(
                    firstItem.getMenuId()
                );

        if (menu == null) {

            response.setContentType("text/html");

            response.getWriter().println(
                "<h2 style='color:red;text-align:center;'>"
                + "Menu item not found."
                + "</h2>"
            );

            return;
        }

        // Get restaurant ID
        int restaurantId =
                menu.getRestaurantId();

        // Create Orders object
        Orders orders =
                new Orders(
                    0,
                    user.getId(),
                    restaurantId,
                    null,
                    totalAmount,
                    "PLACED",
                    paymentMethod
                );

        // Insert order
        int orderId =
                ordersDAO.addOrders(orders);

        // Check order insertion
        if (orderId == 0) {

            response.setContentType("text/html");

            response.getWriter().println(
                "<h2 style='color:red;text-align:center;'>"
                + "Order could not be placed."
                + "</h2>"
            );

            return;
        }

        // Insert every cart item into order_item
        for (CartItem item : cart) {

            double itemTotal =
                    item.getPrice()
                    * item.getQuantity();

            OrderItem orderItem =
                    new OrderItem(
                        0,
                        orderId,
                        item.getMenuId(),
                        item.getQuantity(),
                        itemTotal
                    );

            orderItemDAO.addOrderItem(orderItem);
        }

        // Order successfully placed
        // Clear cart
        session.removeAttribute("cart");

        System.out.println("ORDER PLACED SUCCESSFULLY");
        System.out.println("Order ID: " + orderId);
        System.out.println("User ID: " + user.getId());
        System.out.println("Total Amount: " + totalAmount);
        System.out.println("Payment Method: " + paymentMethod);

        // Go to Order History servlet
        response.sendRedirect(
            response.encodeRedirectURL(
                request.getContextPath()
                + "/order-history"
            )
        );
    }

    /*
     * This method gets the user again from database
     * using the user ID stored in the session.
     */
    private User userDAOGetUser(int userId) {

        com.tap.DAO.UserDAO userDAO =
                new com.tap.DAOImpl.UserDAOImpl();

        return userDAO.getUser(userId);
    }
}
