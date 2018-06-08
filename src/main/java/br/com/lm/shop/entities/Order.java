package br.com.lm.shop.entities;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name="ORDERS")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString(callSuper=true) @EqualsAndHashCode(of="id",callSuper=true) @Builder
public class Order extends BasicEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ORDER_ID")
	@JsonView(ViewLevel.Minimal.class)
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CUSTOMER_ID", nullable=false)
	@NotNull
	@JsonView(ViewLevel.Public.class)
	private CustomerAccount customer;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="CUSTOMER_ADDRESS_ID", nullable=false)
	@NotNull
	@JsonView(ViewLevel.Public.class)
	private CustomerAddress deliveryAddress; 
	
	@Enumerated(EnumType.STRING)
	@Column(name="STATUS",nullable=false, length=20)
	@NotNull
	@JsonView(ViewLevel.Public.class)
	private OrderStates state;
	
	@Enumerated(EnumType.STRING)
	@Column(name="PAYMENT_METHOD",nullable=false, length=20)
	@NotNull
	@JsonView(ViewLevel.Public.class)
	private PaymentMethod paymentMethod;
	
	@Column(name="TOTAL_VALUE", nullable=false)
	@NotNull @PositiveOrZero
	@JsonView(ViewLevel.Public.class)
	private BigDecimal totalValue;
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL, mappedBy="order")
	@JsonView(ViewLevel.Public.class)
	@JsonManagedReference
	private Set<OrderItem> items;
	
	
	public void addItem(OrderItem item) {
		item.setOrder(this);
		items.add(item);
	}
	
	public BigDecimal getTotalValue() {
		_calculate();
		return totalValue;
	}
	
	public void setTotalValue(BigDecimal totalValue) {
		_calculate();
	}

	@PrePersist @PreUpdate
	private void _calculate() {
		if(items != null)
			totalValue = new BigDecimal(
					items.stream().mapToDouble(item -> item.getSubTotal().doubleValue()).sum()
			);
		else
			totalValue = BigDecimal.ZERO;
	}

}
