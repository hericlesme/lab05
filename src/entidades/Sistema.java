package entidades;

import java.util.ArrayList;

public class Sistema {
	private int caixa;
	private double taxa;
	private ArrayList<Cenario> cenarios;

	public Sistema() {
		this.taxa = 0;
		this.cenarios = new ArrayList<>();
	}

	public int getCaixa() {
		return caixa;
	}
	
	public int getNumeracao() {
		return cenarios.size();
	}
	
	public void setCaixa(int caixa) {
		this.caixa = caixa;
	}
	
	public void setTaxa(double taxa) {
		this.taxa = taxa;
	}

	public void cadastrarCenario(String descricao) {
		cenarios.add(this.cenarios.size() + 1, new Cenario(descricao));
	}

	public String exibirCenario(int cenario) {
		return cenarios.get(cenario).toString();
	}

	public String exibirCenarios() {
		String retorno = "";
		for (int i = 0; i < cenarios.size(); i++) {
			retorno +=  i + 1 + " - " + cenarios.get(i).toString();
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
		return (int) ((c.valorTotalDeAposta() - c.getValorAdquirido()) * this.taxa);
	}

	public int totalRateioCenario(int cenario) {
		Cenario c = cenarios.get(cenario);
		return (int) c.getValorAdquirido() / c.calculaQtdVencedores();
	}
}
