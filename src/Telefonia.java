import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

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
		// Limpar Tela
		System.out.print("\033[H\033[2J");
		System.out.flush();

		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("+---------------------------+");
			System.out.println("|                           |");
			System.out.println("|     Selecione o tipo      |");
			System.out.println("|       de Assinatura       |");
			System.out.println("|                           |");
			System.out.println("|   1. Pré-pago             |");
			System.out.println("|   2. Pós-pago             |");
			System.out.println("|                           |");
			System.out.println("+---------------------------+");

			System.out.print("Digite sua opção: ");
			int tipo = scanner.nextInt();

			// Solicitar informações do cliente
			if (tipo == 1 || tipo == 2) {
				System.out.print("\nInforme o CPF do assinante(apenas números): ");
				long cpf = scanner.nextLong();
				scanner.nextLine();

				System.out.print("\nInforme o nome do assinante: ");
				String nome = scanner.nextLine();

				System.out.print("\nInforme o número do assinante: ");
				int num = scanner.nextInt();

				switch (tipo) {
					case 1: // Cadastrar Pós-Pago
						PrePago novoPrePago = new PrePago(cpf, nome, num);
						prePagos[numPrePagos++] = novoPrePago;
						break;
					case 2: // Cadastrar Pré-Pago
						System.out.print("\nInforme o valor da mensal da assinatura do cliente: ");
						float assinatura = scanner.nextFloat();

						PosPago novoPosPago = new PosPago(cpf, nome, num, assinatura);
						posPagos[numPosPagos++] = novoPosPago;
						break;
				}

				System.out.println("\nAssinante cadastrado com sucesso!");

				break;
			} else if (tipo == 3) {
				// Voltar
				// Adicionar opção de voltar
			} else {
				// Avisa quando o valor for incorreto
				// Adicionar aviso de opção incorreta
			}
		}
	}

	public void listarAssinantes() {
		System.out.println("Assinantes de Pré-Pago:");
		for (int i = 0; i < numPrePagos; i++) {
			PrePago prePago = prePagos[i];
			System.out.println(prePago.toString());
		}

		System.out.println("Assinantes de Pós-Pago:");
		for (int i = 0; i < numPosPagos; i++) {
			PosPago posPago = posPagos[i];
			System.out.println(posPago.toString());
		}
	}

	public void fazerChamada() {
		Scanner scanner = new Scanner(System.in);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		GregorianCalendar calendar = new GregorianCalendar();
		boolean foiNull = false;
		do {
			int tipo;
			do {
				System.out.println("+---------------------------+");
				System.out.println("|                           |");
				System.out.println("|     Selecione o tipo      |");
				System.out.println("|       de Assinatura       |");
				System.out.println("|                           |");
				System.out.println("|   1. Pré-pago             |");
				System.out.println("|   2. Pós-pago             |");
				System.out.println("|                           |");
				System.out.println("+---------------------------+");
				tipo = scanner.nextInt();
			} while (tipo != 1 && tipo != 2);

			System.out.println("Informe o CPF do assinante:");
			long cpf = scanner.nextLong();
			scanner.nextLine();

			if (tipo == 1) {
				PrePago prePago = localizarPrePago(cpf);

				if (prePago != null) {
					System.out.println("\nInforme a data da chamada (dd/MM/yyyy):");
					String dataStr = scanner.nextLine();

					Date data = null;
					boolean foi = false;

					while (!foi) {
						try {
							data = dateFormat.parse(dataStr);
							foi = true;
						} catch (ParseException e) {
							System.out.println("\nA data não foi informada no formato correto");
							System.out.println("Por favor, informe a data da chamada no formato dd/MM/yyyy:");
							dataStr = scanner.nextLine();
						}
					}

					calendar.setTime(data);

					System.out.println("\nInforme a duração da chamada em minutos:");
					int duracao = scanner.nextInt();
					scanner.nextLine();

					prePago.fazerChamada(calendar, duracao);
				} else {
					System.out.println("O CPF não consta no registro de assinantes Pré-pago.");
					foiNull = true;
				}
			} else {
				PosPago posPago = localizarPosPago(cpf);

				if (posPago != null) {
					System.out.println("\nInforme a data da chamada (dd/MM/yyyy):");
					String dataStr = scanner.nextLine();

					Date data = null;
					boolean foi = false;

					while (!foi) {
						try {
							data = dateFormat.parse(dataStr);
							foi = true;
						} catch (ParseException e) {
							System.out.println("\nA data não foi informada no formato correto");
							System.out.println("Por favor, informe a data da chamada no formato dd/MM/yyyy:");
							dataStr = scanner.nextLine();
						}
					}

					calendar.setTime(data);

					System.out.println("\nInforme a duração em minutos da chamada:");
					int duracao = scanner.nextInt();
					scanner.nextLine();

					posPago.fazerChamada(calendar, duracao);
				} else {
					System.out.println("O CPF não consta no registro de assinantes Pós-pago.");
					foiNull = true;
				}
			}
		} while (foiNull == true);

	}

	public void fazerRecarga() {
		Scanner scanner = new Scanner(System.in);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		GregorianCalendar calendar = new GregorianCalendar();

		boolean foiNull = false;

		do {
			System.out.println("Informe o CPF do assinante:");
			long cpf = scanner.nextLong();
			String longbuffer = scanner.nextLine();

			PrePago prePago = localizarPrePago(cpf);

			if (prePago != null) {
				System.out.println("\nInforme a data da recarga (dd/MM/yyyy):");
				String dataStr = scanner.nextLine();

				Date data = null;
				boolean foi = false;

				while (!foi) {
					try {
						data = dateFormat.parse(dataStr);
						foi = true;
					} catch (ParseException e) {
						System.out.println("\nA data não foi informada no formato correto");
						System.out.println("Por favor, informe a data da recarga no formato dd/MM/yyyy:");
						dataStr = scanner.nextLine();
					}
				}

				calendar.setTime(data);

				System.out.println("\nInforme o valor da recarga");
				float valor = scanner.nextFloat();
				scanner.nextLine();

				prePago.recarregar(calendar, valor);
			} else {
				System.out.println("O CPF não consta no registro de assinantes Pré-pago.");
				foiNull = true;
			}

		} while (foiNull == true);
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

		Telefonia telefonia = new Telefonia();

		Scanner scanner = new Scanner(System.in);

		while (true) {
			// Limpar Tela
			System.out.print("\033[H\033[2J");
			System.out.flush();

			// Exibir Menu
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
			System.out.println("|" + "\033[1;35m" + "    4. " + "\033[1;34m" + "Fazer Recarga           |");
			System.out.println("|" + "\033[1;35m" + "    5. " + "\033[1;34m" + "Imprimir faturas        |");
			System.out.println("|" + "\033[1;35m" + "    6. " + "\033[1;34m" + "Sair do Programa        |");
			System.out.println("|                               |");
			System.out.println("+-------------------------------+");

			System.out.print("\033[1;37m \nDigite sua opção: " + "\033[0m");
			int aux = scanner.nextInt(); // Pegar valor inteiro digitado

			// Validar valor digitado;
			switch (aux) {
				case 1: // Cadastrar Assinantes
					telefonia.cadastrarAssinante();
					break;
				case 2: // Listar Assinantes
					telefonia.listarAssinantes();
					break;
				case 3: // Fazer Chamada
					telefonia.fazerChamada();
					break;
				case 4: // Fazer Recarga
					telefonia.fazerRecarga();
					break;
				case 5: // Imprimir Faturas
					telefonia.imprimirFatura();
					break;
				case 6: // Sair do Programa
					scanner.close();
					System.exit(0);
					break;
				default: // Avisa quando o valor for incorreto
					System.out.println("\033[1;33m" + "\nAviso " + "\033[1;37m" + "do sistema:");
					System.out.println("\033[1;33m" + "> " + "\033[0m" + "Opção Incorreta...");
					System.out.println("\033[1;33m" + "> " + "\033[0m" + "Escolha uma das opções de " + "\033[1;32m"
							+ "1 a 6!" + "\033[0m");
					System.out.println("\033[1;37m" + "\nPressione" + "\033[1;35m" + " ENTER " + "\033[1;37m"
							+ "para tentar novamente!" + "\033[0m");
					break;
			}

			scanner.nextLine();
			scanner.nextLine();
		}
	}
}
