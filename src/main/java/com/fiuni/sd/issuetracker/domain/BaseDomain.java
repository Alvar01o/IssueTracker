package com.fiuni.sd.issuetracker.domain;

import java.io.Serializable;

public abstract class BaseDomain implements Serializable  {

	private static final long serialVersionUID = 1L;

	public Integer getId() {
		return _id;
	}

	public void setId(Integer id) {
		_id = id;
	}

	private Integer _id;

}
