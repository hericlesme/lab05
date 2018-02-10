package entidades;

/**
 * Representação de um Seguro por Taxa. Herda da classe abstrata Seguro. Possui
 * uma atributo de taxa.
 * 
 * @author Héricles Emanuel - 117110647
 *
 */
public class SeguroTaxa extends Seguro {

	private double taxa;

	/**
	 * Constrói um seguro por taxa a partir do valor da aposta e de uma taxa.
	 * 
	 * @param valor
	 *            o valor da aposta.
	 * @param taxa
	 *            a taxa do seguro.
	 */
	public SeguroTaxa(int valor, double taxa) {
		this.valor = valor;
		this.taxa = taxa;
	}

	/**
	 * Retorna o valor do seguro, baseado na taxa.
	 * 
	 * @return um inteiro que representa o valor do seguro
	 */
	@Override
	public int getValor() {
		return (int) (this.valor * this.taxa);
	}

	/**
	 * Gera a representação em String de um SeguroTaxa. A representação segue o
	 * formato: 'tipo - valor'.
	 * 
	 * @return a representação em String de um seguro por taxa.
	 */
	@Override
	public String toString() {
		return " - ASSEGURADA (TAXA) -" + this.valor * 100 + "%";
	}
}
