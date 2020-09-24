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

import br.com.egressos.service.EgressoService;
import br.com.egressos.model.Egresso;
import br.com.egressos.model.Usuario;

@RestController
@RequestMapping("/egressos")
public class EgressoResource {

	@Autowired
	private EgressoService egressoService;

	@GetMapping
	public ResponseEntity<List<Egresso>> listar() {
		return ResponseEntity.ok().body(egressoService.listar());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Optional<Egresso>> buscarPeloId(@PathVariable String id) {

		// se nao encontrar um egresso com o ID passado como parametro, retorna um 404 NOT FOUND
		Optional<Egresso> egresso = egressoService.buscarPeloId(id);

		return ResponseEntity.ok().body(egresso);
	}

	@PostMapping
	public ResponseEntity<Egresso> salvar(@RequestBody Egresso egresso) {

		Usuario usuario  = egresso.getUsuario();
		usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
		usuario.setRole("1");
		egresso.setUsuario(usuario);
		egresso = egressoService.salvar(egresso);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(egresso.getCpf()).toUri();

		return ResponseEntity.created(uri).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> atualizar(@PathVariable String id, @RequestBody Egresso egresso) {

		// garantindo que vai atualizar o Egresso correto
		egresso.setCpf(id);
		egressoService.atualizar(egresso);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable String id) {
		egressoService.deletar(id);
		return ResponseEntity.noContent().build();
	}

}
