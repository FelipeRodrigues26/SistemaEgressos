package br.com.egressos.service;

	import java.util.List;
	import java.util.Optional;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.dao.EmptyResultDataAccessException;
	import org.springframework.stereotype.Service;

	import br.com.egressos.model.Egresso;
	import br.com.egressos.repository.EgressoRepository;
	import br.com.egressos.service.exceptions.RecursoNaoEncontradoException;

	@Service
	public class EgressoService {
		
		@Autowired
		private EgressoRepository egressoRepository;
		
		public List<Egresso> listar() {
			return egressoRepository.findAll();
		}
		
		public Optional<Egresso> buscarPeloId(String id) {
			
			Optional<Egresso> egresso = egressoRepository.findById(id);
			
			if(egresso == null) {
				
				/* neste caso ira lancar uma RecursoNaoEncontradoException,  que ira chamar o ExceptionHandler 
				 * correspondente da classe RecursosExceptionHandler do pacote handler, quer ira 
				 *retornar um 404 para o cliente com detalhes da possivel causa do erro */
				throw new RecursoNaoEncontradoException("Nenhum egresso foi encontrado");
			}
			
			return egresso;
		}
		
		public Egresso salvar(Egresso egresso) {
			
			
			return egressoRepository.save(egresso);
		}
		
		/**
		 * Metodo para verificar a existencia de um usuario.
		 * Chama o metodo buscarPeloId que se nao encontrar
		 * o usuario lanca uma excecao que lanca um NOT FOUND 
		 * @param usuario
		 */
		public void verificarExistencia(Egresso egresso) {
			buscarPeloId(egresso.getCpf());
		}
		
		public void atualizar(Egresso usuario) {
			
			/* vericando se o vinho realmente existe, poderia ter
			 * chamado o metodo buscarPeloId direto, mas criei o metodo
			 *  verificar existencia para ajudar na legibilidade do codigo */
			verificarExistencia(usuario);
			egressoRepository.save(usuario);
		}
		
		public void deletar(String id) {
			
			try {			
				egressoRepository.deleteById(id);			
			} catch (EmptyResultDataAccessException e) {
				throw new RecursoNaoEncontradoException("O usuario não foi encontrado");
			}
		}

	}