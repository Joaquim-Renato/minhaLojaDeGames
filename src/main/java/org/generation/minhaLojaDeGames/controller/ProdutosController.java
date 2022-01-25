package org.generation.minhaLojaDeGames.controller;

	import java.util.List;

import javax.validation.Valid;

import org.generation.minhaLojaDeGames.model.Produtos;
import org.generation.minhaLojaDeGames.repository.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

	@RestController
	@RequestMapping("/produto")
	public class ProdutosController {

		@Autowired
		private ProdutosRepository repository;

		@GetMapping
		public ResponseEntity<List<Produtos>> findAllProduto() {
			return ResponseEntity.ok(repository.findAll());
		}

		@GetMapping("/{id}")
		public ResponseEntity<Produtos> findByIDProduto(@PathVariable long id) {
			return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
		}

		@GetMapping("titulo/{titulo}")
		public ResponseEntity<List<Produtos>> findByDescricaoTitulo(@PathVariable String titulo) {
			return ResponseEntity.ok(repository.findAllByProdutoContainingIgnoreCase(titulo));
		}
		
		@PostMapping
		public ResponseEntity<Produtos> postProduto (@RequestBody Produtos produtos){
			return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(produtos));
		}
		
		@PutMapping
		public ResponseEntity<Produtos> putProdutos (@Valid @RequestBody Produtos produtos){
			return repository.findById(produtos.getId()).map(resp -> ResponseEntity.ok().body(repository.save(produtos)))
					.orElse(ResponseEntity.notFound().build());
		}
		
		@DeleteMapping("/{id}")
		public void deleteProdutos(@PathVariable long id) {
			repository.deleteById(id);
		}
	
}
