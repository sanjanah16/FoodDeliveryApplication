package com.tap.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tap.DAO.OrderItemDAO;
import com.tap.model.OrderItem;
import com.tap.util.DBConnection;

public class OrderItemDAOImpl implements OrderItemDAO {

    private static final String INSERT_QUERY =
            "INSERT INTO order_item (order_id, menu_id, quantity, item_total) "
            + "VALUES (?, ?, ?, ?)";

    private static final String SELECT_QUERY =
            "SELECT * FROM order_item WHERE order_item_id = ?";

    private static final String UPDATE_QUERY =
            "UPDATE order_item SET order_id = ?, menu_id = ?, "
            + "quantity = ?, item_total = ? WHERE order_item_id = ?";

    private static final String DELETE_QUERY =
            "DELETE FROM order_item WHERE order_item_id = ?";

    private static final String SELECT_ALL_QUERY =
            "SELECT * FROM order_item";


    @Override
    public void addOrderItem(OrderItem orderItem) {

        Connection connection = DBConnection.getConnection();

        try {
            PreparedStatement pstmt =
                    connection.prepareStatement(INSERT_QUERY);

            pstmt.setInt(1, orderItem.getOrderId());
            pstmt.setInt(2, orderItem.getMenuId());
            pstmt.setInt(3, orderItem.getQuantity());
            pstmt.setDouble(4, orderItem.getItemTotal());

            int i = pstmt.executeUpdate();

            System.out.println(i);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public OrderItem getOrderItem(int orderItemId) {

        OrderItem orderItem = null;

        Connection connection = DBConnection.getConnection();

        try {
            PreparedStatement pstmt =
                    connection.prepareStatement(SELECT_QUERY);

            pstmt.setInt(1, orderItemId);

            ResultSet resultset = pstmt.executeQuery();

            if (resultset.next()) {
                orderItem = extractOrderItemFromResultSet(resultset);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderItem;
    }


    private OrderItem extractOrderItemFromResultSet(ResultSet res)
            throws SQLException {

        int orderItemId = res.getInt(1);
        int orderId = res.getInt(2);
        int menuId = res.getInt(3);
        int quantity = res.getInt(4);
        double itemTotal = res.getDouble(5);

        OrderItem orderItem = new OrderItem(
                orderItemId,
                orderId,
                menuId,
                quantity,
                itemTotal
        );

        return orderItem;
    }


    @Override
    public void updateOrderItem(OrderItem orderItem) {

        Connection connection = DBConnection.getConnection();

        try {
            PreparedStatement pstmt =
                    connection.prepareStatement(UPDATE_QUERY);

            pstmt.setInt(1, orderItem.getOrderId());
            pstmt.setInt(2, orderItem.getMenuId());
            pstmt.setInt(3, orderItem.getQuantity());
            pstmt.setDouble(4, orderItem.getItemTotal());
            pstmt.setInt(5, orderItem.getOrderItemId());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void deleteOrderItem(int orderItemId) {

        Connection connection = DBConnection.getConnection();

        try {
            PreparedStatement pstmt =
                    connection.prepareStatement(DELETE_QUERY);

            pstmt.setInt(1, orderItemId);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<OrderItem> getAllOrderItems() {

        ArrayList<OrderItem> allOrderItems =
                new ArrayList<OrderItem>();

        Connection connection = DBConnection.getConnection();

        try {
            Statement stmt = connection.createStatement();

            ResultSet res =
                    stmt.executeQuery(SELECT_ALL_QUERY);

            while (res.next()) {

                OrderItem orderItem =
                        extractOrderItemFromResultSet(res);

                allOrderItems.add(orderItem);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allOrderItems;
    }
}