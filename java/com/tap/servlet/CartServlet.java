
package com.tap.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.tap.model.CartItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        List<CartItem> cart =
                (List<CartItem>) session.getAttribute("cart");

        if (cart == null) {

            cart = new ArrayList<>();

            session.setAttribute("cart", cart);
        }

        /* CALCULATE TOTAL CART QUANTITY */

        int cartCount = 0;

        for (CartItem item : cart) {

            cartCount = cartCount + item.getQuantity();
        }

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");

        out.println("<title>My Cart - FoodieExpress</title>");

        out.println("<style>");

        /* =========================
           COMMON
           ========================= */

        out.println("* {");
        out.println("box-sizing: border-box;");
        out.println("}");

        out.println("body {");
        out.println("margin: 0;");
        out.println("font-family: Arial, Helvetica, sans-serif;");
        out.println("background: linear-gradient(135deg, #fff1eb, #ffe4f0, #eee5ff);");
        out.println("min-height: 100vh;");
        out.println("color: #333;");
        out.println("}");

        /* =========================
           NAVBAR
           ========================= */

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
        out.println("gap: 25px;");
        out.println("align-items: center;");
        out.println("}");

        out.println(".nav-links a {");
        out.println("color: white;");
        out.println("text-decoration: none;");
        out.println("font-weight: bold;");
        out.println("position: relative;");
        out.println("}");

        out.println(".nav-links a:hover {");
        out.println("color: #ffe082;");
        out.println("}");

        /* =========================
           CART BADGE
           ========================= */

        out.println(".cart-link {");
        out.println("display: flex;");
        out.println("align-items: center;");
        out.println("gap: 5px;");
        out.println("}");

        out.println(".cart-badge {");
        out.println("background: #16a34a;");
        out.println("color: white;");
        out.println("font-size: 12px;");
        out.println("font-weight: bold;");
        out.println("min-width: 21px;");
        out.println("height: 21px;");
        out.println("padding: 2px 6px;");
        out.println("border-radius: 50%;");
        out.println("display: inline-flex;");
        out.println("align-items: center;");
        out.println("justify-content: center;");
        out.println("box-shadow: 0 2px 8px rgba(0,0,0,0.2);");
        out.println("}");

        /* =========================
           CONTAINER
           ========================= */

        out.println(".container {");
        out.println("width: 90%;");
        out.println("max-width: 1100px;");
        out.println("margin: 45px auto;");
        out.println("}");

        /* =========================
           HEADING
           ========================= */

        out.println(".heading {");
        out.println("text-align: center;");
        out.println("margin-bottom: 35px;");
        out.println("}");

        out.println(".heading h1 {");
        out.println("font-size: 40px;");
        out.println("margin: 0;");
        out.println("background: linear-gradient(90deg, #ff512f, #dd2476, #7b2ff7);");
        out.println("-webkit-background-clip: text;");
        out.println("-webkit-text-fill-color: transparent;");
        out.println("}");

        out.println(".heading p {");
        out.println("color: #777;");
        out.println("font-size: 17px;");
        out.println("}");

        /* =========================
           CART CONTAINER
           ========================= */

        out.println(".cart-container {");
        out.println("background: white;");
        out.println("border-radius: 22px;");
        out.println("padding: 25px;");
        out.println("box-shadow: 0 12px 30px rgba(0,0,0,0.12);");
        out.println("}");

        /* =========================
           CART ITEM
           ========================= */

        out.println(".cart-item {");
        out.println("display: flex;");
        out.println("align-items: center;");
        out.println("gap: 20px;");
        out.println("padding: 20px;");
        out.println("border-bottom: 1px solid #eee;");
        out.println("}");

        out.println(".food-image {");
        out.println("width: 120px;");
        out.println("height: 100px;");
        out.println("object-fit: cover;");
        out.println("border-radius: 15px;");
        out.println("}");

        out.println(".item-details {");
        out.println("flex: 1;");
        out.println("}");

        out.println(".item-details h2 {");
        out.println("margin: 0 0 8px;");
        out.println("color: #dd2476;");
        out.println("}");

        out.println(".price {");
        out.println("color: #ff512f;");
        out.println("font-weight: bold;");
        out.println("font-size: 17px;");
        out.println("}");

        /* =========================
           QUANTITY
           ========================= */

        out.println(".quantity-box {");
        out.println("display: flex;");
        out.println("align-items: center;");
        out.println("gap: 10px;");
        out.println("margin-top: 12px;");
        out.println("}");

        out.println(".quantity-button {");
        out.println("width: 35px;");
        out.println("height: 35px;");
        out.println("border: none;");
        out.println("border-radius: 50%;");
        out.println("font-size: 20px;");
        out.println("font-weight: bold;");
        out.println("color: white;");
        out.println("cursor: pointer;");
        out.println("}");

        out.println(".minus {");
        out.println("background: #ff512f;");
        out.println("}");

        out.println(".plus {");
        out.println("background: #16a34a;");
        out.println("}");

        out.println(".quantity-number {");
        out.println("font-size: 18px;");
        out.println("font-weight: bold;");
        out.println("min-width: 25px;");
        out.println("text-align: center;");
        out.println("}");

        /* =========================
           ITEM TOTAL
           ========================= */

        out.println(".item-total {");
        out.println("font-weight: bold;");
        out.println("font-size: 19px;");
        out.println("color: #7b2ff7;");
        out.println("min-width: 100px;");
        out.println("text-align: right;");
        out.println("}");

        /* =========================
           REMOVE
           ========================= */

        out.println(".remove-form {");
        out.println("margin: 0;");
        out.println("}");

        out.println(".remove-button {");
        out.println("border: none;");
        out.println("background: #dc2626;");
        out.println("color: white;");
        out.println("padding: 9px 15px;");
        out.println("border-radius: 20px;");
        out.println("cursor: pointer;");
        out.println("font-weight: bold;");
        out.println("}");

        out.println(".remove-button:hover {");
        out.println("background: #b91c1c;");
        out.println("}");

        /* =========================
           TOTAL
           ========================= */

        out.println(".total-section {");
        out.println("text-align: right;");
        out.println("padding: 25px 10px 10px;");
        out.println("}");

        out.println(".total-section h2 {");
        out.println("margin-bottom: 5px;");
        out.println("}");

        out.println(".total-price {");
        out.println("font-size: 30px;");
        out.println("color: #ff512f;");
        out.println("font-weight: bold;");
        out.println("}");

        /* =========================
           BUTTONS
           ========================= */

        out.println(".buttons {");
        out.println("display: flex;");
        out.println("justify-content: center;");
        out.println("gap: 20px;");
        out.println("margin-top: 25px;");
        out.println("flex-wrap: wrap;");
        out.println("}");

        out.println(".button {");
        out.println("padding: 14px 25px;");
        out.println("border-radius: 30px;");
        out.println("text-decoration: none;");
        out.println("font-weight: bold;");
        out.println("color: white;");
        out.println("}");

        out.println(".continue {");
        out.println("background: linear-gradient(135deg, #7b2ff7, #dd2476);");
        out.println("}");

        out.println(".payment {");
        out.println("background: linear-gradient(135deg, #ff512f, #dd2476);");
        out.println("}");

        /* =========================
           EMPTY CART
           ========================= */

        out.println(".empty {");
        out.println("text-align: center;");
        out.println("padding: 60px 20px;");
        out.println("}");

        out.println(".empty h2 {");
        out.println("font-size: 30px;");
        out.println("color: #dd2476;");
        out.println("}");

        out.println(".empty p {");
        out.println("color: #777;");
        out.println("font-size: 17px;");
        out.println("}");

        /* =========================
           MOBILE
           ========================= */

        out.println("@media (max-width: 700px) {");

        out.println(".navbar {");
        out.println("padding: 0 20px;");
        out.println("}");

        out.println(".nav-links {");
        out.println("gap: 10px;");
        out.println("}");

        out.println(".nav-links a {");
        out.println("font-size: 12px;");
        out.println("}");

        out.println(".cart-item {");
        out.println("flex-direction: column;");
        out.println("text-align: center;");
        out.println("}");

        out.println(".item-total {");
        out.println("text-align: center;");
        out.println("}");

        out.println("}");

        out.println("</style>");

        out.println("</head>");

        out.println("<body>");

        /* =========================
           NAVBAR
           ========================= */

        out.println("<div class='navbar'>");

        out.println("<div class='logo'>FoodieExpress</div>");

        out.println("<div class='nav-links'>");

        out.println("<a href='home.html'>Home</a>");

        out.println("<a href='profile'>Profile</a>");

        /* CART WITH COUNT */

        out.println("<a href='cart' class='cart-link'>");

        out.println("Cart");

        out.println("<span class='cart-badge'>"
                + cartCount
                + "</span>");

        out.println("</a>");

        out.println("<a href='orderHistory'>Orders</a>");

        out.println("<a href='logout'>Logout</a>");

        out.println("</div>");

        out.println("</div>");

        /* =========================
           MAIN
           ========================= */

        out.println("<div class='container'>");

        out.println("<div class='heading'>");

        out.println("<h1>My Cart</h1>");

        out.println("<p>Review your favourite food before placing your order.</p>");

        out.println("</div>");

        /* =========================
           EMPTY CART
           ========================= */

        if (cart.isEmpty()) {

            out.println("<div class='cart-container'>");

            out.println("<div class='empty'>");

            out.println("<h2>Your Cart is Empty</h2>");

            out.println("<p>Add some delicious food from the menu!</p>");

            out.println("<div class='buttons'>");

            out.println("<a class='button continue' href='home.html'>");

            out.println("Continue Shopping");

            out.println("</a>");

            out.println("</div>");

            out.println("</div>");

            out.println("</div>");

        }

        /* =========================
           CART HAS ITEMS
           ========================= */

        else {

            double grandTotal = 0;

            out.println("<div class='cart-container'>");

            for (CartItem item : cart) {

                out.println("<div class='cart-item'>");

                out.println("<img src='"
                        + item.getImage()
                        + "' class='food-image'>");

                out.println("<div class='item-details'>");

                out.println("<h2>"
                        + item.getItemName()
                        + "</h2>");

                out.println("<div class='price'>₹"
                        + item.getPrice()
                        + " per item</div>");

                /* QUANTITY BUTTONS */

                out.println("<div class='quantity-box'>");

                /* MINUS */

                out.println("<form action='cart' method='post'>");

                out.println("<input type='hidden' name='action' value='decrease'>");

                out.println("<input type='hidden' name='menuId' value='"
                        + item.getMenuId()
                        + "'>");

                out.println("<button type='submit' class='quantity-button minus'>");

                out.println("-");

                out.println("</button>");

                out.println("</form>");

                /* QUANTITY */

                out.println("<span class='quantity-number'>");

                out.println(item.getQuantity());

                out.println("</span>");

                /* PLUS */

                out.println("<form action='cart' method='post'>");

                out.println("<input type='hidden' name='action' value='increase'>");

                out.println("<input type='hidden' name='menuId' value='"
                        + item.getMenuId()
                        + "'>");

                out.println("<button type='submit' class='quantity-button plus'>");

                out.println("+");

                out.println("</button>");

                out.println("</form>");

                out.println("</div>");

                out.println("</div>");

                /* ITEM TOTAL */

                out.println("<div class='item-total'>");

                out.println("₹" + item.getTotal());

                out.println("</div>");

                /* REMOVE */

                out.println("<form class='remove-form' action='cart' method='post'>");

                out.println("<input type='hidden' name='action' value='remove'>");

                out.println("<input type='hidden' name='menuId' value='"
                        + item.getMenuId()
                        + "'>");

                out.println("<button type='submit' class='remove-button'>");

                out.println("Remove");

                out.println("</button>");

                out.println("</form>");

                out.println("</div>");

                grandTotal = grandTotal + item.getTotal();
            }

            /* =========================
               GRAND TOTAL
               ========================= */

            out.println("<div class='total-section'>");

            out.println("<h2>Grand Total</h2>");

            out.println("<div class='total-price'>₹"
                    + grandTotal
                    + "</div>");

            /* ACTION BUTTONS */

            out.println("<div class='buttons'>");

            out.println("<a class='button continue' href='home.html'>");

            out.println("Continue Shopping");

            out.println("</a>");

            out.println("<a class='button payment' href='payment.html?total=" + grandTotal + "'>");

            out.println("Proceed to Payment");

            out.println("</a>");

            out.println("</div>");

            out.println("</div>");

            out.println("</div>");
        }

        out.println("</div>");

        out.println("</body>");

        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        HttpSession session = request.getSession();

        List<CartItem> cart =
                (List<CartItem>) session.getAttribute("cart");

        if (cart == null) {

            cart = new ArrayList<>();

            session.setAttribute("cart", cart);
        }

        /* =========================
           ADD TO CART
           ========================= */

        if (action == null) {

            int menuId = Integer.parseInt(
                    request.getParameter("menuId"));

            String itemName =
                    request.getParameter("itemName");

            double price = Double.parseDouble(
                    request.getParameter("price"));

            String image =
                    request.getParameter("image");

            boolean itemExists = false;

            for (CartItem item : cart) {

                if (item.getMenuId() == menuId) {

                    item.setQuantity(
                            item.getQuantity() + 1);

                    itemExists = true;

                    break;
                }
            }

            if (!itemExists) {

                CartItem cartItem =
                        new CartItem(
                                menuId,
                                itemName,
                                price,
                                1,
                                image
                        );

                cart.add(cartItem);
            }
        }

        /* =========================
           INCREASE
           ========================= */

        else if (action.equals("increase")) {

            int menuId = Integer.parseInt(
                    request.getParameter("menuId"));

            for (CartItem item : cart) {

                if (item.getMenuId() == menuId) {

                    item.setQuantity(
                            item.getQuantity() + 1);

                    break;
                }
            }
        }

        /* =========================
           DECREASE
           ========================= */

        else if (action.equals("decrease")) {

            int menuId = Integer.parseInt(
                    request.getParameter("menuId"));

            for (CartItem item : cart) {

                if (item.getMenuId() == menuId) {

                    if (item.getQuantity() > 1) {

                        item.setQuantity(
                                item.getQuantity() - 1);
                    }

                    break;
                }
            }
        }

        /* =========================
           REMOVE
           ========================= */

        else if (action.equals("remove")) {

            int menuId = Integer.parseInt(
                    request.getParameter("menuId"));

            CartItem itemToRemove = null;

            for (CartItem item : cart) {

                if (item.getMenuId() == menuId) {

                    itemToRemove = item;

                    break;
                }
            }

            if (itemToRemove != null) {

                cart.remove(itemToRemove);
            }
        }

        /* GO BACK TO CART */

        response.sendRedirect("cart");
    }
}

