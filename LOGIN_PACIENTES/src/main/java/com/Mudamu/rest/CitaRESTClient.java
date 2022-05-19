package com.Mudamu.rest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Mudamu.model.CitasPaciente;
import com.Mudamu.model.User.User;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;


@Service
public class CitaRESTClient {
	//localhost	-> Servidor IA
	
	String urlDDBBService = "http://mudamudb.duckdns.org/mudamuMysql/service/cita";
	
	ClientConfig clientConfig = new DefaultClientConfig();
	String response;  
	int status;
	Client client;
	
	public CitaRESTClient() {
		client= Client.create(clientConfig);
	}
	
	public CitasPaciente getCitas(User user) {
		CitasPaciente predicciones = new CitasPaciente();
		WebResource webResource = client.resource(urlDDBBService).path("citasPaciente").queryParam("pacienteID",Integer.toString(user.getpacienteID()));
		ClientResponse clientResponse = webResource.accept("application/xml").get(ClientResponse.class);
		status= clientResponse.getStatus();
		if (status==200) {
			predicciones = clientResponse.getEntity(CitasPaciente.class);
			}
		else {response="La llamada no ha sido correcta";}
		return predicciones;
	}
}
