package com.Mudamu.service.Login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.Mudamu.model.User.User;
//import edu.mondragon.XyMU.model.User.User2;
import com.Mudamu.rest.UserRESTClient;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	UserRESTClient userRESTClient;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	private boolean checkUsernameAvailable(User user) throws Exception {
		User userFound = userRESTClient.getUserName(user.getUsername());
		if (userFound.getIDPaciente() != null) {
			throw new Exception("Username no disponible");
		}
		return true;
	}

	private boolean checkPasswordValid(User user) throws Exception {
		if ( !user.getPassword().equals(user.getConfirmPassword())) {
			throw new Exception("Password y Confirm Password no son iguales");
			
		}
		return true;
	}


	@Override
	public User createUser(User user2) throws Exception {
		User user = new User();
		user.setIDPaciente(user2.getIDPaciente());
		user.setUsername(user2.getUsername());
		user.setTarjetaSanitaria(user2.getTarjetaSanitaria());
		user.setPassword(user2.getPassword());
		if (checkUsernameAvailable(user2) && checkPasswordValid(user2)) {
			String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
			user.setPassword(encodedPassword);
			userRESTClient.postUserXml(user);
		}
		return user;
	}
	
	
	protected void mapUser(User from,User to) {
		to.setUsername(from.getUsername());
		/*to.setName(from.getName());
		to.setSurname(from.getSurname());
		to.setEmail(from.getEmail());*/
	}
	
	@Override
	public User getLoggedUser() throws Exception {
		//Obtener el usuario logeado
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		UserDetails loggedUser = null;

		//Verificar que ese objeto traido de sesion es el usuario
		if (principal instanceof UserDetails) {
			loggedUser = (UserDetails) principal;
		}
		
		User myUser = userRESTClient
				.getUserName(loggedUser.getUsername());
		
		return myUser;
	}
}
