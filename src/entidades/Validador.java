package entidades;

public class Validador {

	public void cenarioInvalido(int cenario, String mensagem) {
		if (cenario <= 0) {
			throw new IllegalArgumentException(mensagem + ": Cenario invalido");
		}
	}

	public void cenarioExistente(int cenario, String mensagem, int size) {
		if (cenario > size) {
			throw new IllegalArgumentException(mensagem + ": Cenario nao cadastrado");
		}
	}

	public void cenarioNaoFinalizado(Estado estado, String mensagem) {
		if (estado.equals(Estado.NAO_FINALIZADO)) {
			throw new IllegalArgumentException(mensagem + ": Cenario ainda esta aberto");
		}
	}

	public void validaInicializacao(int caixa, double taxa) {
		if (caixa < 0) {
			throw new IllegalArgumentException("Erro na inicializacao: Caixa nao pode ser inferior a 0");
		}

		if (taxa < 0) {
			throw new IllegalArgumentException("Erro na inicializacao: Taxa nao pode ser inferior a 0");
		}
	}

	public void descricaoInvalida(String descricao) {
		if (descricao == null) {
			throw new NullPointerException("Erro no cadastro de cenario: Descricao nao pode ser nula");
		}

		if (descricao.trim().equals("")) {
			throw new IllegalArgumentException("Erro no cadastro de cenario: Descricao nao pode ser vazia");
		}
	}

	public void bonusInvalido(int bonus) {
		if (bonus <= 0) {
			throw new IllegalArgumentException("Erro no cadastro de cenario: Bonus invalido");
		}
	}
	
	public void apostadorInvalido(String apostador, String tipoSeguro) {
		
	}
}
