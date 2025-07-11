package com._onWheels._onWheels.review;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com._onWheels._onWheels.User;
import com._onWheels._onWheels.UserRepository;
import com._onWheels._onWheels.Vehicle;
import com._onWheels._onWheels.VehicleRepository;

@Controller
public class ReviewController {
    // private static final Logger logger =
    // LoggerFactory.getLogger(ReviewController.class);

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/vehicle/{vehicle_id}")
    public String viewReviews(@PathVariable long vehicle_id, Model model) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(vehicle_id);
        List<Review> reviews = reviewRepository.findAllByVehicleId(vehicle_id);
        Vehicle v = vehicle.orElse(new Vehicle());
        model.addAttribute("vehicle", v);
        model.addAttribute("reviews", reviews);
        model.addAttribute("average_rating", avgRating(reviews));

        return "vehicle";
    }

    private double avgRating(List<Review> reviews) {
        double ratings = 0;
        for( Review r : reviews) {
            ratings += r.getRating();
        }
        return ratings / reviews.size();
    }

    @GetMapping("/review_form/{vehicle_id}")
    public String reviewForm(@PathVariable long vehicle_id, Model model) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(vehicle_id);
        List<Review> reviews = reviewRepository.findAllByVehicleId(vehicle_id);
        Vehicle v = vehicle.orElse(new Vehicle());
        model.addAttribute("vehicle", v);
        model.addAttribute("reviews", reviews);

        return "review_form";
    }

    @PostMapping("/review/{vehicle_id}")
    public String WriteReview(@PathVariable String vehicle_id, @ModelAttribute ReviewDTO review, Principal principal) {
        try {
            User u = userRepo.findByEmail(principal.getName());
            System.out.println(principal.getName());
            System.out.println(u);
            if (u != null) {
               Review r = reviewService.WriteReview(u.getId(), Long.parseLong(vehicle_id), review);
               if(r == null) {
                System.out.println("Review not Created");
               }
            }
            
        } catch (Exception e) {
            // logger.error(e);
            System.out.println(e);
        }
        return "redirect:/vehicle/" + vehicle_id;
        // return "redirect:/newVehicle/" + vehicle_id;
    }
}
