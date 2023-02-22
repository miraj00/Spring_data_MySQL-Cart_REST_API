package co.grandcircus.cartapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cart_items")
public class Cart {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long id;
		private String product;
		private Double price;
		private int quantity;
		
		
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public String getProduct() {
			return product;
		}
		public void setProduct(String product) {
			this.product = product;
		}
		public Double getPrice() {
			return price;
		}
		public void setPrice(Double price) {
			this.price = price;
		}
		public int getQuantity() {
			return quantity;
		}
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
		
		
		public Cart() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		public Cart(long id, String product, Double price, int quantity) {
			super();
			this.id = id;
			this.product = product;
			this.price = price;
			this.quantity = quantity;
		}
			
		public Cart(String product, Double price, int quantity) {
			super();
			this.product = product;
			this.price = price;
			this.quantity = quantity;
		}
	
		
}
