package entidades;

/**
 * Modelo de um Seguro. Cada seguro possui um valor.
 * 
 * @author Héricles Emanuel 117110647
 *
 */
public abstract class Seguro {
	protected int valor;

	/**
	 * Retorna o valor do seguro.
	 * 
	 * @return um inteiro que representa o valor do seguro.
	 */
	public int getValor() {
		return valor;
	}

	/**
	 * Solicita a representação em String de um seguro.
	 */
	public abstract String toString();
}
