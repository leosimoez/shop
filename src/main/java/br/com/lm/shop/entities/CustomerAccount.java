package br.com.lm.shop.entities;

import java.io.Serializable;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
@Table(name="CUSTOMERS_ACCOUNTS")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString(callSuper=true) @EqualsAndHashCode(of="id") @Builder
public class CustomerAccount implements Serializable {

	private static final long serialVersionUID = 1L;
	

	@Id
	@Column(name="CUSTOMER_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonView(ViewLevel.Minimal.class)
	private Long id;
	
	@Column(name="EMAIL", nullable=false, unique=true)
	@NotEmpty @Size(min=6, max=100) @Email
	@JsonView(ViewLevel.Public.class)
	private String email;
	
	@Column(name="ACCOUNT_TYPE", nullable=false)
	@Enumerated(EnumType.STRING)
	@NotNull
	@JsonView(ViewLevel.Public.class)
	private AccountType type;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="customer", cascade=CascadeType.ALL)
	@JsonView(ViewLevel.Complete.class)
	private Set<CustomerAddress> addresses;
	
	
}
