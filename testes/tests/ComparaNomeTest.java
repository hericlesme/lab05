package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import comparators.ComparaNome;
import entidades.Cenario;
import entidades.CenarioBonus;
import entidades.CenarioDefault;

/**
 * Classe de Testes JUnity da classe ComparaNome.
 * 
 * Lab05 - Laboratório de Programação II
 * 
 * @author Héricles Emanuel - 117110647
 *
 */
public class ComparaNomeTest {
	ComparaNome cn;
	Cenario c1, c2;

	/**
	 * Testa o método compare, pela ordem alfabética do apostador, quando c1 vier
	 * antes de c2.
	 */
	@Test
	public void testCompareAntes() {
		cn = new ComparaNome();
		c1 = new CenarioDefault("Aninha de biu", 1);
		c2 = new CenarioDefault("biu de aninha", 2);
		assertTrue(cn.compare(c1, c2) < 0);
	}

	/**
	 * Testa o método compare, pela ordem alfabética do apostador, quando c1 vier
	 * depois de c2.
	 */
	@Test
	public void testCompareMenor() {
		cn = new ComparaNome();
		c2 = new CenarioDefault("HELLO FROM", 1);
		c1 = new CenarioBonus("THE OUSIIIIIDE", 1000, 2);
		assertTrue(cn.compare(c1, c2) > 0);
	}

	/**
	 * Testa o método compare, pela ordem alfabética do apostador, quando c1 for
	 * lexicograficamente equivalente a c2.
	 */
	@Test
	public void testCompareIgual() {
		cn = new ComparaNome();
		c1 = new CenarioDefault("HAAAAA", 1900);
		c2 = new CenarioDefault("HAAAAA", 1900);
		assertEquals(0, cn.compare(c1, c2));
	}

	/**
	 * Testa o construtor de um comparador por nome.
	 */
	@Test
	public void testConstrutor() {
		assertTrue(cn == null);
		cn = new ComparaNome();
		assertFalse(cn == null);
	}

}
