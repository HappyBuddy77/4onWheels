package com._onWheels._onWheels.review;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._onWheels._onWheels.Order;
import com._onWheels._onWheels.OrderItem;
import com._onWheels._onWheels.OrderRepository;
import com._onWheels._onWheels.Vehicle;
import com._onWheels._onWheels.VehicleRepository;
import com._onWheels._onWheels.users.User;
import com._onWheels._onWheels.users.UserRepository;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private VehicleRepository vehicleRepo;

    @Autowired
    private OrderRepository orderRepo;

    public List<Review> findAllByVehicleId(long vehicleId){
        return reviewRepository.findAllByVehicleId(vehicleId);
    }

    public double avgRating(List<Review> reviews) {
        double ratings = 0;
        for (Review r : reviews) {
            ratings += r.getRating();
        }
        return ratings / reviews.size();
    }

    public Review WriteReview(long userId, long vehicleId, ReviewDTO dto) throws CreateReviewException {
        Optional<User> user = userRepo.findById(userId);
        User u = user.orElseThrow();
        Optional<Vehicle> vehicle = vehicleRepo.findById(vehicleId);
        Vehicle v = vehicle.orElseThrow();

        Review review = new Review(u, dto.getReview(), dto.getRating(), v);

        if (isVehiclePurchased(review.getUser().getId(), review.getVehicle().getId())) {
            Review r = reviewRepository.save(review);
            if (r == null)
                throw new CreateReviewException();
            return r;
        }
        return null;
    }

    private boolean isVehiclePurchased(long userId, long vehicleId) {
        List<Order> orders = orderRepo.findByUserIdWithItems(userId);
        if (orders.size() > 0) {
            for (Order o : orders) {
                for (OrderItem item : o.getOrderItems()) {
                    if (Long.parseLong(item.getProductId()) == vehicleId) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
}
