package br.com.lm.shop.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonView;

import br.com.lm.shop.view.ViewLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "ORDERS_ITEMS")
@IdClass(OrderItemPK.class)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString(callSuper=true) @EqualsAndHashCode(of= {"order","product"},callSuper=false) @Builder
public class OrderItem extends BasicEntity {

	private static final long serialVersionUID = 1L;
	
	@Id	@ManyToOne @JoinColumn(name = "ORDER_ID")
	@JsonBackReference
	private Order order;

	@Id	@ManyToOne @JoinColumn(name = "PRODUCT_ID")
	@JsonView(ViewLevel.Public.class)
	private Product product;

	@Column(name = "QUANTITY", nullable=false)
	@NotNull @Positive
	@JsonView(ViewLevel.Public.class)
	private Integer quantity;

	@Column(name = "SUBTOTAL", nullable=false)
	@NotNull @PositiveOrZero
	@JsonView(ViewLevel.Public.class)
	private BigDecimal subTotal;
	
	public BigDecimal getSubTotal() {
		_setSubTotal();
		return subTotal;
	}
	
	public void setSubTotal(BigDecimal subtotal) {
		_setSubTotal();
	}

	@PreUpdate @PrePersist
	private void _setSubTotal() {
		if(product != null && quantity != null)
			subTotal = new BigDecimal(quantity * product.getUnitPrice().doubleValue());
		else
			subTotal = BigDecimal.ZERO;
	}

}
