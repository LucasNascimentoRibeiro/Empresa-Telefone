import java.util.GregorianCalendar;

public class PosPago extends Assinante {

	private float assinatura;

	public PosPago(long cpf, String nome, long numero, float assinatura) {
		super(cpf, nome, numero);
		this.assinatura = assinatura;
	}

	public void fazerChamada(GregorianCalendar data, int duracao) {
		if (10 > numChamadas) {
			int novoIndiceChamada = numChamadas + 1;
			
			chamadas[novoIndiceChamada] = new Chamada(duracao, data);

			System.out.println("Chamada feita!");
			
			return;
		}
		
		System.out.println("Número de chamadas excedido");
	}

	public void imprimirFatura(int mes) {

	}
}
