package com.Mudamu.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import com.Mudamu.service.Predictor.PredictorService;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

//import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PredictorController {
	@Autowired
	PredictorService predictorService;

	Map<Integer, String> mapa = new HashMap<>();

    @GetMapping("/prediction")
	public String sintomas(Model model ) throws Exception {
		String url = "index";

		model.addAttribute("sintomas", "active");
		model.addAttribute("sintomas", predictorService.getSintomas());
		
		return url;
	}

	@PostMapping("/getPrediction")
	public String prediction(Model model, @RequestBody String data) throws Exception {

		System.out.println(data);
		String splitData[] = data.split("&");
		for(int i=0;i<splitData.length-1;i++){
			mapa.put(Integer.parseInt(splitData[i].split("=")[1]), 
			splitData[i+1].split("=")[1]);
			i++;
		}

		predictorService.sendNode();

		//List<String> enfermedadesPrediccion = predictorService.getDisease(mapa);

		return "redirect:/prediction";
	}

	@GetMapping("/node")
	public JSONObject sintomas() throws JSONException {
		JSONObject object = null;

		JSONArray array = new JSONArray(
				"[{\"No\":\"17\",\"Name\":\"Andrew\"},{\"No\":\"18\",\"Name\":\"Peter\"}, {\"No\":\"19\",\"Name\":\"Tom\"}]");
		
		for (int i = 0; i < array.length(); i++) {
			object = array.getJSONObject(i);
		}

		return object;
	}
}