package com.Mudamu.service.Login;

import com.Mudamu.model.User.User;

public interface LoginService {

	//public Iterable<User> getAllUsers();

	public User createUser(User user) throws Exception;

	public User getLoggedUser() throws Exception;
}

