package com._onWheels._onWheels.database;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com._onWheels._onWheels.Order;
import com._onWheels._onWheels.OrderItem;
import com._onWheels._onWheels.OrderItemRepository;
import com._onWheels._onWheels.OrderRepository;
import com._onWheels._onWheels.Vehicle;
import com._onWheels._onWheels.VehicleRepository;
import com._onWheels._onWheels.users.User;
import com._onWheels._onWheels.users.UserRepository;

@Component
public class DatabaseSeed {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private VehicleRepository vehicleRepo;

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private OrderItemRepository orderItemRepo;
    @Autowired
    PasswordEncoder encoder;

    @EventListener
    public void seed(ApplicationReadyEvent event) {
        User user;
        Vehicle vehicle;
        Optional<User> u = userRepo.findById((long) 1);

        Optional<Vehicle> v = vehicleRepo.findById((long) 1);

        // user
        if (!u.isPresent()) {
            user = new User("sunny@gmail.com", encoder.encode("Password_2"), "sunny", "", LocalDateTime.now());
            userRepo.save(user);
        } else {
            user = u.get();
        }

        // vehicle
        if (!v.isPresent()) {
            vehicle = new Vehicle("Tesla", "Model  3", 2024, 70000, "model 3");
            vehicleRepo.save(vehicle);
        } else {
            vehicle = v.get();
        }

        // order
        // if (user != null && vehicle != null) {
        //     Order order = orderRepo.save(new Order(user.getId(), Order.generateOrderNumber(), vehicle.getPrice()));
        //     // orderItem
        //     orderItemRepo.save(
        //             new OrderItem(order, vehicle.getFullName(), Long.toString(vehicle.getId()), 1, vehicle.getPrice()));
        // }
    }
}
