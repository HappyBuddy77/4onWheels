package com._onWheels._onWheels;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="cart_items")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class CartItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;
	
	@Column(name = "product_name")
	private String productName;
	
	@Column(name = "product_id")
	private String productId;

	private String color;
	private String battery_capacity; 
    @Column(name = "`range`")
    private String range;    
    private String charging_time;     
    private String top_speed;         
    private String acceleration;
	
	@Column(name = "product_img")
	private String product_img;
	
	private int quantity;
	
	private double price;

	public CartItem(Cart cart, String productName, String productId, int quantity, String color, String battery_capacity, String range, String charging_time, String top_speed, String acceleration, double price) {
		this.cart = cart;
		this.productName = productName;
		this.productId = productId;
		this.quantity = quantity;
		this.color = color;
		this.battery_capacity = battery_capacity;
		this.range = range;
		this.charging_time = charging_time;
		this.top_speed = top_speed;
		this.acceleration = acceleration;
		this.price = price;
	}
	
	public double getTotalprice() {
		return price*quantity;
	}
}
