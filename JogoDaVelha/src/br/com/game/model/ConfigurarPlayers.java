/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.game.model;

/**
 *
 * @author Adrian
 */
public class ConfigurarPlayers {
    
    public Player salvarNovoPlayer(String nome, String x_ou_o, Boolean estaNaVez){
        
        Player player = new Player();
        
        player.setNome(nome);
        player.setX_ou_O(x_ou_o);
        player.setEstaNaVez(estaNaVez);
        return player;
        
    }

}
