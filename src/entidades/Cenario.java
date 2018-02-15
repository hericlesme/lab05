package entidades;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Modelo de Um Cenário. Cada Cenário possui um estado, descrição, caixa e uma
 * lista de apostas.
 * 
 * @author Héricles Emanuel - 117110647
 *
 */
public abstract class Cenario {
	protected List<Aposta> apostas;
	protected String descricao;
	protected Estado estado;
	protected int caixa;
	protected int id;

	/**
	 * Construtor de um cenário, a partir de sua descrição.
	 * 
	 * @param descricao
	 *            a descrição do cenário.
	 */
	public Cenario(String descricao, int id) {
		this.caixa = 0;
		this.descricao = descricao;
		this.apostas = new ArrayList<>();
		this.estado = Estado.NAO_FINALIZADO;
		this.id = id;

	}

	// Métodos Get

	/**
	 * Método get para o estado do Cenário.
	 * 
	 * @return Enum que representa o estado do Cenário.
	 */
	public Estado getEstado() {
		return this.estado;
	}

	/**
	 * Método get para a descrição do Cenário.
	 * 
	 * @return Enum que representa a descrição do Cenário.
	 */
	public String getDescricao() {
		return this.descricao;
	}

	/**
	 * Método get que retorna a caixa do Cenário.
	 * 
	 * @return um inteiro que representa a quantia na caixa do cenário.
	 */
	public int getCaixa() {
		return caixa;
	}

	/**
	 * Método get que retorna o id do Cenário.
	 * 
	 * @return um inteiro que representa o id do cenário.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Método get que retorna a soma dos valores dos seguros.
	 * 
	 * @return um inteiro que representa o valor total dos seguros.
	 */
	public int getSeguro() {
		return apostas.stream().mapToInt(Aposta::getSeguro).sum();
	}

	// Métodos de Cadastro de Aposta

	/**
	 * Cadastra uma aposta desassegurada no Cenário, a partir de seu nome, valor e
	 * previsão, e a adiciona na lista de apostas.
	 * 
	 * @param apostador
	 *            o nome do apostador.
	 * @param valor
	 *            o valor da aposta.
	 * @param previsao
	 *            a previsão da aposta.
	 */
	public void cadastraAposta(String apostador, int valor, String previsao) {
		apostas.add(new Aposta(apostador, valor, previsao));
	}

	/**
	 * Cadastra uma aposta com seguro de valor no Cenário, a partir de seu nome,
	 * valor, previsão e o valor assegurado, e a adiciona na lista de apostas.
	 * 
	 * @param apostador
	 *            o nome do apostador.
	 * @param valor
	 *            o valor da aposta.
	 * @param previsao
	 *            a previsão da aposta.
	 * @param valorAssegurado
	 *            o valor do seguro.
	 * @return o id da aposta assegurada cadastrada.
	 * 
	 */
	public int cadastraAposta(String apostador, int valor, String previsao, int valorAssegurado) {
		apostas.add(new Aposta(apostador, valor, previsao, valorAssegurado));
		return apostas.size();
	}

	/**
	 * Cadastra uma aposta com seguro de taxa no Cenário, a partir de seu nome,
	 * valor, previsão e taxa do seguro, e a adiciona na lista de apostas.
	 * 
	 * @param apostador
	 *            o nome do apostador.
	 * @param valor
	 *            o valor da aposta.
	 * @param previsao
	 *            a previsão da aposta.
	 * @param taxa
	 *            a taxa correspondente ao valor seguro.
	 * @return o id da aposta assegurada cadastrada.
	 * 
	 */
	public int cadastraAposta(String apostador, int valor, String previsao, double taxa) {
		apostas.add(new Aposta(apostador, valor, previsao, taxa));
		return apostas.size();
	}

	// Métodos Gerais de Apostas

	/**
	 * Altera um seguro de uma aposta para um seguro de valor.
	 * 
	 * @param apostaAssegurada
	 *            o id da aposta a ter seu seguro trocado.
	 * @param valor
	 *            o valor do seguro.
	 * @return o id da aposta alterada.
	 */
	public int alterarSeguroValor(int apostaAssegurada, int valor) {
		apostas.get(apostaAssegurada - 1).alterarSeguroValor(valor);
		return apostaAssegurada;
	}

	/**
	 * Altera um seguro de uma aposta para um seguro de taxa.
	 * 
	 * @param apostaAssegurada
	 *            o id da aposta a ter seu seguro trocado.
	 * @param taxa
	 *            a taxa do seguro.
	 * @return o id da aposta alterada.
	 */
	public int alterarSeguroTaxa(int apostaAssegurada, double taxa) {
		apostas.get(apostaAssegurada - 1).alterarSeguroTaxa(taxa);
		return apostaAssegurada;
	}

	// Métodos Gerais de Cenário.

	/**
	 * Concretiza um cenário, como ocorrido, ou não e incrementa a caixa do cenário,
	 * com o valor que deve ser adicionado.
	 * 
	 * @param ocorreu
	 *            valor booleano que indica se o cenário ocorreu ou não.
	 */
	public void concretizaCenario(boolean ocorreu) {
		if (ocorreu) {
			this.estado = Estado.OCORREU;
			this.caixa += calculaCaixa("VAI ACONTECER");
		} else {
			this.estado = Estado.NAO_OCORREU;
			this.caixa += calculaCaixa("N VAI ACONTECER");
		}
	}

	/**
	 * Calcula o valor que será depositado na caixa, baseado na quantia das apostar
	 * perdedoras.
	 * 
	 * @param previsao
	 *            a previsão, que indica o resultado do fechamento do cenario.
	 * @return a quantia referente às apostas perdedoras.
	 * 
	 */
	private int calculaCaixa(String previsao) {
		return apostas.stream().filter(aposta -> !aposta.getPrevisao().equals(previsao)).mapToInt(Aposta::getValor)
				.sum();
	}

	/**
	 * Exibe a representação em string das apostas do cenário, uma a cada linha.
	 * 
	 * @return uma string com as apostas no cenário.
	 */
	public String exibeApostas() {
		return apostas.stream().map(Aposta::toString).collect(Collectors.joining(System.lineSeparator()));
	}

	/**
	 * Retorna a quantidade de apostas num cenário.
	 * 
	 * @return um inteiro que indica a quantidade de apostas de um cenário.
	 */
	public int totalDeApostas() {
		return apostas.size();
	}

	/**
	 * Calcula a soma dos valores das apostas do cenário.
	 * 
	 * @return um inteiro, com a soma do valor todas as apostas do cenario.
	 */
	public int valorTotalDeApostas() {
		return apostas.stream().mapToInt(Aposta::getValor).sum();
	}

	// Métodos abstratos de Cenário.

	/**
	 * Método abstrato que solicita o cálculo do valor de rateio a ser dividido
	 * entre os vencedores, utilizando a taxa do sistema.
	 * 
	 * @param taxa
	 *            a taxa do sistema.
	 * 
	 */
	public abstract int totalRateioCenario(double taxa);

	/**
	 * Método abstrato que solicita a representação em String de um Cenário.
	 */
	public abstract String toString();

}