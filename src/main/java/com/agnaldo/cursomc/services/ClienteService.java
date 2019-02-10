package com.agnaldo.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.agnaldo.cursomc.domain.Cidade;
import com.agnaldo.cursomc.domain.Cliente;
import com.agnaldo.cursomc.domain.Endereco;
import com.agnaldo.cursomc.domain.enums.TipoCliente;
import com.agnaldo.cursomc.dto.ClienteDTO;
import com.agnaldo.cursomc.dto.ClienteNewDTO;
import com.agnaldo.cursomc.repositories.CidadeRepository;
import com.agnaldo.cursomc.repositories.ClienteRepository;
import com.agnaldo.cursomc.repositories.EnderecoRepository;
import com.agnaldo.cursomc.services.exceptions.DataIntegrityException;
import com.agnaldo.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	@Autowired
	private CidadeRepository cidadeRepository;


	@Autowired
	private EnderecoRepository enderecoRepository;
	
	// Retorna null caso não seja localizado nenhum objeto
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	
	public Cliente insert(Cliente obj)
	{
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}
	
	public Cliente update(Cliente obj)
	{
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(obj);
	}
	
	public void delete(Integer id)
	{
		find(id);
		try {
			repo.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir pois há entidades relacionadas!");
		}
		
	}
	
	
	public List<Cliente> findAll()
	{
		return repo.findAll();
	}
	
	
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction)
	{
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy); 
		return repo.findAll(pageRequest);
	}
	
	
	public Cliente fromDTO(ClienteDTO objDto)
	{
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}
	
	
	public Cliente fromDTO(ClienteNewDTO objDto)
	{
		Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()));
		Optional<Cidade> cid = cidadeRepository.findById(objDto.getCidadeId());
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid.orElse(null));
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDto.getTelefone1());
		if(objDto.getTelefone2()!=null)
		{
			cli.getTelefones().add(objDto.getTelefone2());
		}
		if(objDto.getTelefone3()!=null)
		{
			cli.getTelefones().add(objDto.getTelefone3());
		}
		
		return cli;
		
	}
	
	
	private void updateData(Cliente newObj, Cliente obj)
	{
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}

}
