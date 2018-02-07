package entidades;

public class CenarioBonus extends Cenario {

	private int bonus;

	public CenarioBonus(String descricao, int bonus) {
		super(descricao);
		if (bonus <= 0) {
			throw new IllegalArgumentException("Erro no cadastro de cenario: Bonus invalido");
		}
		this.bonus = bonus;
	}

	public int totalRateioCenario(double taxa) {
		return (int) ((this.caixa - this.caixa * taxa) + this.bonus);
	}

	@Override
	public String toString() {
		return this.descricao + " - " + this.estado.getValor() + " - R$ "
				+ String.format("%.2f", ((double) this.bonus / 100));
	}
}