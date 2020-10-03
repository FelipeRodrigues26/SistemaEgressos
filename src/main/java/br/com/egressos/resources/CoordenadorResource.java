package br.com.egressos.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.egressos.service.CoordenadorService;
import br.com.egressos.model.Coordenador;
import br.com.egressos.model.Usuario;

@RestController
@RequestMapping("/coordenador")
public class CoordenadorResource {

	@Autowired
	private CoordenadorService coordenadorService;

	@GetMapping
	public ResponseEntity<List<Coordenador>> listar() {
		return ResponseEntity.ok().body(coordenadorService.listar());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Optional<Coordenador>> buscarPeloId(@PathVariable String id) {

		// se nao encontrar um coordenador com o ID passado como parametro, retorna um 404 NOT FOUND
		Optional<Coordenador> coordenador = coordenadorService.buscarPeloId(id);

		return ResponseEntity.ok().body(coordenador);
	}
	
	@GetMapping("/buscaPorLogin/{login}")
	public ResponseEntity<Coordenador> buscarPeloLogin(@PathVariable String login) {

		// se nao encontrar um coordenador com o Login passado como parametro, retorna um 404 NOT FOUND
		Coordenador coordenador = coordenadorService.buscarPeloLogin(login);

		return ResponseEntity.ok().body(coordenador);
	}


	@PostMapping
	public ResponseEntity<Coordenador> salvar(@RequestBody Coordenador coordenador) {

		Usuario usuario  = coordenador.getUsuario();
		usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
		usuario.setRole("2");
		coordenador.setUsuario(usuario);
		coordenador = coordenadorService.salvar(coordenador);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(coordenador.getSiape()).toUri();

		return ResponseEntity.created(uri).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> atualizar(@PathVariable String id, @RequestBody Coordenador coordenador) {

		// garantindo que vai atualizar o coordenador correto
		coordenador.setSiape(id);
		coordenadorService.atualizar(coordenador);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable String id) {
		coordenadorService.deletar(id);
		return ResponseEntity.noContent().build();
	}

}
