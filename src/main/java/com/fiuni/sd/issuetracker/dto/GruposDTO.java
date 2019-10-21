package com.fiuni.sd.issuetracker.dto;

import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fiuni.sd.issuetracker.domain.User;

public class GruposDTO extends BaseDTO {
	private String nombre;
	private Date creacion;
	//usuarios
	
	public void setNombre(String n) {
		this.nombre= n;
	}

	public String getNombre() {
		return this.nombre;
	}

	public Date getCreacion() {
		return this.creacion;
	}
	
	public void setCreacion(Date c) {
		this.creacion = c;
	}
	
}
