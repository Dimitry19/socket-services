package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class RController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String ping(HttpServletResponse response) throws IOException {

		//response.setContentType("text/html");
		//return new ResponseEntity<String>("Réponse du serveur: "+HttpStatus.OK.name(), HttpStatus.OK);
		//response.sendRedirect("/socket-test/index.html");
		return "index";
	}
}
