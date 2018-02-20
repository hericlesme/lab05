package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import entidades.Seguro;
import entidades.SeguroValor;


/**
 * Classe de Testes JUnity da classe SeguroValor.
 * 
 * Lab05 - Laboratório de Programação II
 * 
 * @author Héricles Emanuel - 117110647
 *
 */
public class SeguroValorTest {
	Seguro seguroValor;

	/**
	 * Testa o construtor de um SeguroValor.
	 */
	@Test
	public void testConstrutor() {
		assertTrue(seguroValor == null);
		seguroValor = new SeguroValor(100);
		assertFalse(seguroValor == null);
	}

	/**
	 * Testa o método getValor do SeguroValor.
	 */
	@Test
	public void testGetValor() {
		seguroValor = new SeguroValor(333);
		assertTrue(seguroValor.getValor() == 333);
	}

	/**
	 * Testa a saída do método toString do SeguroValor.
	 */
	@Test
	public void testToString() {
		seguroValor = new SeguroValor(500);
		assertEquals(" - ASSEGURADA (VALOR) - R$ 5,00", seguroValor.toString());
	}

}
