package com.fiuni.sd.issuetracker.dao;


import org.springframework.stereotype.Repository;

import com.fiuni.sd.issuetracker.domain.Proyectos;
import com.fiuni.sd.issuetracker.domain.Tareas;
import com.fiuni.sd.issuetracker.domain.User;
import com.fiuni.sd.issuetracker.dto.ProyectosResultDTO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

@Repository
public interface IProyectosDao extends PagingAndSortingRepository<Proyectos, Integer>  {
	public Page<Proyectos> findAll(Pageable pageable);
	public Page<Proyectos> findByNombreIgnoreCaseOrDescripcionIgnoreCase(String search1, String search2, Pageable pageable);
	public Page<Proyectos> findByGrupoId( Integer search, Pageable pageable);
}
