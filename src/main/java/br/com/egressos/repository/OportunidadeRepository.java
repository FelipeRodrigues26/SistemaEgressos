package br.com.egressos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.egressos.model.Oportunidade;

public interface OportunidadeRepository extends JpaRepository<Oportunidade, Integer>{
	    @Query("SELECT op FROM Oportunidade op WHERE op.tipo = :tipo")
	    public Oportunidade getOportunidadeByTipo(@Param("tipo") String tipo);
	    
	    @Query("SELECT op FROM Oportunidade op WHERE op.empresa.cnpj =:cnpj")
		public List<Oportunidade> getOportunidadePorCNPJ(@Param("cnpj") String cnpj);
}
