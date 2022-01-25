package org.generation.minhaLojaDeGames.repository;

import java.util.List;

import org.generation.minhaLojaDeGames.model.Produtos;
import org.springframework.data.jpa.repository.JpaRepository; 

public interface ProdutosRepository extends JpaRepository<Produtos, Long>{
	public List<Produtos> findAllByProdutoContainingIgnoreCase(String produto);


}