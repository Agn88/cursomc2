package com.agnaldo.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agnaldo.cursomc.domain.Estado;
import com.agnaldo.cursomc.repositories.EstadoRepository;
import com.agnaldo.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository repo;
	
	
	//Retorna null caso não seja localizado nenhum objeto
	public Estado buscar(Integer id) {
		Optional<Estado> obj = repo.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Estado.class.getName()));
	}
	
}
