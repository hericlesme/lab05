package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import entidades.Seguro;
import entidades.SeguroTaxa;
import entidades.SeguroValor;

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
		assertEquals(10, seguroTaxa.getValor());
	}

	/**
	 * Testa a saída do método toString do SeguroTaxa.
	 */
	@Test
	public void testToString() {
		seguroTaxa = new SeguroTaxa(100, 0.1);
		assertEquals(" - ASSEGURADA (TAXA) - 10%", seguroTaxa.toString());
	}

	@Test
	public void testAlternaTipo() {
		seguroTaxa = new SeguroTaxa(100, 0.1);
		Seguro naoSeguro = seguroTaxa.alternaTipo(new Object[] {100});
		assertEquals(naoSeguro.toString()," - ASSEGURADA (VALOR) - R$ 100,00");
	}

}
