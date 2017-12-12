package entidades;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ApostaTest {

	Aposta aposta, outraAposta;

	@Before
	public void inicializaAposta() {
		aposta = new Aposta("Gauds", 2048, "N VAI ACONTECER");
	}

	@Test
	public void testConstrutor() {
		assertTrue(outraAposta == null);
		outraAposta = new Aposta("Pericles", 9999, "VAI ACONTECER");
		assertFalse(outraAposta == null);
	}

	@Test
	public void testGetValor() {
		assertEquals(2048, aposta.getValor());
	}

	@Test
	public void testGetPrevisao() {
		assertEquals("N VAI ACONTECER", aposta.getPrevisao());
	}

	@Test
	public void testToString() {
		assertEquals("Gauds - R$20,48 - N VAI ACONTECER", aposta.toString());
		outraAposta = new Aposta("Picles", 10000, "N VAI ACONTECER");
		assertEquals("Picles - R$100,00 - N VAI ACONTECER", outraAposta.toString());
	}

	@Test(expected = NullPointerException.class)
	public void testApostadorNulo() {
		outraAposta = new Aposta(null, 156513641, "N VAI ACONTECER");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testApostadorInvalido() {
		outraAposta = new Aposta("", 10000, "N VAI ACONTECER");
		outraAposta = new Aposta("       ", 10000, "N VAI ACONTECER");

	}

	@Test(expected = NullPointerException.class)
	public void testPrevisaoNula() {
		outraAposta = new Aposta("Hemi", 15641, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPrevisaoVazia() {
		outraAposta = new Aposta("Anjo", 123456, "   ");
		outraAposta = new Aposta("Querubim", 123456, "");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPrevisaoInvalida() {
		outraAposta = new Aposta("Arcanjo", 123456, "HAUSHAUHSN");
		outraAposta = new Aposta("Arcanjo", 123456, "N VAI ACONTECR");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testValorInvalido() {
		outraAposta = new Aposta("Gauds", 0, "N VAI ACONTECER");
		outraAposta = new Aposta("Gauds", -1, "N VAI ACONTECER");
	}

}
