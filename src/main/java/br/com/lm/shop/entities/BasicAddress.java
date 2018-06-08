package br.com.lm.shop.entities;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonView;

import br.com.lm.shop.view.ViewLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString(callSuper=true)
@JsonView(ViewLevel.Public.class)
public abstract class BasicAddress extends BasicEntity {

	private static final long serialVersionUID = 1L;
	
	@Column(name="ADDRESS_TYPE", nullable=false, length=20)
	@Enumerated(EnumType.STRING)
	@NotNull
	protected AddressType type;
	
	@Column(name="LINE1", nullable=false, length=100)
	@NotEmpty @Size(min=3, max=100)
	protected String line1;
	
	@Column(name="LINE2", nullable=true, length=100)
	@Size(min=3, max=100)
	protected String line2;
	
	@Column(name="LINE3", nullable=true, length=100)
	@Size(min=3, max=100)
	protected String line3;
	
	@Column(name="CITY", nullable=false, length=100)
	@NotEmpty @Size(min=3, max=100)
	protected String city;
	
	@Column(name="STATE", nullable=false, length=100)
	@NotEmpty @Size(min=2, max=3)
	protected String state;
	
	@Column(name="COUNTRY", nullable=false, length=100)
	@NotEmpty @Size(min=3, max=100)
	protected String country;
	
	@Column(name="ZIP_CODE", nullable=false, length=100)
	@NotEmpty @Pattern(regexp="^(\\d{5}\\-\\d{3})$")
	protected String zipCode;

}
