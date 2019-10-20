package com.fiuni.sd.issuetracker.service.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fiuni.sd.issuetracker.service.base.BaseServiceImpl;
import com.fiuni.sd.issuetracker.service.base.IBaseService;

import com.fiuni.sd.issuetracker.beans.User;
import com.fiuni.sd.issuetracker.dao.IUserDao;
import com.fiuni.sd.issuetracker.dto.UserDTO;
import com.fiuni.sd.issuetracker.dto.UserResultDTO;

@Service
public class UserServiceImp  extends BaseServiceImpl<UserDTO, User, UserResultDTO> implements IUserService {

	@Autowired
	private IUserDao userDao;
	
	@Override
	public UserDTO save(UserDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO getById(Long id) {
		return convertBeanToDto(userDao.findById(id.intValue()).get());
	}

	@Override
	public UserResultDTO getAll(Integer page, Integer size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected UserDTO convertBeanToDto(User domain) {
		final UserDTO dto = new UserDTO();
		dto.setId(domain.getId());
		dto.setApellido(domain.getApellido());
		dto.setEmail(domain.getEmail());
		dto.setCreacion(domain.getCreacion());
		dto.setNombre(domain.getNombre());
		for (int i = 0 ; i < domain.getRoles().size() ; i++) {
//			domain.getRoles().
		}
//		dto.setRol(domain.getRoles()); // set Result on this vblock
		return dto;
	}

	@Override
	protected User convertDtoToBean(UserDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
