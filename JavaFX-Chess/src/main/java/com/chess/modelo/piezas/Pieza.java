/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chess.modelo.piezas;

/**
 *
 * @author Guiller
 */
public abstract class Pieza {
    public static final String NEGRO = "negro";
    public static final String BLANCO = "blanco";
    
    private String color;
    private String letra;
    
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    @Override
    public String toString() {
        return "Pieza{" + "color=" + color + ", letra=" + letra + '}';
    }
    
    /*public void comer(Pieza piezaDestino){
        piezaDestino.setEstado(false);
    }*/
    
    public abstract boolean mover(int origenX, int origenY, int destX, int destY);
    public boolean analizaRecorrido(int origenX, int origenY, int destinoX, int destinoY){
        return true;
    }
}