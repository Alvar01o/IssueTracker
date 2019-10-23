package com.fiuni.sd.issuetracker.dto;

import com.fiuni.sd.issuetracker.domain.Proyectos;
import com.fiuni.sd.issuetracker.domain.Rol;
import com.fiuni.sd.issuetracker.domain.User;

public class UserRolDTO  extends BaseDTO{
	private UserDTO user;
	private RolDTO rol;
	private ProyectosDTO proyecto;
	
    public void setProyecto(ProyectosDTO p) {
    	this.proyecto = p;
    }
    public ProyectosDTO getProyectos() {
    	return this.proyecto;
    }
    public RolDTO getRol() {
    	return this.rol;
    }
    public void setRol(RolDTO r) {
    	this.rol = r;
    }
    public UserDTO getUser() {
    	return this.user;
    }
    public void setUser(UserDTO u) {
    	this.user = u;
    }
}
