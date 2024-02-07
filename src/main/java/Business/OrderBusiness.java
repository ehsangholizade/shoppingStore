package Business;

import Dao.OrderDao;
import Model.Order;

import java.util.List;

public class OrderBusiness {
    private final OrderDao orderDao;
    public OrderBusiness(){this.orderDao=new OrderDao();}
    public void createOrder(Order order){orderDao.createOrder(order);}
    public Order getOrderById(int orderId){return orderDao.getOrderById(orderId);}
    public void updateOrder(Order order){orderDao.updateOrder(order);}
    public void deleteOrder(int orderId){orderDao.deleteOrder(orderId);}
    public List<Order> getAllOrders(){return orderDao.getAllOrders();}
    public double calculateAverageTotalPrice() {
        List<Order> orders = orderDao.getAllOrders();
        if (orders.isEmpty()){
            return 0.0;
        }
        double totalPrice = 0.0;
        for (Order order : orders){
            totalPrice += order.getTotalPrice();
        }
        return totalPrice / orders.size();
    }
}