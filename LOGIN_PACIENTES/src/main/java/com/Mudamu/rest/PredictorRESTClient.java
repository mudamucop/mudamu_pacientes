package com.Mudamu.rest;

import java.util.Map;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

@Service
public class PredictorRESTClient {
	String urlIAService = "http://mudamudb.duckdns.org:1880/ia";

	ClientConfig clientConfig = new DefaultClientConfig();
	String response;
	int status;
	Client client;

	public PredictorRESTClient() {
		client = Client.create(clientConfig);
	}

	public void getDisease(Map<Integer, String> mapa, int idPac) {
		StringBuilder sb = new StringBuilder();

		for (Map.Entry<Integer, String> entry : mapa.entrySet()) {
			sb.append(entry.getKey() + ";");
		}

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		JSONObject personJsonObject = new JSONObject();

		sb.deleteCharAt(sb.length()-1);  
		

		try {
			personJsonObject.put("pacienteID", idPac);
			personJsonObject.put("sintomas", sb.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}

		HttpEntity<String> request = new HttpEntity<String>(personJsonObject.toString(), headers);
		restTemplate.postForObject(urlIAService, request, String.class);
	}
}
