package com.Mudamu.service.Login;

import com.Mudamu.model.User;

public interface LoginService {

	public User createUser(User user) throws Exception;

	public User getLoggedUser() throws Exception;

    public Object getCitas(User user);

    public User loadUserByUsername(String username);

    public void cancelarCita(Integer citaID);
}

