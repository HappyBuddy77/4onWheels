package com._onWheels._onWheels.review;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._onWheels._onWheels.User;
import com._onWheels._onWheels.UserRepository;
import com._onWheels._onWheels.Vehicle;
import com._onWheels._onWheels.VehicleRepository;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private VehicleRepository vehicleRepo;

    public Review WriteReview(long userId, long vehicleId, ReviewDTO dto) throws CreateReviewException {
        Optional<User> user = userRepo.findById(userId);
        User u = user.orElseThrow();
        Optional<Vehicle> vehicle = vehicleRepo.findById(vehicleId);
        Vehicle v =  vehicle.orElseThrow();

        Review review = new Review(u, dto.getReview(), dto.getRating() , v );

        if (isVehiclePurchased(review.getUser().getId(), review.getVehicle().getId())) {
            Review r = reviewRepository.save(review);
            if (r == null)
                throw new CreateReviewException();
            return r;
        }
        return null;
    }

    private boolean isVehiclePurchased(long userId, long vehicleId) {
        return true;
    }
}
