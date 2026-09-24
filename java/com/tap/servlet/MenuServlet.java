package com.tap.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.tap.DAO.MenuDAO;
import com.tap.DAOImpl.MenuDAOImpl;
import com.tap.model.Menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/menu")
public class MenuServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private MenuDAO menuDAO;

    @Override
    public void init() throws ServletException {

        menuDAO = new MenuDAOImpl();

    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String restaurantId =
                request.getParameter("restaurantId");

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out =
                response.getWriter();

        /* =========================
           VALIDATE RESTAURANT ID
        ========================= */

        if (restaurantId == null || restaurantId.isEmpty()) {

            out.println("<h2 style='text-align:center;'>");

            out.println("Restaurant ID is missing");

            out.println("</h2>");

            return;
        }

        int id;

        try {

            id = Integer.parseInt(restaurantId);

        } catch (NumberFormatException e) {

            out.println("<h2 style='text-align:center;'>");

            out.println("Invalid Restaurant ID");

            out.println("</h2>");

            return;
        }

        /* =========================
           GET MENU
        ========================= */

        List<Menu> menuList =
                menuDAO.getMenuByRestaurantId(id);

        /* =========================
           HTML START
        ========================= */

        out.println("<!DOCTYPE html>");

        out.println("<html>");

        out.println("<head>");

        out.println("<meta charset='UTF-8'>");

        out.println("<meta name='viewport' "
                + "content='width=device-width, initial-scale=1.0'>");

        out.println("<title>Restaurant Menu</title>");

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

        out.println("background: linear-gradient(135deg, #fff8f5, #fff0f5);");

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

        out.println("box-shadow: 0 5px 20px rgba(0,0,0,0.15);");

        out.println("}");

        out.println(".logo {");

        out.println("font-size: 25px;");

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

        out.println("font-size: 15px;");

        out.println("transition: 0.3s;");

        out.println("}");

        out.println(".nav-links a:hover {");

        out.println("color: #ffe082;");

        out.println("transform: translateY(-2px);");

        out.println("}");

        /* =========================
           PAGE HEADER
        ========================= */

        out.println(".page-header {");

        out.println("text-align: center;");

        out.println("padding: 45px 20px 25px;");

        out.println("}");

        out.println(".page-header h1 {");

        out.println("margin: 0;");

        out.println("font-size: 38px;");

        out.println("background: linear-gradient(90deg, #ff512f, #ff416c, #8e44ad);");

        out.println("-webkit-background-clip: text;");

        out.println("-webkit-text-fill-color: transparent;");

        out.println("}");

        out.println(".page-header p {");

        out.println("font-size: 17px;");

        out.println("color: #777;");

        out.println("margin-top: 10px;");

        out.println("}");

        /* =========================
           MENU CONTAINER
        ========================= */

        out.println(".menu-container {");

        out.println("width: 92%;");

        out.println("max-width: 1200px;");

        out.println("margin: 20px auto 50px;");

        out.println("display: grid;");

        out.println("grid-template-columns: repeat(auto-fit, minmax(260px, 1fr));");

        out.println("gap: 28px;");

        out.println("}");

        /* =========================
           MENU CARD
        ========================= */

        out.println(".menu-card {");

        out.println("background: white;");

        out.println("border-radius: 22px;");

        out.println("overflow: hidden;");

        out.println("box-shadow: 0 10px 30px rgba(0,0,0,0.10);");

        out.println("transition: 0.3s;");

        out.println("}");

        out.println(".menu-card:hover {");

        out.println("transform: translateY(-8px);");

        out.println("box-shadow: 0 18px 35px rgba(255,65,108,0.20);");

        out.println("}");

        /* =========================
           IMAGE
        ========================= */

        out.println(".food-image {");

        out.println("width: 100%;");

        out.println("height: 210px;");

        out.println("object-fit: cover;");

        out.println("display: block;");

        out.println("}");

        /* =========================
           CARD CONTENT
        ========================= */

        out.println(".menu-content {");

        out.println("padding: 20px;");

        out.println("}");

        out.println(".menu-content h2 {");

        out.println("margin: 0 0 8px;");

        out.println("font-size: 21px;");

        out.println("color: #333;");

        out.println("}");

        out.println(".description {");

        out.println("font-size: 14px;");

        out.println("color: #777;");

        out.println("min-height: 42px;");

        out.println("line-height: 1.5;");

        out.println("}");

        /* =========================
           CATEGORY
        ========================= */

        out.println(".category {");

        out.println("display: inline-block;");

        out.println("margin: 10px 0;");

        out.println("padding: 6px 12px;");

        out.println("border-radius: 20px;");

        out.println("background: #fff0f5;");

        out.println("color: #ff416c;");

        out.println("font-size: 12px;");

        out.println("font-weight: bold;");

        out.println("}");

        /* =========================
           PRICE
        ========================= */

        out.println(".price {");

        out.println("font-size: 22px;");

        out.println("font-weight: bold;");

        out.println("color: #ff512f;");

        out.println("margin: 8px 0;");

        out.println("}");

        /* =========================
           AVAILABLE
        ========================= */

        out.println(".available {");

        out.println("color: #27ae60;");

        out.println("font-size: 13px;");

        out.println("font-weight: bold;");

        out.println("}");

        out.println(".unavailable {");

        out.println("color: #e74c3c;");

        out.println("font-size: 13px;");

        out.println("font-weight: bold;");

        out.println("}");

        /* =========================
           CART BUTTON
        ========================= */

        out.println(".cart-button {");

        out.println("width: 100%;");

        out.println("border: none;");

        out.println("padding: 13px;");

        out.println("margin-top: 15px;");

        out.println("border-radius: 25px;");

        out.println("background: linear-gradient(90deg, #ff512f, #ff416c);");

        out.println("color: white;");

        out.println("font-size: 15px;");

        out.println("font-weight: bold;");

        out.println("cursor: pointer;");

        out.println("transition: 0.3s;");

        out.println("}");

        out.println(".cart-button:hover {");

        out.println("transform: scale(1.03);");

        out.println("box-shadow: 0 8px 18px rgba(255,65,108,0.3);");

        out.println("}");

        /* =========================
           DISABLED BUTTON
        ========================= */

        out.println(".disabled-button {");

        out.println("width: 100%;");

        out.println("border: none;");

        out.println("padding: 13px;");

        out.println("margin-top: 15px;");

        out.println("border-radius: 25px;");

        out.println("background: #ccc;");

        out.println("color: white;");

        out.println("font-size: 15px;");

        out.println("font-weight: bold;");

        out.println("}");

        /* =========================
           EMPTY MENU
        ========================= */

        out.println(".empty-menu {");

        out.println("text-align: center;");

        out.println("padding: 70px 20px;");

        out.println("}");

        out.println(".empty-menu h2 {");

        out.println("font-size: 28px;");

        out.println("color: #555;");

        out.println("}");

        /* =========================
           FOOTER
        ========================= */

        out.println(".back-home {");

        out.println("text-align: center;");

        out.println("margin-bottom: 50px;");

        out.println("}");

        out.println(".back-home a {");

        out.println("display: inline-block;");

        out.println("padding: 12px 25px;");

        out.println("border-radius: 25px;");

        out.println("text-decoration: none;");

        out.println("color: white;");

        out.println("font-weight: bold;");

        out.println("background: linear-gradient(90deg, #8e44ad, #ff416c);");

        out.println("}");

        /* =========================
           MOBILE
        ========================= */

        out.println("@media (max-width: 700px) {");

        out.println(".navbar {");

        out.println("padding: 15px 20px;");

        out.println("flex-direction: column;");

        out.println("gap: 15px;");

        out.println("}");

        out.println(".nav-links {");

        out.println("gap: 15px;");

        out.println("flex-wrap: wrap;");

        out.println("justify-content: center;");

        out.println("}");

        out.println(".page-header h1 {");

        out.println("font-size: 30px;");

        out.println("}");

        out.println("}");

        out.println("</style>");

        out.println("</head>");

        out.println("<body>");

        /* =========================
           NAVBAR
        ========================= */

        out.println("<div class='navbar'>");

        out.println("<div class='logo'>🍴 FoodieExpress</div>");

        out.println("<div class='nav-links'>");

        out.println("<a href='home.html'>Home</a>");

        out.println("<a href='profile'>Profile</a>");

        out.println("<a href='cart'>Cart</a>");

        out.println("<a href='orderHistory'>Orders</a>");

        out.println("<a href='logout'>Logout</a>");

        out.println("</div>");

        out.println("</div>");

        /* =========================
           PAGE HEADER
        ========================= */

        out.println("<div class='page-header'>");

        out.println("<h1>🍽️ Our Delicious Menu</h1>");

        out.println("<p>Choose your favourite food and add it to your cart!</p>");

        out.println("</div>");

        /* =========================
           MENU
        ========================= */

        if (menuList == null || menuList.isEmpty()) {

            out.println("<div class='empty-menu'>");

            out.println("<h2>😔 No menu items available</h2>");

            out.println("<p>Please check back later.</p>");

            out.println("</div>");

        } else {

            out.println("<div class='menu-container'>");

            for (Menu menu : menuList) {

                String image =
                        getImageName(menu.getItemName());

                out.println("<div class='menu-card'>");

                /* IMAGE */

                out.println("<img class='food-image' "
                        + "src='" + image + "' "
                        + "alt='" + menu.getItemName() + "'>");

                out.println("<div class='menu-content'>");

                /* NAME */

                out.println("<h2>"
                        + menu.getItemName()
                        + "</h2>");

                /* DESCRIPTION */

                String description =
                        menu.getDescription();

                if (description == null ||
                        description.trim().isEmpty()) {

                    description =
                            "Delicious and freshly prepared food.";
                }

                out.println("<div class='description'>"
                        + description
                        + "</div>");

                /* CATEGORY */

                if (menu.getCategory() != null) {

                    out.println("<span class='category'>"
                            + menu.getCategory()
                            + "</span>");
                }

                /* PRICE */

                out.println("<div class='price'>₹"
                        + menu.getPrice()
                        + "</div>");

                /* AVAILABILITY */

                /*
                 * IMPORTANT:
                 * Menu.java has getIsAvailable()
                 * and it returns int.
                 *
                 * 1 = Available
                 * 0 = Not Available
                 */

                if (menu.getIsAvailable() == 1) {

                    out.println("<div class='available'>");

                    out.println("● Available");

                    out.println("</div>");

                    /* =========================
                       ADD TO CART
                    ========================= */

                    out.println("<form action='cart' method='post'>");

                    out.println("<input type='hidden' "
                            + "name='menuId' value='"
                            + menu.getMenuId()
                            + "'>");

                    out.println("<input type='hidden' "
                            + "name='itemName' value='"
                            + menu.getItemName()
                            + "'>");

                    out.println("<input type='hidden' "
                            + "name='price' value='"
                            + menu.getPrice()
                            + "'>");

                    out.println("<input type='hidden' "
                            + "name='image' value='"
                            + image
                            + "'>");

                    out.println("<button type='submit' "
                            + "class='cart-button'>");

                    out.println("🛒 Add to Cart");

                    out.println("</button>");

                    out.println("</form>");

                } else {

                    out.println("<div class='unavailable'>");

                    out.println("● Currently Unavailable");

                    out.println("</div>");

                    out.println("<button class='disabled-button' "
                            + "disabled>");

                    out.println("Currently Unavailable");

                    out.println("</button>");
                }

                out.println("</div>");

                out.println("</div>");
            }

            out.println("</div>");
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

    /* =====================================================
       FOOD IMAGE METHOD
       ===================================================== */

    private String getImageName(String itemName) {

        if (itemName == null) {

            return "images/default-food.jpg";
        }

        switch (itemName) {

            case "Paneer Pizza":
                return "images/paneer-pizza.jpg";

            // case "Veg Biryani":
            //     return "images/veg-biryani.jpg";

            case "Burger":
                return "images/burger.jpg";

            case "Noodles":
                return "images/noodles.jpg";

            // case "Dosa":
            //     return "images/dosa.jpg";

            case "Momos":
                return "images/momos.jpg";

            case "Pasta":
                return "images/pasta.jpg";

            case "Cake":
                return "images/cake.jpg";

            case "Ice Cream":
                return "images/ice-cream.jpg";

            // case "Tandoori":
            //     return "images/tandoori.jpg";

            /* Udupi Kitchen */

            case "Masala Dosa":
                return "images/masala-dosa.jpg";

            case "Idli Vada":
                return "images/idli-vada.jpg";

            case "Plain Dosa":
                return "images/plain-dosa.jpg";

            case "Set Dosa":
                return "images/set-dosa.jpg";

            case "Pongal":
                return "images/pongal.jpg";

            case "Upma":
                return "images/upma.jpg";

            case "Medu Vada":
                return "images/medu-vada.jpg";

            case "Lemon Rice":
                return "images/lemon-rice.jpg";

            case "Curd Rice":
                return "images/curd-rice.jpg";

            case "Filter Coffee":
                return "images/filter-coffee.jpg";

            /* Chinese Wok */

            case "Veg Hakka Noodles":
                return "images/veg-hakka-noodles.jpg";

            case "Veg Manchurian":
                return "images/veg-manchurian.jpg";

            case "Schezwan Fried Rice":
                return "images/schezwan-fried-rice.jpg";

            case "Chilli Paneer":
                return "images/chilli-paneer.jpg";

            case "Spring Rolls":
                return "images/spring-rolls.jpg";

            case "Hot & Sour Soup":
                return "images/hot-sour-soup.jpg";

            /* Spice Garden */

            case "Chicken Biryani":
                return "images/chicken-biryani.jpg";

            case "Paneer Tikka":
                return "images/paneer-tikka.jpg";

            case "Chicken Tandoori":
                return "images/chicken-tandoori.jpg";

            case "Butter Chicken":
                return "images/butter-chicken.jpg";

            case "Chicken 65":
                return "images/chicken-65.jpg";

            case "Chicken Kebab":
                return "images/chicken-kebab.jpg";

            /* Burger Hub */

            case "Classic Veg Burger":
                return "images/classic-veg-burger.jpg";

            case "Cheese Burger":
                return "images/cheese-burger.jpg";

            case "Chicken Burger":
                return "images/chicken-burger.jpg";

            case "French Fries":
                return "images/french-fries.jpg";

            case "Chicken Nuggets":
                return "images/chicken-nuggets.jpg";

            case "Chocolate Milkshake":
                return "images/chocolate-milkshake.jpg";

            /* Royal Biryani */

            case "Paneer Butter Masala":
                return "images/paneer-butter-masala.jpg";

            case "Dal Makhani":
                return "images/dal-makhani.jpg";

            case "Veg Pulao":
                return "images/veg-pulao.jpg";

            case "Chicken Tikka Masala":
                return "images/chicken-tikka-masala.jpg";

            case "Mutton Rogan Josh":
                return "images/mutton-rogan-josh.jpg";

            case "Mutton Korma":
                return "images/mutton-korma.jpg";

            /* Asian Treat */

            case "Veg Pad Thai":
                return "images/veg-pad-thai.jpg";

            case "Thai Green Curry":
                return "images/thai-green-curry.jpg";

            case "Veg Momos":
                return "images/veg-momos.jpg";

            case "Chicken Pad Thai":
                return "images/chicken-pad-thai.jpg";

            case "Chicken Satay":
                return "images/chicken-satay.jpg";

            case "Chicken Kung Pao":
                return "images/chicken-kung-pao.jpg";

            /* Sweet Heaven */

            case "Chocolate Brownie":
                return "images/chocolate-brownie.jpg";

            case "Red Velvet Cake":
                return "images/red-velvet-cake.jpg";

            case "Gulab Jamun":
                return "images/gulab-jamun.jpg";

            case "Cheesecake":
                return "images/cheesecake.jpg";

            case "Chocolate Donut":
                return "images/chocolate-donut.jpg";

            case "Fruit Custard":
                return "images/fruit-custard.jpg";

            case "Vanilla Ice Cream":
                return "images/vanilla-ice-cream.jpg";

            case "Chocolate Ice Cream":
                return "images/chocolate-ice-cream.jpg";

            /* Tandoori Tales */

            case "Fish Tikka":
                return "images/fish-tikka.jpg";

            case "Tandoori Prawns":
                return "images/tandoori-prawns.jpg";

            case "Tandoori Mushroom":
                return "images/tandoori-mushroom.jpg";

            case "Tandoori Aloo":
                return "images/tandoori-aloo.jpg";

            case "Mutton Seekh Kebab":
                return "images/mutton-seekh-kebab.jpg";

            case "Garlic Naan":
                return "images/garlic-naan.jpg";

            default:
                return "images/default-food.jpg";
        }
    }
}
