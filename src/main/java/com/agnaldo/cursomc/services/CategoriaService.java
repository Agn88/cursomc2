package com.agnaldo.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agnaldo.cursomc.domain.Categoria;
import com.agnaldo.cursomc.repositories.CategoriaRepository;
import com.agnaldo.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	
	//Retorna null caso não seja localizado nenhum objeto
	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
}
