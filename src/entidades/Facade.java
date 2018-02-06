package entidades;

import easyaccept.EasyAccept;

/**
 * Representação de uma Facade. A facade delega os métodos de um Sistema.
 * 
 * @author Héricles Emanuel - 117110647
 *
 */
public class Facade {
	/**
	 * Testes de Aceitação
	 * 
	 * @param args
	 *            scripts de teste a serem executados.
	 */
	public static void main(String[] args) {
		args = new String[] { "entidades.Facade", "acceptance_test/us1_test.txt", "acceptance_test/us2_test.txt",
				"acceptance_test/us3_test.txt", "acceptance_test/us4_test.txt", "acceptance_test/us5_test.txt",
				"acceptance_test/us6_test.txt" };
		EasyAccept.main(args);
	}

	private Sistema sys;

	/**
	 * Constrói uma Facade, inicializando seu Sistema.
	 */
	public Facade() {
		this.sys = new Sistema();
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
		sys.inicializa(caixa, taxa);
	}

	/**
	 * Método get para a caixa do sistema.
	 * 
	 * @return um inteiro, que carrega a quantia existente no caixa do sistema.
	 */
	public int getCaixa() {
		return sys.getCaixa();
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
		return sys.cadastrarCenario(descricao);
	}

	public int cadastrarCenario(String descricao, int bonus) {
		return sys.cadastrarCenario(descricao, bonus);
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
		return sys.exibirCenario(cenario);
	}

	/**
	 * Exibe todos os cenários cadastrados em um sistema, com sua numeração e
	 * representação em string.
	 * 
	 * @return uma String que contem todos os cenários do sistema.
	 */
	public String exibirCenarios() {
		return sys.exibirCenarios();
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
		sys.cadastrarAposta(cenario, apostador, valor, previsao);
	}

	public int cadastrarApostaSeguraValor(int cenario, String apostador, int valor, String previsao, int valorAssegurado, int custo) {
		return sys.cadastrarApostaSeguraValor(cenario, apostador, valor, previsao, valorAssegurado, custo);
	}

	public int cadastrarApostaSeguraTaxa(int cenario, String apostador, int valor, String previsao, double taxa, int custo) {
		return sys.cadastrarApostaSeguraTaxa(cenario, apostador, valor, previsao, taxa, custo);
	}

	/**
	 * Calcula a soma dos valores das apostas do cenário.
	 * 
	 * @param cenario
	 *            a numeração do cenário.
	 * @return um inteiro, com a soma do valor todas as apostas do cenario.
	 */
	public int valorTotalDeApostas(int cenario) {
		return sys.valorTotalDeApostas(cenario);
	}

	/**
	 * Retorna a quantidade de apostas de um cenário.
	 * 
	 * @param cenario
	 *            a numeração do cenário.
	 * @return um inteiro que indica a quantidade de apostas de um cenário.
	 */
	public int totalDeApostas(int cenario) {
		return sys.totalDeApostas(cenario);
	}

	/**
	 * Exibe a representação em string das apostas de um cenário, uma a cada linha.
	 * 
	 * @param a
	 *            numeração do cenário.
	 * @return uma string com as apostas no cenário.
	 */
	public String exibeApostas(int cenario) {
		return sys.exibeApostas(cenario);
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
		sys.fecharAposta(cenario, ocorreu);
	}

	/**
	 * Calcula a caixa do cenário, que é o produto da quantia de perdedores e taxa
	 * do sistema.
	 * 
	 * @param cenario
	 *            a numeração do cenário.
	 * @return um inteiro representando a caixa do cenário destinada ao sistema.
	 */
	public int getCaixaCenario(int cenario) {
		return sys.caixaCenario(cenario);
	}

	/**
	 * Método de verificação que lança uma exceção caso o cenário seja inexistente.
	 * 
	 * @param cenario
	 *            a numeração do cenário.
	 * @param mensagem
	 *            a mensagem a ser exibida na exceção.
	 */
	public int getTotalRateioCenario(int cenario) {
		return sys.totalRateioCenario(cenario);
	}
}
