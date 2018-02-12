package entidades;

public class Main {
	public static void main(String[] args) {
		Aposta a = new Aposta("Alexsander", 10000, "VAI ACONTECER", 0.1);
		System.out.println(a.getSeguro());
		a.alterarSeguroValor(100);
		System.out.println(a.toString());
	}
}