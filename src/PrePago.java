import java.util.GregorianCalendar;

public class PrePago extends Assinante {

	private float creditos;
	private int numRecargas = 0;
	private Recarga[] recargas;

	public PrePago(long cpf, String nome, int numero, Recarga recargas) {
		super(cpf, nome, numero);
		this.recargas = new Recarga[10];
	}

	public void recarregar(GregorianCalendar data, float valor) {
		if (numRecargas < 10) {
			Recarga novaRecarga = new Recarga(data, valor);
			recargas[numRecargas++] = novaRecarga;

			creditos += valor;
		} else {
			System.out.println("Número de recargas excedido");
		}
	}

	public void fazerChamada(GregorianCalendar data, int duracao) {
		if (numChamadas < 10) {
			double custo = duracao * 1.45;

			if (custo > creditos) {
				System.out.println("Creditos insuficientes");
			} else {
				chamadas[numChamadas++] = new Chamada(duracao, data);
				creditos -= custo;
				System.out.println("Chamada feita");
				System.out.printf("Credito Atual: %.2f \n", creditos);
			}
		} else {
			System.out.println("Número de chamadas excedido");
		}
	}

	public void imprimirFatura(int mes) {

	}
}