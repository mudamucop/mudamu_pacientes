package com.Mudamu.controller.PredictionController;

import java.util.List;

import com.Mudamu.model.Predictor.Predictor;
import com.Mudamu.model.User.User;
import com.Mudamu.service.Login.LoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.Mudamu.service.Predictor.PredictorService;

@Controller
public class PredictionController {

    @Autowired
	LoginService userService;

    @Autowired
	PredictorService predictorService;
    
    @GetMapping("/predictForm")
	public String diseasePredictor(ModelMap model) throws Exception {
		User user = userService.getLoggedUser();
		
        Predictor predictor = new Predictor();
		predictor.setDeveloper(user);


		/*predictor.setDeveloper(dev);
		predictor.setTypeList(gameService.getTypes());
		predictor.setPlatformList(gameService.getPlatformList());
		model.addAttribute("predictorForm",predictor);
		model.addAttribute("min_na_sales",0);
		model.addAttribute("max_na_sales",1000);
		model.addAttribute("min_pal_sales",0);
		model.addAttribute("max_pal_sales",1000);
		model.addAttribute("min_japan_sales",0);
		model.addAttribute("max_japan_sales",1000);
		model.addAttribute("min_other_sales",0);
		model.addAttribute("max_other_sales",1000);
		model.addAttribute("min_music_budget",1);
		model.addAttribute("max_music_budget",1000);
		model.addAttribute("min_design_budget",1);
		model.addAttribute("max_design_budget",1000);
		model.addAttribute("min_gameplay_budget",1);
		model.addAttribute("max_gameplay_budget",1000);
		model.addAttribute("min_year",2021);
		model.addAttribute("max_year",2500);

		model.addAttribute("userForm", user);
		model.addAttribute("formTab","active");
		model.addAttribute("editMode","true");
		model.addAttribute("passwordForm",new ChangePasswordForm(user.getIDuser()));*/
		
		return "signin";
	}

    @PostMapping("/predictResult")
	public String salesResults(@ModelAttribute("predictorForm") Predictor predictor, BindingResult result, ModelMap model) {
			try {
				User user = userService.getLoggedUser();
                //Medico medico = predictor.getMedico(user.getPacId());
				//Developer dev = developerService.getDeveloperByIDUser(user.getIDuser());
				
			
				List<Double> lista = predictorService.getDisease(/*p.getname(), dev.getIDPaciente()*/);	
                
                if(lista.size() > 3) {

                } else {
                    model.addAttribute("result1",lista.get(0));
                    model.addAttribute("result2",lista.get(1));
                    model.addAttribute("result3",lista.get(2));
                }
				
                model.addAttribute("predictor", predictor);
				
			}catch (Exception e) {
				model.addAttribute("formErrorMessage",e.getMessage());
			}
		return "predictions-form/salesPrediction-view";
	}
}
