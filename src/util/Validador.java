package util;

import entidades.Estado;

/**
 * Classe de Validação do Sistema de Apostas.
 * 
 * @author Héricles Emanuel - 117110647
 *
 */
public class Validador {

	/**
	 * Verifica se a numeração do cenário passada é inválida.
	 * 
	 * @param cenario
	 *            a numeração do Cenário.
	 * @param mensagem
	 *            uma mensagem a ser lançada na exceção.
	 */
	public void cenarioInvalido(int cenario, String mensagem) {
		if (cenario <= 0) {
			throw new IllegalArgumentException(mensagem + ": Cenario invalido");
		}
	}

	/**
	 * Verifica se o cenário corresponaontende à numeração passada é inexistente.
	 * 
	 * @param cenario
	 *            a numeração do Cenário.
	 * @param mensagem
	 *            uma mensagem a ser lançada na exceção.
	 */
	public void cenarioExistente(int cenario, String mensagem, int size) {
		if (cenario > size) {
			throw new IllegalArgumentException(mensagem + ": Cenario nao cadastrado");
		}
	}

	/**
	 * Verifica se o cenário ainda não foi finalizado.
	 * 
	 * @param estado
	 *            o estado do cenário.
	 * @param mensagem
	 *            uma mensagem a ser lançada na exceção.
	 */
	public void cenarioNaoFinalizado(Estado estado, String mensagem) {
		if (estado.equals(Estado.NAO_FINALIZADO)) {
			throw new IllegalArgumentException(mensagem + ": Cenario ainda esta aberto");
		}
	}

	/**
	 * Verifica a validade da caixa e taxa na inicialização do sistema.
	 * 
	 * @param caixa
	 *            a caixa do sistema
	 * @param taxa
	 *            a taxa do sistema
	 */
	public void validaInicializacao(int caixa, double taxa) {
		doubleInvalido(taxa, "Erro na inicializacao: Taxa nao pode ser inferior a 0");
		inteiroInvalido(caixa, "Erro na inicializacao: Caixa nao pode ser inferior a 0");
	}

	/**
	 * Verifica a validade da descrição de um cenário.
	 * 
	 * @param descricao
	 *            a descrição do cenário.
	 */
	public void descricaoInvalida(String descricao) {
		if (descricao == null) {
			throw new NullPointerException("Erro no cadastro de cenario: Descricao nao pode ser nula");
		}

		if (descricao.trim().equals("")) {
			throw new IllegalArgumentException("Erro no cadastro de cenario: Descricao nao pode ser vazia");
		}
	}

	/**
	 * Verifica se o bônus do cenário é inválido.
	 * 
	 * @param bonus
	 *            o bônus do cenário.
	 */
	public void bonusInvalido(int bonus) {
		inteiroInvalido(bonus, "Erro no cadastro de cenario: Bonus invalido");
	}

	/**
	 * Verifica a validade do Apostador, se não é nulo ou vazio.
	 * 
	 * @param apostador
	 *            o apostador da Aposta.
	 * @param tipoSeguro
	 *            o tipo do seguro da aposta.
	 */
	private void apostadorInvalido(String apostador, String tipoSeguro) {
		if (apostador == null) {
			throw new NullPointerException(
					"Erro no cadastro de aposta" + tipoSeguro + ":Apostador nao pode ser vazio ou nulo");
		}

		if (apostador.trim().equals("")) {
			throw new IllegalArgumentException(
					"Erro no cadastro de aposta" + tipoSeguro + ": Apostador nao pode ser vazio ou nulo");
		}
	}

	/**
	 * Verifica a validade do Valor, se não é negativo ou igual a zero.
	 * 
	 * @param valor
	 *            o valor da Aposta.
	 * @param tipoSeguro
	 *            o tipo do seguro da aposta.
	 */
	private void valorInvalido(int valor, String tipoSeguro) {
		inteiroInvalido(valor,
				"Erro no cadastro de aposta" + tipoSeguro + ": Valor nao pode ser menor ou igual a zero");
	}

	/**
	 * Verifica a validade da Previsão, se não é nula, vazia ou está fora do formato
	 * esperado.
	 * 
	 * @param previsao
	 *            a previsão da Aposta.
	 */
	private void previsaoInvalida(String previsao, String tipoSeguro) {
		if (previsao == null) {
			throw new NullPointerException(
					"Erro no cadastro de aposta" + tipoSeguro + ": Previsao nao pode ser vazia ou nula");
		}
		if (previsao.trim().equals("")) {
			throw new IllegalArgumentException(
					"Erro no cadastro de aposta" + tipoSeguro + ": Previsao nao pode ser vazia ou nula");
		}
		if (!(previsao.equals("N VAI ACONTECER") || previsao.equals("VAI ACONTECER"))) {
			throw new IllegalArgumentException("Erro no cadastro de aposta" + tipoSeguro + ": Previsao invalida");
		}
	}

	/**
	 * Verifica se os parâmetros de criação de uma aposta são válidos.
	 * 
	 * @param apostador
	 *            o apostador da aposta.
	 * @param previsao
	 *            a previsão da aposta.
	 * @param valor
	 *            o valor da aposta.
	 * @param tipoSeguro
	 *            o tipo de seguro da aposta, caso tenha.
	 */
	public void apostaInvalida(String apostador, String previsao, int valor, String tipoSeguro) {
		apostadorInvalido(apostador, tipoSeguro);
		previsaoInvalida(previsao, tipoSeguro);
		valorInvalido(valor, tipoSeguro);
	}

	/**
	 * Verifica se os parâmetros de cadastro de seguro por valor são válidos.
	 * 
	 * @param valor
	 *            o valor da aposta.
	 * @param custo
	 *            o custo da aposta.
	 */
	public void cadastraSeguroValorInvalido(int valor, int custo) {
		inteiroInvalido(valor, "Erro no cadastro de aposta assegurada por valor: valor inválido");
		inteiroInvalido(custo, "Erro no cadastro de aposta assegurada por valor: custo inválido");
	}

	/**
	 * Verifica se os parâmetros de cadastro de seguro por taxa são válidos.
	 * 
	 * @param taxa
	 *            a taxa da aposta.
	 * @param custo
	 *            o custo da aposta.
	 */
	public void cadastraSeguroTaxaInvalido(double taxa, int custo) {
		doubleInvalido(taxa, "Erro no cadastro de aposta assegurada  por taxa: taxa inválida");
		inteiroInvalido(custo, "Erro no cadastro de aposta assegurada por taxa: custo inválido");
	}

	/**
	 * Verifica a validade dos parâmetros de alteração para um seguro por taxa.
	 * 
	 * @param apostaAssegurada
	 *            o id da aposta.
	 * @param taxa
	 *            a taxa do seguro.
	 * @param size
	 *            o tamanho da lista de apostas.
	 */
	public void alteraSeguroTaxaInvalido(int apostaAssegurada, double taxa, int size) {
		inteiroInvalido(apostaAssegurada, "Erro na alteração para seguro por taxa: id de aposta inválido");
		apostaInexistente(apostaAssegurada, size);
		doubleInvalido(taxa, "Erro na alteração para seguro por taxa: valor inválido");

	}

	/**
	 * Verifica a validade dos parâmetros de alteração para um seguro por valor.
	 * 
	 * @param apostaAssegurada
	 *            o id da aposta.
	 * @param valor
	 *            o valor do seguro.
	 * @param size
	 *            o tamanho da lista de apostas.
	 */
	public void alteraSeguroValorInvalido(int apostaAssegurada, int valor, int size) {
		inteiroInvalido(apostaAssegurada, "Erro na alteração para seguro por valor: id de aposta inválido");
		apostaInexistente(apostaAssegurada, size);
		inteiroInvalido(valor, "Erro na alteração para seguro por valor: valor inválido");

	}

	/**
	 * Verifica se a aposta é inexistente.
	 * 
	 * @param id
	 *            o id da aposta
	 * @param size
	 *            o tamanho da lista de apostas
	 */
	private void apostaInexistente(int id, int size) {
		if (id > size) {
			throw new IllegalArgumentException("Erro na alteração de seguro: aposta inexistente");
		}
	}

	/**
	 * Verifica a validade de um inteiro no sistema.
	 * 
	 * @param inteiro
	 *            o inteiro a ser validado.
	 * @param mensagem
	 *            a mensagem da exceção.
	 */
	private void inteiroInvalido(int inteiro, String mensagem) {
		if (inteiro <= 0) {
			throw new IllegalArgumentException(mensagem);
		}
	}

	/**inteiroInvalido
	 * Verifica a validade de um double no sistema.
	 * 
	 * @param valor
	 *            o valor double a ser validado.
	 * @param mensagem
	 *            a mensagem da exceção.
	 */
	private void doubleInvalido(double valor, String mensagem) {
		if (valor <= 0) {
			throw new IllegalArgumentException(mensagem);
		}
	}
	
	public void ordemInvalida(String ordem) {
		if (ordem.trim().equals("") || ordem == null) {			
			throw new IllegalArgumentException("Erro ao alterar ordem: Ordem nao pode ser vazia ou nula");
		}
		if (!(ordem.equals("nome") || ordem.equals("apostas") || ordem.equals("cadastro"))) {
			throw new IllegalArgumentException("Erro ao alterar ordem: Ordem invalida");
		}
			
	}
}
