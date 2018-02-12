package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import entidades.Aposta;

public class ApostaTest {
	Aposta aposta, outraAposta;

	/**
	 * Inicializa a Aposta aposta.
	 */
	@Before
	public void inicializaAposta() {
		aposta = new Aposta("Gauds", 2048, "N VAI ACONTECER");
	}

	/**
	 * Testa o construtor de uma aposta sem seguro.
	 */
	@Test
	public void testAposta() {
		assertTrue(outraAposta == null);
		outraAposta = new Aposta("To com fome", 9999, "VAI ACONTECER");
		assertFalse(outraAposta == null);
	}

	/**
	 * Testa o construtor de uma aposta assegurada por valor.
	 */
	@Test
	public void testApostaSeguroValor() {
		assertTrue(outraAposta == null);
		outraAposta = new Aposta("bb me nota", 9999, "VAI ACONTECER", 1000);
		assertFalse(outraAposta == null);
	}

	/**
	 * Testa o construtor de uma aposta assegurada por taxa.
	 */
	@Test
	public void testApostaSeguroTaxa() {
		assertTrue(outraAposta == null);
		outraAposta = new Aposta("Eu vou comer", 9999, "N VAI ACONTECER", 0.23);
		assertFalse(outraAposta == null);
	}

	/*
	 * Testa o método get para o valor da aposta.
	 */
	@Test
	public void testGetValor() {
		assertEquals(2048, aposta.getValor());
	}

	/*
	 * Testa o método get para o valor do seguro da aposta.
	 */
	@Test
	public void testGetSeguro() {
		assertEquals(0, aposta.getSeguro());
		outraAposta = new Aposta("q pena q vc n vai ver isso", 100, "VAI ACONTECER", 500);
		assertEquals(500, outraAposta.getSeguro());
		outraAposta = new Aposta("c ta tao longe agora", 1000, "VAI ACONTECER", 0.05);
		assertEquals(50, outraAposta.getSeguro());
	}

	/*
	 * Testa o método get para a previsão da aposta.
	 */
	@Test
	public void testGetPrevisao() {
		assertEquals("N VAI ACONTECER", aposta.getPrevisao());
	}

	/*
	 * Testa a representação em String de uma aposta.
	 */
	@Test
	public void testToString() {
		assertEquals("Gauds - R$20,48 - N VAI ACONTECER", aposta.toString());
	}

	/*
	 * Testa a representação em String de uma aposta assegurada por valor.
	 */
	@Test
	public void testToStringAsseguradaValor() {
		outraAposta = new Aposta("eh ne", 100, "VAI ACONTECER", 500);
		assertEquals("eh ne - R$1,00 - VAI ACONTECER - ASSEGURADA (VALOR) - R$ 5,00", outraAposta.toString());
	}

	/*
	 * Testa a representação em String de uma aposta assegurada por taxa.
	 */
	@Test
	public void testToStringAsseguradaTaxa() {
		outraAposta = new Aposta("eh a vida", 1000, "VAI ACONTECER", 0.5);
		assertEquals("eh a vida - R$10,00 - VAI ACONTECER - ASSEGURADA (TAXA) - 50%", outraAposta.toString());
	}

	/**
	 * Testa a alteração para um seguro de valor.
	 */
	@Test
	public void testAlterarSeguroValor() {
		outraAposta = new Aposta("mas vc n me nota", 1000, "N VAI ACONTECER", 0.1);
		assertEquals(100, outraAposta.getSeguro());
		outraAposta.alterarSeguroValor(200);
		assertEquals(200, outraAposta.getSeguro());
	}

	/**
	 * Testa a alteração para um seguro de taxa.
	 */
	@Test
	public void testAlterarSeguroTaxa() {
		outraAposta = new Aposta("ate quando isso", 1000, "N VAI ACONTECER", 100);
		assertEquals(100, outraAposta.getSeguro());
		outraAposta.alterarSeguroTaxa(0.2);
		assertEquals(200, outraAposta.getSeguro());
	}

}
