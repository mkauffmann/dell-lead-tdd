package com.dell.tdd.main;

import java.util.regex.Pattern;

public class Login {
	private String usuario;
	private String senha;
	private boolean sucesso;
	
	
	
	public Login() {
	}


	public Login(String usuario, String senha) {
		this.usuario = usuario;
		this.senha = senha;
	}
	
	public void validarUsuario(String usuario) {
		if(usuario == null || usuario.isBlank()) {
			throw new IllegalArgumentException("Preencha o campo 'nome de usuário' corretamente");
		} else {
			Pattern pattern = Pattern.compile("[a-zA-Z]");
			
			if(!pattern.matcher(usuario).find()) {
				throw new IllegalArgumentException("Usuário inválido");
			}
		}
		
	}
	
	public void validarSenha(String senha) {
		if(senha == null || senha.isBlank()) {
			throw new IllegalArgumentException("Preencha o campo 'senha' corretamente");
		} else {
			Pattern patternMaiuscula = Pattern.compile("[A-Z]");
			Pattern patternNumeros = Pattern.compile("[0-9]");
			Pattern patternEspeciais = Pattern.compile("\\W");
			
			if(!patternMaiuscula.matcher(senha).find()) {
				throw new IllegalArgumentException("Senha deve conter uma letra maiúscula");
			}
			if(!patternNumeros.matcher(senha).find()) {
				throw new IllegalArgumentException("Senha deve conter um número");
			}
			if(!patternEspeciais.matcher(senha).find()) {
				throw new IllegalArgumentException("Senha deve conter um caracter especial");
			}
		}
	}
	
	public void enviar(String usuario, String senha) {
		try {
			
			this.validarUsuario(usuario);
			this.validarSenha(senha);
			
			this.setUsuario(usuario);
			this.setSenha(senha);
			
			logar(usuario, senha);
			
		}catch (Exception e) {
			
			this.setSucesso(false);
			throw e;
		}
	}
	
	public void logar(String usuario, String senha) {
		/*
		 * Este objeto simula o retorno do banco de dados para checagem do usuario e senha corretos
		 */
		Login loginDTO = new Login("teste123!#$", "t3S!e");
		
		if(loginDTO.getUsuario() == usuario && loginDTO.getSenha() == senha){
			this.setSucesso(true);
		} else {
			this.setSucesso(false);
		}
		
	}


	public String getUsuario() {
		return usuario;
	}


	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}


	public boolean isSucesso() {
		return sucesso;
	}


	public void setSucesso(boolean sucesso) {
		this.sucesso = sucesso;
	}
	
	
	

}
