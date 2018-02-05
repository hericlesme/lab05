package entidades;

public class ApostaAssegurada extends Aposta {
	private Seguro seguro;

	public ApostaAssegurada(String apostador, int valor, String previsao, int seguro) {
		super(apostador, valor, previsao);
		this.seguro = new SeguroValor(seguro);
	}

	public ApostaAssegurada(String apostador, int valor, String previsao, double taxa) {
		super(apostador, valor, previsao);
		this.seguro = new SeguroTaxa(valor, taxa);
	}
	
	@Override
	public String toString() {
		return super.toString() + seguro.toString();
	}
}
