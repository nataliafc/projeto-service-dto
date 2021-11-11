package com.serratec.backend.projetoServiceDto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.serratec.backend.projetoServiceDto.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Usuario findByEmail(String email);

}

// esta classe é usada para configuração de métodos personalizados a serem chamados no Controller e no Service
