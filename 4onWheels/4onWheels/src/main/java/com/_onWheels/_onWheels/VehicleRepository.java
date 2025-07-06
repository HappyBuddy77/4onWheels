package com._onWheels._onWheels;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    
    List<Vehicle> findByMake(String make);
    
    List<Vehicle> findByType(String type);
    
    List<Vehicle> findByPriceBetween(double minPrice, double maxPrice);
    
    List<Vehicle> findByMakeAndModel(String make, String model);
    
    List<Vehicle> findByYear(int year);
    
    @Query("SELECT v FROM Vehicle v WHERE LOWER(v.make) LIKE LOWER(CONCAT('%', ?1, '%')) OR LOWER(v.model) LIKE LOWER(CONCAT('%', ?1, '%'))")
    List<Vehicle> searchByMakeOrModel(String searchTerm);
    
    List<Vehicle> findAllByOrderByPriceAsc();
    
    List<Vehicle> findAllByOrderByPriceDesc();
    
    List<Vehicle> findAllByOrderByYearDesc();
}