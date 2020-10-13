package br.com.egressos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.egressos.model.HistoricoProfissional;
import br.com.egressos.model.Qualificacao;
import br.com.egressos.repository.HistoricoProfissionalRepository;
import br.com.egressos.service.exceptions.RecursoNaoEncontradoException;

@Service
public class HistoricoProfissionalService {
	
	@Autowired
	private HistoricoProfissionalRepository historicoProfissionalRepository;
	
	public List<HistoricoProfissional> listar() {
		return historicoProfissionalRepository.findAll();
	}
	
	public List<HistoricoProfissional> listarPorIdEgresso(String cpf) {
		return historicoProfissionalRepository.getHistoricoPorCpf(cpf);
	}
	
	public Optional<HistoricoProfissional> buscarPeloId(Integer id) {
		
		Optional<HistoricoProfissional> historicoProfissional = historicoProfissionalRepository.findById(id);
		
		if(historicoProfissional == null) {
			
			/* neste caso ira lancar uma RecursoNaoEncontradoException,  que ira chamar o ExceptionHandler 
			 * correspondente da classe RecursosExceptionHandler do pacote handler, quer ira 
			 *retornar um 404 para o cliente com detalhes da possivel causa do erro */
			throw new RecursoNaoEncontradoException("Nenhum Historico Profissional foi encontrado");
		}
		
		return historicoProfissional;
	}
	
	public HistoricoProfissional salvar(HistoricoProfissional historicoProfissional) {
		
		/* este metodo nao faz atualizacao por isso e preciso garantir que o id sera sempre nulo,
		 * caso contrario o Historico Profissional sera atualizado em vez de criado um novo Historico 
		 * */
		
		historicoProfissional.setId(null);
		return historicoProfissionalRepository.save(historicoProfissional);
	}
	
	/**
	 * Metodo para verificar a existencia de um usuario.
	 * Chama o metodo buscarPeloId que se nao encontrar
	 * o Historico Profissional lanca uma excecao que lanca um NOT FOUND 
	 * @param Historico Profissional
	 */
	public void verificarExistencia(HistoricoProfissional historicoProfissional) {
		buscarPeloId(historicoProfissional.getId());
	}
	
	public void atualizar(HistoricoProfissional historicoProfissional) {
		
		/* vericando se o Historico realmente existe, poderia ter
		 * chamado o metodo buscarPeloId direto, mas criei o metodo
		 *  verificar existencia para ajudar na legibilidade do codigo */
		verificarExistencia(historicoProfissional);
		historicoProfissionalRepository.save(historicoProfissional);
	}
	
	public void deletar(Integer id) {
		
		try {			
			historicoProfissionalRepository.deleteById(id);			
		} catch (EmptyResultDataAccessException e) {
			throw new RecursoNaoEncontradoException("Historico Profissional não foi encontrado");
		}
	}
}
