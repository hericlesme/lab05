package entidades;

public abstract class Seguro {
	protected int valor;
	
	public int getValor() {
		return valor;
	}
	
	public abstract String toString();
}
