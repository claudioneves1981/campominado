package campominado.modelo;

import campominado.excecao.ExplosaoException;
import campominado.excecao.SairException;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Tabuleiro {

    public JFrame frame;
    public Container c;
    public JButton[][] button;
    public JLabel[][] label;
    public JTextField field;
    JPanel panel;
    JMenu menu;
    JMenuBar menubar;
    public JMenuItem newgame, exitmenu;

    public int quantidadeDeLinhas;
    public int quantidadeDeColunas;
    public int quantidadeDeMinas;

    public final List<Campo> campos = new ArrayList<>();

    public Tabuleiro(int quantidadeDeLinhas, int quantidadeDeColunas, int quantidadeDeMinas){

        this.quantidadeDeLinhas = quantidadeDeLinhas;
        this.quantidadeDeColunas = quantidadeDeColunas;
        this.quantidadeDeMinas = quantidadeDeMinas;

        button = new JButton[this.quantidadeDeLinhas][this.quantidadeDeColunas];
        label = new JLabel[this.quantidadeDeLinhas][this.quantidadeDeColunas];

        frame = new JFrame("Campo Minado");

        gerarCampos();
        associarVizinhos();
        sortearMinas();
        executarJogo();

    }


    private void executarJogo() {
        try {
            boolean continuar = true;

            int resposta = JOptionPane.showConfirmDialog(null, "Iniciar/ Outra Partida?(S/N)","Outra Partida",JOptionPane.YES_NO_OPTION);
            if(resposta == JOptionPane.NO_OPTION) {
                continuar = false;
            } else {
                limparCampos();
                reiniciar();
            }
        } catch(SairException e) {
            JOptionPane.showMessageDialog(null, "Adeus!");
        }
    }

    public void abrirTabuleiro(){
        int a = 0;
        AtomicInteger finalA = new AtomicInteger();
        finalA.set(a);
        for(int linhaAtual = 0; linhaAtual < quantidadeDeLinhas; linhaAtual++) {
            for(int colunaAtual = 0; colunaAtual < quantidadeDeColunas; colunaAtual++) {
                int finalLinhaAtual = linhaAtual;
                int finalColunaAtual = colunaAtual;
                label[linhaAtual][colunaAtual].setText(campos.get(a).campoSimbolo());
                button[linhaAtual][colunaAtual].addActionListener(e -> {
                        finalA.getAndIncrement();
                        if (finalA.get() == 1 ) {
                           button[finalLinhaAtual][finalColunaAtual].setVisible(false);
                            abrirCelula(finalLinhaAtual, finalColunaAtual);
                        }
                    });
              a++;
            }
        }
    }


        public void abrirCelula(int linhaAtual, int colunaAtual) {

        try {

            Object[] opcoes = {"Abrir", "(Des)Marcar"};
            int op = JOptionPane.showOptionDialog(null, "Abrir ou (Des)Marcar?", "Abrir ou (Des)Marcar", 1, 3, null, opcoes, null);

          if(!objetivoAlcancado()) {

                if (op == 0) {
                        abrirCampo(linhaAtual, colunaAtual);
                      return;

                } else if (op == 1) {
                    alternarMarcacao(linhaAtual, colunaAtual);
                    return;

                }
          }

            if(label[linhaAtual][colunaAtual].getText().equals("*")){
                JOptionPane.showMessageDialog(null, "Você Perdeu!!!");
                abrirTabuleiro();

            }
        } catch(ExplosaoException ex){
            JOptionPane.showMessageDialog(null, "Você Perdeu!!!");
            abrirTabuleiro();

        }
    }

    private void xyAxis(){
        int yaxis = 65;
        for (int a = 0; a < quantidadeDeLinhas; a++) {
            int xaxis = 1;

            for (int b = 0; b < quantidadeDeColunas; b++) {
                button[a][b] = new JButton("");
                button[a][b].setSize(45, 45);
                button[a][b].setLocation(xaxis, yaxis);
                button[a][b].setBackground(Color.LIGHT_GRAY);
                button[a][b].addActionListener(e-> abrirTabuleiro());
                label[a][b] = new JLabel("?", JLabel.CENTER);
                label[a][b].setSize(45, 45);
                label[a][b].setLocation(xaxis, yaxis);
                label[a][b].setBackground(Color.WHITE);
                label[a][b].setVisible(true);
                label[a][b].setOpaque(false);
                c.add(label[a][b]);
                c.add(button[a][b]);
                xaxis += button[a][b].getWidth() + 1;
                campos.add(new Campo(a, b));
            }
            yaxis += button[0][0].getHeight() + 1;
        }
    }

    private void gerarCampos() {

        frame.setSize(469, 580);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        c = frame.getContentPane();
        c.setLayout(null);
        c.setBackground(Color.BLACK);
        menu = new JMenu("Arquivo");
        newgame = new JMenuItem("Novo Jogo");
        menubar = new JMenuBar();
        newgame.addActionListener(e -> {
            limparCampos();
            reiniciar();
            field.setText("Jogo Iniciado");
            c.add(field);
        });
        exitmenu = new JMenuItem("Sair");
        exitmenu.addActionListener(e -> System.exit(0));
        menu.add(newgame);
        menu.add(exitmenu);
        menubar.add(menu);
        frame.setJMenuBar(menubar);

        panel = new JPanel();
        panel.setBackground(Color.GRAY);
        panel.setSize(459, 64);
        panel.setLayout(null);

        field = new JTextField("Jogo Iniciado");
        field.setFont(new Font("Times New Roman", Font.BOLD, 14));
        field.setSize(field.getPreferredSize());
        field.setBackground(Color.BLACK);
        field.setForeground(Color.WHITE);
        field.setLocation(newgame.getX() + 100, (panel.getHeight() / 2) - (field.getHeight() / 2));
        c.add(field);
        c.add(panel);
        frame.setVisible(true);
        xyAxis();
        abrirTabuleiro();
    }

    private void associarVizinhos() {
        for(Campo campo: campos) {
            for(Campo campo2: campos) {
                campo.adicionarVizinho(campo2);
            }
        }
    }

    private void sortearMinas() {
        long minasArmadas;
        Random aleatorio = new Random();
        do {
            campos.get(aleatorio.nextInt(campos.size())).minar();
            minasArmadas = campos.stream()
                    .filter(Campo::isMinado)
                    .count();
        } while(minasArmadas < quantidadeDeMinas);
    }

    public boolean objetivoAlcancado() {

        return campos.stream().allMatch(Campo::objetivoAlcancado);

    }

    public void reiniciar() {
        campos.forEach(Campo::reiniciar);
        associarVizinhos();
        sortearMinas();
    }


    public void abrirCampo(int linha, int coluna) {
        try {
            campos.stream()
                    .filter(campo ->
                            campo.getLinha() == linha && campo.getColuna() == coluna)
                    .findFirst()
                    .ifPresent(Campo::abrir);
        } catch(ExplosaoException explosao) {
            campos.forEach(Campo::setAberto);
            throw explosao;
        }


    }

    public void alternarMarcacao(int linha, int coluna) {
        campos.stream()
                .filter(campo ->
                        campo.getLinha() == linha && campo.getColuna() == coluna)
                .findFirst()
                .ifPresent(Campo::alternarMarcacao);


    }

    public void limparCampos(){
        for(int linhaAtual = 0; linhaAtual < quantidadeDeLinhas; linhaAtual++) {
            for (int colunaAtual = 0; colunaAtual < quantidadeDeColunas; colunaAtual++) {
                button[linhaAtual][colunaAtual].setVisible(true);
                label[linhaAtual][colunaAtual].setText("?");
            }
        }
    }
}
