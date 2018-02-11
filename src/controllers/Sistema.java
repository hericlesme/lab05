package controllers;

import java.util.ArrayList;
import java.util.stream.Collectors;

import entidades.Cenario;
import entidades.CenarioBonus;
import entidades.CenarioDefault;
import entidades.Estado;
import util.Validador;

/**
 * Representação de Um sistema de apostas. Um sistema pode ter vários cenários,
 * um caixa, e uma taxa. Todas as operações referentes à cadastro e manipulação
 * de apostas e cenários também podem ser realizadas pelo sistema.
 * 
 * @author Héricles Emanuel - 117110647
 *
 */
public class Sistema {
	private int caixa;
	private double taxa;
	private Validador validador;
	private ArrayList<Cenario> cenarios;

	/**
	 * Constrói um sistema, com taxa e caixa zerado, e inicializa sua lista de
	 * cenários.
	 */
	public Sistema() {
		this.taxa = 0;
		this.caixa = 0;
		this.cenarios = new ArrayList<>();
		this.validador = new Validador();
	}

	/**
	 * Inicializa um sistema, a partir de sua sua caixa e taxa, elas não podem ser
	 * inferiores a zero.
	 * 
	 * @param caixa
	 *            a caixa do sistema a ser inicializado.
	 * @param taxa
	 *            a do sistema a ser inicializado.
	 */
	public void inicializa(int caixa, double taxa) {
		validador.validaInicializacao(caixa, taxa);
		this.caixa = caixa;
		this.taxa = taxa;
	}

	/**
	 * Método get para a caixa do sistema.
	 * 
	 * @return um inteiro, que carrega a quantia existente no caixa do sistema.
	 */
	public int getCaixa() {
		return caixa;
	}


	/**
	 * Cadastra um cenário a partir de uma descrição, que não pode ser nula ou vazia
	 * e o aloca numa lista de cenários. e retorna a numeração do cenário, que é a
	 * posição de alocação dele.
	 * 
	 * @param descricao
	 *            a descrição do cenario.
	 *
	 * @return a numeração do cenário.
	 */
	public int cadastrarCenario(String descricao) {
		validador.descricaoInvalida(descricao);
		cenarios.add(new CenarioDefault(descricao));
		return cenarios.size();
	}

	/**
	 * Cadastra um cenário com bônus a partir de uma descrição e um bônus, o aloca
	 * numa lista de cenários. e retorna a numeração do cenário, que é a posição de
	 * alocação dele.
	 * 
	 * @param descricao
	 *            a descrição do cenário.
	 * @param bonus
	 *            o bonus do cenário.
	 * @return a numeração do cenário.
	 */
	public int cadastrarCenario(String descricao, int bonus) {
		validador.descricaoInvalida(descricao);
		validador.bonusInvalido(bonus);
		this.caixa -= bonus;
		cenarios.add(new CenarioBonus(descricao, bonus));
		return cenarios.size();
	}

	/**
	 * Exibe a representação em String de um cenário com sua numeração. A
	 * representação segue o formato: "Numeracao - Descricao - Estado".
	 * 
	 * @param cenario
	 *            a numeração do cenário a ser exibido.
	 * @return a String exibindo o cenário.
	 */
	public String exibirCenario(int cenario) {
		validador.cenarioInvalido(cenario, "Erro na consulta de cenario");
		validador.cenarioExistente(cenario, "Erro na consulta de cenario", cenarios.size());
		return cenario + " - " + cenarios.get(cenario - 1).toString();
	}

	/**
	 * Exibe todos os cenários cadastrados em um sistema, com sua numeração e
	 * representação em string.
	 * 
	 * @return uma String que contem todos os cenários do sistema.
	 */
	public String exibirCenarios() {
		return cenarios.stream().map(Cenario::toString).collect(Collectors.joining(System.lineSeparator()));
	}

	/**
	 * Cadastra uma aposta em um determinado cenário, dada sua numeração.
	 * 
	 * @param cenario
	 *            A numeração do cenário a ser cadastrado a aposta.
	 * @param apostador
	 *            O nome do apostador.
	 * @param valor
	 *            O valor da aposta.
	 * @param previsao
	 *            A previsão da aposta.
	 */
	public void cadastrarAposta(int cenario, String apostador, int valor, String previsao) {
		validador.cenarioInvalido(cenario, "Erro no cadastro de aposta");
		validador.cenarioExistente(cenario, "Erro no cadastro de aposta", cenarios.size());
		validador.apostaInvalida(apostador, previsao, valor, "");
		cenarios.get(cenario - 1).cadastraAposta(apostador, valor, previsao);
	}

	/**
	 * Cadastra uma aposta assegurada por taxa em um determinado cenário, dada
	 * sua numeração.
	 * 
	 * @param cenario
	 *            A numeração do cenário a ser cadastrado a aposta.
	 * @param apostador
	 *            O nome do apostador.
	 * @param valor
	 *            O valor da aposta.
	 * @param previsao
	 *            A previsão da aposta.
	 * @param taxa
	 *            a taxa do seguro.
	 * @param custo
	 *            o custo do seguro.
	 * @return o id da aposta assegurada.
	 */
	public int cadastrarApostaSeguraTaxa(int cenario, String apostador, int valor, String previsao, double taxa,
			int custo) {
		validador.cenarioInvalido(cenario, "Erro no cadastro de aposta assegurada por taxa");
		validador.cenarioExistente(cenario, "Erro no cadastro de aposta assegurada por taxa", cenarios.size());
		validador.apostaInvalida(apostador, previsao, valor, " assegurada por taxa");
		validador.cadastraSeguroTaxaInvalido(taxa, custo);
		this.caixa += custo;
		return cenarios.get(cenario - 1).cadastraAposta(apostador, valor, previsao, taxa);
	}

	/**
	 * Cadastra uma aposta assegurada por valor em um determinado cenário, dada
	 * sua numeração.
	 * 
	 * @param cenario
	 *            A numeração do cenário a ser cadastrado a aposta.
	 * @param apostador
	 *            O nome do apostador.
	 * @param valor
	 *            O valor da aposta.
	 * @param previsao
	 *            A previsão da aposta.
	 * @param valor
	 *            o valor do seguro.
	 * @param custo
	 *            o custo do seguro.
	 * @return o id da aposta assegurada.
	 */
	public int cadastrarApostaSeguraValor(int cenario, String apostador, int valor, String previsao,
			int valorAssegurado, int custo) {
		validador.cenarioInvalido(cenario, "Erro no cadastro de aposta assegurada por valor");
		validador.cenarioExistente(cenario, "Erro no cadastro de aposta assegurada por valor", cenarios.size());
		validador.apostaInvalida(apostador, previsao, valor, " assegurada por valor");
		validador.cadastraSeguroValorInvalido(valorAssegurado, custo);
		this.caixa += custo;
		return cenarios.get(cenario - 1).cadastraAposta(apostador, valor, previsao, valorAssegurado);
	}

	/**
	 * Calcula a soma dos valores das apostas do cenário.
	 * 
	 * @param cenario
	 *            a numeração do cenário.
	 * @return um inteiro, com a soma do valor todas as apostas do cenario.
	 */
	public int valorTotalDeApostas(int cenario) {
		validador.cenarioInvalido(cenario, "Erro na consulta do valor total de apostas");
		validador.cenarioExistente(cenario, "Erro na consulta do valor total de apostas", cenarios.size());

		return cenarios.get(cenario - 1).valorTotalDeApostas();
	}

	/**
	 * Retorna a quantidade de apostas de um cenário.
	 * 
	 * @param cenario
	 *            a numeração do cenário.
	 * @return um inteiro que indica a quantidade de apostas de um cenário.
	 */
	public int totalDeApostas(int cenario) {
		validador.cenarioInvalido(cenario, "Erro na consulta do total de apostas");
		validador.cenarioExistente(cenario, "Erro na consulta do total de apostas", cenarios.size());

		return cenarios.get(cenario - 1).totalDeApostas();
	}

	/**
	 * Exibe a representação em string das apostas de um cenário, uma a cada linha.
	 * 
	 * @param cenario
	 *            a numeração do cenário.
	 * @return uma string com as apostas no cenário.
	 */
	public String exibeApostas(int cenario) {
		validador.cenarioInvalido(cenario, "Erro na consulta das apostas");
		validador.cenarioExistente(cenario, "Erro na consulta das apostas", cenarios.size());
		return cenarios.get(cenario - 1).exibeApostas();
	}

	/**
	 * Concretiza um cenário como ocorrido ou não, e incrementa a caixa do sistema,
	 * de acordo com a caixa do cenário destinada ao sistema.
	 * 
	 * @param cenario
	 *            a numeração do Cenário.
	 * @param ocorreu
	 *            um boolean que indica a ocorrência do cenário.
	 */
	public void fecharAposta(int cenario, boolean ocorreu) {
		validador.cenarioInvalido(cenario, "Erro ao fechar aposta");
		validador.cenarioExistente(cenario, "Erro ao fechar aposta", cenarios.size());

		Cenario c = cenarios.get(cenario - 1);
		if (!c.getEstado().equals(Estado.NAO_FINALIZADO)) {
			throw new IllegalArgumentException("Erro ao fechar aposta: Cenario ja esta fechado");
		}

		c.concretizaCenario(ocorreu);
		this.caixa += caixaCenario(cenario);
		this.caixa -= c.getSeguro();
	}

	/**
	 * Calcula a caixa do cenário, que é o produto da quantia de perdedores e taxa
	 * do sistema.
	 * 
	 * @param cenario
	 *            a numeração do cenário.
	 * @return um inteiro representando a caixa do cenário destinada ao sistema.
	 */
	public int caixaCenario(int cenario) {
		validador.cenarioInvalido(cenario, "Erro na consulta do caixa do cenario");
		validador.cenarioExistente(cenario, "Erro na consulta do caixa do cenario", cenarios.size());
		Cenario c = cenarios.get(cenario - 1);
		validador.cenarioNaoFinalizado(c.getEstado(), "Erro na consulta do caixa do cenario");
		return (int) (c.getCaixa() * this.taxa);
	}

	/**
	 * Calcula a quantia que será dividida entre os vencedores de um cenário.
	 * 
	 * @param cenario
	 *            a numeração do cenário.
	 * @return um inteiro que indica a quantia a ser dividida.
	 */
	public int totalRateioCenario(int cenario) {
		validador.cenarioInvalido(cenario, "Erro na consulta do total de rateio do cenario");
		validador.cenarioExistente(cenario, "Erro na consulta do total de rateio do cenario", cenarios.size());
		Cenario c = cenarios.get(cenario - 1);
		validador.cenarioNaoFinalizado(c.getEstado(), "Erro na consulta do total de rateio do cenario");
		return c.totalRateioCenario(this.taxa);
	}

	/**
	 * Altera um seguro de uma aposta em determinado cenário para um seguro de
	 * valor.
	 * 
	 * @param cenario
	 *            a numeração do cenário.
	 * @param apostaAssegurada
	 *            o id da aposta a ter seu seguro trocado.
	 * @param valor
	 *            o valor do seguro.
	 * @return o id da aposta alterada.
	 */
	public int alterarSeguroValor(int cenario, int apostaAssegurada, int valor) {
		validador.cenarioInvalido(cenario, "Erro na alteração do seguro de valor");
		validador.cenarioExistente(cenario, "Erro na alteração do seguro de valor", cenarios.size());
		validador.alteraSeguroTaxaInvalido(apostaAssegurada, valor, totalDeApostas(cenario));
		return cenarios.get(cenario - 1).alterarSeguroValor(apostaAssegurada, valor);
	}

	/**
	 * Altera um seguro de uma aposta em determinado cenário para um seguro de taxa.
	 * 
	 * @param cenario
	 *            a numeração do cenário.
	 * @param apostaAssegurada
	 *            o id da aposta a ter seu seguro trocado.
	 * @param taxa
	 *            a taxa do seguro.
	 * @return o id da aposta alterada.
	 */
	public int alterarSeguroTaxa(int cenario, int apostaAssegurada, double taxa) {
		validador.cenarioInvalido(cenario, "Erro na alteração do seguro de taxa");
		validador.cenarioExistente(cenario, "Erro na alteração do seguro de taxa", cenarios.size());
		return cenarios.get(cenario - 1).alterarSeguroTaxa(apostaAssegurada, taxa);
	}
}