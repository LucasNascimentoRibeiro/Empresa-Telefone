import java.util.GregorianCalendar;

public class PosPago extends Assinante {

	private float assinatura;

	public PosPago(long cpf, String nome, int numero, float assinatura) {
		super(cpf, nome, numero);
		this.assinatura = assinatura;
	}
	
	public void FazerChamada(GregorianCalendar data, int duracao) {
		
		if (numChamadas < 10) {
			chamadas[numChamadas++] = new Chamada(duracao, data);
			System.out.println("Chamada feita");
		}else{
			System.out.println("Número de chamadas excedido");
		}
		
}
	
	public void ImprimirFatura(int mes) {
		
	}
	
}
