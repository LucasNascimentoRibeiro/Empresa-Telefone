import java.util.GregorianCalendar;

public class PosPago extends Assinante {

	private float assinatura;

	public PosPago(long cpf, String nome, long numero, float assinatura) {
		super(cpf, nome, numero);
		this.assinatura = assinatura;
	}

	public void fazerChamada(GregorianCalendar data, int duracao) {
		if (10 > numChamadas) {
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

	
	public void imprimirFatura(int mes)  {

		int qntdChamadasMes = 0;
		int totalDuracaoChamadas = 0;
		String listaChamadas = "";
		if (numChamadas > 0) {
			for (int i = 0; i < numChamadas; i++) {
				Chamada chamada = chamadas[i];

				int mesChamada = (chamada.getData().get(GregorianCalendar.MONTH) + 1);

				if (mesChamada == mes) {
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

		System.out.println("\n- Nome: " + getNome()
				+ "\n- CPF: " + getCpf()
				+ "\n- Número: " + getNumero()
				+ "\n- Total Valor Chamadas: " + String.format("%.2f", totalFatura)
				+ "\n- Total Duração Chamadas: " + totalDuracaoChamadas
				+ "\n- Nº Chamadas: " + qntdChamadasMes
				+ "\n- Lista de Chamadas: " + listaChamadas);
	}
}
