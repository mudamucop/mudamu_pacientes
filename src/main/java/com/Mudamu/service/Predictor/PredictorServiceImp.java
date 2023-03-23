package com.Mudamu.service.Predictor;

import java.util.Map;

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
	public void getDisease(Map<Integer, String> mapa, int idPac) {
		predictorRESTClient.getDisease(mapa, idPac);
	}
}
