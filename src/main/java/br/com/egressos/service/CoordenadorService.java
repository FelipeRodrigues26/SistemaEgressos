package br.com.egressos.service;

	import java.util.List;
	import java.util.Optional;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.dao.EmptyResultDataAccessException;
	import org.springframework.stereotype.Service;

	import br.com.egressos.model.Coordenador;
	import br.com.egressos.repository.CoordenadorRepository;
	import br.com.egressos.service.exceptions.RecursoNaoEncontradoException;

	@Service
	public class CoordenadorService {
		
		@Autowired
		private CoordenadorRepository coordenadorRepository;
		
		public List<Coordenador> listar() {
			return coordenadorRepository.findAll();
		}
		
		public Optional<Coordenador> buscarPeloId(String id) {
			
			Optional<Coordenador> coordenador = coordenadorRepository.findById(id);
			
			if(coordenador == null) {
				
				/* neste caso ira lancar uma RecursoNaoEncontradoException,  que ira chamar o ExceptionHandler 
				 * correspondente da classe RecursosExceptionHandler do pacote handler, quer ira 
				 *retornar um 404 para o cliente com detalhes da possivel causa do erro */
				throw new RecursoNaoEncontradoException("Nenhum Coordenador foi encontrado");
			}
			
			return coordenador;
		}
		
		public Coordenador salvar(Coordenador coordenador) {
			
			
			return coordenadorRepository.save(coordenador);
		}
		
		/**
		 * Metodo para verificar a existencia de um Coordenador.
		 * Chama o metodo buscarPeloId que se nao encontrar
		 * o Coordenador lanca uma excecao que lanca um NOT FOUND 
		 * @param usuario
		 */
		public void verificarExistencia(Coordenador coordenador) {
			buscarPeloId(coordenador.getSiape());
		}
		
		public void atualizar(Coordenador coordenador) {
			
			/* vericando se o vinho realmente existe, poderia ter
			 * chamado o metodo buscarPeloId direto, mas criei o metodo
			 *  verificar existencia para ajudar na legibilidade do codigo */
			verificarExistencia(coordenador);
			coordenadorRepository.save(coordenador);
		}
		
		public void deletar(String id) {
			
			try {			
				coordenadorRepository.deleteById(id);			
			} catch (EmptyResultDataAccessException e) {
				throw new RecursoNaoEncontradoException("O Coordenador não foi encontrado");
			}
		}
		
		public Coordenador buscarPeloLogin(String login) {
			
			Coordenador coordenador = coordenadorRepository.getCoordenadorByLogin(login);
			
			if(coordenador == null) {
				/* neste caso ira lancar uma RecursoNaoEncontradoException,  que ira chamar o ExceptionHandler 
				 * correspondente da classe RecursosExceptionHandler do pacote handler, quer ira 
				 *retornar um 404 para o cliente com detalhes da possivel causa do erro */
				throw new RecursoNaoEncontradoException("Nenhum Coordenador foi encontrado");
			}
			
			return coordenador;
		}
	}