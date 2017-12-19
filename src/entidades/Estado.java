package entidades;

/**
 * Representação de um Estado. O Estado pode ser não finalizado, ocorrido ou
 * não.
 */
public enum Estado {
	NAO_FINALIZADO("Nao finalizado"), OCORREU("Finalizado (ocorreu)"), NAO_OCORREU("Finalizado (não ocorreu)");

	private String valor;

	/**
	 * Constroi um Estado a partir de seu valor.
	 */
	Estado(String valor) {
		this.valor = valor;
	}

	/**
	 * Retorna o valor de um Estado.
	 * 
	 * @return o valor de um Estado
	 */
	public String getValor() {
		return valor;
	}
}
