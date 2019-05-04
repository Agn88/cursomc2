package com.agnaldo.cursomc.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.agnaldo.cursomc.domain.Cliente;

//Interface para implementação do JPA, que instanciando a mesma é possivel trabalhar com o banco de dados
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	
	//Utilizando deste modo o Spring automicamente detecta que deve-se procurar o email
	
	 
	
	@Transactional(readOnly=true)		
	Cliente findByEmail(String email);
	
	
}
