package com.fiuni.sd.issuetracker.dao;


import org.springframework.stereotype.Repository;

import com.fiuni.sd.issuetracker.domain.Proyectos;
import com.fiuni.sd.issuetracker.domain.Tareas;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface IProyectosDao extends CrudRepository<Proyectos, Integer>  {
	public Page<Proyectos> findAll(Pageable pageable);
	public Page<Proyectos> findByNombreIgnoreCaseOrDescripcionIgnoreCase(String search1, String search2, Pageable pageable);
	
}
