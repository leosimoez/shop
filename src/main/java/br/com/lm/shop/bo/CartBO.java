package br.com.lm.shop.bo;

import java.math.BigDecimal;
import java.util.Set;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter @NoArgsConstructor @AllArgsConstructor(access=AccessLevel.PRIVATE) @ToString(callSuper=true) @EqualsAndHashCode(callSuper=false) @Builder
public class CartBO {
	
	private String zipCode;
	
	private Set<CartItemBO> items;
	
	private BigDecimal shipCost = BigDecimal.ZERO;
	
	public BigDecimal getTotal() {
		
		return items != null
				? items.stream().map(CartItemBO::getSubTotal).reduce(BigDecimal::add).get().add(shipCost)
				: BigDecimal.ZERO;
		
	}
	
}
