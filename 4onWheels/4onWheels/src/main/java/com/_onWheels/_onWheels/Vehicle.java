package com._onWheels._onWheels;

import java.util.List;

import com._onWheels._onWheels.review.Review;

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
@Table(name = "vehicles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String make;
    private String model;
    private int year;
    private double price;

    @Column(length = 1000)
    private String description;

    private String color;
    private String type; 
    
    @Column(name = "ev_history")
    private String evHistory;
    
    private String battery_capacity; 
    @Column(name = "`range`")
    private String range;    
    private String charging_time;     
    private String top_speed;         
    private String acceleration;
    
    private String image_url;
    @Column(name = "user_views", columnDefinition =  "INTEGER DEFAULT 0")
    private int userViews = 0;
    private int qty = 1;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Review> reviews;
    
    // Custom constructor for basic vehicle
    public Vehicle(String make, String model, int year, double price, String description) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.price = price;
        this.description = description;
    }

    // Helper method to get full name
    public String getFullName() {
        return year + " " + make + " " + model;
    }

    // Helper method to format price
    public String getFormattedPrice() {
        return String.format("$%.2f", price);
    }
    
    public boolean isUsed() {
        return "used".equalsIgnoreCase(type);
    }
    
    public boolean isNew() {
        return "new".equalsIgnoreCase(type);
    }
    
    public void incrementViews() {
        this.userViews++;
    }
    public int getQty() {
    return qty;
    }
    public void setQty(int qty) {
        this.qty = qty;
    }
}