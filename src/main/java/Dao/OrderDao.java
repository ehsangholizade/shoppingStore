package Dao;

import Model.Order;
import Model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
    private  final Connection connection;

    public OrderDao() {
        this.connection = DBConnection.getConnection();
    }


    public void createOrder(Order order) {
        String query = "insert into 'order' (orderDate,status,totalPrice,customerId)values (?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, order.getOrderDate());
            preparedStatement.setString(2, order.getStatus());
            preparedStatement.setDouble(3, order.getTotalPrice());
            preparedStatement.setInt(4, order.getCustomerId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Order getOrderById(int orderId) {
        String query = "select * from 'order' where id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, orderId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Order order = new Order();
                    order.setId(resultSet.getInt("id"));
                    order.setOrderDate(resultSet.getString("orderDate"));
                    order.setStatus(resultSet.getString("status"));
                    order.setTotalPrice(resultSet.getDouble("totalPrice"));
                    order.setCustomerId(resultSet.getInt("customerId"));
                    return order;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void updateOrder(Order order){
        String query = "update 'order' set orderDate=?,status=?,totalPrice=?,customerId=? where id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1,order.getOrderDate());
            preparedStatement.setString(2,order.getStatus());
            preparedStatement.setDouble(3,order.getTotalPrice());
            preparedStatement.setInt(4,order.getCustomerId());
            preparedStatement.setInt(5,order.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteOrder(int orderId){
        String query = "delete from 'order' where id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1,orderId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM 'order' ";
        try (PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet resultSet = pstmt.executeQuery()) {
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt("id"));
                order.setOrderDate(resultSet.getString("orderDate"));
                order.setStatus(resultSet.getString("status"));
                order.setTotalPrice(resultSet.getDouble("totalPrice"));
                order.setCustomerId(resultSet.getInt("customerId"));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
}
