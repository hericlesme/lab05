package entidades;

/**
 * Representação de um cenário com bônus. Herda a classe abstrata cenário, e
 * possui um atributo de bônus.
 * 
 * @author Héricles Emanuel - 117110647
 *
 */
public class CenarioBonus extends Cenario {

	private int bonus;

	/**
	 * Constrói um CenarioBonus a partir de sua descrição e valor de bônus
	 * 
	 * @param descricao
	 *            a descrição do cenário.
	 * @param bonus
	 *            o valor bônus do cenário.
	 */
	public CenarioBonus(String descricao, int bonus, int id) {
		super(descricao, id);
		this.bonus = bonus;
	}

	/**
	 * Calcula o valor de rateio a ser dividido entre os vencedores, utilizando a
	 * taxa do sistema, com o acréscimo do bônus.
	 * 
	 * @param taxa
	 *            a taxa do sistema.
	 * 
	 * @return um inteiro que representa a quantia a ser dividida entre os
	 *         vencedores.
	 */
	public int totalRateioCenario(double taxa) {
		return (int) ((this.caixa - this.caixa * taxa) + this.bonus);
	}

	/**
	 * Gera a representação em String de um Cenário. A representação segue o
	 * formato: 'descricao - estado - bonus(Em reais)'.
	 */
	@Override
	public String toString() {
		return this.id + " - " + this.descricao + " - " + this.estado.getValor() + " - R$ "
				+ String.format("%.2f", ((double) this.bonus / 100));
	}
}