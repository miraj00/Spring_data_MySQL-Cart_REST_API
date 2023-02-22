package co.grandcircus.cartapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.grandcircus.cartapi.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

	List<Cart> findByProduct(String product);

}
