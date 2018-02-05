package entidades;

public class SeguroTaxa extends Seguro {

	private double taxa;

	public SeguroTaxa(int valor, double taxa) {
		this.valor = valor;
		this.taxa = taxa;
	}

	public int getValor() {
		return (int) (this.valor * this.taxa);
	}

	@Override
	public String toString() {
		return " - ASSEGURADA (TAXA) -" + this.valor * 100 + "%";
	}
}
