package com.Mudamu.rest;

import javax.ws.rs.core.MultivaluedMap;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import com.Mudamu.model.User.User;

@Service
public class UserRESTClient {
	//localhost/Servidor ->http://localhost:8080/AlbumDbCRUD/resources/user		
	
	String urlDDBBService = "http://mudamudb.duckdns.org/mudamuMysql/service/user";
	
	ClientConfig clientConfig = new DefaultClientConfig();
	String response;  
	int status;
	Client client;
	
	public UserRESTClient() {
		client= Client.create(clientConfig);
	}
	
	
	//Recoger User con id usando xml	
	public User getUserXml(int id) {
		User user=new User();
		WebResource webResource = client.resource(urlDDBBService).path("user").queryParam("id", Integer.toString(id));
		MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
		queryParams.add("id", Integer.toString(id));
		ClientResponse clientResponse = webResource.queryParams(queryParams).accept("application/xml").get(ClientResponse.class);
		status= clientResponse.getStatus();
		if (status==200) {user = clientResponse.getEntity(User.class);}
		else {response="La llamada no ha sido correcta";}
		return user;
	}
	
	public User getUserName(String username) {
		
		User user= new User();
		WebResource webResource = client.resource(urlDDBBService).path("login").queryParam("username",username);
		ClientResponse clientResponse = webResource.accept("application/xml").get(ClientResponse.class);
		status= clientResponse.getStatus();
		if (status==200) {
			user = clientResponse.getEntity(User.class);
			}
		else {response="La llamada no ha sido correcta";}
		return user;
	}

	//Delete User con path params	
	public String userDelete(int id) {
			WebResource webResource = client.resource(urlDDBBService).path("delete").path(Integer.toString(id));
			ClientResponse clientResponse = webResource.type("text/plain").delete(ClientResponse.class);
			status= clientResponse.getStatus();
			if (status==204) {response = "User Borrado";}
			else {response="NO encontrado";}
			return response;
		}

	public User login(String username, String password) {
		
		User user=new User();
		WebResource webResource = client.resource(urlDDBBService).path("login").queryParam("username", username).queryParam("password", password);
		MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
		queryParams.add("username", username);
		queryParams.add("password", password);
	
		ClientResponse clientResponse = webResource.queryParams(queryParams).accept("application/xml").get(ClientResponse.class);
		
		status= clientResponse.getStatus();
		if (status==200) {
			user = clientResponse.getEntity(User.class);
			}
		else {response="La llamada no ha sido correcta";}

		return user;	
	}

	public String postUserXml(@ModelAttribute("userForm") User user) {
		WebResource webResource = client.resource(urlDDBBService).path("register");
		ClientResponse clientResponse = webResource.type("application/xml").post(ClientResponse.class, user);
		status= clientResponse.getStatus();
		if (status==201) {response = "Creado con Objeto";}
		else {response="La llamada no ha sido correcta";}
		return response;
	}	
}


