import java.util.GregorianCalendar;

public class PrePago extends Assinante {
	
	private Recarga[] recarga;
	private int numRecargas;
	private float creditos;
	
	public PrePago(long cpf, String nome, int numero, Recarga recarga) {
		super(cpf, nome, numero);
		this.recarga = new Recarga[10];
	}
	
	public void FazerChamada(GregorianCalendar data, int duracao) {
		
	}
	
	public void ImprimirFatura(int mes) {
		
	}
	
}