package com.fiuni.sd.issuetracker.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.fiuni.sd.issuetracker.domain.Contacto;
@Repository
public interface IContactosDao extends PagingAndSortingRepository<Contacto, Integer>{

}
