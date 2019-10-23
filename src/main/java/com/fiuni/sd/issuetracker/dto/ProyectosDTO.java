package com.fiuni.sd.issuetracker.dto;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



public class ProyectosDTO  extends BaseDTO{
	
	private String nombre;
	private String descripcion;
	private GruposDTO grupo;
	//tableros
	public void setGrupo(GruposDTO g) {
		this.grupo = g;
	}

	public GruposDTO getGrupo() {
		return this.grupo;
	}
	
	public void setNombre(String n) {
		this.nombre = n;
	}

	public String getNombre() {
		return this.nombre;
	}
	public void setDescripcion(String n) {
		this.descripcion = n;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	@Override
	public String toString() {//grupo_id=" + grupo.toString() + "
		return "Proyecto [nombre=" + nombre + ",descripcion="+descripcion+", grupo = "+grupo.toString()+"]";
	}
}
