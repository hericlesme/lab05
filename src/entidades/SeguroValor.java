package entidades;

public class SeguroValor extends Seguro {
	/**
	 * Constrói um seguro por valor a partir de seu valor.
	 * 
	 * @param valorAssegurado
	 *            o valor do seguro.
	 */
	public SeguroValor(int valorAssegurado) {
		this.valor = valorAssegurado;
	}

	/**
	 * Retorna o valor do seguro.
	 * 
	 * @return um inteiro que representa o valor do seguro.
	 */
	public int getValor() {
		return this.valor;
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

	/**
	 * Retorna o Seguro para qual este pode ser alterado.
	 * 
	 * @param args
	 *            um array de objetos, que contém os parâmetros a serem utilizados
	 *            na modificação do Seguro.
	 * @return um SeguroTaxa com os valores passados.
	 */
	public Seguro alternaTipo(Object[] args) {
		return new SeguroTaxa((int) args[0], (double) args[1]);
	}
}
