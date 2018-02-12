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
	public abstract int getValor();

	/**
	 * Solicita a representação em String de um seguro.
	 */
	public abstract String toString();

	/**
	 * Retorna o Seguro proveniente da alteração, conforme o tipo de seguro em que
	 * for chamado o método.
	 * 
	 * @param args
	 *            um array de objetos, que contém os parâmetros a serem utilizados
	 *            na modificação do Seguro.
	 * @return um novo Seguro.
	 */
	public abstract Seguro alternaTipo(Object[] args);
}
