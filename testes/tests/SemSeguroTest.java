package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import entidades.Seguro;
import entidades.SemSeguro;

public class SemSeguroTest {
	Seguro semSeguro;

	@Test
	public void testConstrutor() {
		assertTrue(semSeguro == null);
		semSeguro = new SemSeguro();
		assertFalse(semSeguro == null);
	}

	@Test
	public void testGetValor() {
		semSeguro = new SemSeguro();
		assertEquals(0, semSeguro.getValor());
	}

	@Test
	public void testToString() {
		semSeguro = new SemSeguro();
		assertEquals("", semSeguro.toString());
	}
}
