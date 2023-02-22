package co.grandcircus.cartapi.controller;

public class ProductNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ProductNotFoundException(Long id) {
		super("ID Not Found");
	}
}
