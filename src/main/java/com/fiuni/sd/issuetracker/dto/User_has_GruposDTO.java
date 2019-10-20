package com.fiuni.sd.issuetracker.dto;

public class User_has_GruposDTO extends BaseDTO{
	private UserDTO user;
	private GruposDTO grupo;
	
	public void setUser(UserDTO u) {
		this.user = u;
	}
	public UserDTO getUser() {
		return this.user;
	}
	public void setGrupo(GruposDTO g) {
		this.grupo = g;
	}
	public GruposDTO getGrupo() {
		return this.grupo;
	}

}
