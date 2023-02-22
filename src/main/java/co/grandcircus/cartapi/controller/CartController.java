package co.grandcircus.cartapi.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import co.grandcircus.cartapi.model.Cart;
import co.grandcircus.cartapi.repository.CartRepository;

@RestController
public class CartController {

	@Autowired
	private CartRepository cartRepo;
	
	
	// @PostConstruct means run this automatically when the application starts. This creates all objects above.
	@PostConstruct
	public void initializeDatabaseIfEmpty() {
		if (cartRepo.count() == 0) {
			this.reset();
		}	
	}
	
	@GetMapping ("/reset")
	public String reset() {
	
		cartRepo.deleteAll();
		
		Cart cr = new Cart("Pencil", 10.5, 6);
		cartRepo.save(cr);
		
		cr = new Cart("Notebook", 25.40, 4);
		cartRepo.save(cr);
		
		cr = new Cart("Computer", 850.50, 1);
		cartRepo.save(cr);
		
		cr = new Cart("Purse", 45.20, 1);
		cartRepo.save(cr);
		
		cr = new Cart("Cell-Phone", 950.50, 1);
		cartRepo.save(cr);
		
		cr = new Cart("Printer", 350.25, 1);
		cartRepo.save(cr);
		
		cr = new Cart("Pen", 30.20, 7);
		cartRepo.save(cr);
		
		cr = new Cart("Cup", 30.20, 1);
		cartRepo.save(cr);
		
		cr = new Cart("Napkins", 5.70, 1);
		cartRepo.save(cr);
		
		cr = new Cart("Coco-cola", 10.10, 4);
		cartRepo.save(cr);
		
		return "Data Reset";
			
	}
		
	@GetMapping("/cart-items")
	public List<Cart> readAll (				
		@RequestParam(required = false) String product)	{
	
		if(product!= null) {
			return cartRepo.findByProduct(product);
		} else {
			return cartRepo.findAll();
		}
		
		
	}
}	
