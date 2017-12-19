package entidades;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

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
}
