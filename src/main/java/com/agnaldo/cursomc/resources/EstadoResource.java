package com.agnaldo.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.agnaldo.cursomc.domain.Estado;
import com.agnaldo.cursomc.services.EstadoService;

@RestController
@RequestMapping(value = "/estados")
public class EstadoResource {

	@Autowired
	private EstadoService service;
	
	//Com base no número de ID recebido via URL retorna os valores na tela
	@RequestMapping(value="/{id}",  method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id)
	{
		Estado obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);
	}
	
}
