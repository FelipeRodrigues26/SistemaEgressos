package br.com.egressos.security;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
	@Autowired
	private ImplementsUserDetailsService userDetailsService;
	
    @Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.cors().and().csrf().disable().authorizeRequests()
			.antMatchers(HttpMethod.POST,"/egressos").permitAll()
			.antMatchers(HttpMethod.POST,"/empresas").permitAll()
			.antMatchers(HttpMethod.POST, "/login").permitAll()
			.anyRequest().authenticated()
			.and()
			
			// filtra requisições de login
			.addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
	                UsernamePasswordAuthenticationFilter.class)
			
			// filtra outras requisições para verificar a presença do JWT no header
			.addFilterBefore(new JWTAuthenticationFilter(),
	                UsernamePasswordAuthenticationFilter.class);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// cria uma conta default
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
	    CorsConfiguration configuration = new CorsConfiguration();
	    configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
	    configuration.setAllowedMethods(Arrays.asList("GET","POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
	    configuration.setExposedHeaders(Arrays.asList("Authorization", "content-type"));
	    configuration.setAllowedHeaders(Arrays.asList("Authorization", "content-type"));
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", configuration);
	    return source;
	}
}