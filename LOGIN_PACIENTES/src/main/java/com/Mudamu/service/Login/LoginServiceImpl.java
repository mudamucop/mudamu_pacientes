package com.Mudamu.service.Login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.Mudamu.model.CitasPaciente;
import com.Mudamu.model.User.User;
import com.Mudamu.rest.CitaRESTClient;
//import edu.mondragon.XyMU.model.User.User2;
import com.Mudamu.rest.UserRESTClient;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	UserRESTClient userRESTClient;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	CitaRESTClient citaRESTClient;
	
	private boolean checkUsernameAvailable(User user) throws Exception {
		User userFound = userRESTClient.getUserName(user.getTarjetaSanitaria());
		if (userFound.getpacienteID() != null) {
			throw new Exception("Username no disponible");
		}
		return true;
	}


	@Override
	public User createUser(User user2) throws Exception {
		User user = new User();
		user.setTarjetaSanitaria(user2.getTarjetaSanitaria());
		user.setPassword(user2.getPassword());
		user.setSalt("salt");
		if (checkUsernameAvailable(user2)) {
			String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
			user.setPassword(encodedPassword);
			userRESTClient.postUserXml(user);
		}
		return user;
	}
	
	
	protected void mapUser(User from,User to) {
		to.setTarjetaSanitaria(from.getTarjetaSanitaria());
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


	@Override
	public Object getCitas(User user) {
		CitasPaciente citas = citaRESTClient.getCitas(user);

		return citas.getListaCD();
	}


	@Override
	public User loadUserByUsername(String username) {
		
		User medico = userRESTClient.getUserName(username);

		return medico;
	}
}
