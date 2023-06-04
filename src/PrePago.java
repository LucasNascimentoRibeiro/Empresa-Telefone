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

			recargas[numRecargas] = novaRecarga;

			numRecargas++;

			creditos += valor;
		} else {
			System.out.println("Número de recargas excedido");
		}
	}

	@Override
	public void fazerChamada(GregorianCalendar data, int duracao) {
		if (10 > numChamadas) {
			float custo = (float) (duracao * 1.45);

			if (creditos > custo) {
				chamadas[numChamadas] = new Chamada(duracao, data);

				numChamadas++;

				creditos = creditos - custo;

				System.out.println("\nChamada feita!\n");

				return;
			}

			System.out.println("\nCreditos insuficientes");

			return;
		}

		System.out.println("Número de chamadas excedido");
	}

	@Override
	public void imprimirFatura(int mes, int ano)  {
		int qntdRecargasMes = 0;
		float totalValorRecarga = 0;
		String listaRecargas = "";
		if (numRecargas > 0) {
			for (int i = 0; i < numRecargas; i++) {
				Recarga recarga = recargas[i];

				int mesChamada = (recarga.getData().get(GregorianCalendar.MONTH) + 1);
				int anoChamada = (recarga.getData().get(GregorianCalendar.YEAR));

				if (mesChamada == mes && anoChamada == ano) {
					qntdRecargasMes++;

					totalValorRecarga += recarga.getValor();

					listaRecargas += "\n\n	" + qntdRecargasMes + "º Recarga:" + recarga.toString();
				}
			}
		}
		if (listaRecargas == "") {
			listaRecargas = "Nenhum.";
		}

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

					float valor = (float) (chamada.getDuracao() * 1.45);
					listaChamadas += "\n\n	" + qntdChamadasMes + "º Chamada:" + chamada.toString() 
						+ "\n			> " + "Valor: " + String.format("%.2f", valor);
				}
			}
		}
		if (listaChamadas == "") {
			listaChamadas = "Nenhum.";
		}

		float creditos = (totalValorRecarga - (float) (totalDuracaoChamadas * 1.45));

		System.out.println("\n- CPF: " + getCpf()
				+ "\n- Créditos: " + String.format("%.2f", creditos)
				+ "\n- Total Valor Recarga: " + String.format("%.2f", totalValorRecarga)
				+ "\n- Nº Recargas: " + qntdRecargasMes
				+ "\n- Lista de Recargas: " + listaRecargas
				+ "\n- Total Duração Chamadas: " + totalDuracaoChamadas
				+ "\n- Nº Chamadas: " + qntdChamadasMes
				+ "\n- Lista de Chamadas: " + listaChamadas);
	}
}