package com._onWheels._onWheels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import com._onWheels._onWheels.Order;

@Data
public class OrderDTO {
    private String orderNumber;
    private String orderStatus;
    private double totalAmount;
    private int numOfItems;
    
    public OrderDTO(Order order){
        this.orderNumber = order.getOrderNumber();
        this.orderStatus = order.getOrderStatus();
        this.totalAmount = order.getTotal();
        this.numOfItems = order.getOrderItems().size();
    }
    public static List<OrderDTO> toList(List<Order> orders){
        List<OrderDTO> list = new ArrayList<>();
        for(Order o : orders) {
            list.add(new OrderDTO(o));
        }
        return list;
    }
}
