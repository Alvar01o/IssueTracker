package com.fiuni.sd.issuetracker.entrega6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fiuni.sd.issuetracker.dao.IUserDao;
import com.fiuni.sd.issuetracker.domain.User;
import com.fiuni.sd.issuetracker.dto.UserDTO;
import com.fiuni.sd.issuetracker.utils.Settings;

@Component 
public class PromotionSchedule {

	@Autowired
	private IUserDao userDao;

	@Autowired
	private IEmailService emailsender;	
	@Scheduled(fixedRate = 9360000)
	public void sendPromotion(){
		Iterable<User> users=userDao.findAll();
	    for (User s:users) {
	    	System.out.println(s.getEmail());
	    	String to = s.getEmail();
	    	String subject = "Mensaje desde issuetracker";
	    	String text = "Esta es una promocion";
	    	emailsender.sendSimpleMessage(to, subject, text);
	    	
	    }
	}
}
