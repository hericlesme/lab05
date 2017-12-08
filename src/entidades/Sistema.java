package entidades;

import java.util.HashMap;

public class Sistema {
	private int caixa;
	private double taxa;
	private int numeracao;
	private HashMap<Integer, Cenario> cenarios;

	public Sistema() {
		this.taxa = 0;
		this.numeracao = 1;
		this.cenarios = new HashMap<>();
	}

	public int getNumeracao() {
		return numeracao;
	}
	public int getCaixa() {
		return caixa;
	}
	public void setCaixa(int caixa) {
		this.caixa = caixa;
	}
	
	public void setTaxa(double taxa) {
		this.taxa = taxa;
	}

	public void cadastrarCenario(String descricao) {
		cenarios.put(this.numeracao, new Cenario(this.numeracao, descricao));
		numeracao++;
	}

	public String exibirCenario(int cenario) {
		return cenarios.get(cenario).toString();
	}

	public String exibirCenarios() {
		String retorno = "";
		for (int i : cenarios.keySet()) {
			retorno += cenarios.get(i).toString();
		}
		return retorno;
	}

	public void cadastrarAposta(int cenario, String apostador, int valor, String previsao) {
		cenarios.get(cenario).cadastraAposta(apostador, valor, previsao);
	}

	public int valorTotalDeApostas(int cenario) {
		return cenarios.get(cenario).valorTotalDeAposta();
	}

	public int totalDeApostas(int cenario) {
		return cenarios.get(cenario).totalDeApostas();
	}

	public String exibeApostas(int cenario) {
		return cenarios.get(cenario).exibeApostas();
	}

	public void fecharAposta(int cenario, boolean ocorreu) {
		cenarios.get(cenario).concretizaCenario(ocorreu);
		this.caixa += caixaCenario(cenario) * 100;
	}

	public int caixaCenario(int cenario) {
		Cenario c = cenarios.get(cenario);
		return (int) (c.valorTotalDeAposta() - c.getValorAdquirido() * this.taxa);
	}

	public int totalRateioCenario(int cenario) {
		Cenario c = cenarios.get(cenario);
		return (int) c.getValorAdquirido() / c.calculaQtdVencedores();
	}
}
