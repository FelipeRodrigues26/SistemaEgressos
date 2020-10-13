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

import br.com.egressos.model.Oportunidade;
import br.com.egressos.model.Qualificacao;
import br.com.egressos.service.OportunidadeService;

@RestController
@RequestMapping("/oportunidades")
public class OportunidadeResource {

	@Autowired
	private OportunidadeService oportunidadeService;

	@GetMapping
	public ResponseEntity<List<Oportunidade>> listar() {
		return ResponseEntity.ok().body(oportunidadeService.listar());
	}
	
	@GetMapping("/buscaPorCNPJ/{idEmpresa}")
	public ResponseEntity<List<Oportunidade>> buscarPeloIdEmpresa(@PathVariable String idEmpresa) {

		// se nao encontrar nenhuma Oportunidade com o ID Empresa passado como parametro, retorna um 404 NOT FOUND
		List<Oportunidade> oportunidades = oportunidadeService.listarPorIdEmpresa(idEmpresa);

		return ResponseEntity.ok().body(oportunidades);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Oportunidade>> buscarPeloId(@PathVariable Integer id) {

		// se nao encontrar uma oportunidade com o ID passado como parametro, retorna um 404 NOT FOUND
		Optional<Oportunidade> oportunidade = oportunidadeService.buscarPeloId(id);

		return ResponseEntity.ok().body(oportunidade);
	}
	
	
	@PostMapping
	public ResponseEntity<Qualificacao> salvar(@RequestBody Oportunidade oportunidade) {

		oportunidade = oportunidadeService.salvar(oportunidade);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(oportunidade.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> atualizar(@PathVariable Integer id, @RequestBody Oportunidade oportunidade) {

		// garantindo que vai atualizar o oportunidade correto
		oportunidade.setId(id);
		oportunidadeService.atualizar(oportunidade);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Integer id) {
		oportunidadeService.deletar(id);
		return ResponseEntity.noContent().build();
	}

}