package com.Mudamu.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

@Service
public class PredictorRESTClient {
	// localhost -> Servidor IA
	String urlIAService = "http://34.122.134.5:1880";

	ClientConfig clientConfig = new DefaultClientConfig();
	String response;
	int status;
	Client client;

	public PredictorRESTClient() {
		client = Client.create(clientConfig);
	}

	// {platform}/{developer}/{total_budget}/{music_budget}/{design_budget}/{gameplay_budget}/{year}/{month}
	public List<String> getDisease(Map<Integer, String> mapa) {

		List<String> lista = new ArrayList<>();
		String lista2 = "";
		StringBuilder sb = new StringBuilder();

		for (Map.Entry<Integer, String> entry : mapa.entrySet()) {
			sb.append(entry.getKey() + ";");

			// System.out.println(entry.getKey() + "/" + );
		}

		// WebResource webResource =
		// client.resource(urlIAService).path("").path(sb.toString());

		WebResource webResource = client.resource(urlIAService).path("").path("Palpitations");

		ClientResponse clientResponse = webResource.accept("application/json").get(ClientResponse.class);
		status = clientResponse.getStatus();
		if (status == 200) {
			lista2 = clientResponse.getEntity(String.class);

			String ncadena = lista2.substring(1, lista2.length() - 1);
			String[] tokens = ncadena.split(",");
			for (int in = 0; in < tokens.length; in++) {
				lista.add(tokens[in]);
			}

		} else {
			lista = null;
		}
		return lista;
	}

	public void sendNode() {
		WebResource webResource = client.resource(urlIAService).path("");
	}	
}
