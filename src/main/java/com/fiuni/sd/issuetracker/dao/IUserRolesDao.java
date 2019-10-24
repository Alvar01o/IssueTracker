package com.fiuni.sd.issuetracker.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.fiuni.sd.issuetracker.domain.UserRoles;

public interface IUserRolesDao extends CrudRepository<UserRoles, Integer>  {
	
}
