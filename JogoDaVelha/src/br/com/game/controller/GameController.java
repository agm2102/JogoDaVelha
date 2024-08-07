/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.game.controller;

import br.com.game.model.ConfigurarPlayers;
import br.com.game.model.Partida;
import br.com.game.model.Player;
import br.com.game.view.GameConfigurarPlayersView;
import br.com.game.view.GameMenuView;
import br.com.game.view.GamePartidaView;

/**
 *
 * @author Adrian
 */
public class GameController {

    GamePartidaView partidaView  = new GamePartidaView();
    Partida partida;

    public void abreMenu() {
        GameMenuView menuView = new GameMenuView();
        menuView.setVisible(true);
    }

    public void fechaMenu(GameMenuView menuView) {
        menuView.dispose();
    }

    public void fechaTelaConfiguracaoPlayer(GameConfigurarPlayersView configurarPlayerView) {
        configurarPlayerView.dispose();
    }

    public void abrirTelaConfiguracaoPlayer() {
        GameConfigurarPlayersView configurarPlayerView = new GameConfigurarPlayersView();
        configurarPlayerView.setVisible(true);
    }

    public void fecharTelaPartida(GamePartidaView partidaView) {
        partidaView.dispose();
    }

    public void iniciarPartida(Player player1, Player player2) {    
        partida = new Partida(player1, player2, this.partidaView);
        partida.iniciarPartida();
    }

    public void reiniciarPartida() {
        partida.reiniciarPartida();
    }

    public Player salvarPlayer1(GameConfigurarPlayersView configurarPlayerView) {
        ConfigurarPlayers configurarPlayer = new ConfigurarPlayers();
        return configurarPlayer.salvarNovoPlayer(configurarPlayerView.getPlayerName1(), configurarPlayerView.getSimbolo1(), true);
    }

    public Player salvarPlayer2(GameConfigurarPlayersView configurarPlayerView) {
        ConfigurarPlayers configurarPlayer = new ConfigurarPlayers();
        return configurarPlayer.salvarNovoPlayer(configurarPlayerView.getPlayerName2(), configurarPlayerView.getSimbolo2(), false);
    }
}
