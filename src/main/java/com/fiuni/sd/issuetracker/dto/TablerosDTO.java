package com.fiuni.sd.issuetracker.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "tableros")
public class TablerosDTO  extends BaseDTO {
	private String nombre; 
	private String descripcion;

	public void setNombre(String n) {
		this.nombre= n;
	}
	@XmlElement
	public String getNombre() {
		return this.nombre;
	}
	
	public void setDescripcion(String d) {
		this.descripcion= d;
	}
	@XmlElement
	public String getDescripcion() {
		return this.descripcion;
	}	
}
