package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import comparators.ComparaId;
import entidades.Cenario;
import entidades.CenarioBonus;
import entidades.CenarioDefault;

/**
 * Classe de Testes JUnity da classe ComparaId.
 * 
 * Lab05 - Laboratório de Programação II
 * 
 * @author Héricles Emanuel - 117110647
 *
 */
public class ComparaIdTest {
	ComparaId ci;
	Cenario c1, c2;

	/**
	 * Testa o método compare, pela ordem da cadastramento, quando c1 for cadastrado
	 * antes de c2.
	 */
	@Test
	public void testCompareAntes() {
		ci = new ComparaId();
		c1 = new CenarioDefault("Sente so", 1);
		c2 = new CenarioBonus("Essa pressao", 1000, 2);
		assertEquals(-1, ci.compare(c1, c2));
	}

	/**
	 * Testa o método compare, pela ordem de cadastramento, quando c1 for cadastrado
	 * depois de c2.
	 */
	@Test
	public void testCompareDepois() {
		ci = new ComparaId();
		c2 = new CenarioBonus("Essa pressao", 1000, 1);
		c1 = new CenarioDefault("Sente so", 2);
		assertEquals(1, ci.compare(c1, c2));
	}

	/**
	 * Testa o construtor de um comparador por Id.
	 */
	@Test
	public void testConstrutor() {
		assertTrue(ci == null);
		ci = new ComparaId();
		assertFalse(ci == null);
	}

}
