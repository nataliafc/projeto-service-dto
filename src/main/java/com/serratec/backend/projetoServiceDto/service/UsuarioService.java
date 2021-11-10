package com.serratec.backend.projetoServiceDto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.serratec.backend.projetoServiceDto.domain.Usuario;
import com.serratec.backend.projetoServiceDto.exception.EmailException;
import com.serratec.backend.projetoServiceDto.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public List<Usuario> findAll(){
		return usuarioRepository.findAll();
	}
	
	public Optional<Usuario> findById(long id){
		return usuarioRepository.findById(id);
	}
	
	public Usuario insert(Usuario user) throws EmailException {
		Usuario usuario = usuarioRepository.findByEmail(user.getEmail());
		if(usuario != null) {
			throw new EmailException("E-mail existente.");
		}
		
		user.setSenha(passwordEncoder.encode(user.getSenha()));
		return usuarioRepository.save(user);
	}
}
