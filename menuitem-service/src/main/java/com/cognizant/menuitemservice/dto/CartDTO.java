package com.cognizant.menuitemservice.dto;

import java.util.ArrayList;
import java.util.List;

import com.cognizant.menuitemservice.model.MenuItem;

public class CartDTO {

	private List<MenuItem> cartItems = new ArrayList<MenuItem>();

	private double total;

	public CartDTO() {
		super();
	}

	public CartDTO(List<MenuItem> cartItems, double total) {
		super();
		this.cartItems = cartItems;
		this.total = total;
	}

	public List<MenuItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<MenuItem> cartItems) {
		this.cartItems = cartItems;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

}
