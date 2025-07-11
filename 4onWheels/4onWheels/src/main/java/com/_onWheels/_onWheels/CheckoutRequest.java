package com._onWheels._onWheels;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutRequest {

	//billing info
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String address;
	private String city;
	private String province;
	private String postal_code;
	
	//payment info
	private String paymentMethod;
	private String cardNumber;
	private String expriyDate;
	private String cvv;
	
	
	//pickup Info
	private String pickupLocation;
	private String pickupDate;
	private String pickupTime;
}
