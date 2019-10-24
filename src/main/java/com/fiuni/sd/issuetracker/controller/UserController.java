package com.fiuni.sd.issuetracker.controller;
import java.util.NoSuchElementException;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	@DeleteMapping("/{id}")
	public MessageDTO deleteById(@PathVariable Long id) {
		try	{
			userService.removeById(id.intValue());
			return new MessageDTO("Eliminado");
		} catch(IllegalArgumentException e) {
			return new MessageDTO("Error al eliminar usuario.");			
		} catch(EmptyResultDataAccessException e1) {
			return new MessageDTO("No se encontro el usuario.");			
		}
	}

	@GetMapping("/{id}")
	public UserDTO getById(@PathVariable Long id) {
		try	{
			UserDTO u= userService.getById(id);
			return userService.getById(id);			
		} catch (NoSuchElementException e ) {
			UserDTO empty = new UserDTO();
			return empty;
		}

	}

	@GetMapping(path = "/page/{page_num}")
	public UserResultDTO getUsers(@PathVariable(value = "page_num")Integer pageNum) {
		return userService.getAll(PageRequest.of((pageNum-1), 30));
	}

	@GetMapping(path = "/find/{search}/{page_num}")
	public UserResultDTO findUser(@PathVariable(value = "search") String search , @PathVariable(value = "page_num")Integer pageNum) {
		logger.warn("Hey, This is a warning!");
		return userService.findALL(PageRequest.of((pageNum-1), 30) , search); 
	}
	
	@PostMapping(path = "/addroll/{user_id}/{proyecto_id}/{rol_id}")
	public UserDTO addRoll(@PathVariable(value = "user_id")int user_id,@PathVariable(value = "proyecto_id")int proyecto_id, @PathVariable(value = "rol_id") int rol_id) {
		return userService.addUserRol(user_id,proyecto_id, rol_id) ;
	}
	
	@PostMapping()
	public UserDTO save(@Valid @RequestBody UserDTO user) {
		return userService.save(user);
	}
	
}
