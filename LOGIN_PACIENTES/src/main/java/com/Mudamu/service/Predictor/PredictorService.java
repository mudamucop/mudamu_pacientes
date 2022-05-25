package com.Mudamu.service.Predictor; 

import java.util.List;
import java.util.Map;


public interface PredictorService {

	public Object getSintomas();
	
	public List<String> getDisease(Map<Integer, String> mapa);

	public void sendNode();
}
