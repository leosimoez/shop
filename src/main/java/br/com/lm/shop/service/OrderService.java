package br.com.lm.shop.service;

import java.util.HashSet;

import br.com.lm.shop.entities.CustomerAddress;
import br.com.lm.shop.entities.Order;
import br.com.lm.shop.entities.OrderItem;
import br.com.lm.shop.entities.OrderStates;

public class OrderService {
	
	public Order createEmptyCart() {
		return Order.builder()
			.deliveryAddress(new CustomerAddress())
			.items(new HashSet<OrderItem>())
			.state(OrderStates.CART)
			.build();
	}
	
	public Order addItem(Order order, OrderItem item) {
		
			return order;
	}

}
