package entidades;

import java.util.HashMap;

public class Sistema {
	private int numeracao;
	private HashMap<Integer, Cenario> cenarios;

	public Sistema() {
		this.numeracao = 1;
		this.cenarios = new HashMap<>();
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
	}
	
//	public int getCaixaCenario(int cenario) {
//		return (int) (cenarios.get(cenario).valorTotalDeAposta() - cenarios.get(cenario).ganhoVencedores());
//	}
		
//	int caixa = (int) (cenarios.get(cenario).caixaCenario() * this.taxa);
//	return caixa;
}
