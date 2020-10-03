package br.com.egressos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.egressos.model.Coordenador;

public interface CoordenadorRepository extends JpaRepository<Coordenador, String>{
	  @Query("SELECT c FROM Coordenador c WHERE c.nome = :nome")
	  public Coordenador getCoordenadorByUsername(@Param("nome") String name);
	  
	  @Query("SELECT c FROM Coordenador c WHERE c.usuario.login = :login")
	  public Coordenador getCoordenadorByLogin(@Param("login") String login);
}
