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
	public String viewUsedVehicleDetail(@PathVariable Long id,Model model, HttpServletRequest req) {
		Optional<Vehicle> vehicleOpt = vehicleRepository.findById(id);
		
		Vehicle vehicle;
		if(vehicleOpt.isPresent()) {
			vehicle = vehicleOpt.get();
		
		
		vehicle.incrementViews();
		vehicleRepository.save(vehicle);
		
		model.addAttribute("vehicles",vehicle);
		setReviews(id, model, req);
		
		return "usedVehicle";
		}
		return "redirect:/HomePage";
	}

	@GetMapping("/newVehicle/{id}")
	public String viewNewVehicleDetail(@PathVariable Long id,Model model, HttpServletRequest req) {
		Optional<Vehicle> vehicleOpt = vehicleRepository.findById(id);
		Vehicle vehicle;
		
		if(vehicleOpt.isPresent()) {
			vehicle = vehicleOpt.get();
			
			vehicle.incrementViews();
			vehicleRepository.save(vehicle);
			model.addAttribute("vehicles",vehicle);
			setReviews(id, model, req);
			return "newVehicle";
			
		}
		return "redirect:/HomePage";
	}

	private void setReviews(long vehicle_id, Model model, HttpServletRequest req) {
		List<Review> reviews = reviewService.findAllByVehicleId(vehicle_id);
		model.addAttribute("reviews", reviews);
		model.addAttribute("average_rating", reviewService.avgRating(reviews));
		model.addAttribute("redirectPath", req.getRequestURI());
	}
	
	@GetMapping("/compare/{id1}/{id2}")
	public String compareVehicles(@PathVariable Long id1, @PathVariable Long id2, Model model) {
		Optional<Vehicle> vehicleOpt1 = vehicleRepository.findById(id1);
		Vehicle vehicle1;
		Optional<Vehicle> vehicleOpt2 = vehicleRepository.findById(id2);
		Vehicle vehicle2;
		if(vehicleOpt1.isPresent() && vehicleOpt2.isPresent()) {
			vehicle1 = vehicleOpt1.get();
			vehicle2 = vehicleOpt2.get();
			model.addAttribute("vehicles1",vehicle1);
			model.addAttribute("vehicles2",vehicle2);
		}
		else {
			return "compare_error";
		}
		return "compare";
	}

	@GetMapping("/HomePage/sort")
	public String sortNewVehicles(@RequestParam String sortbY, Model model) {
		
		List<Vehicle>vehicles;
		
		switch(sortbY)	{
		case "price_low":
			vehicles = vehicleRepository.findAllByOrderByPriceAsc();
			break;
		
		case "price_high":
			vehicles = vehicleRepository.findAllByOrderByPriceDesc();
			break;
		
		case "year":
			vehicles = vehicleRepository.findAllByOrderByYearDesc();
			break;
		
		case "top_five":
			vehicles = vehicleRepository.findTop5ByOrderByUserViewsDesc();
			break;
		
		default:
			vehicles = vehicleRepository.findAll();
			break;
			
		}
		model.addAttribute("vehicles",vehicles);
		return "home :: hotDeals-popup";
	}
}
