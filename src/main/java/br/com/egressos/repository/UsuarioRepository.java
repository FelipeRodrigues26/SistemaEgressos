package br.com.egressos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.egressos.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String>{
	  @Query("SELECT u FROM Usuario u WHERE u.login = :login")
	    public Usuario getUserByUsername(@Param("login") String username);
}
