package com.Mudamu.service.Login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.Mudamu.model.CitasPaciente;
import com.Mudamu.model.User;
import com.Mudamu.rest.CitaRESTClient;
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
	}
	
	@Override
	public User getLoggedUser() throws Exception {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		UserDetails loggedUser = null;

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


	@Override
	public void cancelarCita(Integer citaID) {
		citaRESTClient.cancelarCita(citaID);
	}
}
