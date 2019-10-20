package com.fiuni.sd.issuetracker.dao;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;


import com.fiuni.sd.issuetracker.beans.User;



@Repository
public interface IUserDao extends CrudRepository<User, Integer>  {

}
