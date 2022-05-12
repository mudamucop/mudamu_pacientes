package com.Mudamu.controller.LoginController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.Mudamu.model.User.User;
import com.Mudamu.service.Login.LoginService;

@Controller
public class LoginController {

	@Autowired
	LoginService userService;
	
	@GetMapping({ "/", "/login" })
	public String index() {
		return "login";
	}

	@GetMapping("/signin")
	public String signin(Model model) {
		return "login";
	}

	@GetMapping("/passForm")
	public String forgetPassword(ModelMap model) {
		/*model.addAttribute("userForm", new User());
		model.addAttribute("signup", true);*/

		System.out.println("sada");

		return "password";
	}
	 
	@GetMapping("/signup")
	public String signup(ModelMap model) {
		model.addAttribute("userForm", new User());
		model.addAttribute("signup", true);

		return "register";
	}

	@PostMapping("/signup")
	public String createUser(@ModelAttribute("userForm") User user,@ModelAttribute("confirm") String confirm, BindingResult result, ModelMap model) {
		String url = "";
		model.addAttribute("userForm", user);
		if (result.hasErrors()) {
			return "register"; 
		} else {
			/*try {	
					if((user.getDeveloper()!=null)||(user.getDeveloper()!="")) {
					String content = user.getName()+" "+user.getSurname()+" says he belongs to your company,"+user.getDeveloper()+", and wants to register in our application XyMU,  If all this is correct, please contact us giving your permission. Thank you";
					enviarConGMail(user.getDeveloperEmail(), "New User", content);
					}
					userService.createUser(user);
					url = "index";
				
			} catch (Exception e) {
				model.addAttribute("formErrorMessage", e.getMessage());
				url = "user-form/user-signup";
			}*/

			url = "login";
		}
		return url;
	}
}