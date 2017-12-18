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
		// Testes também se aplicam ao método getEstado() e getCaixa().
		cenario = new Cenario("Os testes tao certo");

		assertEquals(Estado.NAO_FINALIZADO, cenario.getEstado());

		cenario.cadastraAposta("Kaka", 2000, "VAI ACONTECER");
		cenario.cadastraAposta("Keki", 1000, "N VAI ACONTECER");
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
	public void testGetCaixaCenarioNaoFinalizado() {
		cenario = new Cenario("Isto é util.");
		assertTrue(cenario.getCaixa() == 0);

	}

	@Test
	public void testExibeApostasSemApostas() {
		cenario = new Cenario("Sei la ohm");
		assertEquals("", cenario.exibeApostas());
	}

	@Test
	public void testExibeApostas() {
		cenario = new Cenario("Eu to cansado ja meu");
		cenario.cadastraAposta("Fulano", 1, "VAI ACONTECER");
		assertEquals("Fulano - R$0,01 - VAI ACONTECER" + System.lineSeparator(), cenario.exibeApostas());

		cenario.cadastraAposta("Beltrano", 3, "N VAI ACONTECER");
		assertEquals("Fulano - R$0,01 - VAI ACONTECER" + System.lineSeparator() + "Beltrano - R$0,03 - N VAI ACONTECER"
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

	@Test
	public void testToString() {
		cenario = new Cenario("Me nota ane");
		assertEquals("Me nota ane - Nao finalizado", cenario.toString());
		cenario.concretizaCenario(true);
		assertEquals("Me nota ane - Finalizado (ocorreu)", cenario.toString());
		cenario.concretizaCenario(false);
		assertEquals("Me nota ane - Finalizado (não ocorreu)", cenario.toString());
		
			}
}
