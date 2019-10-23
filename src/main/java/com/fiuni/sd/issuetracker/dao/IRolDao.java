package com.fiuni.sd.issuetracker.dao;
import org.springframework.stereotype.Repository;

import com.fiuni.sd.issuetracker.domain.Rol;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;


@Repository
public interface IRolDao extends CrudRepository<Rol, Integer>  {
	public Page<Rol> findAll(Pageable pageable);

	
}