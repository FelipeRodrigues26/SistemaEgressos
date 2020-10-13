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

import br.com.egressos.model.HistoricoProfissional;

import br.com.egressos.service.HistoricoProfissionalService;

@RestController
@RequestMapping("/profissional")
public class HistoricoProfissionalResource {

	@Autowired
	private HistoricoProfissionalService historicoProfissionalService;

	@GetMapping
	public ResponseEntity<List<HistoricoProfissional>> listar() {
		return ResponseEntity.ok().body(historicoProfissionalService.listar());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<HistoricoProfissional>> buscarPeloId(@PathVariable Integer id) {

		// se nao encontrar um historico Profissional com o ID passado como parametro, retorna um 404 NOT FOUND
		Optional<HistoricoProfissional> historicoProfissional = historicoProfissionalService.buscarPeloId(id);

		return ResponseEntity.ok().body(historicoProfissional);
	}
	
	@GetMapping("/buscaPorCPF/{idEgresso}")
	public ResponseEntity<List<HistoricoProfissional>> buscarPeloIdEgresso(@PathVariable String idEgresso) {

		// se nao encontrar nenhum historico Profissional com o ID Egresso passado como parametro, retorna um 404 NOT FOUND
		List<HistoricoProfissional> historicoProfissional = historicoProfissionalService.listarPorIdEgresso(idEgresso);

		return ResponseEntity.ok().body(historicoProfissional);
	}
	
	


	@PostMapping
	public ResponseEntity<HistoricoProfissional> salvar(@RequestBody HistoricoProfissional historicoProfissional) {

		historicoProfissional = historicoProfissionalService.salvar(historicoProfissional);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(historicoProfissional.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> atualizar(@PathVariable Integer id, @RequestBody HistoricoProfissional historicoProfissional) {

		// garantindo que vai atualizar o historico Profissional correto
		historicoProfissional.setId(id);
		historicoProfissionalService.atualizar(historicoProfissional);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Integer id) {
		historicoProfissionalService.deletar(id);
		return ResponseEntity.noContent().build();
	}

}