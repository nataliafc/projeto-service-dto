package com.serratec.backend.projetoServiceDto;

import com.serratec.backend.projetoServiceDto.domain.Usuario;

public class UsuarioDto {
	private long id;
	private String nome;
	private String email;
	
	public UsuarioDto() {
		
	}
	public UsuarioDto(long id, String nome, String email) {
		this.id = id;
		this.nome = nome;
		this.email = email;
	}
	
	public UsuarioDto(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	// essa classe vai se sobrepor à classe Usuario, e vai entregar apenas os atributos descritos aqui;
	// o GET vai entregar id, nome e email, mas o post também vai pedir a tenha
	// portanto, no método POST será usada a classe Usuario, e no método GET será usada a classe UsuarioDto

}
