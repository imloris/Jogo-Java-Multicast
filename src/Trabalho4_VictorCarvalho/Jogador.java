/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trabalho4_VictorCarvalho;

import java.awt.Rectangle;

/**
 *
 * @author vykto
 */
public class Jogador {

    private String nome;
    private int x, y;

    public Jogador(String nome, int X, int Y) {
        this.nome = nome;
        this.x = X;
        this.y = Y;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


}
