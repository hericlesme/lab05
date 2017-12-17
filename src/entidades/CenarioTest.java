package entidades;

import static org.junit.Assert.*;

import org.junit.Test;

public class CenarioTest {
	Cenario cenario;

	@Test
	public void testConstrutor() {
		assertTrue(cenario == null);
		cenario = new Cenario("A BARATA MATOU HEMI");
		assertFalse(cenario == null);
	}

	@Test
	public void testCadastraAposta() {
		cenario = new Cenario("HEMI FOI INCENDIADA PELA BARATA");
		assertTrue(cenario.totalDeApostas() == 0);
		cenario.cadastraAposta("Hue", 666666, "VAI ACONTECER");
		assertTrue(cenario.totalDeApostas() == 1);
		cenario.cadastraAposta("HuA", 666666, "N VAI ACONTECER");
		assertTrue(cenario.totalDeApostas() == 2);
	}

	@Test
	public void testConcretizaCenario() {
		// Testes também se aplicam ao método getEstado().
		cenario = new Cenario("Os testes tao certo");
		assertEquals(Estado.NAO_FINALIZADO, cenario.getEstado());
		cenario.cadastraAposta("Kaka", 2000, "VAI ACONTECER");
		cenario.cadastraAposta("Keki", 1000, "N VAI ACONTECER");
		assertTrue(cenario.getCaixa() == 0);
		cenario.concretizaCenario(true);
		assertTrue(cenario.getCaixa() == 1000);
		assertEquals(Estado.OCORREU, cenario.getEstado());

		cenario = new Cenario("Os testes tao ERRADO");
		cenario.cadastraAposta("Kaka", 2000, "VAI ACONTECER");
		cenario.cadastraAposta("Keki", 1000, "N VAI ACONTECER");
		cenario.concretizaCenario(false);
		assertTrue(cenario.getCaixa() == 2000);
		assertEquals(Estado.NAO_OCORREU, cenario.getEstado());
	}

	@Test
	public void testExibeApostas() {
		cenario = new Cenario("Sei la ohm");
		assertEquals("", cenario.exibeApostas());

		cenario.cadastraAposta("Fulano", 1, "VAI ACONTECER");
		assertEquals("Fulano - 1 - VAI ACONTECER" + System.lineSeparator(), cenario.exibeApostas());

		cenario.cadastraAposta("Beltrano", 3, "N VAI ACONTECER");
		assertEquals("Fulano - 1 - VAI ACONTECER" + System.lineSeparator() + "Beltrano - 3 - N VAI ACONTECER"
				+ System.lineSeparator(), cenario.exibeApostas());

	}

	@Test
	public void testTotalDeApostas() {
		cenario = new Cenario("Gauds me adota");
		assertTrue(cenario.totalDeApostas() == 0);
		cenario.cadastraAposta("Fulano", 1, "VAI ACONTECER");
		assertTrue(cenario.totalDeApostas() == 1);
		cenario.cadastraAposta("Beltrano", 3, "N VAI ACONTECER");
		assertTrue(cenario.totalDeApostas() == 2);
	}
	
	@Test
	public void testValorTotalDeApostas() {
		cenario = new Cenario("Gauds me nota");
		assertTrue(cenario.valorTotalDeApostas() == 0);
		cenario.cadastraAposta("Fulano", 1, "VAI ACONTECER");
		assertTrue(cenario.valorTotalDeApostas() == 1);
		cenario.cadastraAposta("Beltrano", 3, "N VAI ACONTECER");
		assertTrue(cenario.valorTotalDeApostas() == 4);
	}
}
