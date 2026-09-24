package com.tap.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.tap.DAO.RestaurantDAO;
import com.tap.DAOImpl.RestaurantDAOImpl;
import com.tap.model.Restaurant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/restaurants")
public class RestaurantServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private RestaurantDAO restaurantDAO;

    @Override
    public void init() throws ServletException {
        restaurantDAO = new RestaurantDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String restaurantId = request.getParameter("restaurantId");

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");

        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");

        out.println("<title>Restaurants - Foodie</title>");

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
        out.println("background: #fff8f5;");
        out.println("color: #333;");
        out.println("}");

        /* =========================
           NAVBAR
        ========================= */

        out.println(".navbar {");
        out.println("display: flex;");
        out.println("justify-content: space-between;");
        out.println("align-items: center;");
        out.println("padding: 18px 50px;");
        out.println("background: linear-gradient(90deg, #ff512f, #ff416c, #8e44ad);");
        out.println("color: white;");
        out.println("}");

        out.println(".logo {");
        out.println("font-size: 28px;");
        out.println("font-weight: bold;");
        out.println("}");

        out.println(".nav-links {");
        out.println("display: flex;");
        out.println("gap: 25px;");
        out.println("}");

        out.println(".nav-links a {");
        out.println("color: white;");
        out.println("text-decoration: none;");
        out.println("font-size: 16px;");
        out.println("font-weight: bold;");
        out.println("}");

        out.println(".nav-links a:hover {");
        out.println("text-decoration: underline;");
        out.println("}");

        /* =========================
           PAGE HEADER
        ========================= */

        out.println(".page-header {");
        out.println("text-align: center;");
        out.println("padding: 45px 20px 25px;");
        out.println("}");

        out.println(".page-header h1 {");
        out.println("font-size: 38px;");
        out.println("margin: 0 0 10px;");
        out.println("color: #333;");
        out.println("}");

        out.println(".page-header p {");
        out.println("font-size: 17px;");
        out.println("color: #777;");
        out.println("margin: 0;");
        out.println("}");

        /* =========================
           RESTAURANT LIST
        ========================= */

        out.println(".restaurant-container {");
        out.println("width: 90%;");
        out.println("max-width: 1100px;");
        out.println("margin: 20px auto 50px;");
        out.println("display: grid;");
        out.println("grid-template-columns: repeat(3, 1fr);");
        out.println("gap: 25px;");
        out.println("}");

        /* =========================
           RESTAURANT ITEM
        ========================= */

        out.println(".restaurant-item {");
        out.println("background: white;");
        out.println("border-radius: 20px;");
        out.println("padding: 25px;");
        out.println("box-shadow: 0 10px 30px rgba(0,0,0,0.10);");
        out.println("transition: 0.3s;");
        out.println("}");

        out.println(".restaurant-item:hover {");
        out.println("transform: translateY(-7px);");
        out.println("box-shadow: 0 15px 35px rgba(255,65,108,0.18);");
        out.println("}");

        out.println(".restaurant-item h2 {");
        out.println("margin: 0 0 10px;");
        out.println("font-size: 24px;");
        out.println("color: #ff416c;");
        out.println("}");

        out.println(".restaurant-item p {");
        out.println("margin: 8px 0;");
        out.println("font-size: 15px;");
        out.println("color: #555;");
        out.println("}");

        out.println(".restaurant-item strong {");
        out.println("color: #333;");
        out.println("}");

        /* =========================
           VIEW RESTAURANT BUTTON
        ========================= */

        out.println(".view-button {");
        out.println("display: inline-block;");
        out.println("margin-top: 15px;");
        out.println("padding: 12px 22px;");
        out.println("border-radius: 25px;");
        out.println("background: linear-gradient(90deg, #ff512f, #ff416c);");
        out.println("color: white;");
        out.println("text-decoration: none;");
        out.println("font-weight: bold;");
        out.println("transition: 0.3s;");
        out.println("}");

        out.println(".view-button:hover {");
        out.println("transform: scale(1.05);");
        out.println("}");

        /* =========================
           RESTAURANT DETAILS
        ========================= */

        out.println(".restaurant-card {");
        out.println("width: 90%;");
        out.println("max-width: 850px;");
        out.println("margin: 50px auto;");
        out.println("background: white;");
        out.println("border-radius: 25px;");
        out.println("overflow: hidden;");
        out.println("box-shadow: 0 15px 40px rgba(0,0,0,0.12);");
        out.println("}");

        out.println(".restaurant-header {");
        out.println("padding: 40px;");
        out.println("text-align: center;");
        out.println("color: white;");
        out.println("background: linear-gradient(135deg, #ff512f, #ff416c, #8e44ad);");
        out.println("}");

        out.println(".restaurant-header h1 {");
        out.println("font-size: 38px;");
        out.println("margin: 0 0 10px;");
        out.println("}");

        out.println(".restaurant-header p {");
        out.println("font-size: 17px;");
        out.println("margin: 0;");
        out.println("}");

        /* =========================
           INFORMATION
        ========================= */

        out.println(".restaurant-info {");
        out.println("display: grid;");
        out.println("grid-template-columns: repeat(2, 1fr);");
        out.println("gap: 18px;");
        out.println("padding: 35px;");
        out.println("}");

        out.println(".info-box {");
        out.println("display: flex;");
        out.println("align-items: center;");
        out.println("gap: 15px;");
        out.println("padding: 18px;");
        out.println("border-radius: 15px;");
        out.println("background: #fff7f4;");
        out.println("border: 1px solid #ffe1d8;");
        out.println("transition: 0.3s;");
        out.println("}");

        out.println(".info-box:hover {");
        out.println("transform: translateY(-4px);");
        out.println("box-shadow: 0 8px 20px rgba(255,65,108,0.15);");
        out.println("}");

        out.println(".info-icon {");
        out.println("font-size: 30px;");
        out.println("}");

        out.println(".info-box small {");
        out.println("display: block;");
        out.println("color: #888;");
        out.println("font-size: 13px;");
        out.println("margin-bottom: 5px;");
        out.println("}");

        out.println(".info-box strong {");
        out.println("display: block;");
        out.println("color: #333;");
        out.println("font-size: 16px;");
        out.println("}");

        /* =========================
           MENU BUTTON
        ========================= */

        out.println(".menu-button {");
        out.println("display: block;");
        out.println("width: 220px;");
        out.println("margin: 0 auto 35px;");
        out.println("padding: 15px 20px;");
        out.println("text-align: center;");
        out.println("text-decoration: none;");
        out.println("color: white;");
        out.println("font-size: 16px;");
        out.println("font-weight: bold;");
        out.println("border-radius: 30px;");
        out.println("background: linear-gradient(90deg, #ff512f, #ff416c);");
        out.println("box-shadow: 0 8px 20px rgba(255,65,108,0.3);");
        out.println("transition: 0.3s;");
        out.println("}");

        out.println(".menu-button:hover {");
        out.println("transform: translateY(-3px) scale(1.03);");
        out.println("}");

        /* =========================
           BACK HOME
        ========================= */

        out.println(".back-home {");
        out.println("text-align: center;");
        out.println("margin: 30px 0 40px;");
        out.println("}");

        out.println(".back-home a {");
        out.println("color: #ff416c;");
        out.println("text-decoration: none;");
        out.println("font-weight: bold;");
        out.println("}");

        out.println(".back-home a:hover {");
        out.println("color: #8e44ad;");
        out.println("}");

        /* =========================
           MOBILE
        ========================= */

        out.println("@media (max-width: 850px) {");

        out.println(".restaurant-container {");
        out.println("grid-template-columns: repeat(2, 1fr);");
        out.println("}");

        out.println(".navbar {");
        out.println("padding: 15px 25px;");
        out.println("}");

        out.println("}");

        out.println("@media (max-width: 600px) {");

        out.println(".restaurant-container {");
        out.println("grid-template-columns: 1fr;");
        out.println("}");

        out.println(".navbar {");
        out.println("flex-direction: column;");
        out.println("gap: 15px;");
        out.println("}");

        out.println(".nav-links {");
        out.println("gap: 12px;");
        out.println("flex-wrap: wrap;");
        out.println("justify-content: center;");
        out.println("}");

        out.println(".restaurant-info {");
        out.println("grid-template-columns: 1fr;");
        out.println("padding: 20px;");
        out.println("}");

        out.println(".restaurant-header h1 {");
        out.println("font-size: 30px;");
        out.println("}");

        out.println("}");

        out.println("</style>");

        out.println("</head>");

        out.println("<body>");

        /* =========================
           NAVBAR
        ========================= */

        out.println("<nav class='navbar'>");

        out.println("<div class='logo'>🍴 Foodie</div>");

        out.println("<div class='nav-links'>");

        out.println("<a href='home.html'>Home</a>");

        out.println("<a href='restaurants'>Restaurants</a>");

        out.println("<a href='profile'>Profile</a>");

        out.println("<a href='cart'>Cart</a>");

        out.println("<a href='orderHistory'>Orders</a>");

        out.println("<a href='logout'>Logout</a>");

        out.println("</div>");

        out.println("</nav>");

        /* =====================================================
           IF RESTAURANT ID IS PROVIDED
           SHOW SINGLE RESTAURANT DETAILS
        ===================================================== */

        if (restaurantId != null && !restaurantId.trim().isEmpty()) {

            try {

                int id = Integer.parseInt(restaurantId);

                Restaurant restaurant =
                        restaurantDAO.getRestaurant(id);

                if (restaurant != null) {

                    out.println("<div class='restaurant-card'>");

                    /* HEADER */

                    out.println("<div class='restaurant-header'>");

                    out.println("<h1>"
                            + restaurant.getName()
                            + "</h1>");

                    out.println("<p>🍽️ "
                            + restaurant.getCuisineType()
                            + "</p>");

                    out.println("</div>");

                    /* INFORMATION */

                    out.println("<div class='restaurant-info'>");

                    /* Cuisine */

                    out.println("<div class='info-box'>");

                    out.println("<span class='info-icon'>🍽️</span>");

                    out.println("<div>");

                    out.println("<small>Cuisine</small>");

                    out.println("<strong>"
                            + restaurant.getCuisineType()
                            + "</strong>");

                    out.println("</div>");

                    out.println("</div>");

                    /* Delivery */

                    out.println("<div class='info-box'>");

                    out.println("<span class='info-icon'>🚴</span>");

                    out.println("<div>");

                    out.println("<small>Delivery Time</small>");

                    out.println("<strong>"
                            + restaurant.getDeliveryTime()
                            + " minutes</strong>");

                    out.println("</div>");

                    out.println("</div>");

                    /* Address */

                    out.println("<div class='info-box'>");

                    out.println("<span class='info-icon'>📍</span>");

                    out.println("<div>");

                    out.println("<small>Address</small>");

                    out.println("<strong>"
                            + restaurant.getAddress()
                            + "</strong>");

                    out.println("</div>");

                    out.println("</div>");

                    /* Rating */

                    out.println("<div class='info-box'>");

                    out.println("<span class='info-icon'>⭐</span>");

                    out.println("<div>");

                    out.println("<small>Rating</small>");

                    out.println("<strong>"
                            + restaurant.getRating()
                            + " / 5</strong>");

                    out.println("</div>");

                    out.println("</div>");

                    /* Status */

                    out.println("<div class='info-box'>");

                    out.println("<span class='info-icon'>✅</span>");

                    out.println("<div>");

                    out.println("<small>Status</small>");

                    out.println("<strong>Active</strong>");

                    out.println("</div>");

                    out.println("</div>");

                    out.println("</div>");

                    /* VIEW MENU */

                    out.println("<a class='menu-button' href='menu?restaurantId="
                            + restaurant.getRestaurantId()
                            + "'>");

                    out.println("🍽️ View Menu →");

                    out.println("</a>");

                    out.println("</div>");

                } else {

                    out.println("<h2 style='text-align:center; margin-top:50px;'>");

                    out.println("Restaurant not found");

                    out.println("</h2>");
                }

            } catch (NumberFormatException e) {

                out.println("<h2 style='text-align:center; margin-top:50px;'>");

                out.println("Invalid restaurant ID");

                out.println("</h2>");
            }

        }

        /* =====================================================
           NO RESTAURANT ID
           SHOW ALL RESTAURANTS
        ===================================================== */

        else {

            out.println("<div class='page-header'>");

            out.println("<h1>Popular Restaurants in Bengaluru</h1>");

            out.println("<p>Explore restaurants and discover your favourite food</p>");

            out.println("</div>");

            List<Restaurant> restaurants =
                    restaurantDAO.getAllRestaurants();

            if (restaurants != null && !restaurants.isEmpty()) {

                out.println("<div class='restaurant-container'>");

                for (Restaurant restaurant : restaurants) {

                    out.println("<div class='restaurant-item'>");

                    out.println("<h2>"
                            + restaurant.getName()
                            + "</h2>");

                    out.println("<p><strong>Cuisine:</strong> "
                            + restaurant.getCuisineType()
                            + "</p>");

                    out.println("<p><strong>Delivery:</strong> "
                            + restaurant.getDeliveryTime()
                            + " minutes</p>");

                    out.println("<p><strong>Rating:</strong> "
                            + restaurant.getRating()
                            + " / 5</p>");

                    out.println("<p><strong>Address:</strong> "
                            + restaurant.getAddress()
                            + "</p>");

                    out.println("<a class='view-button' href='restaurants?restaurantId="
                            + restaurant.getRestaurantId()
                            + "'>");

                    out.println("View Restaurant →");

                    out.println("</a>");

                    out.println("</div>");
                }

                out.println("</div>");

            } else {

                out.println("<h2 style='text-align:center; margin-top:40px;'>");

                out.println("No restaurants available");

                out.println("</h2>");
            }
        }

        /* =========================
           BACK TO HOME
        ========================= */

        out.println("<div class='back-home'>");

        out.println("<a href='home.html'>");

        out.println("← Back to Home");

        out.println("</a>");

        out.println("</div>");

        out.println("</body>");

        out.println("</html>");
    }
}
