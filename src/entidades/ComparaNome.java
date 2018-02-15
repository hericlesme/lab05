package entidades;

import java.util.Comparator;

/**
 * Classe de comparação de cenário por descrição. Implementa Comparator.
 * 
 * @author Héricles Emanuel - 117110647
 *
 */
public class ComparaNome implements Comparator<Cenario> {

	/**
	 * Comparara dois objetos do tipo Cenario, com base na ordem lexicográfica
	 * de sua descrição.
	 * 
	 * @param c1
	 *            o primeiro Cenario.
	 * @param c2
	 *            o segundo Cenario.
	 * @return um inteiro que indica a posição relativa dos cenários.
	 */
	@Override
	public int compare(Cenario c1, Cenario c2) {
		return c1.getDescricao().compareTo(c2.getDescricao());
	}

}
