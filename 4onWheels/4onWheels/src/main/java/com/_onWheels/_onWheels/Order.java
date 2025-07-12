package com._onWheels._onWheels;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "user_id")
    private Long userId;
    
    @Column(name = "order_number")
    private String orderNumber;
    
    @Column(name = "total_amount")
    private double totalAmount;
    
    @Column(name = "order_status")
    private String orderStatus; 
    
    @Column(name = "payment_method")
    private String paymentMethod;
    
    @Column(name = "pickup_location")
    private String pickupLocation;
    
    @Column(name = "pickup_date")
    private String pickupDate;
    
    @Column(name = "pickup_time")
    private String pickupTime;
    
    // Billing Information
    @Column(name = "billing_first_name")
    private String billingFirstName;
    
    @Column(name = "billing_last_name")
    private String billingLastName;
    
    @Column(name = "billing_email")
    private String billingEmail;
    
    @Column(name = "billing_phone")
    private String billingPhone;
    
    @Column(name = "billing_address")
    private String billingAddress;
    
    @Column(name = "billing_city")
    private String billingCity;
    
    @Column(name = "billing_province")
    private String billingProvince;
    
    @Column(name = "billing_postal_code")
    private String billingPostalCode;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItem> orderItems;
    
    // Custom constructor
    public Order(Long userId, String orderNumber, double totalAmount) {
        this.userId = userId;
        this.orderNumber = orderNumber;
        this.totalAmount = totalAmount;
        this.orderStatus = "PENDING";
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    
    // Helper method to generate order number
    public static String generateOrderNumber() {
        return "ORD" + System.currentTimeMillis();
    }

    public double getTotal() {
        return totalAmount;
    }
}