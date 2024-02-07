import Business.*;
import Dao.DBConnection;
import Dao.DBInitializer;
import Model.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ShoppingStoreApp {
    public static void main(String[] args) throws SQLException {
        DBConnection.getConnection();
        DBInitializer.initializeDatabase();
        try {
            simulateScenario();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection();
        }
    }

    private static void simulateScenario() {
        PaymentBusiness paymentBusiness = new PaymentBusiness();
        ProductBusiness productBusiness = new ProductBusiness();
        CustomerBusiness customerBusiness = new CustomerBusiness();
        OrderBusiness orderBusiness = new OrderBusiness();
        OrderItemBusiness orderItemBusiness = new OrderItemBusiness();

        createSampleProduct(productBusiness);
        createSampleCustomer(customerBusiness);
        createSampleOrder(orderBusiness, customerBusiness, productBusiness);
        createSampleOrderItems(orderItemBusiness, orderBusiness, productBusiness, customerBusiness);
        createSamplePayments(paymentBusiness, orderBusiness);
        retrieveAndDisplayInformation(productBusiness, customerBusiness, orderBusiness, orderItemBusiness);
    }

    private static void createSampleProduct(ProductBusiness productBusiness) {
        Product product1 = new Product();
        product1.setName("apple");
        product1.setPrice(10.4);
        product1.setQuantity(100);
        product1.setDescription("red");
        productBusiness.createProduct(product1);

        Product product2 = new Product();
        product2.setName("banana");
        product2.setPrice(20);
        product2.setQuantity(200);
        product2.setDescription("yellow");
        productBusiness.createProduct(product2);
    }

    private static void createSampleCustomer(CustomerBusiness customerBusiness) {
        Customer customer = new Customer();
        customer.setName("ali");
        customer.setEmail("ali@40");
        customer.setAddress("tehran");
        customerBusiness.createCustomer(customer);

        Customer customer1 = new Customer();
        customer1.setName("ahmad");
        customer1.setEmail("ahmad@50");
        customer1.setAddress("yazd");
        customerBusiness.createCustomer(customer1);
    }

    private static void createSampleOrder(OrderBusiness orderBusiness, CustomerBusiness customerBusiness, ProductBusiness productBusiness) {
        List<Customer> customers = customerBusiness.getAllCustomers();
        List<Product> products = productBusiness.getAllProducts();
        if (customers.isEmpty() || products.isEmpty()) {
            System.out.println("no customer or product for create orders");
            return;
        }
        for (Customer customer : customers) {
            Order order = new Order();
            order.setCustomerId(customer.getId());
            order.setTotalPrice(0);
            order.setStatus("wating");
            order.setOrderDate(LocalDate.now().toString());
//            int numberOfProduct = (int) (Math.random() * 4) + 1;
//            for (int i = 0; i < numberOfProduct; i++) {
//                int randomProductIndex = (int) (Math.random() * products.size());
//                Product selectProduct = products.get(randomProductIndex);
//                order.setTotalPrice(order.getTotalPrice() + selectProduct.getPrice());
//            }
//            for (Product product  : products) {
//                double price = product.getPrice();
//                order.setTotalPrice(order.getTotalPrice()+price);
//            }
            orderBusiness.createOrder(order);
        }
    }

    private static void createSampleOrderItems(
            OrderItemBusiness orderItemBusiness,
            OrderBusiness orderBusiness,
            ProductBusiness productBusiness,
            CustomerBusiness customerBusiness
    ) {

        List<Product> products = productBusiness.getAllProducts();
        List<Order> orders = orderBusiness.getAllOrders();
        OrderItem orderItem = new OrderItem();
        for (Order order : orders) {
            for (Product product : products) {
                double price = product.getPrice();
                orderItem.setOrderId(order.getId());
                orderItem.setProductId(product.getId());
                orderItem.setPrice(price);
                orderItem.setQuantity(1);
                orderItemBusiness.createOrderItem(orderItem);
            }
        }

        int customer1 = customerBusiness.getCustomerById(orders.get(0).getCustomerId()).getId();
        int customer2 = customerBusiness.getCustomerById(orders.get(1).getCustomerId()).getId();


        List<OrderItem> orderItems = orderItemBusiness.getAllOrderItems();
        double totalPrice1 = 0;
        double totalPrice2 = 0;
        for (OrderItem item : orderItems) {
            if (item.getOrderId() == customer1) {
                totalPrice1 = totalPrice1 + (item.getQuantity() * item.getPrice());
            }
            if (item.getOrderId() == customer2) {
                totalPrice2 = totalPrice2 + (item.getQuantity() * item.getPrice());
            }
        }

        for (Order order : orders) {
            if (order.getId() == customer1) {
                Order order1 = new Order();
                order1.setTotalPrice(totalPrice1);
                order1.setOrderDate(order.getOrderDate());
                order1.setCustomerId(order.getCustomerId());
                order1.setStatus("success");
                order1.setId(order.getId());
                orderBusiness.updateOrder(order1);
            }
            if (order.getId() == customer2) {
                Order order2 = new Order();
                order2.setTotalPrice(totalPrice2);
                order2.setOrderDate(order.getOrderDate());
                order2.setCustomerId(order.getCustomerId());
                order2.setStatus("success");
                order2.setId(order.getId());
                orderBusiness.updateOrder(order2);
            }
        }
    }

    private static void createSamplePayments(
            PaymentBusiness paymentBusiness,
            OrderBusiness orderBusiness
    ) {
        List<Order> orders = orderBusiness.getAllOrders();
        for (Order order : orders) {
            Payment payment = new Payment();
            payment.setOrderId(order.getId());
            payment.setPaymentAmount(order.getTotalPrice());
            payment.setPaymentDate(LocalDate.now().toString());
            paymentBusiness.creatPayment(payment);
        }

    }

    private static void retrieveAndDisplayInformation(
            ProductBusiness productBusiness,
            CustomerBusiness customerBusiness,
            OrderBusiness orderBusiness,
            OrderItemBusiness orderItemBusiness

    ) {
        // Retrieve and display product catalog
        List<Product> products = productBusiness.getAllProducts();
        if (!products.isEmpty()) {
            System.out.println("Product Catalog:");
            for (Product product : products) {
                System.out.println("Product ID: " + product.getId());
                System.out.println("Name: " + product.getName());
                System.out.println("Description: " + product.getDescription());
                System.out.println("Price: $" + product.getPrice());
                System.out.println("------------------------------");
            }
        } else {
            System.out.println("No products in the catalog.");
        }

        // Retrieve and display customer information
        List<Customer> customers = customerBusiness.getAllCustomers();
        if (!customers.isEmpty()) {
            System.out.println("\nCustomer Information:");
            for (Customer customer : customers) {
                System.out.println("Customer ID: " + customer.getId());
                System.out.println("Name: " + customer.getName());
                System.out.println("Address: " + customer.getAddress());
                System.out.println("------------------------------");
            }
        } else {
            System.out.println("No customer information available.");
        }

        // Retrieve and display order details
        List<Order> orders = orderBusiness.getAllOrders();
        if (!orders.isEmpty()) {
            System.out.println("\nOrder Details:");
            for (Order order : orders) {
                System.out.println("Order ID: " + order.getId());
                System.out.println("Customer Name: " + customerBusiness.getCustomerById(order.getCustomerId()).getName());
                System.out.println("Total Price: $" + order.getTotalPrice());

                // Retrieve and display order items
                List<OrderItem> orderItems = orderItemBusiness.getAllOrderItems();
                if (!orderItems.isEmpty()) {
                    System.out.println("Order Items:");
                    for (OrderItem orderItem : orderItems) {
                        System.out.println("Product Name: " + productBusiness.getProductById(orderItem.getProductId()).getName());
                        System.out.println("Price: $" + orderItem.getPrice());
                        System.out.println("Quantity: " + orderItem.getQuantity());
                        System.out.println("------------------------------");
                    }
                } else {
                    System.out.println("No order items for this order.");
                }
                System.out.println("------------------------------");
            }
        } else {
            System.out.println("No order details available.");
        }
    }
}

