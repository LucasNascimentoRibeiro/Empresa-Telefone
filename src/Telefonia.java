import java.sql.PreparedStatement;
import java.util.GregorianCalendar;
import java.util.Scanner;

import utils.Utilidades;

public class Telefonia {

	private Assinante[] assinantes;
	private int numAssinantes = 0;

	public Telefonia() {
		this.assinantes = new Assinante[10];
	}

	public void cadastrarAssinante(Assinante assinante) {
		limparTela();

		Scanner scanner = new Scanner(System.in);

		while (true) {
			limparTela();

			System.out.println("+--------------------------+");
			System.out.println("|                          |");
			System.out.println("|     Selecione o tipo     |");
			System.out.println("|       de Assinatura      |");
			System.out.println("|                          |");
			System.out.println("|   1. Pré-pago            |");
			System.out.println("|   2. Pós-pago            |");
			System.out.println("|   3. Voltar              |");
			System.out.println("|                          |");
			System.out.println("+--------------------------+");

			System.out.print("Digite sua opção: ");
			int tipo = scanner.nextInt();

			// Solicitar informações do cliente
			if (tipo == 1 || tipo == 2) {
				System.out.print("\nInforme o CPF do assinante (apenas números): ");
				long cpf = scanner.nextLong();
				scanner.nextLine();

				System.out.print("\nDigite o NOME do assinante: ");
				String nome = scanner.nextLine();

				System.out.print("\nDigite o NÚMERO do assinante (apenas números): ");
				long numero = scanner.nextLong();

				// Cadastrar Pré-Pago
				if (tipo == 1) {
					PrePago novoPrePago = new PrePago(cpf, nome, numero);
					assinante = novoPrePago;
				}

				// Cadastrar Pós-Pago
				if (tipo == 2) {
					System.out.print("\nDigite o VALOR da mensalidade: ");
					float assinatura = scanner.nextFloat();

					PosPago novoPosPago = new PosPago(cpf, nome, numero, assinatura);
					assinante = novoPosPago;
				}

				assinantes[numAssinantes] = assinante;
				numAssinantes++;

				System.out.println("\nAssinante cadastrado com sucesso!");

				break;
			} else if (tipo == 3) {
				// Voltar
				break;
			} else {
				// Avisa quando o valor for incorreto
				avisarOpcaoIncorreta();
				avisarPressione(scanner);
			}
		}
	}

	public void listarAssinantes() {
		PrePago[] prePagos = new PrePago[10];
		int numPrePagos = 0;

		PosPago[] posPagos = new PosPago[10];
		int numPosPagos = 0;

		for (int i = 0; i < numAssinantes; i++) {
			Assinante assinante = assinantes[i];

			try {
				PrePago prePago = (PrePago) assinante;
				prePagos[numPrePagos] = prePago;
				numPrePagos++;
			} catch (Exception e) {
			}

			try {
				PosPago posPago = (PosPago) assinante;
				posPagos[numPosPagos] = posPago;
				numPosPagos++;
			} catch (Exception e) {
			}
		}

		System.out.print("Assinantes de Pré-Pago: ");
		boolean prePagosVazio = Utilidades.verificarListaVazia(prePagos);
		if (prePagosVazio) {
			System.out.println("Nenhum.");
		} else {
			for (int i = 0; i < numPrePagos; i++) {
				PrePago prePago = prePagos[i];

				System.out.println("\n	" + (i + 1) + "º " + "Assinante:" + prePago.toString());
			}
		}

		System.out.print("\nAssinantes de Pós-Pago: ");
		boolean posPagosVazio = Utilidades.verificarListaVazia(posPagos);
		if (posPagosVazio) {
			System.out.println("Nenhum.");
		} else {
			for (int i = 0; i < numPosPagos; i++) {
				PosPago posPago = posPagos[i];
				System.out.println("\n	" + (i + 1) + "º " + "Assinante:" + posPago.toString());
			}
		}
	}

	public void fazerChamada(Assinante assinante) {
		Scanner scanner = new Scanner(System.in);

		GregorianCalendar calendario = new GregorianCalendar();

		// Informar DIA
		System.out.println("\nDigite o DIA da chamada (Ex.: 01):");
		int dia = scanner.nextInt();
		calendario.set(GregorianCalendar.DATE, dia);

		// Informar MÊS
		System.out.println("\nDigite o MÊS da chamada (Ex.: 06):");
		int mes = scanner.nextInt();
		calendario.set(GregorianCalendar.MONTH, (mes - 1));

		// Informar ANO
		System.out.println("\nDigite o ANO da chamada (Ex.: 2023):");
		int ano = scanner.nextInt();
		calendario.set(GregorianCalendar.YEAR, ano);

		// Informar MINUTO
		System.out.println("\nDigite o MINUTO da duração da chamada (Ex.: 5):");
		int duracao = scanner.nextInt();

		assinante.fazerChamada(calendario, duracao);
	}

	public void fazerRecarga(PrePago prePago) {
		Scanner scanner = new Scanner(System.in);

		GregorianCalendar calendario = new GregorianCalendar();

		// Informar DIA
		System.out.println("\nDigite o DIA da recarga (Ex.: 01):");
		int dia = scanner.nextInt();
		calendario.set(GregorianCalendar.DATE, dia);

		// Informar MÊS
		System.out.println("\nDigite o MÊS da recarga (Ex.: 06):");
		int mes = scanner.nextInt();
		calendario.set(GregorianCalendar.MONTH, (mes - 1));

		// Informar ANO
		System.out.println("\nDigite o ANO da recarga (Ex.: 2023):");
		int ano = scanner.nextInt();
		calendario.set(GregorianCalendar.YEAR, ano);

		// Informar VALOR
		System.out.println("\nInforme o VALOR da recarga");
		float valor = scanner.nextFloat();

		prePago.recarregar(calendario, valor);
	}

	public void imprimirFaturas() {
		try {
			Scanner scanner = new Scanner(System.in);

			limparTela();

			// Informar MÊS
			System.out.println("\nDigite o MÊS da fatura (Ex.: 06):");
			int mes = scanner.nextInt();

			// Informar ANO
			System.out.println("\nDigite o ANO da fatura (Ex.: 2023):");
			int ano = scanner.nextInt();

			PrePago[] prePagos = new PrePago[10];
			int numPrePagos = 0;

			PosPago[] posPagos = new PosPago[10];
			int numPosPagos = 0;

			for (int i = 0; i < numAssinantes; i++) {
				Assinante assinante = assinantes[i];

				try {
					PrePago prePago = (PrePago) assinante;
					prePagos[numPrePagos] = prePago;
					numPrePagos++;
				} catch (Exception e) {
				}

				try {
					PosPago posPago = (PosPago) assinante;
					posPagos[numPosPagos] = posPago;
					numPosPagos++;
				} catch (Exception e) {
				}
			}

			System.out.print("Assinantes de Pré-Pago: ");
			boolean prePagosVazio = Utilidades.verificarListaVazia(prePagos);
			if (prePagosVazio) {
				System.out.println("Nenhum.");
			} else {
				for (int i = 0; i < numPrePagos; i++) {
					PrePago prePago = prePagos[i];
					prePago.imprimirFatura(mes, ano);
				}
			}

			System.out.print("\nAssinantes de Pós-Pago: ");
			boolean posPagosVazio = Utilidades.verificarListaVazia(posPagos);
			if (posPagosVazio) {
				System.out.println("Nenhum.");
			} else {
				for (int i = 0; i < numPosPagos; i++) {
					PosPago posPago = posPagos[i];
					posPago.imprimirFatura(mes, ano);
				}
			}

		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}

	private Assinante localizarAssinante(long cpf) {
		for (int i = 0; i < numAssinantes; i++) {
			Assinante assinante = assinantes[i];

			if (cpf == assinante.getCpf()) {
				return assinante;
			}
		}

		return null;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		try {
			Telefonia telefonia = new Telefonia();

			while (true) {
				exibirMenu();

				navegarMenu(telefonia, scanner);
			}
		} catch (Exception e) {
			avisarErroInterno(scanner);
			main(args);
		}
	}

	// FUNÇÕES DE ORGANIZAÇÃO
	private static void exibirMenu() {
		limparTela();

		System.out.println("\033[1;34m");
		System.out.println("+-------------------------------+");
		System.out.println("|                               |");
		System.out.println("|           TELEFONIA           |");
		System.out.println("|                               |");
		System.out.println("|   Informe a opção desejada:   |");
		System.out.println("|                               |");
		System.out.println("|" + "\033[1;35m" + "    1. " + "\033[1;34m" + "Cadastrar assinantes    |");
		System.out.println("|" + "\033[1;35m" + "    2. " + "\033[1;34m" + "Listar assinantes       |");
		System.out.println("|" + "\033[1;35m" + "    3. " + "\033[1;34m" + "Fazer chamada           |");
		System.out.println("|" + "\033[1;35m" + "    4. " + "\033[1;34m" + "Fazer recarga           |");
		System.out.println("|" + "\033[1;35m" + "    5. " + "\033[1;34m" + "Imprimir faturas        |");
		System.out.println("|" + "\033[1;35m" + "    6. " + "\033[1;34m" + "Sair do programa        |");
		System.out.println("|                               |");
		System.out.println("+-------------------------------+");
		System.out.print("\033[1;37m \nDigite sua opção: " + "\033[0m");
	}

	private static void navegarMenu(Telefonia telefonia, Scanner scanner) {
		int menuSelecionado = scanner.nextInt();

		limparTela();

		// Validar valor digitado;
		switch (menuSelecionado) {

			// Cadastrar Assinantes
			case 1:
				Assinante cadastrarAssinante = telefonia.assinantes[telefonia.numAssinantes];
				telefonia.cadastrarAssinante(cadastrarAssinante);
				break;

			// Listar Assinantes
			case 2:
				telefonia.listarAssinantes();
				break;

			// Fazer Chamada
			case 3:
				long chamadaCpf = inserirCPF(scanner);
				Assinante chamadaAssinante = telefonia.localizarAssinante(chamadaCpf);
				telefonia.fazerChamada(chamadaAssinante);
				break;

			// Fazer Recarga
			case 4:
				long recargaCpf = inserirCPF(scanner);
				Assinante recargaAssinante = telefonia.localizarAssinante(recargaCpf);
				PrePago prePago = (PrePago) recargaAssinante;
				telefonia.fazerRecarga(prePago);
				break;

			// Imprimir Faturas
			case 5:
				telefonia.imprimirFaturas();
				break;

			// Sair do Programa
			case 6:
				scanner.close();
				encerrarPrograma();
				break;

			// Avisa quando o valor for incorreto
			default:
				avisarOpcaoIncorreta();
				break;
		}

		avisarPressione(scanner);
	}

	private static void limparTela() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	private static void avisarPressione(Scanner scanner) {
		System.out.println("\033[1;37m" + "\nPressione" + "\033[1;35m" + " ENTER " + "\033[1;37m"
				+ "para continuar!" + "\033[0m");

		scanner.nextLine();
		scanner.nextLine();
	}

	private static void avisarErroInterno(Scanner scanner) {
		System.out.println("\033[1;37m" + "\nHouve um erro interno no sistema!");
		System.out.println("Contate nosso suporte sobre o problema: " + "\033[1;35m" + "+55 (13) 9 8223-4569");
		System.out.println("\033[1;37m" + "\nPressione" + "\033[1;35m" + " ENTER " + "\033[1;37m"
				+ "para continuar!" + "\033[0m");

		scanner.nextLine();
		scanner.nextLine();
	}

	private static void avisarOpcaoIncorreta() {
		System.out.println("\033[1;33m" + "\nAviso " + "\033[1;37m" + "do sistema:");
		System.out.println("\033[1;33m" + "> " + "\033[0m" + "Opção Incorreta...");
		System.out.println("\033[1;33m" + "> " + "\033[0m" + "Verifique a opção inserida\nde acordo com o "
				+ "\033[1;32m" + "menu acima" + "\033[0m");
	}

	private static void encerrarPrograma() {
		System.out.println("Encerrando programa...");
		System.exit(0);
	}

	private static long inserirCPF(Scanner scanner) {
		System.out.print("\nInforme o CPF do assinante(apenas números): ");
		long cpf = scanner.nextLong();
		scanner.nextLine();
		return cpf;
	}
}
