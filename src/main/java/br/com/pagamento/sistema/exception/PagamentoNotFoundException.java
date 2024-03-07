package br.com.pagamento.sistema.exception;

public class PagamentoNotFoundException extends RuntimeException {

	// identificador de versao
	// classe Serializable
	// final a variavel e definida uma unica vez
	// long => um int com maior quantidade de numeros disponiveis
	private static final long serialversionUID = 1L;

	// construtor vazio
	public PagamentoNotFoundException() {
		super();
	}

	// construtor com uma mensagem
	public PagamentoNotFoundException(String mensagemCustomizada) {
		super(mensagemCustomizada);
	}

}
