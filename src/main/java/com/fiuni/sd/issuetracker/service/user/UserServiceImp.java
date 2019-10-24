package com.fiuni.sd.issuetracker.service.user;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import com.fiuni.sd.issuetracker.service.base.BaseServiceImpl;
import com.fiuni.sd.issuetracker.dao.IProyectosDao;
import com.fiuni.sd.issuetracker.dao.IRolDao;
import com.fiuni.sd.issuetracker.dao.IUserDao;
import com.fiuni.sd.issuetracker.domain.Proyectos;
import com.fiuni.sd.issuetracker.domain.Rol;
import com.fiuni.sd.issuetracker.domain.User;
import com.fiuni.sd.issuetracker.dto.GruposDTO;
import com.fiuni.sd.issuetracker.dto.ProyectosDTO;
import com.fiuni.sd.issuetracker.dto.RolDTO;
import com.fiuni.sd.issuetracker.dto.UserDTO;
import com.fiuni.sd.issuetracker.dto.UserResultDTO;
import com.fiuni.sd.issuetracker.dto.UserRolDTO;

@Service
public class UserServiceImp  extends BaseServiceImpl<UserDTO, User, UserResultDTO> implements IUserService {

	@Autowired
	private IUserDao userDao;
	@Autowired
	private IProyectosDao proyectoDao;
	@Autowired
	private IRolDao rolDao;
	
	@Override
	public UserDTO save(UserDTO dto) {
		//encriptar contrasenha
		dto.setPass(DigestUtils.md5DigestAsHex(dto.getPass().getBytes()).toString()); 
		final User userD = userDao.save(convertDtoToDomain(dto));
		return convertDomainToDto(userD);
	}

	@Override
	public UserDTO getById(Long id) {
		return convertDomainToDto(userDao.findById(id.intValue()).get() );
	}

	public UserResultDTO findALL(Pageable pageable ,String search) {
		final List<UserDTO> users = new ArrayList<>();
		Page<User> results=userDao.findByNombreIgnoreCaseOrApellidoIgnoreCaseOrEmailIgnoreCase(search, search, search, pageable);
		results.forEach(us->users.add(convertDomainToDto(us)));
		final UserResultDTO usersResult = new UserResultDTO();
		usersResult.setUsers(users);
		return usersResult;
	}

	public void removeById(int id) {
		userDao.deleteById(id);
	}
	
	public UserDTO addUserRol(int userId ,int proyecto_id, int rolId) {
		Rol r = rolDao.findById(rolId).get();
		User user =userDao.findById(userId).get();
		Proyectos p = proyectoDao.findById(proyecto_id).get();
/*		user.addRol(r);
		userDao.save(user);*/
		return this.convertDomainToDto(user);
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

		if(domain.getUserRoles() != null) {
			Set<UserRolDTO> roles = new HashSet<UserRolDTO>();
	           domain.getUserRoles().forEach((rol) -> {
	        	   UserRolDTO r = new UserRolDTO();
	        	   ProyectosDTO p = new ProyectosDTO();
	        	   p.setDescripcion(rol.getProyectos().getDescripcion());
	        	   p.setId(rol.getProyectos().getId());
	        	   p.setNombre(rol.getProyectos().getNombre());
	        	   GruposDTO g = new GruposDTO();
	        	   g.setId(rol.getProyectos().getGrupo().getId());
	        	   g.setCreacion(rol.getProyectos().getGrupo().getCreacion());
	        	   g.setNombre(rol.getProyectos().getGrupo().getNombre());
	        	   p.setGrupo(g);
	        	   r.setId(rol.getId());
	        	   r.setProyecto(p);
	        	   RolDTO rl = new RolDTO();
	        	   rl.setDescripcion(rol.getRol().getDescription());
	        	   rl.setId(rol.getRol().getId());
	        	   rl.setNombre(rol.getRol().getName());
	        	   rl.setValor(rol.getRol().getValor());
	        	   r.setRol(rl);
	        	   r.setUser(dto);
                   roles.add(r);
	           });
	           if(!roles.isEmpty()) {
	                   dto.setUserRoles(roles); // set Result on this vblock
	           }
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
