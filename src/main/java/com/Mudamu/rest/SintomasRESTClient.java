package com.Mudamu.rest;

import org.springframework.stereotype.Service;

import com.Mudamu.model.Sintomas;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;


@Service
public class SintomasRESTClient {
	
	String urlDDBBService = "http://mudamudb.duckdns.org/mudamuMysql/service/enfermedades_sintomas";
	
	ClientConfig clientConfig = new DefaultClientConfig();
	String response;  
	int status;
	Client client;
	
	public SintomasRESTClient() {
		client= Client.create(clientConfig);
	}
	
	public Sintomas getSintomas() {
		Sintomas predicciones = new Sintomas();
		WebResource webResource = client.resource(urlDDBBService).path("sintomas");
		ClientResponse clientResponse = webResource.accept("application/xml").get(ClientResponse.class);
		status= clientResponse.getStatus();
		if (status==200) {
			predicciones = clientResponse.getEntity(Sintomas.class);
			}
		else {response="La llamada no ha sido correcta";}
		return predicciones;
	}
}
