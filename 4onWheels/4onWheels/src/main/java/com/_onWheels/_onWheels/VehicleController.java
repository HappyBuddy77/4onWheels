package com._onWheels._onWheels;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com._onWheels._onWheels.review.Review;
import com._onWheels._onWheels.review.ReviewService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class VehicleController {

	@Autowired
	private VehicleRepository vehicleRepository;

	@Autowired
	private ReviewService reviewService;

	// @GetMapping("/HomePage")
	@GetMapping({ "/", "/HomePage" })
	public String viewAllVehicles(Model model) {
		List<Vehicle> vehicles = vehicleRepository.findAll();
		model.addAttribute("vehicles", vehicles);
		return "home";
	}

	@GetMapping("/usedVehicle/{id}")
	public String viewUsedVehicleDetail(@PathVariable Long id, Model model, HttpServletRequest req) {
		Optional<Vehicle> vehicle = vehicleRepository.findById(id);
		model.addAttribute("vehicles", vehicle.get());
		setReviews(id, model, req);
		return "usedVehicle";
	}

	@GetMapping("/newVehicle/{id}")
	public String viewNewVehicleDetail(@PathVariable Long id, Model model, HttpServletRequest req) {
		Optional<Vehicle> vehicle = vehicleRepository.findById(id);
		model.addAttribute("vehicles", vehicle.get()); // used by review form to redirect back to either
														// usedVehicle.html or newVehicle.html
		setReviews(id, model, req);
		return "newVehicle";
	}

	private void setReviews(long vehicle_id, Model model, HttpServletRequest req) {
		List<Review> reviews = reviewService.findAllByVehicleId(vehicle_id);
		model.addAttribute("reviews", reviews);
		model.addAttribute("average_rating", reviewService.avgRating(reviews));
		model.addAttribute("redirectPath", req.getRequestURI());
	}

	@GetMapping("/vehicles/sort")
	public String sortVehicles(@RequestParam String sortbY, Model model) {

		List<Vehicle> vehicles;

		switch (sortbY) {
			case "price_low":
				vehicles = vehicleRepository.findAllByOrderByPriceAsc();
				break;

			case "price_high":
				vehicles = vehicleRepository.findAllByOrderByPriceDesc();
				break;

			case "year":
				vehicles = vehicleRepository.findAllByOrderByYearDesc();
				break;

			default:
				vehicles = vehicleRepository.findAll();
				break;

		}
		model.addAttribute("vehicles", vehicles);
		return "vehicles";
	}
}
