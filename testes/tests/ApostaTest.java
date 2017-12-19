package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import entidades.Aposta;

/**
 * Teste da classe Aposta.
 * 
 * Lab05 - Laboratório de Programação II
 * 
 * @author Héricles Emanuel - 117110647
 *
 */
public class ApostaTest {

	Aposta aposta, outraAposta;

	/*
	 * Inicializa a Aposta aposta.
	 */
	@Before
	public void inicializaAposta() {
		aposta = new Aposta("Gauds", 2048, "N VAI ACONTECER");
	}

	/*
	 * Testa o construtor de uma aposta.
	 */
	@Test
	public void testConstrutor() {
		assertTrue(outraAposta == null);
		outraAposta = new Aposta("Pericles", 9999, "VAI ACONTECER");
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
		outraAposta = new Aposta("Picles", 10000, "N VAI ACONTECER");
		assertEquals("Picles - R$100,00 - N VAI ACONTECER", outraAposta.toString());
	}

	/*
	 * Testa o construtor de uma aposta quando o apostador é nulo.
	 */
	@Test(expected = NullPointerException.class)
	public void testApostadorNulo() {
		outraAposta = new Aposta(null, 156513641, "N VAI ACONTECER");
	}

	/*
	 * Testa o construtor de uma aposta quando o apostador é vazio.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testApostadorInvalido() {
		outraAposta = new Aposta("", 10000, "N VAI ACONTECER");
		outraAposta = new Aposta("       ", 10000, "N VAI ACONTECER");

	}

	/*
	 * Testa o construtor de uma aposta quando a previsão é nula.
	 */
	@Test(expected = NullPointerException.class)
	public void testPrevisaoNula() {
		outraAposta = new Aposta("Hemi", 15641, null);
	}

	/*
	 * Testa o construtor de uma aposta quando a previsão é vazia.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testPrevisaoVazia() {
		outraAposta = new Aposta("Anjo", 123456, "   ");
		outraAposta = new Aposta("Querubim", 123456, "");
	}

	/*
	 * Testa o construtor de uma aposta quando a previsão é inválida.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testPrevisaoInvalida() {
		outraAposta = new Aposta("Arcanjo", 123456, "HAUSHAUHSN");
		outraAposta = new Aposta("Arcanjo", 123456, "N VAI ACONTECR");
	}

	/*
	 * Testa o construtor de uma aposta quando o valor é inválido.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testValorInvalido() {
		outraAposta = new Aposta("Gauds", 0, "N VAI ACONTECER");
		outraAposta = new Aposta("Gauds", -1, "N VAI ACONTECER");
	}

}