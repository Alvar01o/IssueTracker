package com.fiuni.sd.issuetracker.dto;

public class User_has_TareasDTO  extends BaseDTO{
		private UserDTO user;
		private TareasDTO tareas;
		
		public UserDTO getUser() {
			return this.user;
		}
		
		public void setUser(UserDTO u) {
			this.user =u;
		}
		
		public TareasDTO getTareas() {
			return this.tareas;
		}
		
		public void setTareas (TareasDTO t) {
			this.tareas = t;
		}
		
}
