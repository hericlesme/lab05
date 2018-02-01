package entidades;

import java.util.ArrayList;

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
	ArrayList<Cenario> cenarios;

	/**
	 * Constrói um sistema, com taxa e caixa zerado, e inicializa sua lista de
	 * cenários.
	 */
	public Sistema() {
		this.taxa = 0;
		this.caixa = 0;
		this.cenarios = new ArrayList<>();
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
		if (caixa < 0) {
			throw new IllegalArgumentException("Erro na inicializacao: Caixa nao pode ser inferior a 0");
		}
		if (taxa < 0) {
			throw new IllegalArgumentException("Erro na inicializacao: Taxa nao pode ser inferior a 0");
		}
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
		cenarios.add(new Cenario(descricao));
		return cenarios.size();
	}

	public int cadastrarCenario(String descricao, int bonus) {
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
		verificaCenarioInvalido(cenario, "Erro na consulta de cenario: Cenario invalido");
		verificaCenarioExistente(cenario, "Erro na consulta de cenario: Cenario nao cadastrado");
		return cenario + " - " + cenarios.get(cenario - 1).toString();
	}

	/**
	 * Exibe todos os cenários cadastrados em um sistema, com sua numeração e
	 * representação em string.
	 * 
	 * @return uma String que contem todos os cenários do sistema.
	 */
	public String exibirCenarios() {
		String retorno = "";
		for (int i = 0; i < cenarios.size(); i++) {
			retorno += exibirCenario(i + 1) + System.lineSeparator();
		}
		return retorno;
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
		verificaCenarioInvalido(cenario, "Erro no cadastro de aposta: Cenario invalido");
		verificaCenarioExistente(cenario, "Erro no cadastro de aposta: Cenario nao cadastrado");

		cenarios.get(cenario - 1).cadastraAposta(apostador, valor, previsao);
	}

	/**
	 * Calcula a soma dos valores das apostas do cenário.
	 * 
	 * @param cenario
	 *            a numeração do cenário.
	 * @return um inteiro, com a soma do valor todas as apostas do cenario.
	 */
	public int valorTotalDeApostas(int cenario) {
		verificaCenarioInvalido(cenario, "Erro na consulta do valor total de apostas: Cenario invalido");
		verificaCenarioExistente(cenario, "Erro na consulta do valor total de apostas: Cenario nao cadastrado");

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
		verificaCenarioInvalido(cenario, "Erro na consulta do total de apostas: Cenario invalido");
		verificaCenarioExistente(cenario, "Erro na consulta do total de apostas: Cenario nao cadastrado");

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
		verificaCenarioInvalido(cenario, "Erro na consulta das apostas: Cenario invalido");
		verificaCenarioExistente(cenario, "Erro na consulta das apostas: Cenario nao cadastrado");
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
		verificaCenarioInvalido(cenario, "Erro ao fechar aposta: Cenario invalido");
		verificaCenarioExistente(cenario, "Erro ao fechar aposta: Cenario nao cadastrado");

		Cenario c = cenarios.get(cenario - 1);
		if (!c.getEstado().equals(Estado.NAO_FINALIZADO)) {
			throw new IllegalArgumentException("Erro ao fechar aposta: Cenario ja esta fechado");
		}

		c.concretizaCenario(ocorreu);
		this.caixa += caixaCenario(cenario);
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
		verificaCenarioInvalido(cenario, "Erro na consulta do caixa do cenario: Cenario invalido");
		verificaCenarioExistente(cenario, "Erro na consulta do caixa do cenario: Cenario nao cadastrado");
		Cenario c = cenarios.get(cenario - 1);
		if (c.getEstado().equals(Estado.NAO_FINALIZADO)) {
			throw new IllegalArgumentException("Erro na consulta do caixa do cenario: Cenario ainda esta aberto");
		}
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
		verificaCenarioInvalido(cenario, "Erro na consulta do total de rateio do cenario: Cenario invalido");
		verificaCenarioExistente(cenario, "Erro na consulta do total de rateio do cenario: Cenario nao cadastrado");
		Cenario c = cenarios.get(cenario - 1);

		if (c.getEstado().equals(Estado.NAO_FINALIZADO)) {
			throw new IllegalArgumentException(
					"Erro na consulta do total de rateio do cenario: Cenario ainda esta aberto");
		}

		return c.totalRateioCenario(this.taxa);
	}

	/**
	 * Método de verificação que lança uma exceção caso o cenário seja inválido.
	 * 
	 * @param cenario
	 *            a numeração do cenário.
	 * @param mensagem
	 *            a mensagem a ser exibida na exceção.
	 */
	private void verificaCenarioInvalido(int cenario, String mensagem) {
		if (cenario <= 0) {
			throw new IllegalArgumentException(mensagem);
		}
	}

	/**
	 * Método de verificação que lança uma exceção caso o cenário seja inexistente.
	 * 
	 * @param cenario
	 *            a numeração do cenário.
	 * @param mensagem
	 *            a mensagem a ser exibida na exceção.
	 */
	private void verificaCenarioExistente(int cenario, String mensagem) {
		if (cenario > cenarios.size()) {
			throw new IllegalArgumentException(mensagem);
		}
	}
}
