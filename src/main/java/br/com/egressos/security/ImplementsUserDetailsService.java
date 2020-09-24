package br.com.egressos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.egressos.model.Usuario;
import br.com.egressos.repository.UsuarioRepository;

@Service
public class ImplementsUserDetailsService implements UserDetailsService {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	 @Override
	    public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException {
	        Usuario user = usuarioRepository.getUserByUsername(username);
	       
	        if (user == null) {
	            throw new UsernameNotFoundException("Usuário não encontrado!");
	        }
	         
	        return new MyUserDetails(user);
	  }

}
