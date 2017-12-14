package entidades;

import java.util.ArrayList;

public class Cenario {
	private Estado estado;
	private String descricao;
	private int caixa;
	private ArrayList<Aposta> apostas;

	public Cenario(String descricao) {
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

	public int getCaixa() {
		return caixa;
	}

	@Override
	public String toString() {
		return this.descricao + " - " + this.estado.getValor();
	}
}
