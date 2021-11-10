package com.serratec.backend.projetoServiceDto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.serratec.backend.projetoServiceDto.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Usuario findByEmail(String email);

}
