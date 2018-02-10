package entidades;

public class SeguroValor extends Seguro {

	/**
	 * Constrói um seguro por valor a partir de seu valor.
	 * 
	 * @param valor
	 *            o valor do seguro.
	 */
	public SeguroValor(int valorAssegurado) {
		this.valor = valorAssegurado;
	}

	/**
	 * Gera a representação em String de um SeguroValor. A representação segue o
	 * formato: 'tipo - valor'.
	 * 
	 * @return a representação em String de um seguro por valor.
	 */
	@Override
	public String toString() {
		return " - ASSEGURADA (VALOR) - R$ " + String.format("%.2f", this.valor);
	}
}
