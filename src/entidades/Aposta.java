package entidades;

public class Aposta {
	private String apostador;
	private int valor;
	private String previsao;

	public Aposta(String apostador, int valor, String previsao) {
		
		validaApostador(apostador);
		validaPrevisao(previsao);
		validaValor(valor);
		
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
		return this.apostador + " - R$" + String.format("%.2f", ((double) this.valor) / 100) + " - " + this.previsao;
	}

	private void validaApostador(String apostador) {
		if (apostador == null) {
			throw new NullPointerException("Apostador Nulo!");
		}

		if (apostador.trim().equals("")) {
			throw new IllegalArgumentException("Apostador inválido!");
		}
	}

	private void validaValor(int valor) {
		if (valor <= 0) {
			throw new IllegalArgumentException("Valor inválido!");
		}
	}

	private void validaPrevisao(String previsao) {
		if (previsao == null) {
			throw new NullPointerException("Previsão Nula!");
		}
		
		if (!(previsao.equals("N VAI ACONTECER") || previsao.equals("VAI ACONTECER")) || previsao.trim().equals("")) {
			throw new IllegalArgumentException("Previsao inválida!");
		}
	}
}
