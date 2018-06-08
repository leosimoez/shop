package br.com.lm.shop.entities;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

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
@Table(name="PRODUCTS")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString(callSuper=true) @EqualsAndHashCode(of="id",callSuper=false) @Builder
@JsonView(ViewLevel.Complete.class)
public class Product extends BasicEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PRODUCT_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(ViewLevel.Minimal.class)
	private Long id;

	@Column(name = "NAME",length=100,nullable=false)
	@NotEmpty @Size(min = 3, max = 100)
	@JsonView(ViewLevel.Public.class)
	private String name;

	@Column(name = "SHORT_DESCRIPTION", length=250, nullable=false)
	@NotEmpty @Size(min = 3, max = 250)
	private String shortDescription;

	@Lob
	@Column(name = "FULL_DESCRIPTION", length = 2048, nullable=false)
	@NotEmpty @Size(min = 3, max = 2048)
	private String fullDescription;

	@Column(name = "UNIT_PRICE", precision = 9, scale = 2, nullable=false)
	@Digits(integer = 9, fraction = 2) @PositiveOrZero
	@JsonView(ViewLevel.Public.class)
	private BigDecimal unitPrice;

	@Column(name = "CURRENT_STOCK", nullable=false)
	@PositiveOrZero
	private Integer currentStock;

	@Column(name = "ENABLED", nullable=false)
	@NotNull
	private Boolean enabled;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getFullDescription() {
		return fullDescription;
	}

	public void setFullDescription(String fullDescription) {
		this.fullDescription = fullDescription;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Integer getCurrentStock() {
		return currentStock;
	}

	public void setCurrentStock(Integer currentStock) {
		this.currentStock = currentStock;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinTable(name = "PRODUCT_HAS_CATEGORY", joinColumns = { @JoinColumn(name = "PRODUCT_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "CATEGORY_ID") })
	@JsonIgnore
	private Set<Category> categories;

	
	
}
