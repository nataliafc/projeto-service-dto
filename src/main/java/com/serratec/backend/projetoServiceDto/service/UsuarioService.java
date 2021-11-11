package com.serratec.backend.projetoServiceDto.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.serratec.backend.projetoServiceDto.UsuarioDto;
import com.serratec.backend.projetoServiceDto.UsuarioInserirDto;
import com.serratec.backend.projetoServiceDto.domain.Usuario;
import com.serratec.backend.projetoServiceDto.exception.EmailException;
import com.serratec.backend.projetoServiceDto.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public List<UsuarioDto> findAll(){
		List<Usuario> usuarios = usuarioRepository.findAll();
		List<UsuarioDto> usuariosDto = new ArrayList<UsuarioDto>();
		
		// os dois-pontos são um operador foreach
		// para cada usuario em usuarios(que é uma instância da lista de usuários),
		// crie uma nova instância de usuarioDTO, e adicione em usuarioDTO. depois, retorne usuarioDTO
		
		for(Usuario usuario : usuarios) {
			UsuarioDto usuarioDto = new UsuarioDto(usuario);
			usuariosDto.add(usuarioDto);
		}
		
		return usuariosDto;
	}
	
	public Optional<Usuario> findById(long id){
		return usuarioRepository.findById(id);
	}
	
	public UsuarioDto inserir(UsuarioInserirDto usuarioInserirDto) throws EmailException {
		
		if (usuarioRepository.findByEmail(usuarioInserirDto.getEmail()) != null) {
			throw new EmailException("E-mail existente.");
		}
		
		Usuario usuario = new Usuario();
		usuario.setNome(usuarioInserirDto.getNome());
		usuario.setEmail(usuarioInserirDto.getEmail());
		usuario.setSenha(passwordEncoder.encode(usuarioInserirDto.getSenha()));
		usuario = usuarioRepository.save(usuario);
		
		return new UsuarioDto(usuario);
	}
}
