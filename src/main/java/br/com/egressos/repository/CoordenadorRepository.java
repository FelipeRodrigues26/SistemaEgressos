package br.com.egressos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.egressos.model.Coordenador;

public interface CoordenadorRepository extends JpaRepository<Coordenador, String>{
	  @Query("SELECT e FROM Egresso e WHERE e.nome = :nome")
	    public Coordenador getEgressoByUsername(@Param("nome") String name);
}
