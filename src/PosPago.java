import java.util.GregorianCalendar;

public class PosPago extends Assinante {

	private float assinatura;

	public PosPago(long cpf, String nome, int numero, float assinatura) {
		super(cpf, nome, numero);
		this.assinatura = assinatura;
	}
	
	public void FazerChamada(GregorianCalendar data, int duracao) {
		if (numChamadas < 10) {
			Chamada[] chamadas = null;
			chamadas[numChamadas] = new Chamada(duracao, data);

			double custo = duracao * 1.04;
		}else{
			System.out.println("Número de chamadas excedido");
		}
		
}
	
	public void ImprimirFatura(int mes) {
		
	}
	
}
