package br.com.egressos.service;

	import java.util.List;
	import java.util.Optional;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.dao.EmptyResultDataAccessException;
	import org.springframework.stereotype.Service;

	import br.com.egressos.model.Oportunidade;
	import br.com.egressos.repository.OportunidadeRepository;
	import br.com.egressos.service.exceptions.RecursoNaoEncontradoException;

	@Service
	public class OportunidadeService {
		
		@Autowired
		private OportunidadeRepository oportunidadeRepository;
		
		public List<Oportunidade> listar() {
			return oportunidadeRepository.findAll();
		}
		
		public Optional<Oportunidade> buscarPeloId(Integer id) {
			
			Optional<Oportunidade> oportunidade = oportunidadeRepository.findById(id);
			
			if(oportunidade == null) {
				
				/* neste caso ira lancar uma RecursoNaoEncontradoException,  que ira chamar o ExceptionHandler 
				 * correspondente da classe RecursosExceptionHandler do pacote handler, quer ira 
				 *retornar um 404 para o oportunidade com detalhes da possivel causa do erro */
				throw new RecursoNaoEncontradoException("Nenhuma oportunidade foi encontrada");
			}
			
			return oportunidade;
		}
		
		public List<Oportunidade> listarPorIdEmpresa(String cnpj) {
			return oportunidadeRepository.getOportunidadePorCNPJ(cnpj);
		}
		
		public Oportunidade salvar(Oportunidade oportunidade) {
			
			/* este metodo nao faz atualizacao por isso e preciso garantir que o id sera sempre nulo,
			 * caso contrario o oportunidade sera atualizado em vez de criado um novo vinho 
			 * */
			
			oportunidade.setId(null);
			return oportunidadeRepository.save(oportunidade);
		}
		
		/**
		 * Metodo para verificar a existencia de um oportunidade.
		 * Chama o metodo buscarPeloId que se nao encontrar
		 * o oportunidade lanca uma excecao que lanca um NOT FOUND 
		 * @param oportunidade
		 */
		public void verificarExistencia(Oportunidade oportunidade) {
			buscarPeloId(oportunidade.getId());
		}
		
		public void atualizar(Oportunidade oportunidade) {
			
			/* vericando se o oportunidade realmente existe, poderia ter
			 * chamado o metodo buscarPeloId direto, mas criei o metodo
			 *  verificar existencia para ajudar na legibilidade do codigo */
			verificarExistencia(oportunidade);
			oportunidadeRepository.save(oportunidade);
		}
		
		public void deletar(Integer id) {
			
			try {			
				oportunidadeRepository.deleteById(id);			
			} catch (EmptyResultDataAccessException e) {
				throw new RecursoNaoEncontradoException("Oportunidade não foi encontrado");
			}
		}

	}
