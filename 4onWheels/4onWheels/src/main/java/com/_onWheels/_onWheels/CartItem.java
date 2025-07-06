package com._onWheels._onWheels;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

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
	private String product_Id;
	
	@Column(name = "product_img")
	private String product_img;
	
	private int quantity;
	
	private double price;

	public CartItem(Cart cart, String productName, String product_Id, int quantity, double price) {
		this.cart = cart;
		this.productName = productName;
		this.product_Id = product_Id;
		this.quantity = quantity;
		this.price = price;
	}
	
	public double getTotalprice() {
		return price*quantity;
	}
}
