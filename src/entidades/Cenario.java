package entidades;

import java.util.ArrayList;

/**
 * Representação de Um Cenário. Cada Cenário possui 
 * @author hericlesegs
 *
 */
public class Cenario {
	private Estado estado;
	private String descricao;
	private int caixa;
	private ArrayList<Aposta> apostas;

	public Cenario(String descricao) {
		
		if (descricao == null) {
			throw new NullPointerException("Erro no cadastro de cenario: Descricao nao pode ser nula");
		}

		if (descricao.trim().equals("")) {
			throw new IllegalArgumentException("Erro no cadastro de cenario: Descricao nao pode ser vazia");
		}
		this.caixa = 0;
		this.descricao = descricao;
		this.apostas = new ArrayList<>();
		this.estado = Estado.NAO_FINALIZADO;
	}

	public Estado getEstado() {
		return this.estado;
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

	public int valorTotalDeApostas() {
		int soma = 0;
		for (Aposta i : apostas) {
			soma += i.getValor();
		}
		return soma;
	}

	public void concretizaCenario(boolean ocorreu) {
		if (ocorreu) {
			this.estado = Estado.OCORREU;
			this.caixa += calculaCaixa("VAI ACONTECER");
		} else {
			this.estado = Estado.NAO_OCORREU;
			this.caixa += calculaCaixa("N VAI ACONTECER");
		}
	}

	private int calculaCaixa(String previsao) {
		int total = 0;
		for (Aposta i : apostas) {
			if (!(i.getPrevisao().equals(previsao))) {
				total += i.getValor();
			}
		}
		return total;
	}

	public int getCaixa() {
		return caixa;
	}

	@Override
	public String toString() {
		return this.descricao + " - " + this.estado.getValor();
	}
}
