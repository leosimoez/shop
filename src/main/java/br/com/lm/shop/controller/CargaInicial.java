package br.com.lm.shop.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashSet;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.lm.shop.entities.AccountType;
import br.com.lm.shop.entities.AddressType;
import br.com.lm.shop.entities.Category;
import br.com.lm.shop.entities.CustomerAccount;
import br.com.lm.shop.entities.CustomerAddress;
import br.com.lm.shop.entities.Order;
import br.com.lm.shop.entities.OrderItem;
import br.com.lm.shop.entities.OrderStates;
import br.com.lm.shop.entities.PaymentMethod;
import br.com.lm.shop.entities.Product;
import br.com.lm.shop.view.ViewLevel;

@Controller
@RequestMapping("/carga")
public class CargaInicial {
	
	@Autowired
	private EntityManager em;
	
	@Autowired
	private ObjectMapper objectMapper;

	@GetMapping
	@ResponseBody
	@JsonView(ViewLevel.Public.class)
	@Transactional
	public Order carga() {
		
		Category cat = Category.builder().name("Notebookd").description("Notepooks").build();
		em.persist(cat);
		
		Product prod = Product.builder()
				.name("Dell Inspiron 5457")
				.fullDescription("Dell Inspiron 5457")
				.shortDescription("Dell Inspiron 5457")
				.currentStock(10)
				.enabled(true)
				.unitPrice(new BigDecimal(3500L))
				.categories(new HashSet<Category>())
				.build();
		prod.getCategories().add(cat);
		em.persist(prod);
		
		CustomerAccount cust = CustomerAccount.builder()
				.email("leoduda@gmail.com")
				.type(AccountType.GOOGLE)
				.addresses(new HashSet<CustomerAddress>())
				.build();
		
		em.persist(cust);
		
		CustomerAddress addr = CustomerAddress.builder()
				.city("Rio de Janeiro")
				.country("Brazil")
				.customer(cust)
				.line1("XXXX XXXX XXXXX")
				.line2("999 888 777")
				.state("RJ")
				.type(AddressType.HOME)
				.zipCode("99999-777")
				.build();
		em.persist(addr);
		
		Order order = Order.builder()
				.customer(cust)
				.deliveryAddress(addr)
				.items(new HashSet<OrderItem>())
				.paymentMethod(PaymentMethod.CREDITCARD)
				.state(OrderStates.ACCEPTED)
				.build();
		
		order.addItem(OrderItem.builder()
//				.id(new OrderItemPK(null, prod.getId()))
				.product(prod)
				.quantity(1)
				.build()
		);
		
		em.persist(order);
		
		return order;
		
		
	}
	
	@GetMapping("/parse")
	@ResponseBody
	@JsonView(ViewLevel.Public.class)
	public Order parse() throws JsonParseException, JsonMappingException, IOException {
		
		String aux = "{\"id\":1,\"customer\":{\"id\":1,\"email\":\"leoduda@gmail.com\",\"type\":\"GOOGLE\"},\"deliveryAddress\":{\"type\":\"HOME\",\"line1\":\"XXXX XXXX XXXXX\",\"line2\":\"999 888 777\",\"line3\":null,\"city\":\"Rio de Janeiro\",\"state\":\"RJ\",\"country\":\"Brazil\",\"zipCode\":\"99999-777\",\"id\":1},\"status\":\"ACCEPTED\",\"paymentMethod\":\"CREDITCARD\",\"totalValue\":3500,\"items\":[{\"product\":{\"id\":1,\"name\":\"Dell Inspiron 5457\",\"unitPrice\":3500},\"quantity\":1,\"subTotal\":3500}]}";
		
		Order order = objectMapper.readValue(aux, Order.class);
		
		return order;
		
	}
	
}
