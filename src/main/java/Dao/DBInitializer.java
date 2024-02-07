
package Dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBInitializer {
    public static void initializeDatabase() {
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement()) {
            createProductTable(statement);
            creatCustomerTable(statement);
            creatPaymentTable(statement);
            createOrderTable(statement);
            createOrderItemTable(statement);

            System.out.println("database initialized");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createProductTable(Statement statement) throws SQLException {
        statement.execute("DROP TABLE IF EXISTS Product");
        statement.execute("CREATE TABLE IF NOT EXISTS Product (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Name TEXT NOT NULL," +
                "Description TEXT," +
                "Price DOUBLE NOT NULL," +
                "quantity TEXT NOT NULL " +
                ")");
    }

    private static void creatCustomerTable(Statement statement) throws SQLException {
        statement.execute("DROP TABLE IF EXISTS customer");
        statement.execute("create table if not exists customer(" +
                "id integer primary key autoincrement," +
                "name text not null," +
                "email text not null," +
                "address text not null" +
                ")");
    }
    private static void creatPaymentTable(Statement statement)throws SQLException{
        statement.execute("DROP TABLE IF EXISTS payment");
        statement.execute("create table if not exists payment(" +
                "id integer primary key autoincrement," +
                "orderId integer  not null ," +
                "paymentAmount text not null, " +
                "paymentDate text not null," +
                "paymentMethod text," +
                "status text," +
                "transactionId text" +
                ")");
    }
    private static void createOrderTable(Statement statement)throws SQLException{
        statement.execute("DROP TABLE IF EXISTS 'order'");
        statement.execute("create table if not exists 'order'(" +
                "id integer primary key autoincrement ," +
                "orderDate text not null ," +
                "status text not null ," +
                "totalPrice double not null ," +
                "customerId integer not null " +
                ")");
    }
    private static void createOrderItemTable(Statement statement)throws SQLException{
        statement.execute("DROP TABLE IF EXISTS orderItem");
        statement.execute("create table if not exists orderItem(" +
                "id integer primary key autoincrement ,"+
                "orderId integer not null ,"+
                "productId integer not null ,"+
                "price double not null ,"+
                "quantity text not null "+
                ")");
    }
}
