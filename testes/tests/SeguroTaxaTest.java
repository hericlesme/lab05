package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import entidades.Seguro;
import entidades.SeguroTaxa;

/**
 * Classe de Testes JUnity da classe SeguroTaxa.
 * 
 * Lab05 - Laboratório de Programação II
 * 
 * @author Héricles Emanuel - 117110647
 *
 */
public class SeguroTaxaTest {
	Seguro seguroTaxa;

	/**
	 * Testa o construtor de um SeguroTaxa.
	 */
	@Test
	public void testConstrutor() {
		assertTrue(seguroTaxa == null);
		seguroTaxa = new SeguroTaxa(999, 0.22);
		assertFalse(seguroTaxa == null);
	}

	/**
	 * Testa o método getValor do SeguroTaxa.
	 */
	@Test
	public void testGetValor() {
		seguroTaxa = new SeguroTaxa(100, 0.1);
		assertTrue(seguroTaxa.getValor() == 10);
	}

	/**
	 * Testa a saída do método toString do SeguroTaxa.
	 */
	@Test
	public void testToString() {
		seguroTaxa = new SeguroTaxa(100, 0.1);
		assertEquals(" - ASSEGURADA (TAXA) - 10%", seguroTaxa.toString());
	}

}