package entidades;

import java.util.ArrayList;

/**
 * Representação de Um Cenário. Cada Cenário possui um estado, descrição, caixa
 * e uma lista de apostas. Podendo cadastrar, manipular
 * 
 * @author Héricles Emanuel - 117110647
 *
 */
public abstract class Cenario {
	protected Estado estado;
	protected String descricao;
	protected int caixa;
	protected ArrayList<Aposta> apostas;
	protected ArrayList<ApostaAssegurada> apostasAsseguradas;

	/**
	 * Constrói um cenário a partir de uma descrição, que não pode ser nula ou
	 * vazia.
	 * 
	 * @param descricao
	 *            a descrição do cenario.
	 */
	public Cenario(String descricao) {
		if (descricao == null) {
			throw new NullPointerException("Erro no cadastro de cenario: Descricao nao pode ser nula");
		}

		if (descricao.trim().equals("")) {
			throw new IllegalArgumentException("Erro no cadastro de cenario: Descricao nao pode ser vazia");
		}
		this.caixa = 0;
		this.descricao = descricao;
		this.apostas = new ArrayList<>();
		this.estado = Estado.NAO_FINALIZADO;

	}

	/**
	 * Método get para o estado do Cenário.
	 * 
	 * @return Enum que representa o estado do Cenário.
	 */
	public Estado getEstado() {
		return this.estado;
	}

	/**
	 * Cadastra uma aposta no Cenário, a partir de seu nome, valor e previsão, e a
	 * adiciona na lista de apostas.
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

	public int cadastraApostaSeguraValor(String apostador, int valor, String previsao, int valorAssegurado) {
		apostas.add(new Aposta(apostador, valor, previsao, valorAssegurado));
		return apostas.size();
	}

	public int cadastraApostaSeguraTaxa(String apostador, int valor, String previsao, double taxa) {
		apostas.add(new Aposta(apostador, valor, previsao, taxa));
		return apostas.size();
	}

	/**
	 * Exibe a representação em string das apostas do cenário, uma a cada linha.
	 * 
	 * @return uma string com as apostas no cenário.
	 */
	public String exibeApostas() {
		String retorno = "";
		for (Aposta i : apostas) {
			retorno += i.toString() + System.lineSeparator();
		}
		return retorno;
	}

	/**
	 * Retorna a quantidade de apostas num cenário.
	 * 
	 * @return um inteiro que indica a quantidade de apostas de um cenário.
	 */
	public int totalDeApostas() {
		return apostas.size() + apostasAsseguradas.size();
	}

	/**
	 * Calcula a soma dos valores das apostas do cenário.
	 * 
	 * @return um inteiro, com a soma do valor todas as apostas do cenario.
	 */
	public int valorTotalDeApostas() {
		int soma = 0;
		for (Aposta i : apostas) {
			soma += i.getValor();
		}
		return soma;
	}

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
		int total = 0;
		for (Aposta i : apostas) {
			if (!(i.getPrevisao().equals(previsao))) {
				total += i.getValor();
			}
		}
		return total;
	}

	/**
	 * Método get que retorna a caixa do Cenário.
	 * 
	 * @return um inteiro que representa a quantia na caixa do cenário.
	 */
	public int getCaixa() {
		return caixa;
	}

	public abstract int totalRateioCenario(double taxa);

	/**
	 * Retorna uma representação em String de um Cenário. A representação segue o
	 * formato: "Descrição - estado".
	 * 
	 * @return a string que representa o Cenário.
	 */
	@Override
	public abstract String toString();
}