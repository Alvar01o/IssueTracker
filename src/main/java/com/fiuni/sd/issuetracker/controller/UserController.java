package com.fiuni.sd.issuetracker.controller;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiuni.sd.issuetracker.dto.UserDTO;
import com.fiuni.sd.issuetracker.dto.UserResultDTO;
import com.fiuni.sd.issuetracker.service.user.IUserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	IUserService userService;
	
	@GetMapping("/{id}")
	public UserDTO getById(@PathVariable Long id) {
		System.out.println(id);
		UserDTO u= userService.getById(id);
		System.out.println(u.getEmail());
		return userService.getById(id);
	}

	@GetMapping(path = "/page/{page_num}")
	public UserResultDTO getUsers(@PathVariable(value = "page_num")Integer pageNum) {
		return userService.getAll(PageRequest.of((pageNum-1), 30));
	}

	@GetMapping(path = "/find/{search}/{page_num}")
	public UserResultDTO findUser(@PathVariable(value = "search") String search , @PathVariable(value = "page_num")Integer pageNum) {
		return userService.findALL(PageRequest.of((pageNum-1), 30) , search); 
	}
	
	@PostMapping(path = "addroll/{user_id}/{proyecto_id}/{rol_id}")
	public UserDTO addRoll(@PathVariable(value = "user_id")int user_id,@PathVariable(value = "proyecto_id")int proyecto_id, @PathVariable(value = "rol_id") int rol_id) {
		return userService.addUserRol(user_id,proyecto_id, rol_id) ;
	}
	@PostMapping()
	public UserDTO save(@Valid @RequestBody UserDTO user) {
		return userService.save(user);
	}
	
}
