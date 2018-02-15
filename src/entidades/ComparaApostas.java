package entidades;

import java.util.Comparator;

public class ComparaApostas implements Comparator<Cenario> {

	@Override
	public int compare(Cenario c1, Cenario c2) {
		return c1.totalDeApostas() - c2.totalDeApostas();
	}

}
