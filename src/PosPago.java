import java.util.GregorianCalendar;

public class PosPago extends Assinante {

	private float assinatura;

	public PosPago(long cpf, String nome, long numero, float assinatura) {
		super(cpf, nome, numero);
		this.assinatura = assinatura;
	}

	@Override
	public void fazerChamada(GregorianCalendar data, int duracao) {
		int limiteChamada = 10;

		if (limiteChamada > numChamadas) {
			float custo = (float) (duracao * 1.04);

			if (assinatura > custo) {
				chamadas[numChamadas] = new Chamada(duracao, data);

				numChamadas++;

				assinatura = assinatura - custo;

				System.out.println("\nChamada feita!\n");

				return;
			}

			System.out.println("\nCreditos insuficientes");

			return;
		}

		System.out.println("\nNúmero de chamadas excedido");
	}

	@Override	
	public void imprimirFatura(int mes, int ano)  {

		int qntdChamadasMes = 0;
		int totalDuracaoChamadas = 0;
		String listaChamadas = "";
		if (numChamadas > 0) {
			for (int i = 0; i < numChamadas; i++) {
				Chamada chamada = chamadas[i];

				int mesChamada = (chamada.getData().get(GregorianCalendar.MONTH) + 1);
				int anoChamada = (chamada.getData().get(GregorianCalendar.YEAR));

				if (mesChamada == mes && anoChamada == ano) {
					qntdChamadasMes++;

					totalDuracaoChamadas += chamada.getDuracao();

					float valor = (float) (chamada.getDuracao() * 1.04);
					listaChamadas += "\n\n	" + qntdChamadasMes + "º Chamada:" + chamada.toString() 
						+ "\n			> " + "Valor: " + String.format("%.2f", valor);
				}
			}
		}
		if (listaChamadas == "") {
			listaChamadas = "Nenhum.";
		}

		float totalFatura = (float) (totalDuracaoChamadas * 1.04);

		System.out.println("\n- CPF: " + getCpf()
				+ "\n- Total Valor Chamadas: " + String.format("%.2f", totalFatura)
				+ "\n- Total Duração Chamadas: " + totalDuracaoChamadas
				+ "\n- Nº Chamadas: " + qntdChamadasMes
				+ "\n- Lista de Chamadas: " + listaChamadas);
	}
}
