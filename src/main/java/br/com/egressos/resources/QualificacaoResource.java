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
import br.com.egressos.model.Qualificacao;
import br.com.egressos.model.QualificacaoTipo;
import br.com.egressos.service.QualificacaoService;

@RestController
@RequestMapping("/qualificacoes")
public class QualificacaoResource {

	@Autowired
	private QualificacaoService qualificacaoService;

	@GetMapping
	public ResponseEntity<List<Qualificacao>> listar() {
		return ResponseEntity.ok().body(qualificacaoService.listar());
	}
	
	@GetMapping("/tipos")
	public ResponseEntity<List<QualificacaoTipo>> listarTiposQualificacao() {
		return ResponseEntity.ok().body(qualificacaoService.listarTiposQualificacao());
	}
	

	@GetMapping("/{id}")
	public ResponseEntity<Optional<Qualificacao>> buscarPeloId(@PathVariable Integer id) {

		// se nao encontrar uma qualificacao com o ID passado como parametro, retorna um 404 NOT FOUND
		Optional<Qualificacao> qualificacao = qualificacaoService.buscarPeloId(id);

		return ResponseEntity.ok().body(qualificacao);
	}
	
	@GetMapping("/buscaPorCPF/{idEgresso}")
	public ResponseEntity<List<Qualificacao>> buscarPeloIdEgresso(@PathVariable String idEgresso) {

		// se nao encontrar nenhuma Qualificacao com o ID Egresso passado como parametro, retorna um 404 NOT FOUND
		List<Qualificacao> qualificacoes = qualificacaoService.listarPorIdEgresso(idEgresso);

		return ResponseEntity.ok().body(qualificacoes);
	}
	


	@PostMapping
	public ResponseEntity<Qualificacao> salvar(@RequestBody Qualificacao qualificacao) {

		qualificacao = qualificacaoService.salvar(qualificacao);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(qualificacao.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> atualizar(@PathVariable Integer id, @RequestBody Qualificacao qualificacao) {

		// garantindo que vai atualizar o qualificacao correto
		qualificacao.setId(id);
		qualificacaoService.atualizar(qualificacao);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Integer id) {
		qualificacaoService.deletar(id);
		return ResponseEntity.noContent().build();
	}

}