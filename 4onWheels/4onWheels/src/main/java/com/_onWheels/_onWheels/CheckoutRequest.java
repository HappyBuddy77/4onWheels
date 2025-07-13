package com._onWheels._onWheels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutRequest {

	//billing info
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String addressStreet;
	private String addressCity;
	private String addressProvince;
	private String addressPostal;
	
	//payment info
	private String paymentType;
	private String cardNumber;
	private String expriyDate;
	private String cvv;
	
	
	//pickup Info
	private String pickupLocation;
	private String pickupDate;
	private String pickupTime;
}
