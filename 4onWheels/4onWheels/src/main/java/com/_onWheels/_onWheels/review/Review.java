package com._onWheels._onWheels.review;

import com._onWheels._onWheels.User;
import com._onWheels._onWheels.Vehicle;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "review")
@Data
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String review;

    private double rating; // 5, 4.1, or 3.5 stars

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Review(User user, String review, double rating, Vehicle vehicle) {
        this.user = user;
        this.review = review;
        this.rating = rating;
        this.vehicle = vehicle;
    }
}
