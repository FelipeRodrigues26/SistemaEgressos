package br.com.egressos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SistemaEgressosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaEgressosApplication.class, args);
		System.out.println(new BCryptPasswordEncoder().encode("123"));
	}

}
