import java.util.GregorianCalendar;

public class PrePago extends Assinante {

	private float creditos;
	private int numRecargas = 0;
	private Recarga[] recargas;

	public PrePago(long cpf, String nome, long numero) {
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
		if (10 > numChamadas) {
			float custo = (float) (duracao * 1.45);

			if (creditos > custo) {
				chamadas[numChamadas] = new Chamada(duracao, data);

				numChamadas++;
				
				creditos = creditos - custo;

				System.out.println("\nChamada feita!\n");
				System.out.printf("Credito Atual: %.2f \n", creditos);

				return;
			} 

			System.out.println("\nCreditos insuficientes");

			return;
		} 

		System.out.println("Número de chamadas excedido");
	}

	public void imprimirFatura(int mes) {

	}
}