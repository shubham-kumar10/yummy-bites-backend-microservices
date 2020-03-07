package com.cognizant.menuitemservice.exception;

public class CartEmptyException extends Exception {
	public CartEmptyException() {
		super("Cart is Empty");
	}
}
