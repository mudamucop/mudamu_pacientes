package com.Mudamu.controller;

import com.Mudamu.model.User.User;
import com.Mudamu.service.Login.LoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Optional;

@Controller
public class PacienteController {

	@Autowired
	LoginService userService;

    @GetMapping("/pacPage")
	public String userForm(Model model ) throws Exception {
		String url = "index";
		
		//Logueado
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.loadUserByUsername(((UserDetails) principal).getUsername());
		
		model.addAttribute("citas", "active");
		model.addAttribute("citas",userService.getCitas(user));

		return url;
	}
}
