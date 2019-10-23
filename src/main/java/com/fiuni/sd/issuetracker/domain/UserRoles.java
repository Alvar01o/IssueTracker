package com.fiuni.sd.issuetracker.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class UserRoles extends BaseDomain{
	private static final long serialVersionUID = 1L;

	
    @Id
    @ManyToOne
    @JoinColumn
    private User user;

    @Id
    @ManyToOne
    @JoinColumn
    private Rol rol;
    

    @ManyToOne
    @MapsId("proyecto_id")
    private Proyectos proyecto;
    
    public void setProyecto(Proyectos p) {
    	this.proyecto = p;
    }
    public Proyectos getProyectos() {
    	return this.proyecto;
    }
    public Rol getRol() {
    	return this.rol;
    }
    public void setRol(Rol r) {
    	this.rol = r;
    }
    public User getUser() {
    	return this.user;
    }
    public void setUser(User u) {
    	this.user = u;
    }
    
}
