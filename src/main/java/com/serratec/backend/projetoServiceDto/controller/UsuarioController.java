package com.serratec.backend.projetoServiceDto.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.serratec.backend.projetoServiceDto.UsuarioDto;
import com.serratec.backend.projetoServiceDto.UsuarioInserirDto;
import com.serratec.backend.projetoServiceDto.domain.Usuario;
import com.serratec.backend.projetoServiceDto.exception.EmailException;
import com.serratec.backend.projetoServiceDto.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/todos")
	public ResponseEntity<List<UsuarioDto>> listar(){
		List<UsuarioDto> usuarios = usuarioService.findAll();
		return ResponseEntity.ok(usuarios);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Usuario>> listarUnit(@PathVariable ("id") long id){
		Optional<Usuario> user = usuarioService.findById(id);
		return ResponseEntity.ok(user);
	}
	
	@PostMapping("/inserir")
	
	//Object pq vai ter vários tipos de retorno
	
	public ResponseEntity<Object> inserir(@RequestBody UsuarioInserirDto usuarioInserirDto) {
		try {
			UsuarioDto usuarioDto = usuarioService.inserir(usuarioInserirDto);
			
			// criador de URI:
			// .do pedido atual
			// .caminho: númeero do id
			// .criar e expandir o númereo do id
			// .para a URI
			
			
			URI uri = ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(usuarioDto.getId())
					.toUri();
			
		return ResponseEntity.created(uri).body(usuarioDto);
		
		} catch (EmailException ex) {
			return ResponseEntity.unprocessableEntity().body(ex.getMessage());
		}
	}
	
	
}
