package entidades;

public class CenarioBonus extends Cenario {

	private int bonus;

	public CenarioBonus(String descricao, int bonus) {
		super(descricao);
		this.bonus = bonus;
	}
	
	public  int totalRateioCenario(double taxa) {
		return super.totalRateioCenario(taxa) + this.bonus;
	}

	@Override
	public String toString() {
		return super.toString() + "- R$ " + String.format("%.2f", ((double) this.bonus));
	}
}