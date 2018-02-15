package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import entidades.Cenario;
import entidades.CenarioDefault;
import entidades.Estado;

/**
 * Classe de Testes JUnity da classe CenarioDefault.
 * 
 * Lab05 - Laboratório de Programação II
 * 
 * @author Héricles Emanuel - 117110647
 *
 */
public class CenarioDefaultTest {
	Cenario cenario, outroCenario;

	/**
	 * Inicializa o CenarioDefault cenario.
	 */
	@Before
	public void inicializaCenario() {
		cenario = new CenarioDefault("O teste vai dar certo", 2);
	}

	/*
	 * Testa o construtor de um cenário Default.
	 */
	@Test
	public void testConstrutor() {
		assertTrue(outroCenario == null);
		outroCenario = new CenarioDefault("A BARATA MATOU HEMI", 2);
		assertFalse(cenario == null);
	}

	/**
	 * Testa o cadastro de uma aposta no cenário, e sua adição à lista de Apostas.
	 */
	@Test
	public void testCadastraAposta() {
		assertTrue(cenario.totalDeApostas() == 0);

		cenario.cadastraAposta("Hue", 666666, "VAI ACONTECER");
		assertTrue(cenario.totalDeApostas() == 1);

		cenario.cadastraAposta("HuA", 666666, "N VAI ACONTECER");
		assertTrue(cenario.totalDeApostas() == 2);
	}

	/**
	 * Testa o cadastro de uma aposta assegurada por taxa no cenário, e sua adição à lista de Apostas.
	 */
	@Test
	public void testCadastraApostaAsseguradaTaxa() {
		assertTrue(cenario.totalDeApostas() == 0);
		assertEquals(1, cenario.cadastraAposta("Hue", 666666, "VAI ACONTECER", 0.1));
		assertEquals(2, cenario.cadastraAposta("HuA", 666666, "N VAI ACONTECER", 0.2));
	}

	/**
	 * Testa o cadastro de uma aposta assegurada por valor no cenário, e sua adição à lista de Apostas.
	 */
	@Test
	public void testCadastraApostaAsseguradaValor() {
		assertTrue(cenario.totalDeApostas() == 0);
		assertEquals(1, cenario.cadastraAposta("oi", 41561, "VAI ACONTECER", 500));
		assertEquals(2, cenario.cadastraAposta("soueu", 1562, "N VAI ACONTECER", 100));
	}
	
	/**
	 * Testa o método concretiza cenário, garantindo o incremento da caixa do
	 * cenário quando acontecer.
	 */
	@Test
	public void testConcretizaCenarioAconteceu() {
		assertEquals(Estado.NAO_FINALIZADO, cenario.getEstado());

		cenario.cadastraAposta("Kaka", 2000, "VAI ACONTECER");
		cenario.cadastraAposta("Keki", 1000, "N VAI ACONTECER");
		cenario.concretizaCenario(true);

		assertTrue(cenario.getCaixa() == 1000);
		assertEquals(Estado.OCORREU, cenario.getEstado());

	}

	/**
	 * Testa o método concretiza cenário, garantindo o incremento da caixa do
	 * cenário quando não acontecer.
	 */
	@Test
	public void testConcretizaCenarioNaoAconteceu() {
		assertEquals(Estado.NAO_FINALIZADO, cenario.getEstado());

		cenario.cadastraAposta("Kaka", 2000, "VAI ACONTECER");
		cenario.cadastraAposta("Keki", 1000, "N VAI ACONTECER");
		cenario.concretizaCenario(false);

		assertTrue(cenario.getCaixa() == 2000);
		assertEquals(Estado.NAO_OCORREU, cenario.getEstado());
	}

	/**
	 * Importante! Os Testes de concretização do cenário também se aplicam aos
	 * métodos getEstado() e getCaixa().
	 */

	/**
	 * Testa o método getCaixa quando o cenário ainda não foi finalizado.
	 */
	@Test
	public void testGetCaixaCenarioNaoFinalizado() {
		assertTrue(cenario.getCaixa() == 0);

	}

	/**
	 * Testa a exibição de apostas quando não há nenhuma.
	 */
	@Test
	public void testExibeApostasSemApostas() {
		assertEquals("", cenario.exibeApostas());
	}

	/**
	 * Testa a exibição de apostas cadastradas no cenário.
	 */
	@Test
	public void testExibeApostas() {
		cenario.cadastraAposta("Fulano", 1, "VAI ACONTECER");
		assertEquals("Fulano - R$0,01 - VAI ACONTECER", cenario.exibeApostas());

		cenario.cadastraAposta("Beltrano", 3, "N VAI ACONTECER");
		assertEquals("Fulano - R$0,01 - VAI ACONTECER" + System.lineSeparator() + "Beltrano - R$0,03 - N VAI ACONTECER",
				cenario.exibeApostas());
	}

	/**
	 * Testa o método totalDeApostas, que calcula a quantia de apostas.
	 */
	@Test
	public void testTotalDeApostas() {
		assertTrue(cenario.totalDeApostas() == 0);
		cenario.cadastraAposta("Fulano", 1, "VAI ACONTECER");
		assertTrue(cenario.totalDeApostas() == 1);
		cenario.cadastraAposta("Beltrano", 3, "N VAI ACONTECER");
		assertTrue(cenario.totalDeApostas() == 2);
	}

	/**
	 * Testa a representação em String de um Cenário Default.
	 */
	@Test
	public void testToString() {
		cenario = new CenarioDefault("Me nota pf", 2);
		assertEquals("Me nota pf - Nao finalizado", cenario.toString());
		cenario.concretizaCenario(true);
		assertEquals("Me nota pf - Finalizado (ocorreu)", cenario.toString());
		cenario.concretizaCenario(false);
		assertEquals("Me nota pf - Finalizado (não ocorreu)", cenario.toString());
	}

	/**
	 * Testa o método getSeguro de um Cenário.
	 */
	@Test
	public void testGetSeguro() {
		cenario.cadastraAposta("eu to", 500, "N VAI ACONTECER", 0.1);
		cenario.cadastraAposta("sofrendo", 1000, "VAI ACONTECER", 100);
		assertTrue(150 == cenario.getSeguro());
	}

	/**
	 * Testa o método totalRateioCenario considerando a taxa do sistema sendo 10%.
	 */
	@Test
	public void testTotalRateioCenario() {
		cenario.cadastraAposta("poucos saberao", 1000, "VAI ACONTECER");
		cenario.cadastraAposta("desta declaracao", 500, "NAO VAI ACONTECER");
		cenario.concretizaCenario(true);
		double taxa = 0.1;
		assertEquals(450, cenario.totalRateioCenario(taxa));
	}
	
	/**
	 * Testa o método AlterarSeguroValor, garantindo a troca do tipo de seguro de
	 * uma aposta no cenário.
	 */
	@Test
	public void testAlterarSeguroValor() {
		cenario.cadastraAposta("ninguem sabera", 1000, "VAI ACONTECER", 0.1);
		assertEquals(100, cenario.getSeguro());
		cenario.alterarSeguroValor(1, 200);
		assertEquals(200, cenario.getSeguro());
	}
	
	/**
	 * Testa o método AlterarSeguroValor, garantindo a troca do tipo de seguro de
	 * uma aposta no cenário.
	 */
	@Test
	public void testAlteraSeguroTaxa() {
		cenario.cadastraAposta("quem eh voce, oh crush", 1000, "VAI ACONTECER", 200);
		assertEquals(200, cenario.getSeguro());
		cenario.alterarSeguroTaxa(1, 0.1);
		assertEquals(100, cenario.getSeguro());
	}
}
