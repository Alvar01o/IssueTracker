package com.fiuni.sd.issuetracker.service.user;

import com.fiuni.sd.issuetracker.service.base.IBaseService;

import org.springframework.data.domain.Pageable;

import com.fiuni.sd.issuetracker.domain.User;
import com.fiuni.sd.issuetracker.dto.UserDTO;
import com.fiuni.sd.issuetracker.dto.UserResultDTO;
public interface IUserService extends IBaseService<UserDTO, User, UserResultDTO>{
	public UserResultDTO findALL(Pageable pageable ,String search);
}
