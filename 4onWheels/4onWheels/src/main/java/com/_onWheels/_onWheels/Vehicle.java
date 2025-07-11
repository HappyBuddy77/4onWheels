package com._onWheels._onWheels;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

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
    
    public boolean isUsed() {
    	return "used".equalsIgnoreCase(type);
    	}
    public boolean isNew() {
    	return "new".equalsIgnoreCase(type);
    }
    
    private String battery_capacity; 
    @Column(name = "`range`")
    private String range;    
    private String charging_time;     
    private String top_speed;         
    private String acceleration; 
    
    private String image_url;
    @Column(name = "user_views", columnDefinition =  "INTEGER DEFAULT 0")
    private int userViews = 0;
    
    
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
    
    public void incrementViews() {
    	this.userViews++;
    }
}