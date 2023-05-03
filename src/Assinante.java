
public class Assinante {
	
	private long cpf;
	private String nome;
	private int numero;
	protected int numChamadas;
	private Chamada[] chamadas;
	
	
	public Assinante(long cpf, String nome, int numero) {
		
		this.cpf = cpf;
		this.nome = nome;
		this.numero = numero;
		this.chamadas = new Chamada[10];
		
	}
	
	public long getCpf() {
		return cpf;
	}

	public String toString() {
		return "Dados do Assinante: "
				+ "\n cpf = " + cpf 
				+ "\n nome = " + nome 
				+ "\n numero = " + numero  
				+ "\n numero de chamadas = " + numChamadas
				+ "\n Chamadas: = " + chamadas; 
	}

	
	
}
