package entidades;

import easyaccept.EasyAccept;

public class Facade {
	
	
	public static void main(String[] args) {
		args = new String[] {"entidades.Facade", "acceptance_test/us1_test.txt", "acceptance_test/us2_test.txt", "acceptance_test/us3_test.txt", "acceptance_test/us4_test.txt"};
		EasyAccept.main(args);
	}
	
	private Sistema sys;

	public Facade() {
		this.sys = new Sistema();
	}

	public void inicializa(int caixa, double taxa) {
		sys.inicializa(caixa, taxa);
	}

	public int getCaixa() {
		return sys.getCaixa();
	}

	public int cadastrarCenario(String descricao) {
		return sys.cadastrarCenario(descricao);
	}

	public String exibirCenario(int cenario) {
		return sys.exibirCenario(cenario);
	}

	public String exibirCenarios() {
		return sys.exibirCenarios();
	}

	public void cadastrarAposta(int cenario, String apostador, int valor, String previsao) {
		sys.cadastrarAposta(cenario, apostador, valor, previsao);
	}

	public int valorTotalDeApostas(int cenario) {
		return sys.valorTotalDeApostas(cenario);
	}

	public int totalDeApostas(int cenario) {
		return sys.totalDeApostas(cenario);
	}

	public String exibeApostas(int cenario) {
		return sys.exibeApostas(cenario);
	}

	public void fecharAposta(int cenario, boolean ocorreu) {
		sys.fecharAposta(cenario, ocorreu);
	}

	public int getCaixaCenario(int cenario) {
		return sys.caixaCenario(cenario);
	}

	public int getTotalRateioCenario(int cenario) {
		return sys.totalRateioCenario(cenario);
	}
}
