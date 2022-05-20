package com.Mudamu.service.Predictor;

import java.util.List;

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

	/*@Override
	public List<Double> getSales(String platform, int IDdeveloper, double total_budget, double music_budget,
			double design_budget, double gameplay_budget, int year, int month) {
		return predictorRESTClient.getSales(platform, IDdeveloper, total_budget, music_budget, design_budget, gameplay_budget, year, month);
	}*/

	@Override
	public List<Double> getDisease() {
		//quitar esta y modificar la de arriba
		return null;
	}

	@Override
	public Object getSintomas() {
		Sintomas sintomas = sintomasRESTClient.getSintomas();

		return sintomas.getListaCD();
	}
}
