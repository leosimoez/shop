package br.com.lm.shop.service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.lm.shop.bo.CartBO;
import br.com.lm.shop.bo.CartItemBO;
import br.com.lm.shop.entities.Product;
import br.com.lm.shop.exception.InvalidProductException;
import br.com.lm.shop.exception.SoldOutProductException;
import br.com.lm.shop.repository.ProductRepository;

public class CartService {
	
	@Autowired
	ProductRepository products;
	
	public CartBO createEmptyCart() {
		return CartBO.builder().items(Collections.unmodifiableSet(new HashSet<CartItemBO>())).build();
	}

	public CartBO addOrReplaceItem(@NotNull CartBO cart, @NotNull Long productId, @NotNull @Positive Integer quantity) throws InvalidProductException, SoldOutProductException {
		
		Optional<Product> product = products.findById(productId);
		
		product.orElseThrow(() -> new InvalidProductException("Product not found") );
		
		if(product.get().getCurrentStock() < quantity) {
			throw new SoldOutProductException("Insuficient stock. Requested: "+quantity+". In sotck: "+product.get().getCurrentStock());
		}
		
		Set<CartItemBO> newItems = cart.getItems().stream().filter(i -> i.getProductId() != productId).collect(Collectors.toSet());
		
		product.ifPresent(p -> newItems.add(
				CartItemBO.builder()
				.productId(p.getId())
				.productName(p.getName())
				.unitPrice(p.getUnitPrice())
				.quantity(quantity)
				.build()
			)
		);
		
		return CartBO.builder()
				.items(Collections.unmodifiableSet(newItems))
				.shipCost(cart.getShipCost())
				.zipCode(cart.getZipCode())
				.build();
	}
	
	public CartBO removeItem(@NotNull CartBO cart, @NotNull Long productId) {
		
		Set<CartItemBO> newItems = cart.getItems().stream().filter(i -> i.getProductId() != productId).collect(Collectors.toSet());
		
		return CartBO.builder()
				.items(Collections.unmodifiableSet(newItems))
				.shipCost(cart.getShipCost())
				.zipCode(cart.getZipCode())
				.build();
	}
	
	//TODO
	//public CartBO setZipCode(CartBO cart) {
	//
	//}
	

}
