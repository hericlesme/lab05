package entidades;

public class CenarioDefault extends Cenario {
	CenarioDefault(String descricao) {
		super(descricao);
	}

	@Override
	public int totalRateioCenario(double taxa) {
		return this.caixa - (int) (this.caixa * taxa);
	}

	@Override
	public String toString() {
		return this.descricao + " - " + this.estado.getValor();
	}
}
