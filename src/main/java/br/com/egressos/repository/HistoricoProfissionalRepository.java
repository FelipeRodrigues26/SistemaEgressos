package br.com.egressos.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.egressos.model.HistoricoProfissional;

public interface HistoricoProfissionalRepository extends JpaRepository<HistoricoProfissional, Integer>{
		  
		  @Query("SELECT hp FROM HistoricoProfissional hp WHERE hp.egresso.cpf = :cpf")
		  public HistoricoProfissional getHistoricoPorCpf(@Param("cpf") String cpf);
	
}
