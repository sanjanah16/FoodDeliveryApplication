package com.tap.DAO;

import java.util.List;

import com.tap.model.Orders;

public interface OrdersDAO {

    int addOrders(Orders orders);

    Orders getOrders(int orderId);

    void updateOrders(Orders orders);

    void deleteOrders(int orderId);

    List<Orders> getAllOrders();

    List<Orders> getOrdersByUserId(int userId);
}