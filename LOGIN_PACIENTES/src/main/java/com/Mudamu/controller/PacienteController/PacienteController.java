package com.Mudamu.controller.PacienteController;

import com.Mudamu.model.User.User;
import com.Mudamu.service.Login.LoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PacienteController {

	@Autowired
	LoginService userService;

    @GetMapping("/pacPage")
	public String userForm(Model model ) throws Exception {
		String url = "index";
		/*User user = userService.getLoggedUser(); descomentar
		model.addAttribute("userForm", user);*/
		





		/*model.addAttribute("editMode","true");
		model.addAttribute("edit","true");
		model.addAttribute("listTab","active");
		model.addAttribute("passwordForm",new ChangePasswordForm(user.getIDuser()));
		Developer dev = developerService.getDeveloperByIDUser(user.getIDuser());
		if(dev.getIDdeveloper() == 0) {
			int id = 0;
			int n = 15098;
			List<Game2> gameList = new ArrayList<Game2>();
			for(int i = 0; i<5; i++) {
				id = (int) (Math.random() * n) + 1;
				Game game = gameService.getGame(id);
				Game2 game2 = new Game2();
				game2.setId(game.getId());
				game2.setName(game.getName());
				game2.setImg(game.getImg());
				game2.setTotalSales(game.getTotalSales());
				game2.setEuSales(game.getEuSales());
				game2.setUsSales(game.getUsSales());
			    game2.setJapanSales(game.getJapanSales());
			    game2.setReleaseDate(game.getReleaseDate());
			    game2.InserDevList(game.getDevelopers());
			    game2.InsertTypList(game.getTypes());
			    game2.InsertPlatList(game.getPlatforms());
				gameList.add(game2);
			}
			List<Game> games = gameService.getGameList();
			model.addAttribute("gameList2", games);
			model.addAttribute("developer", true);
			model.addAttribute("gameList", gameList);
			url= "user-form/user-start";
		}else {
			model.addAttribute("developer", false);
			model.addAttribute("develop", dev);
			url = "redirect:/prediction";
		}*/
		return url;
	}
}
