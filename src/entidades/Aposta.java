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
			throw new NullPointerException("Erro no cadastro de aposta: Apostador nao pode ser vazio ou nulo");
		}

		if (apostador.trim().equals("")) {
			throw new IllegalArgumentException("Erro no cadastro de aposta: Apostador nao pode ser vazio ou nulo");
		}
	}

	private void validaValor(int valor) {
		if (valor <= 0) {
			throw new IllegalArgumentException("Erro no cadastro de aposta: Valor nao pode ser menor ou igual a zero");
		}
	}

	private void validaPrevisao(String previsao) {
		if (previsao == null) {
			throw new NullPointerException("Erro no cadastro de aposta: Previsao nao pode ser vazia ou nula");
		}
		if (previsao.trim().equals("")) {
			throw new IllegalArgumentException("Erro no cadastro de aposta: Previsao nao pode ser vazia ou nula");
		}
		if (!(previsao.equals("N VAI ACONTECER") || previsao.equals("VAI ACONTECER"))) {
			throw new IllegalArgumentException("Erro no cadastro de aposta: Previsao invalida");
		}
	}
}
