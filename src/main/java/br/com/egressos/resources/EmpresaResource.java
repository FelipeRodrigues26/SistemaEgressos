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

import br.com.egressos.service.EmpresaService;
import br.com.egressos.model.Empresa;
import br.com.egressos.model.Usuario;

@RestController
@RequestMapping("/empresas")
public class EmpresaResource {

	@Autowired
	private EmpresaService empresaService;

	@GetMapping
	public ResponseEntity<List<Empresa>> listar() {
		return ResponseEntity.ok().body(empresaService.listar());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Optional<Empresa>> buscarPeloId(@PathVariable String id) {

		// se nao encontrar um empresa com o ID passado como parametro, retorna um 404 NOT FOUND
		Optional<Empresa> empresa = empresaService.buscarPeloId(id);

		return ResponseEntity.ok().body(empresa);
	}
	
	@GetMapping("/buscaPorLogin/{login}")
	public ResponseEntity<Empresa> buscarPeloLogin(@PathVariable String login) {

		// se nao encontrar uma empresa com o Login passado como parametro, retorna um 404 NOT FOUND
		Empresa empresa = empresaService.buscarPeloLogin(login);

		return ResponseEntity.ok().body(empresa);
	}


	@PostMapping
	public ResponseEntity<Empresa> salvar(@RequestBody Empresa empresa) {

		Usuario usuario  = empresa.getUsuario();
		usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
		usuario.setRole("3");
		empresa.setUsuario(usuario);
		empresa = empresaService.salvar(empresa);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(empresa.getCnpj()).toUri();

		return ResponseEntity.created(uri).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> atualizar(@PathVariable String id, @RequestBody Empresa empresa) {

		// garantindo que vai atualizar o empresa correto
		empresa.setCnpj(id);
		empresaService.atualizar(empresa);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable String id) {
		empresaService.deletar(id);
		return ResponseEntity.noContent().build();
	}

}