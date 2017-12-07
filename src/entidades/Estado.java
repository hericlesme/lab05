package entidades;

public enum Estado {
	NAO_FINALIZADO("Não Finalizado"), OCORREU("Finalizado (ocorreu)"), NAO_OCORREU("Finalizado, (não ocorreu)");

	private String valor;

	Estado(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}
}
