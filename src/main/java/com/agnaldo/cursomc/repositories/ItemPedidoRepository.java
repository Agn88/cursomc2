package com.agnaldo.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agnaldo.cursomc.domain.ItemPedido;

//Interface para implementação do JPA, que instanciando a mesma é possivel trabalhar com o banco de dados
@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {
	
}
