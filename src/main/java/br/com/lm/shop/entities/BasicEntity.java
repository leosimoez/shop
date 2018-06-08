package br.com.lm.shop.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonView;

import br.com.lm.shop.view.ViewLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString(callSuper=true)
@JsonView(ViewLevel.Complete.class)
public abstract class BasicEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Column(name="CREATED_AT", nullable=false, updatable=false, insertable=true)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonView(ViewLevel.Complete.class)
	protected Date createdAt;

	@Column(name="UPDATED_AT", updatable=false, insertable=true)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonView(ViewLevel.Complete.class)
	protected Date updatedAt;
	
	@PrePersist
	protected void _prePersist() {
		createdAt = new Date();
	}
	
	@PreUpdate
	protected void _preUpdate() {
		updatedAt = new Date();
	}


}
