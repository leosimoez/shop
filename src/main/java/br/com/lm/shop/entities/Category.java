package br.com.lm.shop.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="CATEGORIES")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString(callSuper=true) @EqualsAndHashCode(of="id",callSuper=true) @Builder
public class Category extends BasicEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="CATEGORY_ID",updatable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="NAME", nullable=false)
	@NotEmpty @Size(min=3, max=50)
	private String name;
	
	@Column(name="DESCRIPTION", nullable=false)
	@NotEmpty @Size(min=3, max=250)
	private String description;
	
	@JsonIgnore
	@ManyToMany(mappedBy="categories",fetch=FetchType.LAZY)
	private Set<Product> products;
//	
	
	

	
}
