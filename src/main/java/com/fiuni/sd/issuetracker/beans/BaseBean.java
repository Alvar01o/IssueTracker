package com.fiuni.sd.issuetracker.beans;

import java.io.Serializable;

public abstract class BaseBean implements Serializable  {

	private static final long serialVersionUID = 1L;

	public Integer getId() {
		return _id;
	}

	public void setId(Integer id) {
		_id = id;
	}


	private Integer _id;

}
