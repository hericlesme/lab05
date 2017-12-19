package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import entidades.Cenario;
import entidades.Estado;

/**
 * Teste da classe Cenario.
 * 
 * Lab05 - Laboratório de Programação II
 * 
 * @author Héricles Emanuel - 117110647
 *
 */
public class CenarioTest {
	Cenario cenario, outroCenario;

	/**
	 * Inicializa o Cenario cenario.
	 */
	@Before
	public void inicializaCenario() {
		cenario = new Cenario("O teste vai dar certo");
	}

	/*
	 * Testa o construtor de um cenário.
	 */
	@Test
	public void testConstrutor() {
		assertTrue(outroCenario == null);
		outroCenario = new Cenario("A BARATA MATOU HEMI");
		assertFalse(cenario == null);
	}

	/**
	 * Testa o cadastro de uma aposta no sistema, e sua adição à lista de Apostas.
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
	 * Testa o método concretiza cenário, garantindo o incremento da caixa do
	 * cenário e sua mudança de estado.
	 */
	@Test
	public void testConcretizaCenario() {
		// Testes também se aplicam ao método getEstado() e getCaixa().
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
		assertEquals("Fulano - R$0,01 - VAI ACONTECER" + System.lineSeparator(), cenario.exibeApostas());

		cenario.cadastraAposta("Beltrano", 3, "N VAI ACONTECER");
		assertEquals("Fulano - R$0,01 - VAI ACONTECER" + System.lineSeparator() + "Beltrano - R$0,03 - N VAI ACONTECER"
				+ System.lineSeparator(), cenario.exibeApostas());

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
	 * Testa o método valorTotalDeApostas, à medida que se adicionam apostas.
	 */
	@Test
	public void testValorTotalDeApostas() {
		assertTrue(cenario.valorTotalDeApostas() == 0);
		cenario.cadastraAposta("Fulano", 1, "VAI ACONTECER");
		assertTrue(cenario.valorTotalDeApostas() == 1);
		cenario.cadastraAposta("Beltrano", 3, "N VAI ACONTECER");
		assertTrue(cenario.valorTotalDeApostas() == 4);
	}

	/**
	 * Testa a representação em String de um Cenário.
	 */
	@Test
	public void testToString() {
		cenario = new Cenario("Me nota ane");
		assertEquals("Me nota ane - Nao finalizado", cenario.toString());
		cenario.concretizaCenario(true);
		assertEquals("Me nota ane - Finalizado (ocorreu)", cenario.toString());
		cenario.concretizaCenario(false);
		assertEquals("Me nota ane - Finalizado (não ocorreu)", cenario.toString());

	}

	/**
	 * Testa o construtor de um Cenario com a descrição vazia.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testDescricaoVazia() {
		cenario = new Cenario("");
		cenario = new Cenario("       ");
	}

	/**
	 * Testa o construtor de um Cenario com a descrição nula.
	 */
	@Test(expected = NullPointerException.class)
	public void testDescricaoNula() {
		cenario = new Cenario(null);
	}

	/**
	 * Testa o cadastro de uma aposta com o apostador nulo.
	 */
	@Test(expected = NullPointerException.class)
	public void testCadastraApostaApostadorNulo() {
		cenario.cadastraAposta(null, 156513641, "N VAI ACONTECER");
	}

	/*
	 * Testa o cadastro de uma aposta quando o apostador é vazio.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCadastraApostaApostadorInvalido() {
		cenario.cadastraAposta("", 10000, "N VAI ACONTECER");
		cenario.cadastraAposta("       ", 10000, "N VAI ACONTECER");

	}

	/*
	 * Testa o cadastro de uma aposta quando a previsão é nula.
	 */
	@Test(expected = NullPointerException.class)
	public void testCadastraApostaPrevisaoNula() {
		cenario.cadastraAposta("Hemi", 15641, null);
	}

	/*
	 * Testa o cadastro de uma aposta quando a previsão é vazia.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCadastraApostaPrevisaoVazia() {
		cenario.cadastraAposta("Anjo", 123456, "   ");
		cenario.cadastraAposta("Querubim", 123456, "");
	}

	/*
	 * Testa o cadastro de uma aposta quando a previsão é inválida.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCadastraApostaPrevisaoInvalida() {
		cenario.cadastraAposta("Arcanjo", 123456, "HAUSHAUHSN");
		cenario.cadastraAposta("Arcanjo", 123456, "N VAI ACONTECR");
	}

	/*
	 * Testa o cadastro de uma aposta quando o valor é inválido.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCadastraApostaValorInvalido() {
		cenario.cadastraAposta("Gauds", -1, "VAI ACONTECER");
	}

}
