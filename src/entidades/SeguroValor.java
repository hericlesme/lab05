package entidades;

public class SeguroValor extends Seguro {

	public SeguroValor(int valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return " - ASSEGURADA (VALOR) - R$ " + String.format("%.2f", this.valor);
	}
}
