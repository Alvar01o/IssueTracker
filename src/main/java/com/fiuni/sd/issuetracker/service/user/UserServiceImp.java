package com.fiuni.sd.issuetracker.service.user;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import com.fiuni.sd.issuetracker.service.base.BaseServiceImpl;
import com.fiuni.sd.issuetracker.utils.Settings;
import com.fiuni.sd.issuetracker.dao.IProyectosDao;
import com.fiuni.sd.issuetracker.dao.IRolDao;
import com.fiuni.sd.issuetracker.dao.IUserDao;
import com.fiuni.sd.issuetracker.dao.IUserRolesDao;
import com.fiuni.sd.issuetracker.domain.Proyectos;
import com.fiuni.sd.issuetracker.domain.Rol;
import com.fiuni.sd.issuetracker.domain.User;
import com.fiuni.sd.issuetracker.domain.UserRoles;
import com.fiuni.sd.issuetracker.dto.GruposDTO;
import com.fiuni.sd.issuetracker.dto.ProyectosDTO;
import com.fiuni.sd.issuetracker.dto.RolDTO;
import com.fiuni.sd.issuetracker.dto.UserDTO;
import com.fiuni.sd.issuetracker.dto.UserResultDTO;
import com.fiuni.sd.issuetracker.dto.UserRolDTO;

@Service
public class UserServiceImp  extends BaseServiceImpl<UserDTO, User, UserResultDTO> implements IUserService , UserDetailsService {

	@Autowired
	private IUserDao userDao;
	@Autowired
	private IProyectosDao proyectoDao;
	@Autowired
	private IRolDao rolDao;
	@Autowired 
	private IUserRolesDao user_rolesDao;
	@Autowired 
	private CacheManager cacheManager;
	@Autowired 	
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	@Transactional
	public UserDTO save(UserDTO dto) {

		final User userD = userDao.save(convertDtoToDomain(dto));
		UserDTO us = convertDomainToDto(userD);
		if(userD.getId() == null) {
			cacheManager.getCache(Settings.CACHE_NAME).put("api_user_"+us.getId()  , us );
		}
		return convertDomainToDto(userD);
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	@Cacheable(value = Settings.CACHE_NAME , key = "'api_user_'+ #id")
	public UserDTO getById(Long id) {
		return convertDomainToDto(userDao.findById(id.intValue()).get() );
	}
	
	@Transactional(readOnly = true)
	public UserResultDTO findALL(Pageable pageable ,String search) {
		final List<UserDTO> users = new ArrayList<>();
		Page<User> results=userDao.findByNombreIgnoreCaseOrApellidoIgnoreCaseOrEmailIgnoreCase(search, search, search, pageable);
		results.forEach(us->users.add(convertDomainToDto(us)));
		final UserResultDTO usersResult = new UserResultDTO();
		usersResult.setUsers(users);
		return usersResult;
	}

	public void removeById(int id) throws IllegalArgumentException {
		userDao.deleteById(id); 
	}
	
	public UserDTO addUserRol(int userId ,int proyecto_id, int rolId) {
		Rol r = rolDao.findById(rolId).get();
		User user =userDao.findById(userId).get();
		Proyectos p = proyectoDao.findById(proyecto_id).get();
		UserRoles ur = new UserRoles();
		ur.setProyecto(p);
		ur.setRol(r);
		ur.setUser(user);
		user_rolesDao.save(ur);
		return this.convertDomainToDto(userDao.findById(userId).get());
	}
	
	public UserResultDTO getAll(Pageable pageable) {
		final List<UserDTO> users = new ArrayList<>();
		final UserResultDTO usersResult = new UserResultDTO();
		Page<User> page=userDao.findAll(pageable);
		page.getContent().forEach((user) ->{
			UserDTO ud = convertDomainToDto(user);
			users.add(ud);
			cacheManager.getCache(Settings.CACHE_NAME).put("api_user_"+user.getId()  , ud );
		});
		usersResult.setUsers(users);
		usersResult.setCurrentPage(page.getNumber());
		usersResult.setLastPage(page.getTotalPages());
		usersResult.setCurrentPageTotalItems(page.getNumberOfElements());
		usersResult.setTotalItems(page.getTotalElements());
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
	        	   System.out.println(rol.getId());
	        	   UserRolDTO r = new UserRolDTO();
	        	   ProyectosDTO p = new ProyectosDTO();
	        	   System.out.println(rol.getProyectos());
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
//	        	   r.setUser(dto);
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
		String pwd = dto.getPass();
		System.out.println("ENCODIING" + pwd);
		u.setPass(passwordEncoder.encode(pwd));
		return u;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userDao.findByEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException("No se encontro el usuario con email" + username);
		} else {
			return user;			
		}
	}

}
