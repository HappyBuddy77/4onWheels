package com._onWheels._onWheels;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "carts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "user_id")
    private Long userId;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CartItem> cartItems;

	public Cart(Long userId) {
		
		this.userId = userId;
        this.createdAt = LocalDateTime.now();

	}
	
	//calculate total amount
	public double calculateTotal() {
		if(cartItems == null || cartItems.isEmpty()) {
			return 0.0;
		}
		double total =0.0;
		for(CartItem item: cartItems) {
			total += item.getTotalprice();
		}
		return total;
	}



}
