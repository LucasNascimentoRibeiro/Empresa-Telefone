public class Assinante {

	private long cpf;
	private String nome;
	private long numero;
	protected int numChamadas;
	protected Chamada[] chamadas;

	public Assinante(long cpf, String nome, long numero) {
		this.cpf = cpf;
		this.nome = nome;
		this.numero = numero;
		this.chamadas = new Chamada[10];
	}

	public long getCpf() {
		return cpf;
	}

	@Override
	public String toString() {
		return "\n		- Nome: " + nome
				+ "\n		- CPF: " + cpf
				+ "\n		- Número: " + numero
				+ "\n		- Nº Chamadas: " + numChamadas
				+ "\n		- Chamadas: " + chamadas.toString();
	}
}
