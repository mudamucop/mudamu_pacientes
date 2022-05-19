package com.Mudamu.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.Mudamu.service.Predictor.PredictorService;

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

@Controller
public class PredictorController {
	@Autowired
	PredictorService predictorService;

    @GetMapping("/prediction")
	public String userForm(Model model ) throws Exception {
		String url = "index";

		model.addAttribute("sintomas", "active");
		model.addAttribute("sintomas", predictorService.getSintomas());
		
		return url;
	}
	
}


