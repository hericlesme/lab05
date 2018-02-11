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
	private String previsao;
	private Seguro seguro;
	private int valor;

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
		this.valor = valor;
		this.previsao = previsao;
		this.apostador = apostador;
		this.seguro = new SemSeguro();
	}

	/**
	 * Construtor de uma aposta assegurada por valor. Constrói a aposta a partir de
	 * um apostador, valor, previsão e valor assegurado.
	 * 
	 * @param apostador
	 *            o apostador da aposta.
	 * @param valor
	 *            o valor da aposta.
	 * @param previsao
	 *            a previsão da aposta, se vai acontecer ou não.
	 * @param valorAssegurado
	 *            o valor do seguro da aposta.
	 */
	public Aposta(String apostador, int valor, String previsao, int valorAssegurado) {
		this.valor = valor;
		this.previsao = previsao;
		this.apostador = apostador;
		this.seguro = new SeguroValor(valorAssegurado);

	}

	/**
	 * Construtor de uma aposta assegurada por taxa. Constrói a aposta a partir de
	 * um apostador, valor, e previsão.
	 * 
	 * @param apostador
	 *            o apostador da aposta.
	 * @param valor
	 *            o valor da aposta.
	 * @param previsao
	 *            a previsão da aposta, se vai acontecer ou não.
	 * @param taxa
	 *            a taxa do seguro da aposta.
	 * 
	 * 
	 */
	public Aposta(String apostador, int valor, String previsao, double taxa) {
		this.valor = valor;
		this.previsao = previsao;
		this.apostador = apostador;
		this.seguro = new SeguroTaxa(valor, taxa);

	}

	/**
	 * Método get que retorna o valor da aposta.
	 * 
	 * @return um inteiro, que é o valor da aposta.
	 */
	public int getValor() {
		return valor;
	}

	public int getSeguro() {
		return seguro.getValor();
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
	 * formato: "Apostador - R$ valor - previsão."
	 * 
	 * @return uma string que representa a Aposta.
	 */
	@Override
	public String toString() {
		return this.apostador + " - R$" + String.format("%.2f", ((double) this.valor) / 100) + " - " + this.previsao
				+ seguro.toString();
	}

	/**
	 * Altera um seguro da aposta para um seguro de valor.
	 * 
	 * @param valorAssegurado
	 *            o valor do seguro.
	 */
	public void alterarSeguroValor(int valorAssegurado) {
		this.seguro = ((SeguroTaxa) (seguro)).alternaTipo(valorAssegurado);
	}

	/**
	 * Altera um seguro da aposta para um seguro de taxa.
	 * 
	 * @param valor
	 *            a taxa da aposta
	 */
	public void alterarSeguroTaxa(double taxa) {
		this.seguro = ((SeguroValor) (seguro)).alternaTipo(this.valor, taxa);
	}
}
