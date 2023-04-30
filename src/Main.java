import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

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
				case 1:
					break;
				
				case 2:
					break;
				
				case 3:
					break;
				
				case 4:
					break;
				
				case 5:
					break;
				
				case 6:
					System.exit(0);
					break;
				
				default:
					System.out.println("Opção inexistente, escolha uma das opções acima.");
					break;
			}
			
			
			
			
			System.out.println("Pressione qualquer tecla para continuar");
			scanner.nextInt();
			
		}

		scanner.close();

	}

}
