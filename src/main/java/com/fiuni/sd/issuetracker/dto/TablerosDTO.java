package com.fiuni.sd.issuetracker.dto;

import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fiuni.sd.issuetracker.domain.Tareas;



public class TablerosDTO  extends BaseDTO {
	private String nombre; 
	private String descripcion;
	private Set<Tareas> tareas;
	public Set<Tareas> getTareas() {
		return tareas;
	}

	public void setTareas(Set<Tareas> tareas) {
		this.tareas = tareas;
	}
	public void setNombre(String n) {
		this.nombre= n;
	}

	public String getNombre() {
		return this.nombre;
	}
	
	public void setDescripcion(String d) {
		this.descripcion= d;
	}

	public String getDescripcion() {
		return this.descripcion;
	}	
}
