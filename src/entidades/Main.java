package entidades;

public class Main {
	public static void main(String[] args) {
		Aposta a = new Aposta("Alexsander", 10000, "VAI ACONTECER", 1000);
		System.out.println(a.getSeguro());
		a.alterarSeguroTaxa(0.1);
		System.out.println(a.toString());
		System.out.println(String.format("%.2f", 1000));
	}
}