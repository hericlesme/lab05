package entidades;

/**
 * Representação de um Seguro inexistente.
 * 
 * @author Héricles Emanuel - 117110647
 *
 */
public class SemSeguro extends Seguro {

	/**
	 * Retorna o valor do seguro, zerado.
	 * 
	 * @return um inteiro que representa o valor do seguro
	 */
	public int getValor() {
		return 0;
	}

	/**
	 * Retorna a representação em String da Classe. A representação segue o formato
	 * de uma String vazia.
	 */
	@Override
	public String toString() {
		return "";
	}

	/**
	 * Lança um Erro, pois não é possível modificar este tipo de seguro.
	 */
	@Override
	public Seguro alternaTipo(Object[] args) {
		throw new Error("Erro na alteração de seguro: A aposta não possui seguro");
	}
}
