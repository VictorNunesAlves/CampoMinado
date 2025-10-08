import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.random;
import static java.util.Objects.isNull;

public class Tabuleiro {
    List<List<Campo>> tabuleiro = new ArrayList<>();
    int altura, largura;
    Tamanho tamanho;

    public Tabuleiro(Dificuldade dificuldade, Tamanho tamanho) {

        if (tamanho == Tamanho.PEQUENO) {
            this.largura = 7;
            this.altura = 7
            ;
        } else if (tamanho == Tamanho.MÈDIO) {
            this.largura = 8;
            this.altura = 8;
        } else if (tamanho == Tamanho.GRANDE) {
            this.largura = 9;
            this.altura = 9;
        }
        else {
            return;
        }
        Campo campo =  new Campo();
        for (int i = 0; i < this.altura;i++){
            tabuleiro.add(new ArrayList<>());
            for (int j = 0; j < this.largura;j++){
                if (dificuldade == Dificuldade.FÁCIL){
                    int val = (new Random().nextInt(100));
                    if (val <= 20) {
                        campo = new Campo(EstadosCampo.Bomba);
                    }
                    else {
                        campo = new Campo();
                    }
                }
                if (dificuldade == Dificuldade.MÉDIO){
                    int val = (new Random().nextInt(100));
                    if (val <= 35) {
                        campo = new Campo(EstadosCampo.Bomba);
                    }
                    else {
                        campo = new Campo();
                    }
                }
                if (dificuldade == Dificuldade.DIFICIL){
                    int val = (new Random().nextInt(100));
                    if (val <= 50) {
                        campo = new Campo(EstadosCampo.Bomba);
                    }
                    else {
                        campo = new Campo();
                    }
                }

                tabuleiro.get(i).add(campo);
            }
        }
    }

    public void verificaVizinhos(int y, int x){
        Campo atual = tabuleiro.get(y).get(x);
        if(atual.getVisitado()){
            return;
        }
        atual.setVisitado(true);

        atual.setValor(qtdBombaVizinhos(y, x));
        if(atual.getValor() != 0){
            atual.setEstadoatual(EstadosCampo.Enumerado);
            return;
        };

        atual.setEstadoatual(EstadosCampo.Aberto);

        if (x + 1 < this.largura && y + 1 < this.altura) {
            verificaVizinhos(y + 1, x + 1);
        }

        if (x + 1 < this.largura && y - 1 >= 0) {
            verificaVizinhos(y - 1, x + 1);
        }

        if (x - 1 >= 0 && y + 1 < this.altura) {
            verificaVizinhos(y + 1, x - 1);

        }

        if (x - 1 >= 0 && y - 1 >= 0) {
            verificaVizinhos(y - 1, x - 1);
        }

//------------------------------------------------------------------------------

        if (x + 1 < this.largura) {
            verificaVizinhos( y, x+1);
        }

        if (y + 1 < this.altura) {
            verificaVizinhos(y+1, x);
        }

        if (x - 1 >= 0) {
            verificaVizinhos(y, x-1);

        }
        if (y - 1 >= 0) {
            verificaVizinhos(y-1, x);
        }
    }

    public int qtdBombaVizinhos(int y, int x){
        int qtdBombas = 0;

        if (x + 1 < this.largura && y + 1 < this.altura) {
            if(tabuleiro.get(y+1).get(x+1).getEstadoatual() == EstadosCampo.Bomba) {
                qtdBombas += 1;
            }

        }

        if (x + 1 < this.largura && y - 1 >= 0) {
            if(tabuleiro.get(y-1).get(x+1).getEstadoatual() == EstadosCampo.Bomba){
                qtdBombas += 1;
            }

        }

        if (x - 1 >= 0 && y + 1 < this.altura) {
            if(tabuleiro.get(y+1).get(x-1).getEstadoatual() == EstadosCampo.Bomba){
                qtdBombas += 1;
            }

        }

        if (x - 1 >= 0 && y - 1 >= 0) {
            if(tabuleiro.get(y-1).get(x-1).getEstadoatual() == EstadosCampo.Bomba){
                qtdBombas += 1;
            }
        }

//------------------------------------------------------------------------------

        if (x + 1 < this.largura) {
            if(tabuleiro.get(y).get(x+1).getEstadoatual() == EstadosCampo.Bomba){
                qtdBombas += 1;
            }
        }

        if (y + 1 < this.altura) {
            if(tabuleiro.get(y+1).get(x).getEstadoatual() == EstadosCampo.Bomba){
                qtdBombas += 1;
            }
        }

        if (x - 1 >= 0) {
            if(tabuleiro.get(y).get(x-1).getEstadoatual() == EstadosCampo.Bomba){
                qtdBombas += 1;;
            }
        }
        if (y - 1 >= 0) {
            if(tabuleiro.get(y-1).get(x).getEstadoatual() == EstadosCampo.Bomba){
                qtdBombas += 1;
            }
        }
        return qtdBombas;
    }

    public void marcar(int y, int x){
        tabuleiro.get(y).get(x).setEstadoatual(EstadosCampo.Marcado);
    }
}
