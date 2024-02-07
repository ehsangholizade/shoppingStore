package Business;

import Dao.OrderItemDao;
import Model.OrderItem;


import java.util.List;

public class OrderItemBusiness {
    private OrderItemDao orderItemDao;
    public OrderItemBusiness(){this.orderItemDao =new OrderItemDao();}
    public void createOrderItem(OrderItem orderItem){orderItemDao.createOrderItem(orderItem);}
    public OrderItem getOrderItemById(int orderItemId){return orderItemDao.getOrderItemById(orderItemId);}
    public void updateOrderItem(OrderItem orderItem){orderItemDao.updateOrderItem(orderItem);}
    public void deleteOrderItem(int orderItemId){orderItemDao.deleteOrderItem(orderItemId);}
    public List<OrderItem> getAllOrderItems(){return orderItemDao.getAllOrderItems();}

    public double calculateAveragePrice() {
        List<OrderItem> orderItems = orderItemDao.getAllOrderItems();
        if (orderItems.isEmpty()) {
            return 0.0;
        }

        double totalPrice = 0.0;
        for (OrderItem orderItem: orderItems) {
            totalPrice += orderItem.getPrice();
        }

        return totalPrice / orderItems.size();
    }
}
