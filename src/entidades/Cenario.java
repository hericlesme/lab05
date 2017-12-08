package entidades;

import java.util.HashSet;

public class Cenario {
	private int numeracao;
	private Estado estado;
	private String descricao;
	private int valorAdquirido;
	private HashSet<Aposta> apostas;

	public Cenario(int numeracao, String descricao) {
		this.valorAdquirido = 0;
		this.numeracao = numeracao;
		this.descricao = descricao;
		this.apostas = new HashSet<>();
		this.estado = Estado.NAO_FINALIZADO;
	}

	public void cadastraAposta(String apostador, int valor, String previsao) {
		apostas.add(new Aposta(apostador, valor, previsao));
	}

	public String exibeApostas() {
		String retorno = "";
		for (Aposta i : apostas) {
			retorno += i.toString() + System.lineSeparator();
		}
		return retorno;
	}

	public int totalDeApostas() {
		return apostas.size();
	}

	public int valorTotalDeAposta() {
		int soma = 0;
		for (Aposta i : apostas) {
			soma += i.getValor();
		}
		return soma;
	}

	public void concretizaCenario(boolean ocorreu) {
		if (ocorreu) {
			this.estado = Estado.OCORREU;
			this.valorAdquirido += calculaValorAdquirido("VAI ACONTECER");
		} else {
			this.estado = Estado.NAO_OCORREU;
			this.valorAdquirido += calculaValorAdquirido("N VAI ACONTECER");
		}
	}

	private int calculaValorAdquirido(String previsao) {
		int total = 0;
		for (Aposta i : apostas) {
			if (i.getPrevisao().equals(previsao)) {
				total += i.getValor();
			}
		}
		return total;
	}

	public int calculaQtdVencedores() {
		if (this.estado.equals(Estado.NAO_OCORREU)) {
			return procuraVencedores("N VAI ACONTECER");
		} else if (this.estado.equals(Estado.OCORREU)) {
			return procuraVencedores("VAI ACONTECER");
		} else {
			return 0;
		}
	}

	private int procuraVencedores(String previsao) {
		int vencedores = 0;
		for (Aposta a : apostas) {
			if (a.getPrevisao().equals(previsao)) {
				vencedores += 1;
			}
		}
		return vencedores;
	}

	public int getValorAdquirido() {
		return valorAdquirido;
	}

	@Override
	public String toString() {
		return this.numeracao + " - " + this.descricao + " - " + this.estado.getValor();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numeracao;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cenario other = (Cenario) obj;
		if (numeracao != other.numeracao)
			return false;
		return true;
	}

}
