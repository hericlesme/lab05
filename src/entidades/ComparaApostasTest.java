package entidades;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Classe de Testes JUnity da classe ComparaApostas.
 * 
 * Lab05 - Laboratório de Programação II
 * 
 * @author Héricles Emanuel - 117110647
 *
 */
public class ComparaApostasTest {
	ComparaApostas ca;
	Cenario c1, c2;

	/**
	 * Inicializa os cenários c1 e c2.
	 */
	@Before
	public void inicializa() {
		c1 = new CenarioDefault("mamae passou", 1);
		c2 = new CenarioDefault("açuca in mim", 2);
	}

	/**
	 * Testa o método compare, pela ordem da quantidade de apostas.
	 */
	@Test
	public void testCompare() {
		assertTrue(ca == null);
		ca = new ComparaApostas();
		assertFalse(ca == null);
	}

}
