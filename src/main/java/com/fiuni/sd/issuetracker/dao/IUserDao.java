package com.fiuni.sd.issuetracker.dao;

import org.springframework.stereotype.Repository;

import com.fiuni.sd.issuetracker.domain.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;


public interface IUserDao extends CrudRepository<User, Integer>  {
	public Page<User> findAll(Pageable pageable);
	public Page<User> findByNombreIgnoreCaseOrApellidoIgnoreCaseOrEmailIgnoreCase(String search1, String search2, String search3, Pageable pageable);
	
}
