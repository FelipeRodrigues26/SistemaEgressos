package br.com.egressos.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.egressos.service.UsuarioService;
import br.com.egressos.model.Usuario;

@RestController
@RequestMapping("/usuarios")
public class UsuarioResource {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping
	public ResponseEntity<List<Usuario>> listar() {
		return ResponseEntity.ok().body(usuarioService.listar());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Optional<Usuario>> buscarPeloId(@PathVariable String id) {

		// se nao encontrar um vinho com o ID passado como parametro, retorna um 404 NOT FOUND
		Optional<Usuario> usuario = usuarioService.buscarPeloId(id);
		return ResponseEntity.ok().body(usuario);
	}

	@PostMapping
	public ResponseEntity<Void> salvar(@RequestBody Usuario usuario) {

		usuario = usuarioService.salvar(usuario);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getLogin()).toUri();

		return ResponseEntity.created(uri).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> atualiazar(@PathVariable String id, @RequestBody Usuario usuario) {

		// garantindo que vai atualizar o usuario correto
		usuario.setLogin(id);
		usuarioService.atualizar(usuario);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable String id) {
		usuarioService.deletar(id);
		return ResponseEntity.noContent().build();
	}

}
