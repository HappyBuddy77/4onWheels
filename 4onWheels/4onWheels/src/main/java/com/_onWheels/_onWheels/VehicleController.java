package com._onWheels._onWheels;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class VehicleController {

	@Autowired
	private VehicleRepository vehicleRepository;
	
	@GetMapping("/HomePage")
	public String viewAllVehicles(Model model) {
		List<Vehicle> vehicles = vehicleRepository.findAll();
		model.addAttribute("vehicles",vehicles);
		return "home";
	}
	
	@GetMapping("/vehicles/{id}")
	public String viewVehicleDetail(@PathVariable Long id,Model model) {
		Optional<Vehicle> vehicle = vehicleRepository.findById(id);
		model.addAttribute("vehicles",vehicle.get());
		return "vehicle-detail";
	}
	
	
	@GetMapping("/vehicles/sort")
	public String sortVehicles(@RequestParam String sortbY, Model model) {
		
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
		
		default:
			vehicles = vehicleRepository.findAll();
			break;
			
		}
		model.addAttribute("vehicles",vehicles);
		return "vehicles";
	}
}
