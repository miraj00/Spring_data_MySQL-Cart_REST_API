package co.grandcircus.cartapi.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
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
	// GET /cart-items
	@GetMapping("/cart-items")
	public List<Cart> readAll (				
		@RequestParam(required = false) String product)	{
	
		if(product!= null) {
			return cartRepo.findByProduct(product);
		} else {
			return cartRepo.findAll();
		}
	}
	
	//	GET - /cart-items/{id}
	@GetMapping("/cart-items/{id}")
	public Cart readOne(@PathVariable("id") Long id) {
		return cartRepo.findById(id).orElseThrow(()-> new ProductNotFoundException(id));
	}
	
	// POST - /cart-items   ---> Use this in Body : {"product":"HeadPhone", "price":40,	"quantity":1}
	@PostMapping("/cart-items")
	@ResponseStatus(HttpStatus.CREATED)
	public Cart createOrAdd (@RequestBody Cart cart) {
		cart.setId(null);
		cartRepo.save(cart);
		// cartRepo.insert(cart);    ---> can use insert
		return cart;			
	}
		
	// PUT - cart-items/{id}   ---> use this to edit id 1 :   {"id": 1, "product": "Stencil", "price": 16.5, "quantity": 2}
	@PutMapping("/cart-items/{id}")
	public Cart updateCart (@RequestBody Cart cart, @PathVariable("id") Long id) {
		cart.setId(id);
		cartRepo.save(cart);
		return cart;
	}
		
		
		
	
}	
