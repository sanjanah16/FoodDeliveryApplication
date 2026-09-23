package com.tap.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.tap.DAO.MenuDAO;
import com.tap.model.Menu;
import com.tap.util.DBConnection;

public class MenuDAOImpl implements MenuDAO {

    private static final String INSERT_QUERY =
            "INSERT INTO menu (restaurant_id, item_name, description, price, "
            + "is_available, category, created_at, updated_at, deleted_at) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SELECT_QUERY =
            "SELECT * FROM menu WHERE menu_id = ?";

    private static final String UPDATE_QUERY =
            "UPDATE menu SET restaurant_id = ?, item_name = ?, description = ?, "
            + "price = ?, is_available = ?, category = ?, updated_at = ? "
            + "WHERE menu_id = ?";

    private static final String DELETE_QUERY =
            "DELETE FROM menu WHERE menu_id = ?";

    private static final String SELECT_ALL_QUERY =
            "SELECT * FROM menu";


    @Override
    public void addMenu(Menu menu) {

        Connection connection = DBConnection.getConnection();

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(INSERT_QUERY);

            pstmt.setInt(1, menu.getRestaurantId());
            pstmt.setString(2, menu.getItemName());
            pstmt.setString(3, menu.getDescription());
            pstmt.setDouble(4, menu.getPrice());
            pstmt.setInt(5, menu.getIsAvailable());
            pstmt.setString(6, menu.getCategory());

            pstmt.setTimestamp(7,
                    new Timestamp(System.currentTimeMillis()));

            pstmt.setTimestamp(8,
                    new Timestamp(System.currentTimeMillis()));

            pstmt.setTimestamp(9, menu.getDeletedAt());

            int i = pstmt.executeUpdate();

            System.out.println(i);

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }


    @Override
    public Menu getMenu(int menuId) {

        Menu menu = null;

        Connection connection = DBConnection.getConnection();

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(SELECT_QUERY);

            pstmt.setInt(1, menuId);

            ResultSet resultset = pstmt.executeQuery();

            if (resultset.next()) {

                menu = extractMenuFromResultSet(resultset);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return menu;
    }


    private Menu extractMenuFromResultSet(ResultSet res)
            throws SQLException {

        int menuId = res.getInt(1);
        int restaurantId = res.getInt(2);
        String itemName = res.getString(3);
        String description = res.getString(4);
        double price = res.getDouble(5);
        int isAvailable = res.getInt(6);
        String category = res.getString(7);

        Timestamp createdAt = res.getTimestamp(8);
        Timestamp updatedAt = res.getTimestamp(9);
        Timestamp deletedAt = res.getTimestamp(10);

        Menu menu = new Menu(
                menuId,
                restaurantId,
                itemName,
                description,
                price,
                isAvailable,
                category,
                createdAt,
                updatedAt,
                deletedAt
        );

        return menu;
    }


    @Override
    public void updateMenu(Menu menu) {

        Connection connection = DBConnection.getConnection();

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(UPDATE_QUERY);

            pstmt.setInt(1, menu.getRestaurantId());
            pstmt.setString(2, menu.getItemName());
            pstmt.setString(3, menu.getDescription());
            pstmt.setDouble(4, menu.getPrice());
            pstmt.setInt(5, menu.getIsAvailable());
            pstmt.setString(6, menu.getCategory());

            pstmt.setTimestamp(7,
                    new Timestamp(System.currentTimeMillis()));

            pstmt.setInt(8, menu.getMenuId());

            pstmt.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }


    @Override
    public void deleteMenu(int menuId) {

        Connection connection = DBConnection.getConnection();

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(DELETE_QUERY);

            pstmt.setInt(1, menuId);

            pstmt.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }


    @Override
    public List<Menu> getAllMenu() {

        ArrayList<Menu> allMenu =
                new ArrayList<Menu>();

        Connection connection =
                DBConnection.getConnection();

        try {

            Statement stmt =
                    connection.createStatement();

            ResultSet res =
                    stmt.executeQuery(SELECT_ALL_QUERY);

            while (res.next()) {

                Menu menu =
                        extractMenuFromResultSet(res);

                allMenu.add(menu);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return allMenu;
    }


    @Override
    public List<Menu> getMenuByRestaurantId(int restaurantId) {

        ArrayList<Menu> menuList =
                new ArrayList<Menu>();

        Connection connection =
                DBConnection.getConnection();

        String query =
                "SELECT * FROM menu WHERE restaurant_id = ?";

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(query);

            pstmt.setInt(1, restaurantId);

            ResultSet res =
                    pstmt.executeQuery();

            while (res.next()) {

                Menu menu =
                        extractMenuFromResultSet(res);

                menuList.add(menu);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return menuList;
    }

}