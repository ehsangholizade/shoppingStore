package Dao;

import Model.OrderItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDao {
    private Connection connection;

    public OrderItemDao() {
        this.connection = DBConnection.getConnection();
    }
    public void createOrderItem(OrderItem orderItem){
        String query = "insert into orderItem(productId,orderId,price,quantity) values (?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1,orderItem.getProductId());
            preparedStatement.setInt(2,orderItem.getOrderId());
            preparedStatement.setDouble(3,orderItem.getPrice());
            preparedStatement.setInt(4,orderItem.getQuantity());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public OrderItem getOrderItemById(int ordeItemId){
        String query = "select * from orderItem where id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1,ordeItemId);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()){
                    OrderItem orderItem = new OrderItem();
                    orderItem.setId(resultSet.getInt("id"));
                    orderItem.setOrderId(resultSet.getInt("orderId"));
                    orderItem.setProductId(resultSet.getInt("productId"));
                    orderItem.setPrice(resultSet.getDouble("price"));
                    orderItem.setQuantity(resultSet.getInt("quantity"));
                    return orderItem;
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void updateOrderItem(OrderItem orderItem){
        String query = "update orderItem set orderId=?,productId=?,price=?,quantity=? where id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1,orderItem.getOrderId());
            preparedStatement.setInt(2,orderItem.getProductId());
            preparedStatement.setDouble(3,orderItem.getPrice());
            preparedStatement.setInt(4,orderItem.getQuantity());
            preparedStatement.setInt(5,orderItem.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteOrderItem(int orderItemId){
        String query = "delete from orderItem where id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1,orderItemId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<OrderItem> getAllOrderItems(){
        List<OrderItem> orderItems = new ArrayList<>();
        String query = "select * from orderItem";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery()){
            while (resultSet.next()){
                OrderItem orderItem = new OrderItem();
                orderItem.setId(resultSet.getInt("id"));
                orderItem.setOrderId(resultSet.getInt("orderId"));
                orderItem.setProductId(resultSet.getInt("productId"));
                orderItem.setPrice(resultSet.getDouble("price"));
                orderItem.setQuantity(resultSet.getInt("quantity"));
                orderItems.add(orderItem);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItems;
    }
}
