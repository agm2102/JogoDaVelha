/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.game.model;

import br.com.game.view.GamePartidaView;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Adrian
 */
public class Partida {

    private Player player1;
    private Player player2;
    private GamePartidaView gameView;
    private Component[] container;
    private MouseListener mouseListener;
    private int numJogadas;
    private String[][] sequenciaJogadas = {{"", "", ""}, {"", "", ""}, {"", "", ""},};

    public Partida(Player player1, Player player2, GamePartidaView gameView) {
        this.player1 = player1;
        this.player2 = player2;
        this.gameView = gameView;
        this.numJogadas = 1;
        this.gameView.setTxtPlayer1(player1.getNome() + " " + player1.getX_ou_O());
        this.gameView.setTxtPlayer2(player2.getNome() + " " + player2.getX_ou_O());
        
        if (this.player1.isEstaNaVez()) {
            this.gameView.setTxtPlayerDaVez(this.player1.getNome());
        } else {
            this.gameView.setTxtPlayerDaVez(this.player2.getNome());
        }
        this.gameView.setVisible(true);
    }
    
    public void iniciarPartida() {
        esvaziaQuadros();
        fazerJogada();
        reiniciarPartida();
    }

    public void reiniciarPartida() {
        MouseListener mouseListener;
        mouseListener = new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                esvaziaQuadros();
                gameView.setTxtResultado("Resultado: ");
                sequenciaJogadas = new String[][]{{"", "", ""}, {"", "", ""}, {"", "", ""},};
                numJogadas = 0;
                fazerJogada();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override

            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };
        JButton btnReiniciar = gameView.getBtnReiniciar();
        btnReiniciar.addMouseListener(mouseListener);
    }

    private void removeAllEventsListeners() {
        for (Component jpanelComp : container) {
            if (jpanelComp instanceof JPanel jPanel) {
                jPanel.addMouseListener(mouseListener);
                Component container2[];
                container2 = jPanel.getComponents();
                for (Component jlabelComp : container2) {
                    if (jlabelComp instanceof JLabel jLabelQuadro) {
                        jLabelQuadro.removeMouseListener(mouseListener);
                    }
                }
            }
        }
    }

    private void esvaziaQuadros() {

        container = gameView.getjPanel1().getComponents();
        for (Component jpanelComp : container) {
            if (jpanelComp instanceof JPanel jPanel) {
                Component container2[];
                container2 = jPanel.getComponents();
                for (Component jlabelComp : container2) {
                    if (jlabelComp instanceof JLabel jLabel) {
                        jLabel.setText(null);
                    }
                }
            }
        }
    }

    private void fazerJogada() {
        container = this.gameView.getjPanel1().getComponents();
        mouseListener = new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

                JLabel jlabel = (JLabel) e.getComponent();
                JPanel jpanel = (JPanel) e.getComponent().getParent();

                if (player1.isEstaNaVez()) {
                    marcarQuadro(player1, jlabel, jpanel);

                } else {
                    marcarQuadro(player2, jlabel, jpanel);
                }
                numJogadas++;
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override

            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };

        for (Component jpanelComp : container) {
            if (jpanelComp instanceof JPanel jPanel) {
                jPanel.addMouseListener(mouseListener);
                Component container2[];
                container2 = jPanel.getComponents();
                for (Component jlabelComp : container2) {
                    if (jlabelComp instanceof JLabel jLabelQuadro) {
                        jLabelQuadro.addMouseListener(mouseListener);
                    }
                }
            }
        }

    }

    private void marcarQuadro(Player player, JLabel jlabelClicada, JPanel jpanelClicado) {

        int indice = pegaIndiceComponente(container, jpanelClicado);

        jlabelClicada.setText(player.getX_ou_O());

        if (player.getX_ou_O().equals("X")) {
            jlabelClicada.setForeground(Color.red);
        } else {
            jlabelClicada.setForeground(Color.blue);
        }

        montaSequenciaJogadas(indice, player.getX_ou_O());
        verificaGanhador();

        jlabelClicada.removeMouseListener(mouseListener);
        mudaPlayerDaVez();
    }

    private void mudaPlayerDaVez() {
        if (player1.isEstaNaVez()) {
            player1.setEstaNaVez(false);
            player2.setEstaNaVez(true);
            gameView.setTxtPlayerDaVez(player2.getNome());
        } else {
            player2.setEstaNaVez(false);
            player1.setEstaNaVez(true);
            gameView.setTxtPlayerDaVez(player1.getNome());
        }
    }

    private void verificaGanhador() {

        String possibilidadeHorizont1 = sequenciaJogadas[0][0] + sequenciaJogadas[0][1] + sequenciaJogadas[0][2];
        String possibilidadeHorizont2 = sequenciaJogadas[1][0] + sequenciaJogadas[1][1] + sequenciaJogadas[1][2];
        String possibilidadeHorizont3 = sequenciaJogadas[2][0] + sequenciaJogadas[2][1] + sequenciaJogadas[2][2];

        String possibilidadeVertic1 = sequenciaJogadas[0][0] + sequenciaJogadas[1][0] + sequenciaJogadas[2][0];
        String possibilidadeVertic2 = sequenciaJogadas[0][1] + sequenciaJogadas[1][1] + sequenciaJogadas[2][1];
        String possibilidadeVertic3 = sequenciaJogadas[0][2] + sequenciaJogadas[1][2] + sequenciaJogadas[2][2];

        String possibilidadeDiagon1 = sequenciaJogadas[0][0] + sequenciaJogadas[1][1] + sequenciaJogadas[2][2];
        String possibilidadeDiagon2 = sequenciaJogadas[0][2] + sequenciaJogadas[1][1] + sequenciaJogadas[2][0];

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        if (possibilidadeHorizont1.equals("XXX") || possibilidadeHorizont1.equals("OOO")) {
            if (possibilidadeHorizont1.contains("X")) {
                if ("X".equals(player1.getX_ou_O())) {
                    gameView.setTxtResultado(gameView.getTxtResultado() + player1.getNome() + " Venceu a partida!");
                } else {
                    gameView.setTxtResultado(gameView.getTxtResultado() + player2.getNome() + " Venceu a partida!");
                }
            } else if (possibilidadeHorizont1.contains("O")) {
                if ("O".equals(player1.getX_ou_O())) {
                    gameView.setTxtResultado(gameView.getTxtResultado() + player1.getNome() + " Venceu a partida!");
                } else {
                    gameView.setTxtResultado(gameView.getTxtResultado() + player2.getNome() + " Venceu a partida!");
                }
            }
            removeAllEventsListeners();
        } else if (possibilidadeHorizont2.equals("XXX") || possibilidadeHorizont2.equals("OOO")) {
            if (possibilidadeHorizont2.contains("X")) {
                if ("X".equals(player1.getX_ou_O())) {
                    gameView.setTxtResultado(gameView.getTxtResultado() + player1.getNome() + " Venceu a partida!");
                } else {
                    gameView.setTxtResultado(gameView.getTxtResultado() + player2.getNome() + " Venceu a partida!");
                }
            } else if (possibilidadeHorizont2.contains("O")) {
                if ("O".equals(player1.getX_ou_O())) {
                    gameView.setTxtResultado(gameView.getTxtResultado() + player1.getNome() + " Venceu a partida!");
                } else {
                    gameView.setTxtResultado(gameView.getTxtResultado() + player2.getNome() + " Venceu a partida!");
                }
            }
            removeAllEventsListeners();
        } else if (possibilidadeHorizont3.equals("XXX") || possibilidadeHorizont3.equals("OOO")) {
            if (possibilidadeHorizont3.contains("X")) {
                if ("X".equals(player1.getX_ou_O())) {
                    gameView.setTxtResultado(gameView.getTxtResultado() + player1.getNome() + " Venceu a partida!");
                } else {
                    gameView.setTxtResultado(gameView.getTxtResultado() + player2.getNome() + " Venceu a partida!");
                }
            } else if (possibilidadeHorizont3.contains("O")) {
                if ("O".equals(player1.getX_ou_O())) {
                    gameView.setTxtResultado(gameView.getTxtResultado() + player1.getNome() + " Venceu a partida!");
                } else {
                    gameView.setTxtResultado(gameView.getTxtResultado() + player2.getNome() + " Venceu a partida!");
                }
            }
            removeAllEventsListeners();
        } //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        else if (possibilidadeVertic1.equals("XXX") || possibilidadeVertic1.equals("OOO")) {
            if (possibilidadeVertic1.contains("X")) {
                if ("X".equals(player1.getX_ou_O())) {
                    gameView.setTxtResultado(gameView.getTxtResultado() + player1.getNome() + " Venceu a partida!");
                } else {
                    gameView.setTxtResultado(gameView.getTxtResultado() + player2.getNome() + " Venceu a partida!");
                }
            } else if (possibilidadeVertic1.contains("O")) {
                if ("O".equals(player1.getX_ou_O())) {
                    gameView.setTxtResultado(gameView.getTxtResultado() + player1.getNome() + " Venceu a partida!");
                } else {
                    gameView.setTxtResultado(gameView.getTxtResultado() + player2.getNome() + " Venceu a partida!");
                }
            }
            removeAllEventsListeners();
        } else if (possibilidadeVertic2.equals("XXX") || possibilidadeVertic2.equals("OOO")) {
            if (possibilidadeVertic2.contains("X")) {
                if ("X".equals(player1.getX_ou_O())) {
                    gameView.setTxtResultado(gameView.getTxtResultado() + player1.getNome() + " Venceu a partida!");
                } else {
                    gameView.setTxtResultado(gameView.getTxtResultado() + player2.getNome() + " Venceu a partida!");
                }
            } else if (possibilidadeVertic2.contains("O")) {
                if ("O".equals(player1.getX_ou_O())) {
                    gameView.setTxtResultado(gameView.getTxtResultado() + player1.getNome() + " Venceu a partida!");
                } else {
                    gameView.setTxtResultado(gameView.getTxtResultado() + player2.getNome() + " Venceu a partida!");
                }
            }
            removeAllEventsListeners();
        } else if (possibilidadeVertic3.equals("XXX") || possibilidadeVertic3.equals("OOO")) {
            if (possibilidadeVertic3.contains("X")) {
                if ("X".equals(player1.getX_ou_O())) {
                    gameView.setTxtResultado(gameView.getTxtResultado() + player1.getNome() + " Venceu a partida!");
                } else {
                    gameView.setTxtResultado(gameView.getTxtResultado() + player2.getNome() + " Venceu a partida!");
                }
            } else if (possibilidadeVertic3.contains("O")) {
                if ("O".equals(player1.getX_ou_O())) {
                    gameView.setTxtResultado(gameView.getTxtResultado() + player1.getNome() + " Venceu a partida!");
                } else {
                    gameView.setTxtResultado(gameView.getTxtResultado() + player2.getNome() + " Venceu a partida!");
                }
            }
            removeAllEventsListeners();
        } //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        else if (possibilidadeDiagon1.equals("XXX") || possibilidadeDiagon1.equals("OOO")) {
            if (possibilidadeDiagon1.contains("X")) {
                if ("X".equals(player1.getX_ou_O())) {
                    gameView.setTxtResultado(gameView.getTxtResultado() + player1.getNome() + " Venceu a partida!");
                } else {
                    gameView.setTxtResultado(gameView.getTxtResultado() + player2.getNome() + " Venceu a partida!");
                }
            } else if (possibilidadeDiagon1.contains("O")) {
                if ("O".equals(player1.getX_ou_O())) {
                    gameView.setTxtResultado(gameView.getTxtResultado() + player1.getNome() + " Venceu a partida!");
                } else {
                    gameView.setTxtResultado(gameView.getTxtResultado() + player2.getNome() + " Venceu a partida!");
                }
            }
            removeAllEventsListeners();
        } else if (possibilidadeDiagon2.equals("XXX") || possibilidadeDiagon2.equals("OOO")) {
            if (possibilidadeDiagon2.contains("X")) {
                if ("X".equals(player1.getX_ou_O())) {
                    gameView.setTxtResultado(gameView.getTxtResultado() + player1.getNome() + " Venceu a partida!");
                } else {
                    gameView.setTxtResultado(gameView.getTxtResultado() + player2.getNome() + " Venceu a partida!");
                }
            } else if (possibilidadeDiagon2.contains("O")) {
                if ("O".equals(player1.getX_ou_O())) {
                    gameView.setTxtResultado(gameView.getTxtResultado() + player1.getNome() + " Venceu a partida!");
                } else {
                    gameView.setTxtResultado(gameView.getTxtResultado() + player2.getNome() + " Venceu a partida!");
                }
            }
            removeAllEventsListeners();
        } else {
            if (numJogadas == 9) {
                gameView.setTxtResultado("O jogo deu velha, ninguem venceu a partida.");
                removeAllEventsListeners();
            }
        }
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private int pegaIndiceComponente(Component[] components, Component comp) {
        for (int i = 0; i < components.length; i++) {
            if (components[i] == comp) {
                return i;
            }
        }
        return -1;
    }

    private void montaSequenciaJogadas(int indice, String simbolo) {

        switch (indice) {
            case 0:
                sequenciaJogadas[2][0] = simbolo;
                break;
            case 1:
                sequenciaJogadas[0][0] = simbolo;
                break;
            case 2:
                sequenciaJogadas[1][0] = simbolo;
                break;
            case 3:
                sequenciaJogadas[2][1] = simbolo;
                break;
            case 4:
                sequenciaJogadas[2][2] = simbolo;
                break;
            case 5:
                sequenciaJogadas[1][1] = simbolo;
                break;
            case 6:
                sequenciaJogadas[1][2] = simbolo;
                break;
            case 7:
                sequenciaJogadas[0][1] = simbolo;
                break;
            case 8:
                sequenciaJogadas[0][2] = simbolo;
                break;
            default:
                throw new AssertionError();

        }
    }
}
