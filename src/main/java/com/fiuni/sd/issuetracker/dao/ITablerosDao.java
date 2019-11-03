package com.fiuni.sd.issuetracker.dao;

import org.springframework.stereotype.Repository;

import com.fiuni.sd.issuetracker.domain.Tableros;
import com.fiuni.sd.issuetracker.domain.Tareas;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

@Repository
public interface ITablerosDao extends PagingAndSortingRepository<Tableros, Integer>  {
	public Page<Tableros> findAll(Pageable pageable);
	public Page<Tableros> findByNombreIgnoreCaseOrDescripcionIgnoreCase(String search1, String search2, String search3, Pageable pageable);	
	public Tableros findFirstByOrderByIdDesc();
}
