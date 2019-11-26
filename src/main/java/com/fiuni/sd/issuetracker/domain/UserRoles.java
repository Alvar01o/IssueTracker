package com.fiuni.sd.issuetracker.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;

@Entity
public class UserRoles extends BaseDomain{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private User user;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Rol rol;

    @OneToOne
    @JoinColumn(name = "proyecto_id", nullable = true)
    private Proyectos proyecto;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
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
