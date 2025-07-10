package com._onWheels._onWheels.review;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    @Query("SELECT r FROM Review r LEFT JOIN FETCH r.vehicle WHERE r.vehicle.id = ?1 ")
    List<Review> findAllByVehicleId(long vehicleId);
}
