package br.com.egressos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.egressos.model.Empresa;
public interface EmpresaRepository extends JpaRepository<Empresa, String>{
	
	 @Query("SELECT e FROM Empresa e WHERE e.nomeFantasia = :nomeFantasia")
	  public Empresa getCoordenadorByUsername(@Param("nomeFantasia") String nomeFantasia);
	  
	  @Query("SELECT e FROM Empresa e WHERE e.usuario.login = :login")
	  public Empresa getEmpresaByLogin(@Param("login") String login);
}
