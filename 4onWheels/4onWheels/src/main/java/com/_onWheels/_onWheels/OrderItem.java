package com._onWheels._onWheels;

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
    private Order order;
    
    @Column(name = "product_name")
    private String productName;
    
    @Column(name = "product_id")
    private String productId;
    
    private int quantity;
    
    private double price;
    
    @Column(name = "product_image")
    private String productImage;
    
    public OrderItem(Order order, String productName, String productId, int quantity, double price) {
        this.order = order;
        this.productName = productName;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }
    
    public double getTotalPrice() {
        return price * quantity;
    }
}