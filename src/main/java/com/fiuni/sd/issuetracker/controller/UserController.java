package com.fiuni.sd.issuetracker.controller;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.NoSuchElementException;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fiuni.sd.issuetracker.utils.Settings;
import com.fiuni.sd.issuetracker.dto.MessageDTO;
import com.fiuni.sd.issuetracker.dto.UserDTO;
import com.fiuni.sd.issuetracker.dto.UserResultDTO;
import com.fiuni.sd.issuetracker.service.user.IUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping("/user")
public class UserController {

	private static final Logger logger = LogManager.getLogger(UserController.class);

	@Autowired
	IUserService userService;
	@Secured({ "ROLE_ADMIN" })
	@DeleteMapping("/{id}")
	public MessageDTO deleteById(@PathVariable Long id) {
		try	{
			userService.removeById(id.intValue());
			return new MessageDTO("Usuario Eliminado");
		} catch(IllegalArgumentException e) {
			return new MessageDTO("Error al eliminar usuario.");			
		} catch(EmptyResultDataAccessException e1) {
			return new MessageDTO("No se encontro el usuario.");			
		}
	}
	@GetMapping(path = "/logged")
	@Secured({ "ROLE_ADMIN", "ROLE_MANAGER" , "ROLE_DEVELOPER" })
	public Object getLoggedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getAuthorities();
	}
	
	@Secured({ "ROLE_ADMIN", "ROLE_MANAGER" })	
	@GetMapping("/{id}")
	public UserDTO getById(@PathVariable Long id) {
		UserDTO u  = new UserDTO();
		try	{
			u = userService.getById(id);
		} catch (NoSuchElementException e ) {
			logger.info("Usuario con id " + id  + " no existe");
		}
		return u;
	}

	@GetMapping(path = "/page/{page_num}")
	@Secured({ "ROLE_ADMIN", "ROLE_MANAGER" })	
	public UserResultDTO getUsers(@PathVariable(value = "page_num")Integer pageNum) {
		return userService.getAll(PageRequest.of((pageNum-1), Settings.PAGINACION));
	}


	@GetMapping(path = "/find/{search}/{page_num}")
	@Secured({ "ROLE_ADMIN", "ROLE_MANAGER" , "ROLE_DEVELOPER" })	
	public UserResultDTO findUser(@PathVariable(value = "search") String search , @PathVariable(value = "page_num")Integer pageNum) {
		return userService.findALL(PageRequest.of((pageNum-1), Settings.PAGINACION) , search); 
	}
	
	@PostMapping(path = "/addroll/{user_id}/{proyecto_id}/{rol_id}")
	@Secured({ "ROLE_ADMIN", "ROLE_MANAGER" })	
	public UserDTO addRoll(@PathVariable(value = "user_id")int user_id,@PathVariable(value = "proyecto_id")int proyecto_id, @PathVariable(value = "rol_id") int rol_id) {
		UserDTO udto = new UserDTO();
		try {
			udto= userService.addUserRol(user_id,proyecto_id, rol_id);			
		} catch(Exception ex) {
			logger.info(ex.toString());			
		}
		return udto ;
	}
	
	@SuppressWarnings("finally")
	@PostMapping()
	public UserDTO save(@Valid @RequestBody UserDTO user) {
		UserDTO  userdto = new UserDTO();
		try	{
			userdto = userService.save(user);
		} catch (Exception ex) {
			logger.info(ex.toString());
		} finally {
			return userdto;			
		}

	}
	
}
