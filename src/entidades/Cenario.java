package entidades;

import java.util.HashSet;

public class Cenario {
	private HashSet<Aposta> apostas;
	private int numeracao;
	private String descricao;
	private Estado estado;

	public Cenario(int numeracao, String descricao) {
		this.numeracao = numeracao;
		this.descricao = descricao;
		this.estado = Estado.NAO_FINALIZADO;
		this.apostas = new HashSet<>();
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

	public int ganhoVencedores() {
		int total = 0;
		if (estado.equals(Estado.NAO_OCORREU)) {
			for (Aposta i : apostas) {
				if (i.getPrevisao().equals("N VAI ACONTECER")) {
					total += i.getValor();
				}
			}
		} else if (estado.equals(Estado.OCORREU)) {
			for (Aposta i : apostas) {
				if (i.getPrevisao().equals("VAI ACONTECER")) {
					total += i.getValor();
				}
			}
		}
		return total;
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

	public void concretizaCenario(boolean ocorreu) {
		if (ocorreu) {
			this.estado = Estado.OCORREU;
		} else {
			this.estado = Estado.NAO_OCORREU;
		}
	}
}
