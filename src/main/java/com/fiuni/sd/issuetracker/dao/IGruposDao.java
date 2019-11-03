package com.fiuni.sd.issuetracker.dao;

import com.fiuni.sd.issuetracker.domain.Grupos;
import com.fiuni.sd.issuetracker.domain.Tareas;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGruposDao extends PagingAndSortingRepository<Grupos, Integer>  {
	public Page<Grupos> findAll(Pageable pageable);
	public Page<Grupos> findByNombreIgnoreCase(String search1, Pageable pageable);
}
