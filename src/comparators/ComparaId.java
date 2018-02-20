package comparators;

import java.util.Comparator;

import entidades.Cenario;

/**
 * Classe de comparação de cenário por id. Implementa Comparator.
 * 
 * @author Héricles Emanuel - 117110647
 *
 */
public class ComparaId implements Comparator<Cenario> {

	/**
	 * Comparara dois objetos do tipo Cenario, com base na sua ordem de
	 * cadastro.
	 * 
	 * @param c1
	 *            o primeiro Cenario.
	 * @param c2
	 *            o segundo Cenario.
	 * @return um inteiro que indica a posição relativa dos cenários.
	 */
	@Override
	public int compare(Cenario c1, Cenario c2) {
		return c1.getId() - c2.getId();
	}

}
