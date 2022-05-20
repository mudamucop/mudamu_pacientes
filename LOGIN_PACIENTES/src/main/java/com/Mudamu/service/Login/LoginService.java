package com.Mudamu.service.Login;

import java.util.Optional;

import com.Mudamu.model.User.User;

public interface LoginService {

	//public Iterable<User> getAllUsers();

	public User createUser(User user) throws Exception;

	public User getLoggedUser() throws Exception;

    public Object getCitas(User user);

    public User loadUserByUsername(String username);
}

