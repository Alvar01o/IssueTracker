package com.fiuni.sd.issuetracker.service.user;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fiuni.sd.issuetracker.service.base.BaseServiceImpl;
import com.fiuni.sd.issuetracker.service.base.IBaseService;
import com.fiuni.sd.issuetracker.dao.IUserDao;
import com.fiuni.sd.issuetracker.domain.User;
import com.fiuni.sd.issuetracker.dto.RolDTO;
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
		return convertDomainToDto(userDao.findById(id.intValue()).get());
	}


	public UserResultDTO getAll(Pageable pageable) {

		final List<UserDTO> users = new ArrayList<>();
		Page<User> results=userDao.findAll(pageable);
		results.forEach(us->users.add(convertDomainToDto(us)));
		final UserResultDTO usersResult = new UserResultDTO();
		usersResult.setUsers(users);
		return usersResult;
	}

	@Override
	protected UserDTO convertDomainToDto(User domain) {
		final UserDTO dto = new UserDTO();
		dto.setId(domain.getId());
		dto.setApellido(domain.getApellido());
		dto.setEmail(domain.getEmail());
		dto.setCreacion(domain.getCreacion());
		dto.setNombre(domain.getNombre());
		Set<RolDTO> roles = new HashSet<RolDTO>();
		domain.getRoles().forEach((rol) -> {
			RolDTO r = new RolDTO();
			r.setId(rol.getId());
			r.setNombre(rol.getName());
			r.setDescripcion(rol.getDescription());
			r.setValor(rol.getValor());
			roles.add(r);
		});
		if(!roles.isEmpty()) {
			dto.setRoles(roles); // set Result on this vblock			
		}
		return dto;
	}

	@Override
	protected User convertDtoToDomain(UserDTO dto) {
		User u = new User();
		u.setNombre(dto.getNombre());
		u.setApellido(dto.getApellido());
		u.setCreacion(dto.getCreacion());
		u.setEmail(dto.getEmail());
		u.setPass(dto.getPass());
		return u;
	}

}
