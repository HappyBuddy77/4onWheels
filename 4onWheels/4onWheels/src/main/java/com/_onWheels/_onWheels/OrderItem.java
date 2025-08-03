package com._onWheels._onWheels;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "order_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Order order;
    
    @Column(name = "product_name")
    private String productName;
    
    @Column(name = "product_id")
    private String productId;
    
    private int quantity;
    
    private double price;
    
    @Column(name = "product_image")
    private String product_img;
    
	private String battery_capacity; 
    @Column(name = "`range`")
    private String range;    
    private String charging_time;     
    private String top_speed;         
    private String acceleration;
    private String color;
    
    public OrderItem(Order order, String productName, String productId, int quantity, double price) {
        this.order = order;
        this.productName = productName;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }
    
    public OrderItem(Order order, CartItem cartItem) {
        this.order = order;
        this.productName = cartItem.getProductName();
        this.productId = cartItem.getProductId();
        this.quantity = cartItem.getQuantity();
        this.price = cartItem.getPrice();
        this.battery_capacity = cartItem.getBattery_capacity();
        this.range = cartItem.getRange();
        this.charging_time = cartItem.getCharging_time();
        this.top_speed = cartItem.getTop_speed();
        this.acceleration = cartItem.getAcceleration();
        this.product_img = cartItem.getProduct_img();
        this.color = cartItem.getColor();
        }
    
    public double getTotalPrice() {
        return price * quantity;
    }

    public double getQuantity(){
        return this.quantity;
    }
}