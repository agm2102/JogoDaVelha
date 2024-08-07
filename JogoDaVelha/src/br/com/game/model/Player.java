/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.game.model;

/**
 *
 * @author Adrian
 */
public class Player {
    
    private String nome;
    private String sequenciaJogas;
    private String x_ou_O;
    private int numVitorias;
    private boolean estaNaVez;
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSequenciaJogas() {
        return sequenciaJogas;
    }

    public void setSequenciaJogas(String sequenciaJogas) {
        this.sequenciaJogas = sequenciaJogas;
    }

    public String getX_ou_O() {
        return x_ou_O;
    }

    public void setX_ou_O(String x_ou_O) {
        this.x_ou_O = x_ou_O;
    }

    public int getNumVitorias() {
        return numVitorias;
    }

    public void setNumVitorias(int numVitorias) {
        this.numVitorias = numVitorias;
    }

    public boolean isEstaNaVez() {
        return estaNaVez;
    }

    public void setEstaNaVez(boolean estaNaVez) {
        this.estaNaVez = estaNaVez;
    }
    
    
}
