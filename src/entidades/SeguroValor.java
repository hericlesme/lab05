package entidades;

public class SeguroValor extends Seguro {

	public SeguroValor(int valorAssegurado) {
		this.valor = valorAssegurado;
	}

	@Override
	public String toString() {
		return " - ASSEGURADA (VALOR) - R$ " + String.format("%.2f", this.valor);
	}
}
