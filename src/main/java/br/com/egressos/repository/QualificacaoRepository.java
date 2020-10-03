package br.com.egressos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.egressos.model.Qualificacao;
import br.com.egressos.model.QualificacaoTipo;

public interface QualificacaoRepository extends JpaRepository<Qualificacao, Integer>{
		  
		  @Query("SELECT q FROM Qualificacao q WHERE q.egresso.cpf = :cpf")
		  public Qualificacao getQualificacaoPorCpf(@Param("cpf") String cpf);
		  
		  
		  @Query("SELECT tq FROM QualificacaoTipo tq")
		  public List<QualificacaoTipo> getTiposQualificacao();
	
}
