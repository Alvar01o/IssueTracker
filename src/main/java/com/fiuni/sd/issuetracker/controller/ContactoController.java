package com.fiuni.sd.issuetracker.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fiuni.sd.issuetracker.dto.ContactoDTO;
import com.fiuni.sd.issuetracker.entrega6.IEmailService;
import com.fiuni.sd.issuetracker.service.contacto.IContactoService;

@RestController
@RequestMapping("/contacto")
public class ContactoController {
	@Autowired
	IContactoService contactoService;
	@Autowired
	private IEmailService emailsender;	
	@PostMapping()
	public ContactoDTO save(@Valid @RequestBody ContactoDTO grupo) {
		ContactoDTO g = new ContactoDTO();
		g = contactoService.save(grupo);
    	String subject = " Mensaje desde issuetracker " ;
    	String text = g.getEmail()+" > "+g.getDescripcion();
    	emailsender.sendSimpleMessage("itdistri000000@gmail.com", subject, text);
		return g;
	}

}
