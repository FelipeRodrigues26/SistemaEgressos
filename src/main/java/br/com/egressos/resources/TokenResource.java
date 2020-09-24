package br.com.egressos.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/")
public class TokenResource{

	@GetMapping
	public String verificaToken() {
		return "ok"; //Passou pelo security 
	}


}