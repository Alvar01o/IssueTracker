package com.fiuni.sd.issuetracker.dao;

import org.springframework.stereotype.Repository;

import com.fiuni.sd.issuetracker.domain.Tareas;
import com.fiuni.sd.issuetracker.domain.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

@Repository
public interface ITareasDao extends PagingAndSortingRepository<Tareas, Integer>  {
	public Page<Tareas> findAll(Pageable pageable);
	public Page<Tareas> findByNombreIgnoreCaseOrDescripcionIgnoreCase(String search1, String search2, Pageable pageable);
}
