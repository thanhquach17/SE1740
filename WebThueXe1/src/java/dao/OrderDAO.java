/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import entites.Order;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class OrderDAO extends DBContext {

    public boolean addOrder(Order order) {
    PreparedStatement statement = null;

    try {
        // Create the SQL query
        String query = "INSERT INTO orders (account_id, product_id, price, time_start, time_end) "
                + "VALUES (?, ?,  ?, ?, ?)";
        statement = connection.prepareStatement(query);

        // Set the parameter values
        statement.setInt(1, order.getAccountId());
        statement.setInt(2, order.getProductId());
        statement.setDouble(3, order.getPrice());

        statement.setTimestamp(4, order.getStart());
        statement.setTimestamp(5, order.getEnd());

        // Execute the query
        int rowsInserted = statement.executeUpdate();

        // Check if any rows were inserted
        return rowsInserted > 0;
    } catch (SQLException e) {
        // Handle any database errors here
        e.printStackTrace();
    } finally {
        // Close the statement
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    return false;
}


   public List<Order> getOrderToManage(int page, int pageSize) {
    List<Order> orders = new ArrayList<>();
    int offset = (page - 1) * pageSize;
    try (
        PreparedStatement stmt = connection.prepareStatement(
            "SELECT o.id, a.fullname, p.name, o.price, o.created_at, o.status, o.time_start, o.time_end "
            + "FROM orders o "
            + "JOIN accounts a ON a.id = o.account_id "
            + "JOIN products p ON o.product_id = p.id "
            + "ORDER BY o.created_at DESC "
            + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;"
        )
    ) {
        stmt.setInt(1, offset);
        stmt.setInt(2, pageSize);
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Order order = new Order(
                    rs.getInt("id"),
                    rs.getString("fullname"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getTimestamp("created_at"),
                    rs.getInt("status"),
                    rs.getTimestamp("time_start"),
                    rs.getTimestamp("time_end")
                );
                orders.add(order);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return orders;
}


public List<Order> getOrderByAccountID(int page, int pageSize, int accId) {
    List<Order> orders = new ArrayList<>();
    int offset = (page - 1) * pageSize;
    try (
        PreparedStatement stmt = connection.prepareStatement(
            "SELECT o.id, a.fullname, p.name, o.price, o.created_at, o.status, o.time_start, o.time_end "
            + "FROM orders o "
            + "JOIN accounts a ON a.id = o.account_id "
            + "JOIN products p ON o.product_id = p.id "
            + "WHERE a.id = ? "
            + "ORDER BY o.created_at DESC "
            + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY"
        )
    ) {
        stmt.setInt(1, accId);
        stmt.setInt(2, offset);
        stmt.setInt(3, pageSize);
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Order order = new Order(
                    rs.getInt("id"),
                    rs.getString("fullname"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getTimestamp("created_at"),
                    rs.getInt("status"),
                    rs.getTimestamp("time_start"),
                    rs.getTimestamp("time_end")
                );
                orders.add(order);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return orders;
}


    public int getTotalOrdersCount() {
        int count = 0;
        try (
                PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(*) AS count FROM Orders"); ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public int getTotalOrdersUserCount(int accountId) {
        int count = 0;
        try (
                PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(*) AS count FROM Orders WHERE account_id = ?");) {
            stmt.setInt(1, accountId); // Set the account_id value
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public boolean confirmOrder(int orderId) {
        try (PreparedStatement stmt = connection.prepareStatement("UPDATE Orders SET status = 1 WHERE id = ?")) {
            stmt.setInt(1, orderId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean cancelOrder(int orderId) {
        try (PreparedStatement stmt = connection.prepareStatement("UPDATE Orders SET status = 2 WHERE id = ?")) {
            stmt.setInt(1, orderId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Timestamp getCurrentTimestamp() {
        return Timestamp.valueOf(LocalDateTime.now());
    }

    public static void main(String[] args) {
        OrderDAO dao = new OrderDAO();
        System.out.println("a");
        List<Order> list = dao.getOrderByAccountID(6, 1, 1);
        for (Order order : list) {
            System.out.println("Order details:");
            System.out.println(order);
        }

    }

}
