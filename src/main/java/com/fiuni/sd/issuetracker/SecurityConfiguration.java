package com.fiuni.sd.issuetracker;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fiuni.sd.issuetracker.service.user.UserServiceImp;

@Configuration
@EnableWebSecurity//(debug = true)
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	UserServiceImp userService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(encodePWD());
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers(HttpMethod.POST, "/user").and().ignoring().antMatchers(HttpMethod.POST, "/contacto");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception { // mas detalles de permisos en controladores
		http.csrf().disable().httpBasic().and()
		.authorizeRequests()
		//registro habilitado
		.antMatchers(HttpMethod.POST,  "/contacto").authenticated().anyRequest().permitAll().and() 
		.authorizeRequests().antMatchers(HttpMethod.POST,  "/user").authenticated().anyRequest().permitAll().and() 
		//rutas para crud de usuarios
		.authorizeRequests().antMatchers("/user/page/**").authenticated().anyRequest().hasAnyRole("ADMIN", "MANAGER" , "DEVELOPER").and()
		// rutas para informacion de grupo
		.authorizeRequests().antMatchers("/grupo**").authenticated().anyRequest().hasAnyRole("ADMIN", "MANAGER" , "DEVELOPER").and()
		//rutas para crud de proyectos
		.authorizeRequests().antMatchers("/proyecto**").authenticated().anyRequest().hasAnyRole("ADMIN", "MANAGER" , "DEVELOPER").and()	
		.authorizeRequests().antMatchers("/tablero**").authenticated().anyRequest().hasAnyRole("ADMIN", "MANAGER" , "DEVELOPER").and()		
		.authorizeRequests().antMatchers("/tarea**").authenticated().anyRequest().hasAnyRole("ADMIN", "MANAGER" , "DEVELOPER")		
		;//.disable();
	}
	
	@Bean
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
}
