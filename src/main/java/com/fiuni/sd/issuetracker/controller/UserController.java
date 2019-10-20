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
	public UserResultDTO getClients(@PathVariable(value = "page_num")Integer pageNum) {
		return userService.getAll(PageRequest.of((pageNum-1), 30));
	}
	
	@PostMapping()
	public UserDTO save(@Valid @RequestBody UserDTO client) {
		return userService.save(client);
	}
	
}
