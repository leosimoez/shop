package br.com.lm.shop.bo;

import java.math.BigDecimal;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter @AllArgsConstructor(access=AccessLevel.PRIVATE) @ToString(callSuper=true) @EqualsAndHashCode(of="productId",callSuper=false) @Builder
public class CartItemBO {
	
	private Long productId;
	
	private String productName;
	
	private BigDecimal unitPrice;
	
	private Integer quantity;
	
	public BigDecimal getSubTotal() {
		return unitPrice.multiply(BigDecimal.valueOf(quantity));
	}

}
