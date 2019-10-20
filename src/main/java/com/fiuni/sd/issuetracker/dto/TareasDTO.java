package com.fiuni.sd.issuetracker.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "tareas")
public class TareasDTO  extends BaseDTO{
	
	private String nombre;
	private String descripcion;
	private int prioridad;
	private String estado;
	private Date creacion;
	private Date limite;
	@XmlElement
	public String getEstado() {
		return this.estado;
	}
	
	public void setEstado(String s) {
		this.estado = s;
	}
	
	public void setPrioridad(int p) {
		this.prioridad = p;
	}
	@XmlElement
	public Date getLimite() {
		return this.limite;
	}
	
	public void setLimite(Date d) {
		this.limite = d;
	}
	@XmlElement
	public Date getCreacion() {
		return this.creacion;
	}
	
	public void setCreacion(Date d) {
		this.creacion = d;
	}
	@XmlElement
	public int getPrioridad() {
		return this.prioridad;
	}
	
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
