package com.Mudamu.rest;

import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Service;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;


@Service
public class PredictorRESTClient {
	//localhost	-> Servidor IA
	
	String urlIAService = "http://localhost:8000";
	
	ClientConfig clientConfig = new DefaultClientConfig();
	String response;  
	int status;
	Client client;
	
	public PredictorRESTClient() {
		client= Client.create(clientConfig);
	}
	
	//.path("ventas")
	//{platform}/{developer}/{total_budget}/{music_budget}/{design_budget}/{gameplay_budget}/{year}/{month}
	public List<Double> getDisease(/*String platform, int IDdeveloper, double total_budget,  */ ) {
	
		List<Double> lista = new ArrayList<>();
		String lista2 = "";
		
		//parametros
		//WebResource webResource = client.resource(urlIAService).path("ventas").path(platform).path(Integer.toString(IDdeveloper)).path(Double.toString(total_budget)).path(Double.toString(music_budget)).path(Double.toString(design_budget)).path(Double.toString(gameplay_budget)).path(Integer.toString(year)).path(Integer.toString(month));
		WebResource webResource = null;

		ClientResponse clientResponse = webResource.accept("application/json").get(ClientResponse.class);
		status= clientResponse.getStatus();
		if (status==200) {
			lista2 = clientResponse.getEntity(String.class);
			String ncadena= lista2.substring(1,lista2.length()-1);
			String[]tokens = ncadena.split(","); 
			for(int in =0;in < tokens.length;in ++) {
				lista.add(Double.parseDouble(tokens[in]));
			}
		}
		else {lista = null;}
		return lista;
	}
}


