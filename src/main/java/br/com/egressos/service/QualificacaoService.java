package br.com.egressos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.egressos.model.Qualificacao;
import br.com.egressos.model.QualificacaoTipo;
import br.com.egressos.repository.QualificacaoRepository;
import br.com.egressos.service.exceptions.RecursoNaoEncontradoException;

@Service
public class QualificacaoService {
	
	@Autowired
	private QualificacaoRepository qualificacaoRepository;
	
	public List<Qualificacao> listar() {
		return qualificacaoRepository.findAll();
	}
	
	public List<Qualificacao> listarPorIdEgresso(String cpf) {
		return qualificacaoRepository.getQualificacaoPorCpf(cpf);
	}
	
	public List<QualificacaoTipo> listarTiposQualificacao() {
		return qualificacaoRepository.getTiposQualificacao();
	}
	
	
	
	public Optional<Qualificacao> buscarPeloId(Integer id) {
		
		Optional<Qualificacao> qualificacao = qualificacaoRepository.findById(id);
		
		if(qualificacao == null) {
			
			/* neste caso ira lancar uma RecursoNaoEncontradoException,  que ira chamar o ExceptionHandler 
			 * correspondente da classe RecursosExceptionHandler do pacote handler, quer ira 
			 *retornar um 404 para o cliente com detalhes da possivel causa do erro */
			throw new RecursoNaoEncontradoException("Nenhuma qualificacao foi encontrada");
		}
		
		return qualificacao;
	}
	
	public Qualificacao salvar(Qualificacao qualificacao) {
		
		/* este metodo nao faz atualizacao por isso e preciso garantir que o id sera sempre nulo,
		 * caso contrario o qualificacao sera atualizado em vez de criado um nova qualificacao 
		 * */
		
		qualificacao.setId(null);
		return qualificacaoRepository.save(qualificacao);
	}
	
	/**
	 * Metodo para verificar a existencia de um usuario.
	 * Chama o metodo buscarPeloId que se nao encontrar
	 * a qualificacao lanca uma excecao que lanca um NOT FOUND 
	 * @param qualificacao
	 */
	public void verificarExistencia(Qualificacao qualificacao) {
		buscarPeloId(qualificacao.getId());
	}
	
	public void atualizar(Qualificacao qualificacao) {
		
		/* vericando se o qualificacao realmente existe, poderia ter
		 * chamado o metodo buscarPeloId direto, mas criei o metodo
		 *  verificar existencia para ajudar na legibilidade do codigo */
		verificarExistencia(qualificacao);
		qualificacaoRepository.save(qualificacao);
	}
	
	public void deletar(Integer id) {
		
		try {			
			qualificacaoRepository.deleteById(id);			
		} catch (EmptyResultDataAccessException e) {
			throw new RecursoNaoEncontradoException("Qualificacao não foi encontrada");
		}
	}
}