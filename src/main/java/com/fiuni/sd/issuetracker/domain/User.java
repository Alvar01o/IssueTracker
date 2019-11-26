package com.fiuni.sd.issuetracker.domain;


import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.transaction.Transactional;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fiuni.sd.issuetracker.dto.RolDTO;

@Entity
@Transactional
public class User extends BaseDomain implements UserDetails{

	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private Integer id;
	@Column
	private String nombre;
	@Column
	private String apellido;
	@Column(unique = true)
	private String email;
	@Basic(optional = false)
	@CreationTimestamp
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date creacion = new Date();
	@Column
	private String pass;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER  , cascade = CascadeType.ALL )
    
    private Set<UserRoles> user_roles;
	
    public void addRol(UserRoles r) {
		this.user_roles.add(r);
	}
	
	public Set<UserRoles> getUserRoles() {
		return user_roles;
	}

	public void setRoles(Set<UserRoles> roles) {
		this.user_roles = roles;
	}
   
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setNombre(String n) {
		this.nombre= n;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public void setApellido(String ap) {
		this.apellido = ap;
	}
	
	public String getApellido() {
		return this.apellido;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Date getCreacion() {
		return this.creacion;
	}
	
	public void setCreacion(Date c) {
		this.creacion = c;
	}
	
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public String getPass() {
		return this.pass;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + nombre + ", lastName=" + apellido + ", email=" + email
				+ ", username=" + email + ", password=" + pass + ", user_roles=" + user_roles + "]";
	}
	/*USERDETAIL IMPLMENTATION*/
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.getUserRoles().stream().map(role -> new  SimpleGrantedAuthority( role.getRol().getName())).collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return this.getPass();
	}

	@Override
	public String getUsername() {
		return this.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
