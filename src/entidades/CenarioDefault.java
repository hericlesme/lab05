package entidades;

public class CenarioDefault extends Cenario {
	/**
	 * Constrói um CenarioDefault a partir de sua descrição
	 * 
	 * @param descricao
	 *            a descrição do cenário.
	 */
	public CenarioDefault(String descricao, int id) {
		super(descricao, id);
	}

	/**
	 * Calcula o valor de rateio a ser dividido entre os vencedores, utilizando a
	 * taxa do sistema.
	 * 
	 * @param taxa
	 *            a taxa do sistema.
	 * 
	 * @return um inteiro que representa a quantia a ser dividida entre os
	 *         vencedores.
	 */
	@Override
	public int totalRateioCenario(double taxa) {
		return this.caixa - (int) (this.caixa * taxa);
	}

	/**
	 * Gera a representação em String de um Cenário. A representação segue o
	 * formato: 'descricao - estado'.
	 */
	@Override
	public String toString() {
		return this.id + " - " + this.descricao + " - " + this.estado.getValor();
	}
}
