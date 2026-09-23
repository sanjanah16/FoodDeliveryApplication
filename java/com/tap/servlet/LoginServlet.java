
package com.tap.servlet;

import java.io.IOException;

import com.tap.DAO.UserDAO;
import com.tap.DAOImpl.UserDAOImpl;
import com.tap.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        // Get login details
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Find user using email
        User user = userDAO.getUserByEmail(email);

        // Check email and password
        if (user != null && user.getPassword().equals(password)) {

            // Create/get session
            HttpSession session = request.getSession(true);

            // Keep session active for 30 minutes
            session.setMaxInactiveInterval(30 * 60);

            // Store complete user object
            session.setAttribute("loggedInUser", user);

            // Also store user ID separately
            session.setAttribute("userId", user.getId());

            // Store email separately
            session.setAttribute("userEmail", user.getEmail());

            System.out.println("LOGIN SUCCESS");
            System.out.println("User ID: " + user.getId());
            System.out.println("User Email: " + user.getEmail());
            System.out.println("Session ID: " + session.getId());

            // Go to home page
            response.sendRedirect(
                    response.encodeRedirectURL(
                            request.getContextPath() + "/home.html"
                    )
            );

        } else {

            // Invalid login
            response.setContentType("text/html");
            response.getWriter().println(
                "<h2 style='color:red;text-align:center;'>Invalid Email or Password</h2>"
            );

            response.getWriter().println(
                "<p style='text-align:center;'>"
                + "<a href='login.html'>Try Again</a>"
                + "</p>"
            );
        }
    }
}

