package com.Mudamu.service.Predictor; 

import java.util.Map;


public interface PredictorService {

	public Object getSintomas();
	
	public void getDisease(Map<Integer, String> mapa, int idPac);
}
