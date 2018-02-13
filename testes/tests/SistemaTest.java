package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import controllers.Sistema;

/**
 * Classe de Testes JUnity da classe Sistema.
 * 
 * Lab05 - Laboratório de Programação II
 * 
 * @author Héricles Emanuel - 117110647
 *
 */
public class SistemaTest {

	Sistema sys, outroSys;

	/**
	 * Inicializa o Sistema sys.
	 */
	@Before
	public void inicializaSistema() {
		sys = new Sistema();
	}

	/**
	 * Testa o construtor de um sistema.
	 */
	@Test
	public void testConstrutor() {
		assertTrue(outroSys == null);
		outroSys = new Sistema();
		assertFalse(outroSys == null);
	}

	/**
	 * Testa o inicializador do sistema, com taxa e caixa.
	 */
	@Test
	public void testInicializa() {
		// Teste também se aplica ao método getCaixa().
		assertTrue(sys.getCaixa() == 0);
		sys.inicializa(1000, 10);
		assertEquals(1000, sys.getCaixa());
	}

	/**
	 * Testa o método getCaixa do sistema.
	 */
	@Test
	public void testGetCaixa() {
		sys.inicializa(1000, 0.01);
		assertTrue(sys.getCaixa() == 1000);
	}

	/**
	 * Testa o cadastro de um cenário default.
	 */
	@Test
	public void testCadastrarCenario() {
		assertEquals("", sys.exibirCenarios());
		assertEquals(1, sys.cadastrarCenario("Serra 'its' vania"));
		assertEquals(2, sys.cadastrarCenario("ne nao?"));
	}

	@Test
	public void testCadastrarCenarioBonus() {
		assertEquals("", sys.exibirCenarios());
		assertEquals(1, sys.cadastrarCenario("eu nao me canso de pensar em voce", 100));
		assertEquals(2, sys.cadastrarCenario("but u is too far", 99156));
	}

	/**
	 * Testa a exibição de um cenário default não finalizado.
	 */
	@Test
	public void testExibirCenarioDefaultNaoFinalizado() {
		sys.cadastrarCenario("cc > eletrica");
		assertEquals("1 - cc > eletrica - Nao finalizado", sys.exibirCenario(1));

		sys.cadastrarCenario("MonitoriaEhVida");
		assertEquals("2 - MonitoriaEhVida - Nao finalizado", sys.exibirCenario(2));
	}

	/**
	 * Testa a exibição de um cenário bonus não finalizado.
	 */
	@Test
	public void testExibirCenarioBonusNaoFinalizado() {
		sys.cadastrarCenario("voltando, voce eh um misterio", 100);
		assertEquals("1 - voltando, voce eh um misterio - Nao finalizado - R$ 1,00", sys.exibirCenario(1));

		sys.cadastrarCenario("provavelmente nunca me entendera", 150);
		assertEquals("2 - provavelmente nunca me entendera - Nao finalizado - R$ 1,50", sys.exibirCenario(2));
	}

	/**
	 * Testa a exibição de um cenário ocorrido.
	 */
	@Test
	public void testExibirCenarioDefaultOcorrido() {
		sys.cadastrarCenario("mas que fique aqui");
		sys.fecharAposta(1, true);
		assertEquals("1 - mas que fique aqui - Finalizado (ocorreu)", sys.exibirCenario(1));

		sys.cadastrarCenario("a marca da minha paixao");
		sys.fecharAposta(2, true);
		assertEquals("2 - a marca da minha paixao - Finalizado (ocorreu)", sys.exibirCenario(2));
	}

	/**
	 * Testa a exibição de um cenário bonus ocorrido.
	 */
	@Test
	public void testExibirCenarioBonusOcorrido() {
		sys.cadastrarCenario("e um abraco a todos", 200);
		sys.fecharAposta(1, true);
		assertEquals("1 - e um abraco a todos - Finalizado (ocorreu) - R$ 2,00", sys.exibirCenario(1));

		sys.cadastrarCenario("que me acompanharam nesta luta", 300);
		sys.fecharAposta(2, true);
		assertEquals("2 - que me acompanharam nesta luta - Finalizado (ocorreu) - R$ 3,00", sys.exibirCenario(2));
	}

	/**
	 * Testa a exibição de um cenário default não ocorrido.
	 */
	@Test
	public void testExibirCenarioDefaultNaoOcorrido() {
		sys.cadastrarCenario("e quero pedir");
		sys.fecharAposta(1, false);
		assertEquals("1 - e quero pedir - Finalizado (não ocorreu)", sys.exibirCenario(1));

		sys.cadastrarCenario("que me ajudem dnv");
		sys.fecharAposta(2, false);
		assertEquals("2 - que me ajudem dnv - Finalizado (não ocorreu)", sys.exibirCenario(2));
	}

	/**
	 * Testa a exibição de um cenário bonus não ocorrido.
	 */
	@Test
	public void testExibirCenarioBonusNaoOcorrido() {
		sys.cadastrarCenario("mas agora a conquistar", 170);
		sys.fecharAposta(1, false);
		assertEquals("1 - mas agora a conquistar - Finalizado (não ocorreu) - R$ 1,70", sys.exibirCenario(1));

		sys.cadastrarCenario("a rainha do meu coracao", 140);
		sys.fecharAposta(2, false);
		assertEquals("2 - a rainha do meu coracao - Finalizado (não ocorreu) - R$ 1,40", sys.exibirCenario(2));
	}

	/**
	 * Testa o método de exibição de cenarios, à medida que se adicionam cenários..
	 */
	@Test
	public void testExibirCenarios() {
		sys.cadastrarCenario("Isto é um cenário");
		assertEquals("1 - Isto é um cenário - Nao finalizado" + System.lineSeparator(), sys.exibirCenarios());

		sys.cadastrarCenario("Esse aqui ocorre");
		sys.fecharAposta(2, true);
		assertEquals("1 - Isto é um cenário - Nao finalizado" + System.lineSeparator()
				+ "2 - Esse aqui ocorre - Finalizado (ocorreu)" + System.lineSeparator(), sys.exibirCenarios());

		sys.cadastrarCenario("Esse n ocorre, e tem bonus", 120);
		sys.fecharAposta(3, false);
		assertEquals("1 - Isto é um cenário - Nao finalizado" + System.lineSeparator()
				+ "2 - Esse aqui ocorre - Finalizado (ocorreu)" + System.lineSeparator()
				+ "3 - Esse n ocorre, e tem bonus - Finalizado (não ocorreu) - R$ 1,20" + System.lineSeparator(),
				sys.exibirCenarios());
	}

	/**
	 * Testa o cadastro de uma aposta sem seguro num cenário.
	 */
	@Test
	public void testCadastrarAposta() {
		sys.cadastrarCenario("nao, nao te esqueci");
		assertEquals(0, sys.totalDeApostas(1));
		sys.cadastrarAposta(1, "vc vaga em meus sonhos todos os dias", 10432, "VAI ACONTECER");
		assertEquals(1, sys.totalDeApostas(1));
	}

	/**
	 * Testa o cadastro de uma aposta assegurada por taxa num cenário.
	 */
	@Test
	public void testCadastrarApostaSeguraTaxa() {
		sys.cadastrarCenario("hihihi");
		assertEquals(0, sys.totalDeApostas(1));
		sys.cadastrarApostaSeguraTaxa(1, "s2s2", 10432, "VAI ACONTECER", 0.15, 100);
		assertEquals(1, sys.totalDeApostas(1));
	}

	/**
	 * Testa o cadastro de uma aposta assegurada por valor num cenário.
	 */
	@Test
	public void testCadastrarApostaSeguraValor() {
		sys.cadastrarCenario("vem ser feliz");
		assertEquals(0, sys.totalDeApostas(1));
		sys.cadastrarApostaSeguraValor(1, "vamo tomar um cafe", 10432, "VAI ACONTECER", 512, 14);
		assertEquals(1, sys.totalDeApostas(1));
	}

	/**
	 * Testa o valorTotalDeApostas quando é zero.
	 */
	@Test
	public void testValorTotalDeApostasZerado() {
		sys.cadastrarCenario("i'm in love");
		assertEquals(0, sys.valorTotalDeApostas(1));
	}

	/**
	 * Testa o valor total das apostas.
	 */
	@Test
	public void testValorTotalDeApostas() {
		sys.cadastrarCenario("Exemplou");
		sys.cadastrarAposta(1, "picles", 10000, "N VAI ACONTECER");
		assertEquals(10000, sys.valorTotalDeApostas(1));

		sys.cadastrarApostaSeguraTaxa(1, "Pericles", 5000, "VAI ACONTECER", 0.1, 50);
		assertEquals(15000, sys.valorTotalDeApostas(1));

		sys.cadastrarApostaSeguraValor(1, "mais Picleeeees", 5000, "VAI ACONTECER", 510, 50);
		assertEquals(20000, sys.valorTotalDeApostas(1));

	}

	/**
	 * Testa o Total de apostas quando é zero.
	 */
	@Test
	public void testTotalDeApostaZeroApostas() {
		sys.cadastrarCenario("Eu vejo!");
		assertEquals(0, sys.totalDeApostas(1));
	}

	/**
	 * Testa o Total de apostas enquanto se adicionam.
	 */
	@Test
	public void testTotalDeApostas() {
		sys.cadastrarCenario("Ele vê");
		sys.cadastrarAposta(1, "fulanin", 2000, "N VAI ACONTECER");
		assertEquals(1, sys.totalDeApostas(1));

		sys.cadastrarApostaSeguraTaxa(1, "Kaka Jogador", 5000, "N VAI ACONTECER", 0.5, 40);
		sys.cadastrarAposta(1, "Teemo", 2300, "VAI ACONTECER");
		assertEquals(3, sys.totalDeApostas(1));
	}

	/**
	 * Testa a exibição de apostas quando não há nenhuma.
	 */
	@Test
	public void testExibeApostasSemAposta() {
		sys.cadastrarCenario("henrique e chato");
		assertEquals("", sys.exibeApostas(1));

	}

	@Test
	public void testExibeApostas() {
		sys.cadastrarCenario("Hemi e um amorzin");
		sys.cadastrarAposta(1, "eduardo", 1000, "VAI ACONTECER");
		sys.cadastrarAposta(1, "henrique", 5000, "N VAI ACONTECER");
		sys.cadastrarAposta(1, "Wes", 7000, "N VAI ACONTECER");

		assertEquals("eduardo - R$10,00 - VAI ACONTECER" + System.lineSeparator()
				+ "henrique - R$50,00 - N VAI ACONTECER" + System.lineSeparator() + "Wes - R$70,00 - N VAI ACONTECER",
				sys.exibeApostas(1));
	}

	/**
	 * Testa a concretização de um cenário, verificando o incremento no caixa do
	 * sistema e o rateio total.
	 */
	@Test
	public void testFecharApostas() {
		sys.cadastrarCenario("yoda > all");
		sys.inicializa(1000, 0.1);
		sys.cadastrarApostaSeguraValor(1, "kaka", 1000, "VAI ACONTECER", 50, 50);
		sys.cadastrarApostaSeguraTaxa(1, "HAUSHAU", 1000, "N VAI ACONTECER", 0.1, 150);
		assertEquals(1200, sys.getCaixa());
		sys.fecharAposta(1, true);

		assertEquals(1150, sys.getCaixa());
		assertEquals(900, sys.totalRateioCenario(1));
	}

	/**
	 * Testa o método caixaCenario.
	 */
	@Test
	public void testCaixaCenario() {
		sys.inicializa(1000, 0.1);
		sys.cadastrarCenario("Eduardo vai pagar calculo 2");
		sys.cadastrarAposta(1, "oi", 2000, "VAI ACONTECER");
		sys.cadastrarAposta(1, "turubom", 2000, "N VAI ACONTECER");
		sys.fecharAposta(1, true);
		assertEquals(200, sys.caixaCenario(1));
	}

	/**
	 * Testa o método que retorna a quantidade a ser dividida entre os apostadores
	 * quando o cenário é Default.
	 */
	@Test
	public void testTotalRateioCenarioDefault() {
		sys.inicializa(134, 0.2);
		sys.cadastrarCenario("Eduardo vai pagar calculo 2");
		sys.cadastrarAposta(1, "oi", 10000, "VAI ACONTECER");
		sys.cadastrarAposta(1, "turubom", 2000, "N VAI ACONTECER");
		sys.fecharAposta(1, false);

		assertEquals(8000, sys.totalRateioCenario(1));
	}

	/**
	 * Testa o método que retorna a quantidade a ser dividida entre os apostadores
	 * quando o cenário é bonus.
	 */
	@Test
	public void testTotalRateioCenarioBonus() {
		sys.inicializa(134, 0.2);
		sys.cadastrarCenario("eu ainda te amo bb", 500);
		sys.cadastrarAposta(1, "vc apenas", 10000, "VAI ACONTECER");
		sys.cadastrarAposta(1, "nao me nota", 2000, "N VAI ACONTECER");
		sys.fecharAposta(1, false);

		assertEquals(8500, sys.totalRateioCenario(1));
	}

	@Test
	public void testAlterarSeguroValor() {
		sys.cadastrarCenario("HELLO FROM THE OUTSIIIIIIIIIDEEEEEEEEEE");
		int id = sys.cadastrarApostaSeguraTaxa(1, "eu", 1500, "VAI ACONTECER", 0.2, 50);
		sys.alterarSeguroValor(1, id, 100);
		assertEquals("eu - R$15,00 - VAI ACONTECER - ASSEGURADA (VALOR) - R$ 1,00", sys.exibeApostas(1));
	}

	@Test
	public void testAlterarSeguroTaxa() {
		sys.cadastrarCenario("eu comi borboletas e peidei arco-íris");
		int id = sys.cadastrarApostaSeguraValor(1, "tu", 1500, "VAI ACONTECER", 600, 50);
		sys.alterarSeguroTaxa(1, id, 0.1);
		assertEquals("tu - R$15,00 - VAI ACONTECER - ASSEGURADA (TAXA) - 10%", sys.exibeApostas(1));
	}

	/**
	 * Testa a inicialização quando o caixa é inválido.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testInicializaCaixaInvalido() {
		sys.inicializa(-1, 0.1);
	}

	/**
	 * Testa a inicialização quando a taxa é inválida.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testInicializaTaxaInvalida() {
		sys.inicializa(0, -1);
	}

	/**
	 * Testa o cadastro de um Cenario Default com a descrição vazia.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCadastraCenarioDefaultDescInvalida() {
		sys.cadastrarCenario("");
		sys.cadastrarCenario("     ");
	}

	/**
	 * Testa o cadastro de um Cenario Default com a descrição nula.
	 */
	@Test(expected = NullPointerException.class)
	public void testCadastraCenarioDefaultDescNula() {
		sys.cadastrarCenario(null);
	}

	/**
	 * Testa o cadastro de um Cenario Bonus com bonus invalido.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCadastraCenarioBonusInvalido() {
		sys.cadastrarCenario("ahshaus", 0);
		sys.cadastrarCenario("usahuwh", -1);
	}

	/**
	 * Testa o cadastro de um Cenario Bonus com a descrição vazia.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCadastraCenarioBonusDescInvalida() {
		sys.cadastrarCenario("", 12345);
		sys.cadastrarCenario("     ", 6789);
	}

	/**
	 * Testa o cadastro de um Cenario Bonus com a descrição nula.
	 */
	@Test(expected = NullPointerException.class)
	public void testCadastraCenarioBonusDescNula() {
		sys.cadastrarCenario(null, 124);
	}

	/**
	 * Testa a exibição de um cenário inválido.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testExibirCenarioInvalido() {
		sys.exibirCenario(-1);
	}

	/**
	 * Testa a exibição de um Cenário não cadastrado.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testExibeCenarioNaoCadastrado() {
		sys.exibirCenario(1);
	}

	// Cadastro de Apostas Sem Seguro

	/**
	 * Testa o cadastro de uma aposta com o apostador nulo.
	 */
	@Test(expected = NullPointerException.class)
	public void testCadastraApostaApostadorNulo() {
		sys.cadastrarCenario("Teste");
		sys.cadastrarAposta(1, null, 156513641, "N VAI ACONTECER");
	}

	/*
	 * Testa o cadastro de uma aposta quando o apostador é vazio.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCadastraApostaApostadorInvalido() {
		sys.cadastrarCenario("Teste");
		sys.cadastrarAposta(1, "", 10000, "N VAI ACONTECER");
		sys.cadastrarAposta(1, "       ", 10000, "N VAI ACONTECER");

	}

	/*
	 * Testa o cadastro de uma aposta quando a previsão é nula.
	 */
	@Test(expected = NullPointerException.class)
	public void testCadastraApostaPrevisaoNula() {
		sys.cadastrarCenario("Teste");
		sys.cadastrarAposta(1, "Hemi", 15641, null);
	}

	/*
	 * Testa o cadastro de uma aposta quando a previsão é vazia.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCadastraApostaPrevisaoVazia() {
		sys.cadastrarCenario("Teste");
		sys.cadastrarAposta(1, "Anjo", 123456, "   ");
		sys.cadastrarAposta(1, "Querubim", 123456, "");
	}

	/*
	 * Testa o cadastro de uma aposta quando a previsão é inválida.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCadastraApostaPrevisaoInvalida() {
		sys.cadastrarCenario("Teste");
		sys.cadastrarAposta(1, "Arcanjo", 123456, "HAUSHAUHSN");
		sys.cadastrarAposta(1, "Arcanjo", 123456, "N VAI ACONTECR");
	}

	/*
	 * Testa o cadastro de uma aposta quando o valor é inválido.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCadastraApostaValorInvalido() {
		sys.cadastrarCenario("Teste");
		sys.cadastrarAposta(1, "Gauds", -1, "VAI ACONTECER");
	}

	/**
	 * Testa o cadastro de uma Aposta sem seguro quando o cenário é inválido.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCadastrarApostaCenarioInvalido() {
		sys.cadastrarAposta(-1, "sausu", 1515, "VAI ACONTECER");
	}

	/**
	 * Testa o cadastro de uma Aposta sem seguro quando o cenário é inexistente.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCadastrarApostaCenarioInexistente() {
		sys.cadastrarAposta(1, "saci", 4545, "VAI ACONTECER");
	}

	// Cadastro de Apostas Asseguradas por Valor

	/**
	 * Testa o cadastro de uma aposta assegurada por valor com o apostador nulo.
	 */
	@Test(expected = NullPointerException.class)
	public void testCadastraApostaSeguraValorApostadorNulo() {
		sys.cadastrarCenario("Teste");
		sys.cadastrarApostaSeguraValor(1, null, 156513641, "N VAI ACONTECER", 150, 20);
	}

	/*
	 * Testa o cadastro de uma aposta assegurada por valor quando o apostador é
	 * vazio.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCadastraApostaSeguraValorApostadorInvalido() {
		sys.cadastrarCenario("Teste");
		sys.cadastrarApostaSeguraValor(1, "", 10000, "N VAI ACONTECER", 150, 20);
		sys.cadastrarApostaSeguraValor(1, "       ", 10000, "N VAI ACONTECER", 150, 20);

	}

	/*
	 * Testa o cadastro de uma aposta assegurada por valor quando a previsão é nula.
	 */
	@Test(expected = NullPointerException.class)
	public void testCadastraApostaSeguraValorPrevisaoNula() {
		sys.cadastrarCenario("Teste");
		sys.cadastrarApostaSeguraValor(1, "Hemi", 15641, null, 150, 20);
	}

	/*
	 * Testa o cadastro de uma aposta assegurada por valor quando a previsão é
	 * vazia.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCadastraApostaSeguraValorPrevisaoVazia() {
		sys.cadastrarCenario("Teste");
		sys.cadastrarApostaSeguraValor(1, "Anjo", 123456, "   ", 150, 20);
		sys.cadastrarApostaSeguraValor(1, "Querubim", 123456, "", 150, 20);
	}

	/*
	 * Testa o cadastro de uma aposta assegurada por valor quando a previsão é
	 * inválida.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCadastraApostaSeguraValorPrevisaoInvalida() {
		sys.cadastrarCenario("Teste");
		sys.cadastrarApostaSeguraValor(1, "Arcanjo", 123456, "HAUSHAUHSN", 150, 20);
		sys.cadastrarApostaSeguraValor(1, "Arcanjo", 123456, "N VAI ACONTECR", 150, 20);
	}

	/*
	 * Testa o cadastro de uma aposta assegurada por valor quando o valor é
	 * inválido.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCadastraApostaAsseguradaValorInvalido() {
		sys.cadastrarCenario("Teste");
		sys.cadastrarApostaSeguraValor(1, "Gauds", -1, "VAI ACONTECER", 150, 20);
	}

	/*
	 * Testa o cadastro de uma aposta assegurada por valor quando o valor do seguro
	 * é inválido.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCadastraApostaSeguraValorAsseguradoInvalido() {
		sys.cadastrarCenario("Teste");
		sys.cadastrarApostaSeguraValor(1, "Gauds", 1000, "VAI ACONTECER", -1, 50);
	}

	/**
	 * Testa o cadastro de uma Aposta assegurada por valor quando o cenário é
	 * inválido.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCadastrarApostaSeguraValorCenarioInvalido() {
		sys.cadastrarApostaSeguraValor(-1, "ain scrr", 1000, "VAI ACONTECER", 500, 50);
	}

	/**
	 * Testa o cadastro de uma Aposta assegurada por valor quando o cenário é
	 * inexistente.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCadastrarApostaSeguraValorCenarioInexistente() {
		sys.cadastrarApostaSeguraValor(1, "ain scrr dnv", 1000, "VAI ACONTECER", 500, 50);
	}

	/*
	 * Testa o cadastro de uma aposta assegurada por valor quando o custo do seguro
	 * é inválido.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCadastraApostaSeguraValorCustoInvalido() {
		sys.cadastrarCenario("Teste");
		sys.cadastrarApostaSeguraValor(1, "Gauds", 1000, "VAI ACONTECER", 500, 0);
		sys.cadastrarApostaSeguraValor(1, "Nhaaa", 531, "N VAI ACONTECER", 500, -10);
	}

	// Cadastro de Apostas Asseguradas por Taxa

	/**
	 * Testa o cadastro de uma aposta assegurada por taxa com o apostador nulo.
	 */
	@Test(expected = NullPointerException.class)
	public void testCadastraApostaSeguraTaxaApostadorNulo() {
		sys.cadastrarCenario("Teste");
		sys.cadastrarApostaSeguraTaxa(1, null, 156513641, "N VAI ACONTECER", 0.12, 20);
	}

	/*
	 * Testa o cadastro de uma aposta assegurada por Taxa quando o apostador é
	 * vazio.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCadastraApostaSeguraTaxaApostadorInvalido() {
		sys.cadastrarCenario("Teste");
		sys.cadastrarApostaSeguraTaxa(1, "", 10000, "N VAI ACONTECER", 0.12, 20);
		sys.cadastrarApostaSeguraTaxa(1, "       ", 10000, "N VAI ACONTECER", 0.12, 20);

	}

	/*
	 * Testa o cadastro de uma aposta assegurada por Taxa quando a previsão é nula.
	 */
	@Test(expected = NullPointerException.class)
	public void testCadastraApostaSeguraTaxaPrevisaoNula() {
		sys.cadastrarCenario("Teste");
		sys.cadastrarApostaSeguraTaxa(1, "Hemi", 15641, null, 0.12, 20);
	}

	/*
	 * Testa o cadastro de uma aposta assegurada por Taxa quando a previsão é vazia.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCadastraApostaSeguraTaxaPrevisaoVazia() {
		sys.cadastrarCenario("Teste");
		sys.cadastrarApostaSeguraTaxa(1, "Anjo", 123456, "   ", 0.12, 20);
		sys.cadastrarApostaSeguraTaxa(1, "Querubim", 123456, "", 0.12, 20);
	}

	/*
	 * Testa o cadastro de uma aposta assegurada por Taxa quando a previsão é
	 * inválida.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCadastraApostaSeguraTaxaPrevisaoInvalida() {
		sys.cadastrarCenario("Teste");
		sys.cadastrarApostaSeguraTaxa(1, "Arcanjo", 123456, "HAUSHAUHSN", 0.12, 20);
		sys.cadastrarApostaSeguraTaxa(1, "Arcanjo", 123456, "N VAI ACONTECR", 0.12, 20);
	}

	/**
	 * Testa o cadastro de uma Aposta assegurada por Taxa quando o cenário é
	 * inválido.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCadastrarApostaSeguraTaxaCenarioInvalido() {
		sys.cadastrarApostaSeguraTaxa(-1, "ain scrr", 1000, "VAI ACONTECER", 0.356, 50);
	}

	/**
	 * Testa o cadastro de uma Aposta assegurada por Taxa quando o cenário é
	 * inexistente.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCadastrarApostaSeguraTaxaCenarioInexistente() {
		sys.cadastrarApostaSeguraTaxa(1, "ain scrr dnv", 1000, "VAI ACONTECER", 0.45, 50);
	}

	/**
	 * Testa o cadastro de uma aposta Assegurada quando a taxa do seguro é inválida.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCadastraApostaSeguraTaxaInvalida() {
		sys.cadastrarCenario("Teste");
		sys.cadastrarApostaSeguraTaxa(1, "Goku", 1000, "VAI ACONTECER", -0.54, 50);
	}

	/*
	 * Testa o cadastro de uma aposta Assegurada por taxa quando o custo do seguro é
	 * inválido.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCadastraApostaSeguraTaxaCustoInvalido() {
		sys.cadastrarCenario("Teste");
		sys.cadastrarApostaSeguraTaxa(1, "Anne", 1000, "VAI ACONTECER", 500, 0);
		sys.cadastrarApostaSeguraTaxa(1, "Hemi", 531, "N VAI ACONTECER", 0.15, -10);
	}

	/**
	 * Testa o totalDeApostas quando o cenário é inválido.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testTotalDeApostasCenarioInvalido() {
		sys.totalDeApostas(-1);
	}

	/**
	 * Testa o totalDeApostas quando o cenário é inexistente.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testTotalDeApostasCenarioInexistente() {
		sys.totalDeApostas(1);
	}

	/**
	 * Testa o ExibeApostas quando o cenário é inválido.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testExibeApostasCenarioInvalido() {
		sys.exibeApostas(-1);
	}

	/**
	 * Testa o ExibeApostas quando o cenário é inexistente.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testExibeApostasCenarioInexistente() {
		sys.exibeApostas(1);
	}

	/**
	 * Testa o FecharApostas quando o cenário é inválido.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testFecharApostaCenarioInvalido() {
		sys.valorTotalDeApostas(-1);
	}

	/**
	 * Testa o fecharAposta quando o cenário é inexistente.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testFecharApostaCenarioInexistente() {
		sys.valorTotalDeApostas(1);
	}

	/**
	 * Testa o fecharAposta num cenário já fechado.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testFecharApostaJaFechada() {
		sys.cadastrarCenario("ola");
		sys.fecharAposta(1, true);
		sys.fecharAposta(1, false);
	}

	/**
	 * Testa o caixaCenario quando o cenário é inválido.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCaixaCenarioCenarioInvalido() {
		sys.caixaCenario(-1);
	}

	/**
	 * Testa o caixaCenario quando o cenário é inexistente.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCaixaCenarioCenarioInexistente() {
		sys.caixaCenario(1);
	}

	/**
	 * Testa o totalRateioCenario quando o cenário é inválido.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testTotalRateioCenarioInvalido() {
		sys.totalRateioCenario(-1);
	}

	/**
	 * Testa o totalRateioCenario quando o cenário é inexistente.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testTotalRateioCenarioInexistente() {
		sys.totalRateioCenario(1);
	}

	/**
	 * Testa o alterarSeguroValor quando o cenário é invalido.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testAlterarSeguroValorCenarioInvalido() {
		sys.alterarSeguroValor(-1, 15, 100);
	}

	/**
	 * Testa o alterarSeguroValor quando o cenário é inexistente.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testAlterarSeguroValorCenarioInexistente() {
		sys.alterarSeguroValor(1, 15, 100);
	}

	/**
	 * Testa o alterarSeguroValor quando o id da aposta é inválido.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testAlterarSeguroValorIdInvalido() {
		sys.cadastrarCenario("hello");
		sys.alterarSeguroValor(1, -3, 100);
	}

	/**
	 * Testa o alterarSeguroValor quando o id da aposta é inexistente.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testAlterarSeguroValorIdInexistente() {
		sys.cadastrarCenario("fon");
		sys.alterarSeguroValor(1, 1, 100);
	}

	/**
	 * Testa o alterarSeguroValor quando o valor do seguro é inválido.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testAlterarSeguroValorInvalido() {
		sys.cadastrarCenario("fon");
		sys.cadastrarApostaSeguraTaxa(1, "eu", 1451, "VAI ACONTECER", 0.15, 50);
		sys.alterarSeguroValor(1, 1, -151);
	}

	/**
	 * Testa o alterarSeguroTaxa quando o cenário é invalido.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testalterarSeguroTaxaCenarioInvalido() {
		sys.alterarSeguroTaxa(-1, 1, 0.16);
	}

	/**
	 * Testa o alterarSeguroTaxa quando o cenário é inexistente.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testalterarSeguroTaxaCenarioInexistente() {
		sys.alterarSeguroTaxa(1, 15, 0.12);
	}

	/**
	 * Testa o alterarSeguroTaxa quando o id da aposta é inválido.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testalterarSeguroTaxaIdInvalido() {
		sys.cadastrarCenario("hello");
		sys.alterarSeguroTaxa(1, -3, 0.05);
	}

	/**
	 * Testa o alterarSeguroTaxa quando o id da aposta é inexistente.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testalterarSeguroTaxaIdInexistente() {
		sys.cadastrarCenario("fon");
		sys.alterarSeguroTaxa(1, 1, 0.164);
	}

	/**
	 * Testa o alterarSeguroTaxa quando o valor do seguro é inválido.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testalterarSeguroTaxaInvalido() {
		sys.cadastrarCenario("fon");
		sys.cadastrarApostaSeguraValor(1, "eu", 1451, "VAI ACONTECER", 150, 50);
		sys.alterarSeguroTaxa(1, 1, -0.16);
	}

}
