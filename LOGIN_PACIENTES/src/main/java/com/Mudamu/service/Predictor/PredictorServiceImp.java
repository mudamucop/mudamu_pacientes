package com.Mudamu.service.Predictor;

import java.util.List;
import java.util.Map;

//import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Mudamu.model.Sintomas;
import com.Mudamu.rest.PredictorRESTClient;
import com.Mudamu.rest.SintomasRESTClient;

@Service
public class PredictorServiceImp implements PredictorService{

	@Autowired
	PredictorRESTClient predictorRESTClient;

	@Autowired
	SintomasRESTClient sintomasRESTClient;

	@Override
	public Object getSintomas() {
		Sintomas sintomas = sintomasRESTClient.getSintomas();

		return sintomas.getListaCD();
	}

	@Override
	public List<String> getDisease(Map<Integer, String> mapa) {
		return predictorRESTClient.getDisease(mapa);
	}

	@Override
	public void sendNode(){
		predictorRESTClient.sendNode();
	}
}
