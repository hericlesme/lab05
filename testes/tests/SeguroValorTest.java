package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import entidades.Seguro;
import entidades.SeguroTaxa;
import entidades.SeguroValor;


/**
 * Classe de testes JUnity da classe SeguroValor.
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

	/**
	 * Testa a alteração do tipo de um SeguroValor.
	 */
	@Test
	public void testAlternaTipo() {
		seguroValor = new SeguroValor(100);
		Seguro naoSeguro = seguroValor.alternaTipo(new Object[] { 1000, 0.1 });
		Seguro outroSeguro = new SeguroTaxa(1000, 0.1);
		assertEquals(naoSeguro.toString(), outroSeguro.toString());
		assertEquals(naoSeguro.getValor(), outroSeguro.getValor());
	}
}
