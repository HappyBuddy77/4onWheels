package com._onWheels._onWheels;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

import com._onWheels._onWheels.review.Review;

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

    private String battery_capacity;
    @Column(name = "`range`")
    private String range;
    private String charging_time;
    private String top_speed;
    private String acceleration;

    private String image_url;

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
}