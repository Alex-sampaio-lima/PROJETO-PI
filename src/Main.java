import org.w3c.dom.ls.LSOutput;

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

    public static void main(String[] args) {

        Scanner read = new Scanner(System.in);

        String jogador1 = "Alek", jogador2 = "Guil";
        Set<String> palavrasUsadas = new HashSet<>();
        String palavraJogador1 = "", palavraJogador2 = "";
        char ultimaLetraJogador1 = ' ', primeiraLetraJogador1 = ' ', ultimaLetraJogador2 = ' ', primeiraLetraJogador2 = ' ', letraValida = ' ';
        int menu = 1, pontosJogador1 = 5, pontosJogador2 = 5, round = 1;
        boolean palavraValida = true;

        System.out.println(ANSI_CYAN + "Seja Bem Vindo Ao Jogo Das Palavras: " + ANSI_RESET);

        System.out.println("Nome do Jogador 1: ");
//        jogador1 = read.nextLine();
        System.out.println("Nome do Jogador 2: ");
//        jogador2 = read.nextLine();

        do {
            System.out.println("[1] - Digite 1 para Começar o jogo.");
            System.out.println("[2] - Digite 2 para ver as regras do jogo.");
            System.out.println("[0] - Digite 0 para Encerrar o jogo.");
            menu = read.nextInt();
            read.nextLine();

            if (menu == 0) {
                break;
            } else if (menu == 2) {
                System.out.println(ANSI_CYAN + "Regras Do Jogo Das Palavras: " + ANSI_RESET);
                System.out.println("1. Os jogadores se revezam para dizer uma palavra.");
                System.out.println("2. O primeiro jogador irá digitar uma palavra, o segundo jogador terá que digitar uma outra palavara que comece com a última letra da palavra do primeiro jogador.\n" + ANSI_BLACK_BACKGROUND + ANSI_CYAN + "EXEMPLO:" + ANSI_RESET + ANSI_CYAN + "\nJogador 1: Posta" + ANSI_RESET + ANSI_CYAN + "\nJogador 2: Alface" + ANSI_RESET);
                System.out.println("3. As palavras não podem ser repetidas.");
                System.out.println("4. Cada jogador irá começar com 5 pontos.");
                System.out.println("5. O jogo termina quando um dos jogadores não tiver mais pontos.");
                System.out.println("Presione Enter para continuar...");
                read.nextLine();
                continue;
            }
            System.out.println("Jogo Iniciado:");
            System.out.println();

            do {
                System.out.printf(ANSI_BLUE + "ROUND %d%n" + ANSI_RESET, round);

                // Jogador 1
                System.out.printf(ANSI_GREEN + "%s " + ANSI_RESET + "sua vez de jogar!%nDigite uma palavra: ", jogador1);
                palavraJogador1 = read.nextLine().toLowerCase();
                primeiraLetraJogador1 = palavraJogador1.charAt(0);
                ultimaLetraJogador1 = palavraJogador1.charAt(palavraJogador1.length() - 1);


//                String[] frutas = getFrutas();

//                for (int i = 0; i < frutas.length; i++) {
//                    System.out.println(frutas[i]);
//                    if (frutas[i].equalsIgnoreCase(palavraJogador1)) {
//                        System.out.println("Palavra válida!");
//                    }
//                }

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


                System.out.println(palavrasUsadas);
                // Após a Primeira Rodada verifica se a palavra do P1 corresponde com a do P2
//                if (round > 1) {
//                    if (ultimaLetraJogador2 != primeiraLetraJogador1) {
//                        System.out.printf("Palavras não correspondem " + ANSI_RED + "%s PERDEU 2 PONTOS!%n" + ANSI_RESET, jogador1);
//                        palavraValida = false;
//                        pontosJogador1 -= 2;
//                    }
//                }

                System.out.println("---------------------");
                System.out.printf(ANSI_CYAN + "Letra da vez = %s%n" + ANSI_RESET, letraValida);


                // Jogador 2
                System.out.printf(ANSI_GREEN + "%s " + ANSI_RESET + "sua vez jogar!%nDigite uma palavra: ", jogador2);
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

//                if (palavraValida) {
//                    if (ultimaLetraJogador1 == primeiraLetraJogador2) {
//                        letraValida = ultimaLetraJogador2;
//                    } else if (letraValida == primeiraLetraJogador2) {
//                        letraValida = ultimaLetraJogador2;
//                    } else if (ultimaLetraJogador1 != primeiraLetraJogador2) {
//                        letraValida = ultimaLetraJogador1;
//                    }
//                }

//              Verifica se já existe

//                    if (palavraValida) {
//                        palavrasUsadas.add(palavraJogador2);
//                    }

//                if (palavraValida) {
//                    letraValida = ultimaLetraJogador2;
//                } else if (letraValida != primeiraLetraJogador2) {
//
//                } else {
//                    letraValida = ultimaLetraJogador1;
//                }


//                if (ultimaLetraJogador1 != primeiraLetraJogador2) {
//                    System.out.printf("Palavras não correspondem " + ANSI_RED + "%s PERDEU 2 PONTOS!%n" + ANSI_RESET, jogador2);
//                    palavraValida = false;
//                    pontosJogador2 -= 2;
//                }

                System.out.printf(ANSI_PURPLE + "PONTUAÇÃO:" + ANSI_RESET);
                System.out.printf(ANSI_CYAN + "%n%s\t" + ANSI_RESET + "Sua Pontuação: %d%n", jogador1, pontosJogador1);
                System.out.printf(ANSI_CYAN + "%s\t" + ANSI_RESET + "Sua Pontuação: %d%n%n", jogador2, pontosJogador2);
                System.out.printf(ANSI_CYAN + "Letra da vez = %s%n", letraValida);
                round++;
            }
            while (pontosJogador1 >= 0 && pontosJogador2 >= 0);
            if (pontosJogador1 > pontosJogador2) {
                System.out.printf(ANSI_CYAN + "PARÁBENS %s VOCÊ VENCEU!" + ANSI_RESET, jogador1);
            } else {
                System.out.printf(ANSI_CYAN + "PARÁBENS %s VOCÊ VENCEU!" + ANSI_RESET, jogador2);
            }
            break;
        } while (menu != 0);
        if (menu == 0) {
            System.out.println(ANSI_BLACK + ANSI_CYAN_BACKGROUND + "JOGO ENCERRADO COM SUCESSO!" + ANSI_RESET);
        }

    }

    public int campeao(int x, int y) {

        return 1;
    }

    public static String[] getFrutas() {
        return new String[]{
                // Frutas com A
                "Abacate", "Abacaxi", "Abiu", "Abricó", "Abrunho",
                "Açaí", "Acerola", "Akee", "Alfarroba", "Ameixa",
                "Amêndoa", "Amora", "Ananás", "Anona", "Araçá",
                "Arando", "Araticum", "Ata", "Atemoia", "Avelã",
                // Frutas com B
                "Babaco", "Babaçu", "Bacaba", "Bacuri", "Bacupari",
                "Banana", "Baru", "Bergamota", "Biribá", "Buriti",
                "Butiá",
                // Frutas com C
                "Cabeludinha", "Cacau", "Cagaita", "Caimito", "Cajá",
                "Caju", "Calabaça", "Calabura", "Calamondin", "Cambucá",
                "Cambuci", "Camu-camu", "Caqui", "Carambola", "Carnaúba",
                "Castanha", "Castanha-do-pará", "Cereja", "Ciriguela",
                "Ciruela", "Coco", "Cranberry", "Cupuaçu",
                // Frutas com D
                "Damasco", "Dekopon", "Dendê", "Dióspiro", "Dovyalis",
                "Durião",
                // Frutas com E
                "Embaúba", "Embaubarana", "Engkala", "Escropari",
                "Esfregadinha", "Esporão-de-galo",
                // Frutas com F
                "Figo", "Framboesa", "Fruta-do-conde", "Fruta-pão",
                "Feijoa", "Figo-da-índia", "Fruta-de-cedro",
                "Fruta-de-lobo", "Fruta-do-milagre", "Fruta-de-tatu",
                // Frutas com G
                "Gabiroba", "Glicosmis", "Goiaba", "Granadilla",
                "Gravatá", "Graviola", "Groselha", "Grumixama",
                "Guabiju", "Guabiroba", "Guaraná",
                // Frutas com H
                "Hawthorn", "Heisteria", "Hilocéreo",
                // Frutas com I
                "Ibacurupari", "Ilama", "Imbe", "Imbu", "Inajá",
                "Ingá", "Inharé",
                // Frutas com J
                "Jabuticaba", "Jaca", "Jambo", "Jambolão",
                "Jamelão", "Jaracatiá", "Jatobá", "Jenipapo",
                "Jerivá", "Juá", "Jujuba",
                // Frutas com K
                "Kiwi", "Kumquat", "Kinkan", "Kino", "Kiwano",
                "Kabosu", "Karité", "Korlan",
                // Frutas com L
                "Laranja", "Limão", "Lima", "Lichia", "Longan",
                "Lucuma", "Lacucha", "Lulo", "Lobeira",
                "Langsat", "Laranja-de-pacu",
                // Frutas com M
                "Mabolo", "Maçã", "Macadâmia", "Macaúba", "Mamão",
                "Mamey", "Mamoncillo", "Maná-cubiu", "Manga",
                "Mangaba", "Mangostão", "Maracujá", "Marang",
                "Marmelo", "Marolo", "Marula", "Massala",
                "Melancia", "Melão", "Meloa", "Mexerica",
                "Mirtilo", "Morango", "Murici",
                // Frutas com N
                "Naranjilla", "Nectarina", "Nêspera", "Noni",
                "Noz", "Noz-pecã", "Noz-macadâmia",
                // Frutas com O
                "Oiti", "Oxicoco", "Orangelo",
                // Frutas com P
                "Pera", "Pêssego", "Pitanga", "Pinha", "Pitaia",
                "Pitomba", "Pitangatuba", "Pindaíba", "Pequi",
                "Pequiá", "Physalis", "Pulasan", "Pomelo",
                "Pupunha", "Puçá", "Patauá", "Pajurá",
                "Pixirica", "Pistache",
                // Frutas com Q
                "Quina", "Quiuí", "Quixabeira",
                // Frutas com R
                "Romã", "Rambai", "Rambutão", "Rukam",
                // Frutas com S
                "Saguaraji", "Salak", "Santol", "Sapota", "Sapoti",
                "Sapucaia", "Saputá", "Seriguela", "Sorvinha",
                // Frutas com T
                "Tangerina", "Tamarindo", "Tâmara", "Toranja",
                "Tucumã", "Taiuva", "Tapiá", "Tarumã",
                "Tangor", "Tucujá",
                // Frutas com U
                "Uva", "Umbu", "Uvaia", "Uchuva", "Umê",
                "Uxi", "Ucuuba",
                // Frutas com V
                "Vacínio", "Veludo", "Vergamota", "Veludo-branco",
                // Frutas com W
                "Wampi",
                // Frutas com X
                "Xixá",
                // Frutas com Y
                "Yamamomo", "Yuzu",
                // Frutas com Z
                "Zimbro"
        };
    }
}