
package com.tap.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

import com.tap.DAO.UserDAO;
import com.tap.model.User;
import com.tap.util.DBConnection;

public class UserDAOImpl implements UserDAO {

    private static final String INSERT_QUERY =
            "INSERT INTO user (name,email,password,phone,adress,role,"
            + "createdDate,lastLoginDate) VALUES (?,?,?,?,?,?,?,?)";

    private static final String SELECT_QUERY =
            "SELECT * FROM user WHERE id = ?";

    private static final String SELECT_BY_EMAIL_QUERY =
            "SELECT * FROM user WHERE email = ? ORDER BY id DESC LIMIT 1";
    
    private static final String UPDATE_QUERY =
            "UPDATE user SET name = ?, email = ?, password = ?, phone = ?,"
            + "adress = ?, role = ?, lastLoginDate = ? WHERE id = ?";

    private static final String DELETE_QUERY =
            "DELETE FROM user WHERE id = ?";

    private static final String SELECT_ALL_QUERY =
            "SELECT * FROM user";


    @Override
    public void addUser(User user) {

        Connection connection = DBConnection.getConnection();

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(INSERT_QUERY);

            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getPhone());
            pstmt.setString(5, user.getAdress());
            pstmt.setString(6, user.getRole());

            pstmt.setTimestamp(7,
                    new Timestamp(System.currentTimeMillis()));

            pstmt.setTimestamp(8,
                    new Timestamp(System.currentTimeMillis()));

            int i = pstmt.executeUpdate();

            System.out.println(i);

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }


    @Override
    public User getUser(int id) {

        User user = null;

        Connection connection = DBConnection.getConnection();

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(SELECT_QUERY);

            pstmt.setInt(1, id);

            ResultSet resultset = pstmt.executeQuery();

            if (resultset.next()) {

                user = extractUserFromResultSet(resultset);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return user;
    }


    // NEW METHOD FOR LOGIN
    @Override
    public User getUserByEmail(String email) {

        User user = null;

        Connection connection = DBConnection.getConnection();

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(SELECT_BY_EMAIL_QUERY);

            pstmt.setString(1, email);

            ResultSet resultset = pstmt.executeQuery();

            if (resultset.next()) {

                user = extractUserFromResultSet(resultset);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return user;
    }


    private User extractUserFromResultSet(ResultSet res)
            throws SQLException {

        User user = null;

        int id = res.getInt(1);

        String name = res.getString(2);

        String email = res.getString(3);

        String password = res.getString(4);

        String phone = res.getString(5);

        String adress = res.getString(6);

        String role = res.getString(7);

        Timestamp createdDate = res.getTimestamp(8);

        Timestamp lastLoginDate = res.getTimestamp(9);

        user = new User(
                id,
                name,
                email,
                password,
                phone,
                adress,
                role,
                createdDate,
                lastLoginDate
        );

        return user;
    }


    @Override
    public void updateUser(User user) {

        Connection connection = DBConnection.getConnection();

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(UPDATE_QUERY);

            pstmt.setString(1, user.getName());

            pstmt.setString(2, user.getEmail());

            pstmt.setString(3, user.getPassword());

            pstmt.setString(4, user.getPhone());

            pstmt.setString(5, user.getAdress());

            pstmt.setString(6, user.getRole());

            pstmt.setTimestamp(7,
                    new Timestamp(System.currentTimeMillis()));

            pstmt.setInt(8, user.getId());

            pstmt.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }


    @Override
    public void deleteUser(int id) {

        Connection connection = DBConnection.getConnection();

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(DELETE_QUERY);

            pstmt.setInt(1, id);

            pstmt.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }


    @Override
    public List<User> getAllUser() {

        ArrayList<User> allUser = new ArrayList<User>();

        Connection connection = DBConnection.getConnection();

        try {

            Statement stmt = connection.createStatement();

            ResultSet res =
                    stmt.executeQuery(SELECT_ALL_QUERY);

            while (res.next()) {

                User user =
                        extractUserFromResultSet(res);

                allUser.add(user);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return allUser;
    }
}

