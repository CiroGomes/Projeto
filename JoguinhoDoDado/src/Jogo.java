import java.util.Random;
import java.util.Scanner;
public class Jogo {
	static Scanner leitor = new Scanner(System.in);
	public static void main(String[] args) {
		Random gerador = new Random();
		int vezes = 0, jogada = 0, vezesRestantes = 0, random = 0;
		String entrada = "";
		while (!entrada.equals("desistir") && !entrada.equals("não")) {
			System.out.println("Quantas vezes o dado deverá ser repetido para vencer essa rodada?");
			entrada = leitor.nextLine();
			if (entrada.equals("desistir")) {
				System.out.println("Adeus! Volte sempre para jogar mais!");
				System.exit(0);
			} else {
				vezes = Integer.parseInt(entrada);
				vezesRestantes = vezes;
			}
			System.out.println("Bem vindo ao Roll-" + vezes);
//			do {//NORMAL
			long i = 0;
			for(i = 0; vezesRestantes > 0; i++){
				System.out.println("Quanto você acha que vai sair no próximo dado?");
//				entrada = leitor.nextLine();//NORMAL
				if (entrada.equals("desistir")) {
					System.out.println("Adeus! Volte sempre para jogar mais!");
					System.exit(0);
				} else {
//					jogada = Integer.parseInt(entrada);//NORMAL
					jogada = gerador.nextInt(6)+1;//PREGUIÇA
				}
				System.out.println(jogada);//PREGUIÇA
				System.out.println("Rolando o dado...");
				random = gerador.nextInt(6) + 1;

				if (random == jogada)
					vezesRestantes--;
				else
					vezesRestantes = vezes;

				System.out.print(random + "! " + (random == jogada
						? (vezesRestantes == 0 ? " Você venceu!" : "Você acertou") : " Você errou"));
				System.out.println(vezesRestantes > 0 ? ", falta(m) " + vezesRestantes + " acerto(s). " : "");
//			} while (vezesRestantes > 0);//NORMAL
			}
			System.out.println("\nSequencia escolhida: " + vezes + "\nVezes jogadas: " + i);
			do {
				System.out.println("Deseja jogar de novo?");
				entrada = leitor.nextLine();
			} while (!(entrada.equals("não") || entrada.equals("sim")));
		}
		System.out.println("Adeus! Volte sempre para jogar mais!");
	}
}