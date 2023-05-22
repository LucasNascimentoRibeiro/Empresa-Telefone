import java.util.GregorianCalendar;
import java.util.Scanner;

import utils.Utilidades;

public class Telefonia {

	private PrePago[] prePagos;
	private int numPrePagos = 0;

	private PosPago[] posPagos;
	private int numPosPagos = 0;

	public Telefonia() {
		this.prePagos = new PrePago[10];
		this.posPagos = new PosPago[10];
	}

	public void cadastrarAssinante() {
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
					prePagos[numPrePagos] = novoPrePago;

					numPrePagos++;
				}

				// Cadastrar Pós-Pago
				if (tipo == 2) {
					// Informar o VALOR
					System.out.print("\nDigite o VALOR da mensalidade: ");
					float assinatura = scanner.nextFloat();

					PosPago novoPosPago = new PosPago(cpf, nome, numero, assinatura);
					posPagos[numPosPagos] = novoPosPago;
					
					numPosPagos++;
				}

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

	public void fazerChamada() {
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
				System.out.print("\nInforme o CPF do assinante(apenas números): ");
				long cpf = scanner.nextLong();
				scanner.nextLine();

				PrePago prePago = null;
				PosPago posPago = null;

				// Localizar Pré-Pago
				if (tipo == 1) {
					prePago = localizarPrePago(cpf);

					if (prePago == null) {
						System.out.println("\nO CPF não consta no registro de assinantes Pré-Pago.");
						avisarPressione(scanner);
						continue;
					}
				}

				// Localizar Pós-Pago
				if (tipo == 2) {
					posPago = localizarPosPago(cpf);

					if (posPago == null) {
						System.out.println("\nO CPF não consta no registro de assinantes Pós-Pago.");
						avisarPressione(scanner);
						continue;
					}
				}

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

				// Validar calendário

				// Cadastrar Pré-Pago
				if (tipo == 1) {
					prePago.fazerChamada(calendario, duracao);
				}

				// Cadastrar Pos-Pago
				if (tipo == 2) {
					posPago.fazerChamada(calendario, duracao);
				}

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

	public void fazerRecarga() {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("+-------------------------+");
			System.out.println("|                         |");
			System.out.println("|     Selecione o tipo    |");
			System.out.println("|       de Assinatura     |");
			System.out.println("|                         |");
			System.out.println("|   1. Pré-pago           |");
			System.out.println("|   2. Voltar             |");
			System.out.println("|                         |");
			System.out.println("+-------------------------+");

			System.out.print("Digite sua opção: ");
			int tipo = scanner.nextInt();

			switch (tipo) {
				// Solicitar informações do cliente PRÉ-PAGO
				case 1:
					System.out.print("\nInforme o CPF do assinante(apenas números): ");
					long cpf = scanner.nextLong();
					scanner.nextLine();

					PrePago prePago = localizarPrePago(cpf);

					if (prePago == null) {
						System.out.println("\nO CPF não consta no registro de assinantes Pré-Pago.");
						break;
					}

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

					break;

				// Voltar
				case 2:
					break;

				// Avisa quando o valor for incorreto
				default:
					avisarOpcaoIncorreta();
					avisarPressione(scanner);
					break;
			}

			break;
		}
	}

	private PrePago localizarPrePago(long cpf) {
		for (int i = 0; i < numPrePagos; i++) {
			PrePago prePago = prePagos[i];

			if (cpf == prePago.getCpf()) {
				return prePago;
			}
		}

		return null;
	}

	private PosPago localizarPosPago(long cpf) {
		for (int i = 0; i < numPosPagos; i++) {
			PosPago posPago = posPagos[i];

			if (cpf == posPago.getCpf()) {
				return posPago;
			}
		}

		return null;
	}

	public void imprimirFatura() {

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
				telefonia.cadastrarAssinante();
				break;

			// Listar Assinantes
			case 2:
				telefonia.listarAssinantes();
				break;

			// Fazer Chamada
			case 3:
				telefonia.fazerChamada();
				break;

			// Fazer Recarga
			case 4:
				telefonia.fazerRecarga();
				break;

			// Imprimir Faturas
			case 5:
				telefonia.imprimirFatura();
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

}
