package com.fiuni.sd.issuetracker.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TareasDTO  extends BaseDTO{
	
	private String nombre;
	private String descripcion;
	private int prioridad;
	private String estado;
	@JsonFormat(pattern = "dd/MM/yyyy")
	// Allows dd/MM/yyyy date to be passed into GET request in JSON
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date creacion;
	@JsonFormat(pattern = "dd/MM/yyyy")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date limite;

	public String getEstado() {
		return this.estado;
	}
	
	public void setEstado(String s) {
		this.estado = s;
	}
	
	public void setPrioridad(int p) {
		this.prioridad = p;
	}

	public Date getLimite() {
		return this.limite;
	}
	
	public void setLimite(Date d) {
		this.limite = d;
	}

	public Date getCreacion() {
		return this.creacion;
	}
	
	public void setCreacion(Date d) {
		this.creacion = d;
	}

	public int getPrioridad() {
		return this.prioridad;
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
