package entidades;

public class Facade {
	private Sistema sys;

	public Facade() {
		this.sys = new Sistema();
	}

	public void inicializa(int caixa, double taxa) {
		sys.setCaixa(caixa);
		sys.setTaxa(taxa);
	}

	public int getCaixa() {
		return sys.getCaixa();
	}

	public int cadastrarCenario(String descricao) {
		sys.cadastrarCenario(descricao);
		return sys.getNumeracao();
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
