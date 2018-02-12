package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import entidades.Seguro;
import entidades.SemSeguro;

/**
 * Classe de testes JUnity da classe SemSeguro.
 * 
 * @author Héricles Emanuel - 117110647
 *
 */
public class SemSeguroTest {
	Seguro semSeguro;

	/**
	 * Testa o construtor de um Objeto de tipo SemSeguro.
	 */
	@Test
	public void testConstrutor() {
		assertTrue(semSeguro == null);
		semSeguro = new SemSeguro();
		assertFalse(semSeguro == null);
	}

	/**
	 * Verifica que a saída do método getValor é zero.
	 */
	@Test
	public void testGetValor() {
		semSeguro = new SemSeguro();
		assertEquals(0, semSeguro.getValor());
	}

	/**
	 * Verifica que a saída do método toString é sempre uma String vazia.
	 */
	@Test
	public void testToString() {
		semSeguro = new SemSeguro();
		assertEquals("", semSeguro.toString());
	}
}
