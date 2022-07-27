package campominado.modelo;

import campominado.excecao.ExplosaoException;

import java.util.ArrayList;
import java.util.List;

public class Campo {

    private final int linha;
    private final int coluna;

    private boolean aberto;
    private boolean minado;
    private boolean marcado;

    private final List<Campo> vizinhos = new ArrayList<>();

    Campo(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;

    }

    void adicionarVizinho(Campo vizinho) {
        boolean linhaDiferente = linha != vizinho.linha;
        boolean colunaDiferente = coluna != vizinho.coluna;
        boolean diagonal = linhaDiferente && colunaDiferente;

        int deltaLinha = Math.abs(linha - vizinho.linha);
        int deltaColuna = Math.abs(coluna - vizinho.coluna);
        int deltaGeral = deltaLinha + deltaColuna;

        if (deltaGeral == 1) {
            vizinhos.add(vizinho);
        } else if (deltaGeral == 2 && diagonal) {
            vizinhos.add(vizinho);
        }

    }

    void alternarMarcacao() {
        if (!aberto)
            marcado = !marcado;
    }

    void minar() {
        minado = true;
    }

    void abrir() {
        if (!aberto && !marcado) {
            aberto = true;
            if (minado) {
                throw new ExplosaoException();
            }
            if (vizinhancaSegura()) {
                vizinhos.forEach(Campo::abrir);
            }
        }
    }

    boolean objetivoAlcancado() {
        boolean desvendado = !minado && aberto;
        boolean protegido = minado && marcado;
        return desvendado || protegido;
    }

    long minasNaVizinhanca() {
        return vizinhos.stream()
                .filter(vizinho -> vizinho.minado)
                .count();
    }

    void reiniciar() {
        vizinhos.clear();
        aberto = false;
        minado = false;
        marcado = false;
    }

    public String campoSimbolo() {
        if (marcado) {
            return "X";
        } else if (aberto && minado) {
            return "*";
        } else if (aberto && minasNaVizinhanca() > 0) {
            return Long.toString(minasNaVizinhanca());
        } else if (aberto) {
            return " ";
        } else {
            return "?";
        }
    }

    boolean vizinhancaSegura() {
        return vizinhos.stream().noneMatch(vizinho -> vizinho.minado);
    }

    public boolean isMinado() {
        return minado;
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    void setAberto() {
        this.aberto = true;
    }

}
