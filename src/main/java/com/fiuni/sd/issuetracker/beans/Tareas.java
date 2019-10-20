package com.fiuni.sd.issuetracker.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
@Entity
public class Tareas extends BaseBean{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private Integer id;
	@Column
	private String nombre;
	@Column
	private String descripcion;
	@Column
	private int prioridad;
	@Column
	private String estado;
	@Column
	private Date creacion;
	@Column
	private Date limite;

	private static final String BAJO = "bajo";
	private static final String MEDIO = "medio";	
	private static final String ALTO = "alto";
	private static final String PAUSA = "pausa";

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public Date getlimite() {
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
	public String getEstado() {
		return this.estado;
	}
	
	public void setEstado(String s) {
		this.estado = s;
	}
	
	public void setPrioridad(int p) {
		this.prioridad = p;
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
	
	@Override
	public String toString() {
		return "Tareas [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", prioridad=" + prioridad
				+ ", estado=" + estado + ", creacion=" + creacion + ", limite=" + limite + "]";
	}
	
}
