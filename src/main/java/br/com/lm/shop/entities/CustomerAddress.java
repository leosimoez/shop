package br.com.lm.shop.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name="CUSTOMERS_ADDRESSES")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString(callSuper=true) @EqualsAndHashCode(of="id",callSuper=true) @Builder
public class CustomerAddress extends BasicAddress {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="CUSTOMER_ADDRESS_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonView(ViewLevel.Minimal.class)
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CUSTOMER_ID", nullable=false)
	@NotNull
	@JsonIgnore
	private CustomerAccount customer;

	@Builder
	public CustomerAddress(Long id, CustomerAccount customer,AddressType type, String line1, String line2, String line3, String city, String state,
			String country, String zipCode) {
		super(type, line1, line2, line3, city, state, country, zipCode);
		this.id = id;
		this.customer = customer;
		// TODO Auto-generated constructor stub
	}
	
	


}
