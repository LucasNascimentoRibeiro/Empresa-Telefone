import java.util.GregorianCalendar;

public class PosPago extends Assinante {

	private float assinatura;

	public PosPago(long cpf, String nome, long numero, float assinatura) {
		super(cpf, nome, numero);
		this.assinatura = assinatura;
	}

	public void fazerChamada(GregorianCalendar data, int duracao) {
		if (10 > numChamadas) {
			chamadas[numChamadas] = new Chamada(duracao, data);

			numChamadas++;

			System.out.println("\nChamada feita!");
			
			return;
		}
		
		System.out.println("\nNúmero de chamadas excedido");
	}

	public void imprimirFatura(int mes) {

	}
}
