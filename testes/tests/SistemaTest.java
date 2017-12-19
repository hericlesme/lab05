package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import entidades.Sistema;

public class SistemaTest {

	Sistema sys, outroSys;

	@Before
	public void inicializaSistema() {
		sys = new Sistema();
	}

	@Test
	public void testConstrutor() {
		assertTrue(outroSys == null);
		outroSys = new Sistema();
		assertFalse(outroSys == null);
	}

	@Test
	public void testInicializaSistema() {
		// Teste também se aplica ao método getCaixa().
		assertTrue(sys.getCaixa() == 0);
		sys.inicializa(1000, 10);
		assertEquals(1000, sys.getCaixa());
	}

	@Test
	public void testCadastraCenario() {
		assertEquals("", sys.exibirCenarios());
		assertEquals(1, sys.cadastrarCenario("Serra 'its' vania"));
		assertEquals(2, sys.cadastrarCenario("ne nao?"));
	}

	@Test
	public void testExibirCenarioNaoFinalizado() {
		sys.cadastrarCenario("cc > eletrica");
		assertEquals("1 - cc > eletrica - Nao finalizado", sys.exibirCenario(1));

		sys.cadastrarCenario("MonitoriaEhVida");
		assertEquals("2 - MonitoriaEhVida - Nao finalizado", sys.exibirCenario(2));
	}

	@Test
	public void testExibirCenarioOcorrido() {
		sys.cadastrarCenario("cc > eletrica");
		sys.fecharAposta(1, true);
		assertEquals("1 - cc > eletrica - Finalizado (ocorreu)", sys.exibirCenario(1));

		sys.cadastrarCenario("MonitoriaEhVida");
		sys.fecharAposta(2, true);
		assertEquals("2 - MonitoriaEhVida - Finalizado (ocorreu)", sys.exibirCenario(2));
	}

	@Test
	public void testExibirCenarioNaoOcorrido() {
		sys.cadastrarCenario("cc > eletrica");
		sys.fecharAposta(1, false);
		assertEquals("1 - cc > eletrica - Finalizado (não ocorreu)", sys.exibirCenario(1));

		sys.cadastrarCenario("MonitoriaEhVida");
		sys.fecharAposta(2, false);
		assertEquals("2 - MonitoriaEhVida - Finalizado (não ocorreu)", sys.exibirCenario(2));
	}

	@Test
	public void testExibirCenariosSemCenarios() {
		assertEquals("", sys.exibirCenarios());
	}

	@Test
	public void testExibirCenarios() {
		sys.cadastrarCenario("Isto é um cenário");
		assertEquals("1 - Isto é um cenário - Nao finalizado" + System.lineSeparator(), sys.exibirCenarios());

		sys.cadastrarCenario("Esse aqui ocorre");
		sys.fecharAposta(2, true);
		assertEquals("1 - Isto é um cenário - Nao finalizado" + System.lineSeparator()
				+ "2 - Esse aqui ocorre - Finalizado (ocorreu)" + System.lineSeparator(), sys.exibirCenarios());

		sys.cadastrarCenario("Esse nao ocorre");
		sys.fecharAposta(3, false);
		assertEquals(
				"1 - Isto é um cenário - Nao finalizado" + System.lineSeparator()
						+ "2 - Esse aqui ocorre - Finalizado (ocorreu)" + System.lineSeparator()
						+ "3 - Esse nao ocorre - Finalizado (não ocorreu)" + System.lineSeparator(),
				sys.exibirCenarios());
	}

	@Test
	public void testCadastrarAposta() {
		sys.cadastrarCenario("Eu sou o numero 1");
		assertEquals(0, sys.totalDeApostas(1));
		sys.cadastrarAposta(1, "hemi", 10432, "VAI ACONTECER");
		assertEquals(1, sys.totalDeApostas(1));
	}

	@Test
	public void testValorTotalDeApostasZerado() {
		sys.cadastrarCenario("Exemplou");
		assertEquals(0, sys.valorTotalDeApostas(1));
	}

	@Test
	public void testValorTotalDeApostas() {
		sys.cadastrarCenario("Exemplou");
		sys.cadastrarAposta(1, "picles", 10000, "N VAI ACONTECER");
		assertEquals(10000, sys.valorTotalDeApostas(1));

		sys.cadastrarAposta(1, "Pericles", 5000, "VAI ACONTECER");
		assertEquals(15000, sys.valorTotalDeApostas(1));
	}

	@Test
	public void testTotalDeApostaZeroApostas() {
		sys.cadastrarCenario("Eu vejo!");
		assertEquals(0, sys.totalDeApostas(1));
	}

	@Test
	public void testTotalDeApostas() {
		sys.cadastrarCenario("Ele vê");
		sys.cadastrarAposta(1, "fulanin", 2000, "N VAI ACONTECER");
		assertEquals(1, sys.totalDeApostas(1));

		sys.cadastrarAposta(1, "Kaka Jogador", 5000, "N VAI ACONTECER");
		sys.cadastrarAposta(1, "Teemo", 2300, "VAI ACONTECER");
		assertEquals(3, sys.totalDeApostas(1));
	}

	@Test
	public void testExibeApostasSemAposta() {
		sys.cadastrarCenario("henrique e chato");
		assertEquals("", sys.exibeApostas(1));

	}

	@Test
	public void testExibeApostas() {
		sys.cadastrarCenario("Hemi e um amorzin");
		sys.cadastrarAposta(1, "eduardo", 1000, "VAI ACONTECER");
		assertEquals(sys.exibeApostas(1), "eduardo - R$10,00 - VAI ACONTECER" + System.lineSeparator());

		sys.cadastrarAposta(1, "henrique", 5000, "N VAI ACONTECER");
		assertEquals("eduardo - R$10,00 - VAI ACONTECER" + System.lineSeparator()
				+ "henrique - R$50,00 - N VAI ACONTECER" + System.lineSeparator(), sys.exibeApostas(1));

		sys.cadastrarAposta(1, "Wes", 7000, "N VAI ACONTECER");
		assertEquals(
				"eduardo - R$10,00 - VAI ACONTECER" + System.lineSeparator() + "henrique - R$50,00 - N VAI ACONTECER"
						+ System.lineSeparator() + "Wes - R$70,00 - N VAI ACONTECER" + System.lineSeparator(),
				sys.exibeApostas(1));

	}

	@Test
	public void testFecharApostas() {
		sys.cadastrarCenario("yoda > all");
		sys.inicializa(1000, 0.1);
		assertEquals(1000, sys.getCaixa());

		sys.cadastrarAposta(1, "kaka", 1000, "VAI ACONTECER");
		sys.cadastrarAposta(1, "HAUSHAU", 1000, "N VAI ACONTECER");
		sys.fecharAposta(1, true);

		assertEquals(1100, sys.getCaixa());
		assertEquals(900, sys.totalRateioCenario(1));
	}

	@Test
	public void testFecharApostasTaxaZero() {
		sys.cadastrarCenario("da nada");
		sys.inicializa(1000, 0);
		assertEquals(1000, sys.getCaixa());

		sys.cadastrarAposta(1, "kaka", 1000, "VAI ACONTECER");
		sys.cadastrarAposta(1, "HAUSHAU", 1000, "N VAI ACONTECER");
		sys.fecharAposta(1, true);

		assertEquals(1000, sys.getCaixa());
		assertEquals(1000, sys.totalRateioCenario(1));

	}

	@Test
	public void testCaixaCenario() {
		sys.inicializa(0, 0.1);
		sys.cadastrarCenario("Eduardo vai pagar calculo 2");
		sys.cadastrarAposta(1, "oi", 2000, "VAI ACONTECER");
		sys.cadastrarAposta(1, "turubom", 2000, "N VAI ACONTECER");
		sys.fecharAposta(1, true);
		assertEquals(200, sys.caixaCenario(1));

	}

	@Test
	public void testTotalRateioCenario() {
		sys.inicializa(0, 0.2);
		sys.cadastrarCenario("Eduardo vai pagar calculo 2");
		sys.cadastrarAposta(1, "oi", 10000, "VAI ACONTECER");
		sys.cadastrarAposta(1, "turubom", 2000, "N VAI ACONTECER");
		sys.fecharAposta(1, false);

		assertEquals(8000, sys.totalRateioCenario(1));

		sys.inicializa(0, 0.5);
		sys.cadastrarCenario("Eduardo vai pagar calculo 2");
		sys.cadastrarAposta(2, "sr vlw", 15000, "VAI ACONTECER");
		sys.cadastrarAposta(2, "sr flw", 300, "N VAI ACONTECER");
		sys.fecharAposta(2, false);

		assertEquals(7500, sys.totalRateioCenario(2));

	}

	@Test(expected = IllegalArgumentException.class)
	public void testInicializaCaixaInvalido() {
		sys.inicializa(-1, 0.1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInicializaTaxaInvalida() {
		sys.inicializa(0, -1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCadastraCenarioDescInvalida() {
		sys.cadastrarCenario("");
		sys.cadastrarCenario("     ");
	}

	@Test(expected = NullPointerException.class)
	public void testCadastraCenarioDescNula() {
		sys.cadastrarCenario(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testExibirCenarioInvalido() {
		sys.exibirCenario(-1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCadastraCenarioNaoCadastrado() {
		sys.exibirCenario(1);
	}

	@Test(expected = NullPointerException.class)
	public void testCadastraApostaApostadorNulo() {
		sys.cadastrarCenario("Teste");
		sys.cadastrarAposta(1, null, 156513641, "N VAI ACONTECER");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCadastraApostaApostadorInvalido() {
		sys.cadastrarCenario("Teste");
		sys.cadastrarAposta(1, "", 10000, "N VAI ACONTECER");
		sys.cadastrarAposta(1, "       ", 10000, "N VAI ACONTECER");

	}

	@Test(expected = NullPointerException.class)
	public void testCadastraApostaPrevisaoNula() {
		sys.cadastrarCenario("Teste");
		sys.cadastrarAposta(1, "Hemi", 15641, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCadastraApostaPrevisaoVazia() {
		sys.cadastrarCenario("Teste");
		sys.cadastrarAposta(1, "Anjo", 123456, "   ");
		sys.cadastrarAposta(1, "Querubim", 123456, "");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCadastraApostaPrevisaoInvalida() {
		sys.cadastrarCenario("Teste");
		sys.cadastrarAposta(1, "Arcanjo", 123456, "HAUSHAUHSN");
		sys.cadastrarAposta(1, "Arcanjo", 123456, "N VAI ACONTECR");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCadastraApostaValorInvalido() {
		sys.cadastrarCenario("Teste");
		sys.cadastrarAposta(1, "Gauds", -1, "VAI ACONTECER");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testTotalDeApostasCenarioInvalido() {
		sys.totalDeApostas(-1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testTotalDeApostasCenarioInexistente() {
		sys.totalDeApostas(1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testExibeApostasCenarioInvalido() {
		sys.exibeApostas(-1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testExibeApostasCenarioInexistente() {
		sys.exibeApostas(1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFecharApostaCenarioInvalido() {
		sys.valorTotalDeApostas(-1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFecharApostaCenarioInexistente() {
		sys.valorTotalDeApostas(1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFecharApostaJaFechada() {
		sys.cadastrarCenario("ola");
		sys.fecharAposta(1, true);
		sys.fecharAposta(1, false);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCaixaCenarioCenarioInvalido() {
		sys.caixaCenario(-1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCaixaCenarioCenarioInexistente() {
		sys.caixaCenario(1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testTotalRateioCenarioInvalido() {
		sys.totalRateioCenario(-1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testTotalRateioCenarioInexistente() {
		sys.totalRateioCenario(1);
	}

}
