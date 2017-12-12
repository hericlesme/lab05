package entidades;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CenarioTest {
	Cenario cenario;

	@Test
	public void testConstrutor() {
		assertTrue(cenario == null);
		cenario =  new Cenario("A BARATA MATOU HEMI");
		assertFalse(cenario == null);
	}
	
	@Test
	public void testCadastraAposta() {
		cenario = new Cenario("HEMI FOI INCENDIADA PELA BARATA");
		assertTrue(cenario.totalDeApostas() == 0);
		cenario.cadastraAposta("Hue", 666666, "VAI ACONTECER");
		assertTrue(cenario.totalDeApostas() == 1);
		cenario.cadastraAposta("HuA", 666666, "N VAI ACONTECER");
		assertTrue(cenario.totalDeApostas() == 2);
	}

}
