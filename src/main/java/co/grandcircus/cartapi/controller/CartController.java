package co.grandcircus.cartapi.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import co.grandcircus.cartapi.model.Cart;
import co.grandcircus.cartapi.repository.CartRepository;

@CrossOrigin
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
	/* GET  localhost:8080/cart-items?product=Printer  will return only Printer item
	 * GET  localhost:8080/cart-items will return all items
	 */
	@GetMapping("/cart-items")
		public List<Cart> readAll (				
			@RequestParam(required = false) String product,
			@RequestParam(required = false) Double maxPrice)	{
						
			if(product!= null) {
				return cartRepo.findByProduct(product);
			
			} else if (maxPrice !=null) { 
				List<Cart> cartList = cartRepo.findAll();
				
				List<Cart> itemsUnderMaxPrice = new ArrayList<>();
				// using for each loop to count total cost
					for (Cart c : cartList ) {						
						if(c.getPrice()<= maxPrice) {
							itemsUnderMaxPrice.add(c); 				 
			     		}
					}
				return itemsUnderMaxPrice;
								
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
		
	// DELETE - cart-items/{id}    ---> use link with id to delete : localhost:8080/cart-items/1
	@DeleteMapping("/cart-items/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) {
		cartRepo.deleteById(id);			
	}
	
	//GET /cart-items/total-cost
	@GetMapping("cart-items/total-cost")
	public Double totalCost () {
		
		List<Cart> cartList = cartRepo.findAll();
		
		Double total = 0.00;
		// using for each loop to count total cost
		for (Cart c : cartList ) {
			total+= c.getPrice() * c.getQuantity(); 
		}
		Double totalwithTax = total * 1.06;
		
		// formating to 2 decimals 
		DecimalFormat df = new DecimalFormat("#.##");      
		totalwithTax = Double.valueOf(df.format(totalwithTax));
		
		
		return totalwithTax;
	}
	
	// PATCH cart-items/{id}/add
	//Updates the quantity of an existing item in the cart whose id matches 
    //count: The amount to add to the cart. For example, if the cart presently has 3 apples and this API call has a count of 2, the cart will be updated to contain 5 apples.

	/*For Example : Use : 
	 *  QueryParams :  Key : count, Value : 4;    ---> With this endpoint turns to : localhost:8080/cart-items/4/add?count=4
	 *  Body :   
	 *    {
        	"id": 4,
        	"product": "Purse",
        	"price": 45.2,
        	"quantity": 1
    	  }
    	  
    	Upon success, Response body will be : 
    	  {
		    "id": 4,
		    "product": "Purse",
		    "price": 45.2,
		    "quantity": 5
		  }
	 */
	@PatchMapping("/cart-items/{id}/add")
	public Cart updateCartQuantity(@RequestBody Cart cart, @PathVariable("id") Long id, Integer count ) {

		cart.setQuantity(cart.getQuantity() + count);
		return cartRepo.save(cart);
		
	}
		
}	
