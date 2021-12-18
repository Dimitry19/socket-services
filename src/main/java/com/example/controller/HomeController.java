package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import static com.example.constants.Constants.*;


/*https://javatechonline.com/how-to-implement-security-in-spring-boot-project/#What_will_you_learn_from_this_article*/
@Controller
public class HomeController {

	@GetMapping(home_path)
	public String home(){

		return "homePage";
	}

	@GetMapping(welcome_path)
	public String welcome(){

		return "welcomePage";
	}

	@GetMapping(admin_path)
	public String admin(){

		return "adminPage";
	}

	@GetMapping(manager_path)
	public String manager(){

		return "managerPage";
	}

	@GetMapping(user_path)
	public String user(){

		return "userPage";
	}

	@GetMapping(common_path)
	public String common(){

		return "commonPage";
	}

	@GetMapping(denied_path)
	public String denied(){

		return "deniedPage";
	}
}
