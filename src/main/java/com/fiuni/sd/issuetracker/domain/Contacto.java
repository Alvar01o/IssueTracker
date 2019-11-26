package com.fiuni.sd.issuetracker.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
@Entity
public class Contacto  extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private Integer id;
	@Column
	private String email;
	@Column
	private String descripcion;	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public void setDescripcion(String n) {
		this.descripcion = n;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}	
	public void setEmail(String n) {
		this.email = n;
	}
	public String getEmail() {
		return this.email;
	}
}
