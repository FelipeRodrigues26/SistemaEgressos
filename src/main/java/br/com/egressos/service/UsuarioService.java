package br.com.egressos.service;

	import java.util.List;
	import java.util.Optional;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.dao.EmptyResultDataAccessException;
	import org.springframework.stereotype.Service;

	import br.com.egressos.model.Usuario;
	import br.com.egressos.repository.UsuarioRepository;
	import br.com.egressos.service.exceptions.RecursoNaoEncontradoException;

	@Service
	public class UsuarioService {
		
		@Autowired
		private UsuarioRepository usuarioRepository;
		
		public List<Usuario> listar() {
			return usuarioRepository.findAll();
		}
		
		public Optional<Usuario> buscarPeloId(String id) {
			
			Optional<Usuario> usuario = usuarioRepository.findById(id);
			
			if(usuario == null) {
				
				/* neste caso ira lancar uma RecursoNaoEncontradoException,  que ira chamar o ExceptionHandler 
				 * correspondente da classe RecursosExceptionHandler do pacote handler, quer ira 
				 *retornar um 404 para o cliente com detalhes da possivel causa do erro */
				throw new RecursoNaoEncontradoException("Nenhum usuario foi encontrado");
			}
			
			return usuario;
		}
		
		public Usuario salvar(Usuario usuario) {
			
			/* este metodo nao faz atualizacao por isso e preciso garantir que o id sera sempre nulo,
			 * caso contrario o vinho sera atualizado em vez de criado um novo vinho 
			 * */
			
			usuario.setLogin(null);
			return usuarioRepository.save(usuario);
		}
		
		/**
		 * Metodo para verificar a existencia de um usuario.
		 * Chama o metodo buscarPeloId que se nao encontrar
		 * o usuario lanca uma excecao que lanca um NOT FOUND 
		 * @param usuario
		 */
		public void verificarExistencia(Usuario usuario) {
			buscarPeloId(usuario.getLogin());
		}
		
		public void atualizar(Usuario usuario) {
			
			/* vericando se o vinho realmente existe, poderia ter
			 * chamado o metodo buscarPeloId direto, mas criei o metodo
			 *  verificar existencia para ajudar na legibilidade do codigo */
			verificarExistencia(usuario);
			usuarioRepository.save(usuario);
		}
		
		public void deletar(String id) {
			
			try {			
				usuarioRepository.deleteById(id);			
			} catch (EmptyResultDataAccessException e) {
				throw new RecursoNaoEncontradoException("O usuario não foi encontrado");
			}
		}

	}
