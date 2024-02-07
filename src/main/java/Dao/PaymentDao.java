package Dao;

import Model.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentDao {
    private final Connection connection;

    public PaymentDao() {
        this.connection = DBConnection.getConnection();
    }

    public void createPayment(Payment payment) {
        String query = "insert into payment (orderId,paymentAmount,paymentDate,paymentMethod,status,transactionId)values (?,?,?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, payment.getOrderId());
            preparedStatement.setDouble(2, payment.getPaymentAmount());
            preparedStatement.setString(3, payment.getPaymentDate());
            preparedStatement.setString(4, payment.getPaymentMethod());
            preparedStatement.setString(5, payment.getStatus());
            preparedStatement.setString(6, payment.getTransactionId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Payment getPaymentById(int paymentId) {
        String query = "select * from payment where id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, paymentId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Payment payment = new Payment();
                    payment.setId(resultSet.getInt("id"));
                    payment.setOrderId(resultSet.getInt("orderId"));
                    payment.setPaymentAmount(resultSet.getDouble("paymentAmount"));
                    payment.setPaymentDate(resultSet.getString("paymentDate"));
                    payment.setPaymentMethod(resultSet.getString("paymentMethod"));
                    payment.setStatus(resultSet.getString("status"));
                    payment.setTransactionId(resultSet.getString("transactionId"));
                    return payment;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updatePayment(Payment payment) {
        String query = "update payment set orderId=?,paymentAmount=?,paymentDate=?,paymentMethod=?,status=?,transactionId=? where id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, payment.getOrderId());
            preparedStatement.setDouble(2, payment.getPaymentAmount());
            preparedStatement.setString(3, payment.getPaymentDate());
            preparedStatement.setString(4, payment.getPaymentMethod());
            preparedStatement.setString(5, payment.getStatus());
            preparedStatement.setString(6, payment.getTransactionId());
            preparedStatement.setInt(7, payment.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePayment(int paymentId) {
        String query = "delete from payment where id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, paymentId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Payment> getAllPayments(){
        List<Payment> payments = new ArrayList<>();
        String query = "select * from payment";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery()){
            while (resultSet.next()){
                Payment payment = new Payment();
                payment.setId(resultSet.getInt("id"));
                payment.setOrderId(resultSet.getInt("orderId"));
                payment.setPaymentAmount(resultSet.getDouble("paymentAmount"));
                payment.setPaymentDate(resultSet.getString("paymentDate"));
                payment.setPaymentMethod(resultSet.getString("paymentMethod"));
                payment.setStatus(resultSet.getString("status"));
                payment.setTransactionId(resultSet.getString("transactionId"));
                payments.add(payment);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return payments;
    }
}
