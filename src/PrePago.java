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
				int novoIndiceChamada = numChamadas + 1;

				chamadas[novoIndiceChamada] = new Chamada(duracao, data);

				creditos = creditos - custo;

				System.out.println("Chamada feita!\n");
				System.out.printf("Credito Atual: %.2f \n", creditos);

				return;
			} 

			System.out.println("Creditos insuficientes");

			return;
		} 

		System.out.println("Número de chamadas excedido");
	}

	public void imprimirFatura(int mes) {

	}
}