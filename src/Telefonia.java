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
	
	public void CadastrarAssinante() {
			Scanner scanner = new Scanner(System.in);
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			GregorianCalendar calendar = new GregorianCalendar();
			
			int tipo;
			do{
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
			} while(tipo !=1 && tipo !=2 );

			System.out.println("\nInforme o CPF do assinante(apenas números):");
			long cpf = scanner.nextLong();
			scanner.nextLine();

			System.out.println("\nInforme o nome do assinante:");
			String nome = scanner.nextLine();

			System.out.println("\nInforme o número do assinante:");
			int num = scanner.nextInt();
			scanner.nextLine();

			if(tipo == 1){
				System.out.println("\nInforme o valor da recarga:");
				float valorRecarga = scanner.nextFloat();
				scanner.nextLine();

				System.out.println("\nInforme a data da recarga (dd/MM/yyyy):");
				String dataStr = scanner.nextLine();
				
				Date data = null;
				boolean foi = false;

				while(!foi){
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

				Recarga recarga = new Recarga(calendar, valorRecarga);
				PrePago novoPrePago = new PrePago(cpf, nome, num,  recarga);
				prePagos[numPrePagos++] = novoPrePago;
				novoPrePago.Recarregar(calendar, valorRecarga);

			}else{
				System.out.println("\nInforme o valor da mensal da assinatura do cliente:");
				float assinatura = scanner.nextFloat();
				scanner.nextLine();

				PosPago novoPosPago = new PosPago(cpf, nome, num, assinatura);
				posPagos[numPosPagos++] = novoPosPago;
			}
		}
	
	public void ListarAssinantes() {
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
	
	public void FazerChamada() {
		Scanner scanner = new Scanner(System.in);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		GregorianCalendar calendar = new GregorianCalendar();
		boolean foiNull = false;
		do{
			int tipo;
				do{
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
				} while(tipo !=1 && tipo !=2 );

			System.out.println("Informe o CPF do assinante:");
			long cpf = scanner.nextLong();
			scanner.nextLine();

			if(tipo == 1){
				PrePago prePago = LocalizarPrePago(cpf);

				if(prePago != null){
					System.out.println("\nInforme a data da chamada (dd/MM/yyyy):");
					String dataStr = scanner.nextLine();
					
					Date data = null;
					boolean foi = false;

					while(!foi){
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

					prePago.FazerChamada(calendar, duracao);
				}else{
					System.out.println("O CPF não consta no registro de assinantes Pré-pago.");
					foiNull = true;
				}
			}else{			
				PosPago posPago = LocalizarPosPago(cpf);

				if(posPago != null){
					System.out.println("\nInforme a data da chamada (dd/MM/yyyy):");
					String dataStr = scanner.nextLine();
					
					Date data = null;
					boolean foi = false;

					while(!foi){
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

					posPago.FazerChamada(calendar, duracao);
				}else{
					System.out.println("O CPF não consta no registro de assinantes Pós-pago.");
					foiNull = true;
				}
			}	
		}while(foiNull == true);
	
	}
	
	public void FazerRecarga() {
		Scanner scanner = new Scanner(System.in);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		GregorianCalendar calendar = new GregorianCalendar();
		
		boolean foiNull = false;

		do{
			System.out.println("Informe o CPF do assinante:");
			long cpf = scanner.nextLong();
			String longbuffer = scanner.nextLine();

				PrePago prePago = LocalizarPrePago(cpf);

				if(prePago != null){
					System.out.println("\nInforme a data da recarga (dd/MM/yyyy):");
					String dataStr = scanner.nextLine();
					
					Date data = null;
					boolean foi = false;

					while(!foi){
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

					prePago.Recarregar(calendar, valor);
				}else{
					System.out.println("O CPF não consta no registro de assinantes Pré-pago.");
					foiNull = true;
				}
			
		}while(foiNull == true);
	}
	
	public void ImprimirFatura() {
		
	}

	private PrePago LocalizarPrePago(long cpf) {

		for (int i = 0; i < numPrePagos; i++){
			PrePago prePago = prePagos[i];

			if(cpf == prePago.getCpf()){
				return prePago;
			}
		}

		return null;
	}
	
	private PosPago LocalizarPosPago(long cpf) {

		for (int i = 0; i < numPosPagos; i++){
			PosPago posPago = posPagos[i];

			if(cpf == posPago.getCpf()){
				return posPago;
			}
		}

		return null;
		
	}

	public static void main(String[] args) {
		
		Telefonia telefonia = new Telefonia();

		Scanner scanner = new Scanner(System.in);
		
		for(int i = 0; i != 1;) {
			int aux;
			
			
			System.out.println("+---------------------------+");
			System.out.println("|         Telefonia         |");
			System.out.println("|                           |");
			System.out.println("| Informe a opção desejada: |");
			System.out.println("|                           |");
			System.out.println("| 1. Cadastrar assinantes   |");
			System.out.println("| 2. Listar assinantes      |");
			System.out.println("| 3. Fazer chamada          |");
			System.out.println("| 4. Fazer Recarga          |");
			System.out.println("| 5. Imprimir faturas       |");
			System.out.println("| 6. Sair do programa       |");
			System.out.println("|                           |");
			System.out.println("+---------------------------+");
			aux = scanner.nextInt();
			
			
			switch (aux) {
				case 1: telefonia.CadastrarAssinante();
					break;
				
				case 2: telefonia.ListarAssinantes();
					break;
				
				case 3: telefonia.FazerChamada();
					break;
				
				case 4: telefonia.FazerRecarga();
					break;
				
				case 5: telefonia.ImprimirFatura();
					break;
				
				case 6:
					System.exit(0);
					break;
				
				default:
					System.out.println("Opção inexistente, escolha uma das opções acima.");
					break;
			}
			
			System.out.println("Pressione Enter para continuar");
			scanner.nextLine();
			scanner.nextLine();
		}
		
		scanner.close();	
	}
	
}
