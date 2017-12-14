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

	public void inicializa(int caixa, double taxa) {
		if (caixa < 0) {
			throw new IllegalArgumentException("Erro na inicializacao: Caixa nao pode ser inferior a 0");
		}
		if (taxa < 0) {
			throw new IllegalArgumentException("Erro na inicializacao: Taxa nao pode ser inferior a 0");	
		}
		this.caixa = caixa;
		this.taxa = taxa;
	}
	
	public int getCaixa() {
		return caixa;
	}

	public int cadastrarCenario(String descricao) {
		cenarios.add(new Cenario(descricao));
		return cenarios.size();
	}

	public String exibirCenario(int cenario) {
		if (cenario < 1) {
			throw new IllegalArgumentException("Erro na consulta de cenario: Cenario invalido");
		}
		
		if (cenario > cenarios.size()) {
			throw new IllegalArgumentException("Erro na consulta de cenario: Cenario nao cadastrado");
		}
		return cenario + " - " +cenarios.get(cenario - 1).toString();
	}

	public String exibirCenarios() {
		String retorno = "";
		for (int i = 0; i < cenarios.size(); i++) {
			retorno +=  i + 1 + " - " + cenarios.get(i).toString();
		}
		return retorno;
	}

	public void cadastrarAposta(int cenario, String apostador, int valor, String previsao) {
		if (cenario <= 0) {
			throw new IllegalArgumentException("Erro no cadastro de aposta: Cenario invalido");
		}
		
		if (cenario > cenarios.size()) {
			throw new IllegalArgumentException("Erro no cadastro de aposta: Cenario nao cadastrado");
		}
		
		cenarios.get(cenario - 1).cadastraAposta(apostador, valor, previsao);
	}

	public int valorTotalDeApostas(int cenario) {
		if (cenario <= 0) {
			throw new IllegalArgumentException("Erro na consulta do valor total de apostas: Cenario invalido");
		}
		
		if (cenario > cenarios.size()) {
			throw new IllegalArgumentException("Erro na consulta do valor total de apostas: Cenario nao cadastrado");
		}
		
		return cenarios.get(cenario - 1).valorTotalDeAposta();
	}

	public int totalDeApostas(int cenario) {
		if (cenario <= 0) {
			throw new IllegalArgumentException("Erro na consulta do total de apostas: Cenario invalido");
		}
		
		if (cenario > cenarios.size()) {
			throw new IllegalArgumentException("Erro na consulta do total de apostas: Cenario nao cadastrado");
		}
		
		return cenarios.get(cenario - 1).totalDeApostas();
	}

	public String exibeApostas(int cenario) {
		return cenarios.get(cenario - 1).exibeApostas();
	}

	public void fecharAposta(int cenario, boolean ocorreu) {
		cenarios.get(cenario - 1).concretizaCenario(ocorreu);
		this.caixa += caixaCenario(cenario);
	}

	public int caixaCenario(int cenario) {
		Cenario c = cenarios.get(cenario - 1);
		return (int) (c.getCaixa() * this.taxa);
	}

	public int totalRateioCenario(int cenario) {
		Cenario c = cenarios.get(cenario - 1);
		return (int) c.getCaixa() - caixaCenario(cenario);
	}
}
