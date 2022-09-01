package com.dell.tdd.test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import com.dell.tdd.main.Login;

class LoginTest {
	private String usuarioValido;
	private String senhaValida;
	private Login login;
	
	@BeforeEach
	void init() {
		this.usuarioValido = "teste123!#$";
		this.senhaValida = "t3S!e";
		this.login = new Login();
	}

	@Test
	void deveInstanciarClasseSemParametros() {
		assertNotNull(login);
	}
	
	@Test
	void deveInstanciarClasseComParametros() {
		
		Login login2 = new Login(this.usuarioValido, this.senhaValida);
		
		assertNotNull(login2);
		assertEquals(login2.getUsuario(),this.usuarioValido);
		assertEquals(login2.getSenha(), this.senhaValida);
	}
	
	@ParameterizedTest
	@ValueSource(strings = {" ", ""})
	@NullAndEmptySource
	void deveValidarUsuarioVazio(String usuario) {
		
		Exception e = assertThrows(IllegalArgumentException.class, () -> login.validarUsuario(usuario));
		assertEquals(e.getMessage(), "Preencha o campo 'nome de usuário' corretamente");
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"123", "1.23/5", ".....;%#"})
	void deveValidarUsuarioNaoContemLetras(String usuario) {
		
		Exception e = assertThrows(IllegalArgumentException.class, () -> login.validarUsuario(usuario));
		assertEquals(e.getMessage(), "Usuário inválido");
	}
	
	@ParameterizedTest
	@ValueSource(strings = {" ", ""})
	@NullAndEmptySource
	void deveValidarSenhaVazia(String senha) {
		
		Exception e = assertThrows(IllegalArgumentException.class, () -> login.validarSenha(senha));
		assertEquals(e.getMessage(), "Preencha o campo 'senha' corretamente");
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"teste", "teste123", "..,maria"})
	void deveValidarSenhaSemMaiuscula(String senha) {
		
		Exception e = assertThrows(IllegalArgumentException.class, () -> login.validarSenha(senha));
		assertEquals(e.getMessage(), "Senha deve conter uma letra maiúscula");
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"Teste", "tesTTT", "..,B"})
	void deveValidarSenhaSemNumero(String senha) {
		
		Exception e = assertThrows(IllegalArgumentException.class, () -> login.validarSenha(senha));
		assertEquals(e.getMessage(), "Senha deve conter um número");
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"Teste12", "tesT312TT", "Gsda2312"})
	void deveValidarSenhaSemCaracteresEspeciais(String senha) {
		
		Exception e = assertThrows(IllegalArgumentException.class, () -> login.validarSenha(senha));
		assertEquals(e.getMessage(), "Senha deve conter um caracter especial");
	}
	
	@Test
	void deveEnviarUsuarioESenhaValidos() {
		this.login.enviar(this.usuarioValido, this.senhaValida);
		assertTrue(this.login.isSucesso());
	}
	
	@Test
	void naoDeveEnviarUsuarioESenhaInvalidos() {
		assertThrows(IllegalArgumentException.class, () -> login.enviar("", ""));
		assertFalse(this.login.isSucesso());
	}
	
	@Test
	void deveLogarComSenhaValida() {
		this.login.logar(usuarioValido, senhaValida);
		
		assertTrue(this.login.isSucesso());
	}
	
	@Test
	void naoDeveLogarComSenhaInvalida() {
		this.login.logar("Mar1a#Teste", "Mar1a#");
		
		assertFalse(this.login.isSucesso());
	}

}
