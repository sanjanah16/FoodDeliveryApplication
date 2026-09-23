package com.tap.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tap.DAO.RestaurantDAO;
import com.tap.model.Restaurant;
import com.tap.util.DBConnection;

public class RestaurantDAOImpl implements RestaurantDAO {

    private static final String INSERT_QUERY =
            "INSERT INTO restaurant (name, cuisine_type, delivery_time, "
            + "address, admin_user_id, rating, is_active) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?)";

    private static final String SELECT_QUERY =
            "SELECT * FROM restaurant WHERE restaurant_id = ?";

    private static final String UPDATE_QUERY =
            "UPDATE restaurant SET name = ?, cuisine_type = ?, "
            + "delivery_time = ?, address = ?, admin_user_id = ?, "
            + "rating = ?, is_active = ? WHERE restaurant_id = ?";

    private static final String DELETE_QUERY =
            "DELETE FROM restaurant WHERE restaurant_id = ?";

    private static final String SELECT_ALL_QUERY =
            "SELECT * FROM restaurant";


    @Override
    public void addRestaurant(Restaurant restaurant) {

        Connection connection = DBConnection.getConnection();

        try {
            PreparedStatement pstmt =
                    connection.prepareStatement(INSERT_QUERY);

            pstmt.setString(1, restaurant.getName());
            pstmt.setString(2, restaurant.getCuisineType());
            pstmt.setInt(3, restaurant.getDeliveryTime());
            pstmt.setString(4, restaurant.getAddress());
            pstmt.setInt(5, restaurant.getAdminUserId());
            pstmt.setDouble(6, restaurant.getRating());
            pstmt.setInt(7, restaurant.getIsActive());

            int i = pstmt.executeUpdate();

            System.out.println(i);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Restaurant getRestaurant(int restaurantId) {

        Restaurant restaurant = null;

        Connection connection = DBConnection.getConnection();

        try {
            PreparedStatement pstmt =
                    connection.prepareStatement(SELECT_QUERY);

            pstmt.setInt(1, restaurantId);

            ResultSet resultset = pstmt.executeQuery();

            if (resultset.next()) {
                restaurant = extractRestaurantFromResultSet(resultset);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return restaurant;
    }


    private Restaurant extractRestaurantFromResultSet(ResultSet res)
            throws SQLException {

        int restaurantId = res.getInt(1);
        String name = res.getString(2);
        String cuisineType = res.getString(3);
        int deliveryTime = res.getInt(4);
        String address = res.getString(5);
        int adminUserId = res.getInt(6);
        double rating = res.getDouble(7);
        int isActive = res.getInt(8);

        Restaurant restaurant = new Restaurant(
                restaurantId,
                name,
                cuisineType,
                deliveryTime,
                address,
                adminUserId,
                rating,
                isActive
        );

        return restaurant;
    }


    @Override
    public void updateRestaurant(Restaurant restaurant) {

        Connection connection = DBConnection.getConnection();

        try {
            PreparedStatement pstmt =
                    connection.prepareStatement(UPDATE_QUERY);

            pstmt.setString(1, restaurant.getName());
            pstmt.setString(2, restaurant.getCuisineType());
            pstmt.setInt(3, restaurant.getDeliveryTime());
            pstmt.setString(4, restaurant.getAddress());
            pstmt.setInt(5, restaurant.getAdminUserId());
            pstmt.setDouble(6, restaurant.getRating());
            pstmt.setInt(7, restaurant.getIsActive());
            pstmt.setInt(8, restaurant.getRestaurantId());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void deleteRestaurant(int restaurantId) {

        Connection connection = DBConnection.getConnection();

        try {
            PreparedStatement pstmt =
                    connection.prepareStatement(DELETE_QUERY);

            pstmt.setInt(1, restaurantId);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Restaurant> getAllRestaurant() {

        ArrayList<Restaurant> allRestaurants =
                new ArrayList<Restaurant>();

        Connection connection = DBConnection.getConnection();

        try {
            Statement stmt = connection.createStatement();

            ResultSet res =
                    stmt.executeQuery(SELECT_ALL_QUERY);

            while (res.next()) {

                Restaurant restaurant =
                        extractRestaurantFromResultSet(res);

                allRestaurants.add(restaurant);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allRestaurants;
    }
}