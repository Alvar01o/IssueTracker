package com.fiuni.sd.issuetracker.dto;

public class MessageDTO extends BaseDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;

	public MessageDTO(String Message) {
		this.setMessage(Message);
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessage() {
		return this.message;
	}
}
