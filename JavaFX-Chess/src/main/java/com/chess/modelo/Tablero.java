/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chess.modelo;

import com.chess.modelo.piezas.Pieza;

/**
 *
 * @author Guiller
 */
public class Tablero {
    
    private static Tablero instance;
    //resto de atributos normales
    private final Casilla [][] casillas;
    private int orientacion = 0;
            
    public Tablero() {
        this.casillas = new Casilla[8][8];
    }

    public int getOrientacion() {
        return orientacion;
    }
    
    public static Tablero getInstance() {
        if(instance == null)
            instance = new Tablero();
        
        return instance;
    }
    
    public Casilla getCasilla(int i, int j) {
        return casillas[i][j];
    }
    
    //Para sacar el id de cada casilla
    public Casilla buscarCasilla(){
        //De momento no sabemos que pasamos por referencia
        return null;
    }
    
    public void meterPiezaEnCasilla(int fila, int columna, Pieza p){
        casillas[fila][columna].setPieza(p);
    }
    
    //Metodo para crear el tablero
    public void iniciarTablero(int orientacion){
        this.orientacion = orientacion;
        for (int i = 0; i < casillas.length; i++) {
            for (int j = 0; j < casillas.length; j++) {
                casillas[i][j] = new Casilla();
                casillas[i][j].setFila(i);
                casillas[i][j].setColumna(j);
            }
        }
    }
}
