package entidades;

import java.util.Comparator;

public class ComparaId implements Comparator<Cenario> {

	@Override
	public int compare(Cenario c1, Cenario c2) {
		return c1.getId() - c2.getId();
	}

}
