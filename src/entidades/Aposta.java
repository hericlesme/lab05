package entidades;

/**
 * Representação de uma Aposta. Cada aposta possui um apostador, um valor, e a
 * previsão, que pode ser "VAI ACONTECER" ou "N VAI ACONTECER
 * 
 * @author Héricles Emanuel - 117110647
 *
 */
public class Aposta {
	private String apostador;
	private int valor;
	private String previsao;
	
	/**
	 * Construtor de uma aposta. Constrói a aposta a partir de um apostador, valor,
	 * e previsão.
	 * 
	 * @param apostador
	 *            o apostador da aposta.
	 * @param valor
	 *            o valor da aposta.
	 * @param previsao
	 *            a previsão da aposta, se vai acontecer ou não.
	 */
	public Aposta(String apostador, int valor, String previsao) {
		validaApostador(apostador);
		validaPrevisao(previsao);
		validaValor(valor);

		this.apostador = apostador;
		this.valor = valor;
		this.previsao = previsao;
	}

	/**
	 * Método get que retorna o valor da aposta.
	 * 
	 * @return um inteiro, que é o valor da aposta.
	 */
	public int getValor() {
		return valor;
	}

	/**
	 * Método get para a previsão da aposta.
	 * 
	 * @return uma string, que pode ser "Finalizado (ocorreu)", "Finalizado (não
	 *         ocorreu)" ou "Nao finalizado".
	 */
	public String getPrevisao() {
		return previsao;
	}

	/**
	 * Retorna uma representação em String de uma Aposta. A representação segue o
	 * formato: "Apostador - R$valor - previsão."
	 * 
	 * @return uma string que representa a Aposta.
	 */
	@Override
	public String toString() {
		return this.apostador + " - R$" + String.format("%.2f", ((double) this.valor) / 100) + " - " + this.previsao;
	}

	/**
	 * Verifica a validade do Apostador, se não é nulo ou vazio.
	 * 
	 * @param apostador
	 *            o apostador da Aposta.
	 */
	private void validaApostador(String apostador) {
		if (apostador == null) {
			throw new NullPointerException("Erro no cadastro de aposta: Apostador nao pode ser vazio ou nulo");
		}

		if (apostador.trim().equals("")) {
			throw new IllegalArgumentException("Erro no cadastro de aposta: Apostador nao pode ser vazio ou nulo");
		}
	}

	/**
	 * Verifica a validade do Valor, se não é negativo ou igual a zero.
	 * 
	 * @param valor
	 *            o valor da Aposta.
	 */
	private void validaValor(int valor) {
		if (valor <= 0) {
			throw new IllegalArgumentException("Erro no cadastro de aposta: Valor nao pode ser menor ou igual a zero");
		}
	}

	/**
	 * Verifica a validade da Previsão, se não é nula, vazia ou está fora do formato esperado.
	 * 
	 * @param previsao
	 *            a previsão da Aposta.
	 */
	private void validaPrevisao(String previsao) {
		if (previsao == null) {
			throw new NullPointerException("Erro no cadastro de aposta: Previsao nao pode ser vazia ou nula");
		}
		if (previsao.trim().equals("")) {
			throw new IllegalArgumentException("Erro no cadastro de aposta: Previsao nao pode ser vazia ou nula");
		}
		if (!(previsao.equals("N VAI ACONTECER") || previsao.equals("VAI ACONTECER"))) {
			throw new IllegalArgumentException("Erro no cadastro de aposta: Previsao invalida");
		}
	}
}
