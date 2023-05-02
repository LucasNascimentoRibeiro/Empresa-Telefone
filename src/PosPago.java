import java.util.GregorianCalendar;

public class PosPago extends Assinante {

	private float assinatura;

	public PosPago(long cpf, String nome, int numero, float assinatura) {
		super(cpf, nome, numero);
		this.assinatura = assinatura;
	}
	
	public void FazerChamada(GregorianCalendar data, int duracao) {
		
	}
	
	public void ImprimirFatura(int mes) {
		
	}
	
}
