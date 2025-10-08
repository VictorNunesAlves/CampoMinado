import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Tabuleiro jogo = new Tabuleiro(Dificuldade.FÁCIL, Tamanho.PEQUENO);
        imprimirTabuleiro(jogo);
        int y,x;

        while(true) {
            System.out.println("Escolha uma opção: ");
            System.out.println("[1] - Imprimir Tabuleiro");
            System.out.println("[2] - Escolher posição");
            System.out.println("[3] - Marcar uma posição");
            int i = sc.nextInt();
            sc.nextLine();
            switch (i) {

                case 1:
                    imprimirTabuleiro(jogo);
                case 2:
                    System.out.println("Insira a posição (linha, coluna):");
                    String[] posicoes = sc.nextLine().split(",");
                    y = Integer.parseInt(posicoes[0].trim());
                    x = Integer.parseInt(posicoes[1].trim());
                    if(escolherPosicao(jogo, y, x) == 0){
                        return;
                    };
                    imprimirTabuleiro(jogo);
                    if(verificarSeGanhou(jogo)){
                        System.out.println("Parabéns Você Ganhou!!!!");
                    }
                    break;
                case 3:
                    y = sc.nextInt();
                    x = sc.nextInt();
                    sc.nextLine();
                    marcarPosição(jogo,y,x);
                    if(verificarSeGanhou(jogo)){
                        System.out.println("Parabéns Você Ganhou!!!!");
                    }
                    break;
            }
            System.out.println();
        }
    }

    private static void imprimirTabuleiro(Tabuleiro jogo) {
        System.out.println();
        System.out.print("  | ");
        for(int i = 0; i < jogo.largura;i++){
            System.out.print(i+1 + " | ");
        }
        System.out.println();
        System.out.print("  ");
        for(int i = 0; i < jogo.largura; i++){
            System.out.print("----");
        }
        System.out.println("|");

        for (int i = 0; i < jogo.altura;i++){
            System.out.print(i+1 +" |");
            for (int j = 0; j < jogo.largura;j++){
                Campo campoatual = jogo.tabuleiro.get(i).get(j);
                EstadosCampo estado_atual = campoatual.getEstadoatual();

                if(estado_atual == EstadosCampo.Fechado ){
                    System.out.print("   |");
                }
                if(estado_atual == EstadosCampo.Bomba){
                        System.out.print("   |");

                }

                if(estado_atual == EstadosCampo.Aberto){
                    System.out.print(" A |");
                }
                if (estado_atual == EstadosCampo.Enumerado){
                    System.out.print(" "+campoatual.getValor()+" |");
                }
                if(estado_atual == EstadosCampo.Marcado){
                    System.out.print(" P |");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void imprimirFimDeJogoTabuleiro(Tabuleiro jogo) {
        System.out.println();
        System.out.print("  | ");
        for(int i = 0; i < jogo.largura;i++){
            System.out.print(i+1 + " | ");
        }
        System.out.println();
        System.out.print("  ");
        for(int i = 0; i < jogo.largura; i++){
            System.out.print("----");
        }
        System.out.println("|");

        for (int i = 0; i < jogo.altura;i++){
            System.out.print(i+1 +" |");
            for (int j = 0; j < jogo.largura;j++){
                Campo campoatual = jogo.tabuleiro.get(i).get(j);
                EstadosCampo estado_atual = campoatual.getEstadoatual();

                if(estado_atual == EstadosCampo.Fechado ){
                    System.out.print("   |");
                }
                if(estado_atual == EstadosCampo.Bomba){
                    System.out.print(" * |");
                }

                if(estado_atual == EstadosCampo.Aberto){
                    System.out.print(" A |");
                }
                if (estado_atual == EstadosCampo.Enumerado){
                    System.out.print(" "+campoatual.getValor()+" |");
                }
                if(estado_atual == EstadosCampo.Marcado){
                    System.out.print(" P |");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int escolherPosicao(Tabuleiro jogo,int y, int x){
        if(jogo.tabuleiro.get(y-1).get(x-1).getEstadoatual() ==  EstadosCampo.Bomba){
            imprimirFimDeJogoTabuleiro(jogo);
            System.out.println("EXPLODIU !!!!!");
            return 0;
        }

        jogo.verificaVizinhos(y-1,x-1);
        return 1;
    }

    public static void marcarPosição(Tabuleiro jogo, int y, int x){
        jogo.marcar(y,x);
    }

    public static boolean verificarSeGanhou(Tabuleiro jogo){
        for (int i = 0; i < jogo.altura;i++) {
            for (int j = 0; j < jogo.largura; j++) {
                if (jogo.tabuleiro.get(i).get(j).getEstadoatual() == EstadosCampo.Fechado) {
                    return false;
                }
            }
        }
        return true;
    }
}

