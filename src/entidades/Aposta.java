package entidades;

public class Aposta {
	private String apostador;
	private int valor;
	private String previsao;

	public Aposta(String apostador, int valor, String previsao) {
		this.apostador = apostador;
		this.valor = valor;
		this.previsao = previsao;
	}

	public int getValor() {
		return valor;
	}
	
	public String getPrevisao() {
		return previsao;
	}
	@Override
	public String toString() {
		return this.apostador + " - " + this.valor + " - " + this.previsao;
	}
}
