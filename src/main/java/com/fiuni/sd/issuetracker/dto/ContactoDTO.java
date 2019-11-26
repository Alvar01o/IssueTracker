package com.fiuni.sd.issuetracker.dto;


public class ContactoDTO extends BaseDTO{
	private Integer id;

	private String email;

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
