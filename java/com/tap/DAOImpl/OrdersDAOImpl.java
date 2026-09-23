package com.tap.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tap.DAO.OrdersDAO;
import com.tap.model.Orders;
import com.tap.util.DBConnection;

public class OrdersDAOImpl implements OrdersDAO {

    private static final String INSERT_QUERY =
            "INSERT INTO orders (user_id, restaurant_id, order_date, total_amount, status, payment_method) "
            + "VALUES (?, ?, ?, ?, ?, ?)";

    private static final String SELECT_QUERY =
            "SELECT * FROM orders WHERE order_id = ?";

    private static final String UPDATE_QUERY =
            "UPDATE orders SET user_id = ?, restaurant_id = ?, order_date = ?, "
            + "total_amount = ?, status = ?, payment_method = ? WHERE order_id = ?";

    private static final String DELETE_QUERY =
            "DELETE FROM orders WHERE order_id = ?";

    private static final String SELECT_ALL_QUERY =
            "SELECT * FROM orders";

    // NEW QUERY
    private static final String SELECT_BY_USER_QUERY =
            "SELECT * FROM orders WHERE user_id = ? ORDER BY order_date DESC";


    @Override
    public int addOrders(Orders orders) {

        Connection connection = DBConnection.getConnection();

        int orderId = 0;

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(
                        INSERT_QUERY,
                        Statement.RETURN_GENERATED_KEYS
                    );

            pstmt.setInt(1, orders.getUserId());
            pstmt.setInt(2, orders.getRestaurantId());

            pstmt.setTimestamp(3,
                    new Timestamp(System.currentTimeMillis()));

            pstmt.setDouble(4, orders.getTotalAmount());
            pstmt.setString(5, orders.getStatus());
            pstmt.setString(6, orders.getPaymentMethod());

            pstmt.executeUpdate();

            ResultSet resultSet = pstmt.getGeneratedKeys();

            if (resultSet.next()) {
                orderId = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderId;
    }


    @Override
    public Orders getOrders(int orderId) {

        Orders orders = null;

        Connection connection = DBConnection.getConnection();

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(SELECT_QUERY);

            pstmt.setInt(1, orderId);

            ResultSet resultset = pstmt.executeQuery();

            if (resultset.next()) {

                orders =
                        extractOrdersFromResultSet(resultset);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }


    private Orders extractOrdersFromResultSet(ResultSet res)
            throws SQLException {

        int orderId = res.getInt(1);
        int userId = res.getInt(2);
        int restaurantId = res.getInt(3);
        Timestamp orderDate = res.getTimestamp(4);
        double totalAmount = res.getDouble(5);
        String status = res.getString(6);
        String paymentMethod = res.getString(7);

        Orders orders = new Orders(
                orderId,
                userId,
                restaurantId,
                orderDate,
                totalAmount,
                status,
                paymentMethod
        );

        return orders;
    }


    @Override
    public void updateOrders(Orders orders) {

        Connection connection = DBConnection.getConnection();

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(UPDATE_QUERY);

            pstmt.setInt(1, orders.getUserId());
            pstmt.setInt(2, orders.getRestaurantId());
            pstmt.setTimestamp(3, orders.getOrderDate());
            pstmt.setDouble(4, orders.getTotalAmount());
            pstmt.setString(5, orders.getStatus());
            pstmt.setString(6, orders.getPaymentMethod());
            pstmt.setInt(7, orders.getOrderId());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void deleteOrders(int orderId) {

        Connection connection = DBConnection.getConnection();

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(DELETE_QUERY);

            pstmt.setInt(1, orderId);

            int i = pstmt.executeUpdate();

            if (i > 0) {

                System.out.println("Order Deleted Successfully");

            } else {

                System.out.println("Order Not Found");
            }

        } catch (SQLException e) {

            System.out.println("Order could not be deleted.");

            e.printStackTrace();
        }
    }


    @Override
    public List<Orders> getAllOrders() {

        ArrayList<Orders> allOrders =
                new ArrayList<Orders>();

        Connection connection = DBConnection.getConnection();

        try {

            Statement stmt = connection.createStatement();

            ResultSet res =
                    stmt.executeQuery(SELECT_ALL_QUERY);

            while (res.next()) {

                Orders orders =
                        extractOrdersFromResultSet(res);

                allOrders.add(orders);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return allOrders;
    }


    // NEW METHOD
    @Override
    public List<Orders> getOrdersByUserId(int userId) {

        ArrayList<Orders> userOrders =
                new ArrayList<Orders>();

        Connection connection = DBConnection.getConnection();

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(
                            SELECT_BY_USER_QUERY);

            pstmt.setInt(1, userId);

            ResultSet res =
                    pstmt.executeQuery();

            while (res.next()) {

                Orders orders =
                        extractOrdersFromResultSet(res);

                userOrders.add(orders);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return userOrders;
    }

}