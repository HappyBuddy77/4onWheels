package com._onWheels._onWheels.review;

import java.security.Principal;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com._onWheels._onWheels.users.User;
import com._onWheels._onWheels.users.UserRepository;

@Controller
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserRepository userRepo;

    @PostMapping("/review/{vehicle_id}")
    public String WriteReview(@PathVariable String vehicle_id, @ModelAttribute ReviewDTO review, Principal principal) {
        try {
            User u = userRepo.findByEmail(principal.getName());
            if (u != null) {
                Review r = reviewService.WriteReview(u.getId(), Long.parseLong(vehicle_id), review);
                if (r == null) {
                    System.out.println("Review not Created");
                }
            }

        } catch (Exception e) {
        }

        return "redirect:" + review.getRedirect();
    }
}
