package br.com.egressos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import br.com.egressos.model.Empresa;
import br.com.egressos.repository.EmpresaRepository;
import br.com.egressos.service.exceptions.RecursoNaoEncontradoException;

@Service
public class EmpresaService {

	@Autowired
	private EmpresaRepository empresaRepository;

	public List<Empresa> listar() {
		return empresaRepository.findAll();
	}

	public Optional<Empresa> buscarPeloId(String id) {

		Optional<Empresa> empresa = empresaRepository.findById(id);

		if (empresa == null) {

			/*
			 * neste caso ira lancar uma RecursoNaoEncontradoException, que ira chamar o
			 * ExceptionHandler correspondente da classe RecursosExceptionHandler do pacote
			 * handler, quer ira retornar um 404 para o cliente com detalhes da possivel
			 * causa do erro
			 */
			throw new RecursoNaoEncontradoException("Nenhum empresa foi encontrado");
		}

		return empresa;
	}

	public Empresa salvar(Empresa empresa) {

		return empresaRepository.save(empresa);
	}

	/**
	 * Metodo para verificar a existencia de um empresa. Chama o metodo buscarPeloId
	 * que se nao encontrar o Empresa lanca uma excecao que lanca um NOT FOUND
	 * 
	 * @param empresa
	 */
	public void verificarExistencia(Empresa empresa) {
		buscarPeloId(empresa.getCnpj());
	}

	public void atualizar(Empresa empresa) {

		/*
		 * vericando se o empresa realmente existe, poderia ter chamado o metodo
		 * buscarPeloId direto, mas criei o metodo verificar existencia para ajudar na
		 * legibilidade do codigo
		 */
		verificarExistencia(empresa);
		empresaRepository.save(empresa);
	}

	public void deletar(String id) {

		try {
			empresaRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new RecursoNaoEncontradoException("O empresa não foi encontrado");
		}
	}

	public Empresa buscarPeloLogin(String login) {

		Empresa empresa = empresaRepository.getEmpresaByLogin(login);

		if (empresa == null) {
			/*
			 * neste caso ira lancar uma RecursoNaoEncontradoException, que ira chamar o
			 * ExceptionHandler correspondente da classe RecursosExceptionHandler do pacote
			 * handler, quer ira retornar um 404 para o cliente com detalhes da possivel
			 * causa do erro
			 */
			throw new RecursoNaoEncontradoException("Nenhum empresa foi encontrado");
		}

		return empresa;
	}
}