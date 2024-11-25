import org.w3c.dom.ls.LSOutput;

import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static Scanner read = new Scanner(System.in);

    public static int pontosJogador1 = 5, pontosJogador2 = 5;

    public static void main(String[] args) {


        String jogador1 = "Alek", jogador2 = "Luca";
        Set<String> palavrasUsadas = new HashSet<>();
        String palavraJogador1 = "", palavraJogador2 = "";
        char ultimaLetraJogador1 = ' ', primeiraLetraJogador1 = ' ', ultimaLetraJogador2 = ' ', primeiraLetraJogador2 = ' ', letraValida = ' ';
        int menu = 1, round = 1;
        boolean palavraValida = true;


        System.out.println(ANSI_CYAN + "Seja Bem Vindo Ao Jogo Das Palavras: " + ANSI_RESET);

        do {
            Tela.escrever("[1] - Digite 1 para Começar o jogo.");
            Tela.escrever("[2] - Digite 2 para ver as regras do jogo.");
            Tela.escrever("[0] - Digite 0 para Encerrar o jogo.");
            menu = read.nextInt();
            read.nextLine();

            if (menu == 0) {
                break;
            } else if (menu == 2) {
                Tela.escrever(ANSI_CYAN + "Regras Do Jogo Das Palavras: " + ANSI_RESET);
                Tela.escrever("1. Os jogadores se revezam para digitar uma palavra.");
                Tela.escrever("2. O primeiro jogador irá digitar uma palavra, o segundo jogador terá que digitar uma outra palavra que comece com a última letra da palavra do primeiro jogador.\n" + ANSI_BLACK_BACKGROUND + ANSI_CYAN + "EXEMPLO:" + ANSI_RESET + ANSI_CYAN + "\nJogador 1: Posta" + ANSI_RESET + ANSI_CYAN + "\nJogador 2: Alface" + ANSI_RESET);
                Tela.escrever("3. As palavras não podem ser repetidas.");
                Tela.escrever("4. Cada jogador irá começar com 5 pontos.");
                Tela.escrever("5. O jogo termina quando um dos jogadores não tiver mais pontos.");
                Tela.escrever("Presione Enter para continuar...");
                read.nextLine();
                continue;
            }

            jogador1 = jogadores(jogador1);
            jogador2 = jogadores2(jogador2);
            System.out.println();
            Tela.escrever("Jogo Iniciado :");
            System.out.println();

            do {
                System.out.printf(ANSI_BLUE + "ROUND %d%n" + ANSI_RESET, round);

                // Jogador 1
                System.out.printf(ANSI_GREEN + "%s " + ANSI_RESET + "sua vez de jogar!%nDigite uma palavra: ", jogador1);
                long horaInicial = System.currentTimeMillis();
                palavraJogador1 = read.nextLine().toLowerCase();
                primeiraLetraJogador1 = palavraJogador1.charAt(0);
                ultimaLetraJogador1 = palavraJogador1.charAt(palavraJogador1.length() - 1);


                // Após o primeiro round verifica se a Primeira Letra do Jogador 1 é válida
                if (round > 1) {
                    if (letraValida != primeiraLetraJogador1) {
                        System.out.printf("Palavras não correspondem " + ANSI_RED + "%s PERDEU 2 PONTOS!%n" + ANSI_RESET, jogador1);
                        palavraValida = false;
                        pontosJogador1 -= 2;
                    } else {
                        palavraValida = true;
                    }
                }

                // Verifica se a palavra já existe
                if (palavraValida) {
                    if (palavrasUsadas.contains(palavraJogador1)) {
                        System.out.printf(ANSI_YELLOW + "PALAVRA JÁ FOI USADA! %s PERDEU 1 PONTO!%n" + ANSI_RESET, jogador1);
                        palavraValida = false;
                        pontosJogador1--;
                        if (pontosJogador1 <= 0) {
                            break;
                        }
                    } else {
                        palavrasUsadas.add(palavraJogador1);
                    }
                }

                if (palavraValida) {
                    letraValida = ultimaLetraJogador1;
                }

                long horaFinal = System.currentTimeMillis();
                long diferenca = (horaFinal - horaInicial) / 1000;

                if (diferenca > 20) {
                    System.out.printf(ANSI_RED + "%s PERDEU 1 PONTO" + ANSI_RESET + ", ultrapassou o tempo, usando % d segundos para digitar.%n", jogador1, diferenca);
                    pontosJogador1--;
                } else {
                    System.out.printf(ANSI_GREEN + "%s" + ANSI_RESET + " demorou %d segundos para digitar.%n", jogador1, diferenca);
                }

                Tela.escrever("---------------------");
                System.out.printf(ANSI_CYAN + "Letra da vez = %s%n" + ANSI_RESET, letraValida);

                // Jogador 2
                System.out.printf(ANSI_GREEN + "%s " + ANSI_RESET + "sua vez jogar!%nDigite uma palavra: ", jogador2);
                long horaInicialJogador2 = System.currentTimeMillis();
                palavraJogador2 = read.nextLine().toLowerCase();
                primeiraLetraJogador2 = palavraJogador2.charAt(0);
                ultimaLetraJogador2 = palavraJogador2.charAt(palavraJogador2.length() - 1);

                // Verificar se a ultima letra do P1 é difente da primeira letra do P2
                if (letraValida != primeiraLetraJogador2) {
                    System.out.printf("Palavras não correspondem " + ANSI_RED + "%s PERDEU 2 PONTOS!%n" + ANSI_RESET, jogador2);
                    palavraValida = false;
                    pontosJogador2 -= 2;
                    if (pontosJogador2 <= 0) {
                        break;
                    }
                } else {
                    palavraValida = true;
                }

                if (palavraValida) {
                    if (palavrasUsadas.contains(palavraJogador2)) {
                        System.out.printf(ANSI_YELLOW + "PALAVRA JÁ FOI USADA! %s PERDEU 1 PONTO!%n" + ANSI_RESET, jogador2);
                        palavraValida = false;
                        pontosJogador2--;
                        if (pontosJogador2 <= 0) {
                            break;
                        }
                    } else {
                        palavrasUsadas.add(palavraJogador2);
                    }
                }

                if (palavraValida) {
                    letraValida = ultimaLetraJogador2;
                }

                long horaFinalJogador2 = System.currentTimeMillis();
                long diferencaJogador2 = (horaFinalJogador2 - horaInicialJogador2) / 1000;

                if (diferencaJogador2 > 20) {
                    System.out.printf(ANSI_RED + "%s PERDEU 1 PONTO" + ANSI_RESET + ", pois ultrapassou o tempo, usando % d segundos para digitar.%n", jogador2, diferencaJogador2);
                    pontosJogador2--;
                } else {
                    System.out.printf(ANSI_GREEN + "%s" + ANSI_RESET + " demorou %d segundos para digitar.%n", jogador2, diferencaJogador2);
                }

                System.out.println();
                System.out.printf(ANSI_PURPLE + "PONTUAÇÃO:" + ANSI_RESET);
                System.out.printf(ANSI_CYAN + "%n%s\t" + ANSI_RESET + "Sua Pontuação: %d%n", jogador1, pontosJogador1);
                System.out.printf(ANSI_CYAN + "%s\t" + ANSI_RESET + "Sua Pontuação: %d%n%n", jogador2, pontosJogador2);
                System.out.printf(ANSI_CYAN + "Letra da vez = %s%n", letraValida);
                round++;

            } while (pontosJogador1 >= 0 && pontosJogador2 >= 0);

            campeao(pontosJogador1, pontosJogador2, jogador1, jogador2);

            break;
        } while (menu != 0);
        if (menu == 0) {
            System.out.println(ANSI_BLACK + ANSI_CYAN_BACKGROUND + "JOGO ENCERRADO COM SUCESSO!" + ANSI_RESET);
        }

    }

    public static void campeao(int pontosJogador1, int pontosJogador2, String jogador1, String jogador2) {
        if (pontosJogador1 > pontosJogador2) {
            System.out.printf(ANSI_CYAN + "PARÁBENS %s VOCÊ VENCEU!" + ANSI_RESET, jogador1);
        } else {
            System.out.printf(ANSI_CYAN + "PARÁBENS %s VOCÊ VENCEU!" + ANSI_RESET, jogador2);
        }
    }

    public static String jogadores(String jogador1) {
        Tela.escrever(ANSI_CYAN + "Escolher nome dos jogadores: " + ANSI_RESET);
        Tela.escrever("Nome do Jogador 1 :");
        jogador1 = read.nextLine();
        return jogador1;
    }

    public static String jogadores2(String jogador2) {
        Tela.escrever(ANSI_CYAN + "Escolher nome dos jogadores: " + ANSI_RESET);
        Tela.escrever("Nome do Jogador 2 :");
        jogador2 = read.nextLine();
        return jogador2;
    }
}
