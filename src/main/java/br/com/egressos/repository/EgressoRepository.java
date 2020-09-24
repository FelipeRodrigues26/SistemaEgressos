package br.com.egressos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import br.com.egressos.model.Egresso;

public interface EgressoRepository extends JpaRepository<Egresso, String>{
	  @Query("SELECT e FROM Egresso e WHERE e.nome = :nome")
	    public Egresso getEgressoByUsername(@Param("nome") String name);
}
