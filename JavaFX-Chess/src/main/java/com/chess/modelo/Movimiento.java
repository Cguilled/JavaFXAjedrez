/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chess.modelo;

import com.chess.modelo.piezas.Peon;
import com.chess.modelo.piezas.Pieza;

/**
 *
 * @author Guiller
 */
public class Movimiento {
    private final Casilla casillaOrigen;
    private final Casilla casillaDestino;
    private boolean comePieza;

    public boolean isComePieza() {
        return comePieza;
    }

    public Movimiento(Casilla casillaOrigen, Casilla casillaDestino) {
        this.casillaOrigen = casillaOrigen;
        this.casillaDestino = casillaDestino;
        this.comePieza = false;
    }
    
    public Boolean mueve(){
        int origenX = casillaOrigen.getFila();
        int origenY = casillaOrigen.getColumna();
        int destinoX = casillaOrigen.getFila();
        int destinoY = casillaOrigen.getColumna();
        Boolean puedeMover = this.casillaOrigen.getPieza().mover(origenX, origenY, destinoX, destinoY);
        
        if(puedeMover){
            Pieza piezaOrigen = this.casillaOrigen.getPieza();
            Pieza piezaDestino = this.casillaDestino.getPieza();
            if(piezaOrigen instanceof Peon){
                Peon peon = (Peon) piezaOrigen;
                //Si el movimiento es en diagonal, la pieza destino no es nula y su color es el contrario a la que hace el movimiento
                if(peon.esMovimientoDiagonal(origenX, origenY, destinoX, destinoY) && (piezaDestino != null) && (!piezaDestino.getColor().equals(piezaOrigen.getColor())))
                    //Puede comer
                    comePieza = true;
                //Si el movimiento es en diagonal, la pieza destino no es nula y su color es el mismo a la que hace el movimiento
                else if(peon.esMovimientoDiagonal(origenX, origenY, destinoX, destinoY) && (piezaDestino != null) && (piezaDestino.getColor().equals(piezaOrigen.getColor()))){
                    //No puede mover ni comer
                    comePieza = false;
                    puedeMover = false;
                    //Si el movimiento es en diagonal, la pieza destino es nula
                }else if(peon.esMovimientoDiagonal(origenX,origenY,destinoX,destinoY) && (piezaDestino == null)){
                    //No puede mover ni comer
                    comePieza = false;
                    puedeMover = false;
                }
            }else{
                //para resto de piezas que no sean peon
                //ver si comen
                //hay pieza en destino (se la come, no valido para peon)
                if(piezaDestino != null && (!piezaDestino.getColor().equals(piezaOrigen.getColor())))
                    //Puede comer
                    comePieza = true;
                else if(piezaDestino != null && (piezaDestino.getColor().equals(piezaOrigen.getColor()))){
                    //No puede mover ni comer
                    comePieza = false;
                    puedeMover = false;
                }else
                    comePieza = false;
            }
        }
        return puedeMover;
    }
    
    public Boolean analizaRecorrido(int origenX, int origenY, int destinoX, int destinoY){
        int xInicio, xFin, yInicio, yFin;
        //Retorno
        Boolean ret = true;
        if(origenX == destinoX){
            yInicio = origenY;
            yFin = destinoY;
            if(origenY > destinoY){
                yInicio = destinoY;
                yFin = origenY;                
            }

            for (int i = yInicio + 1; i < yFin; i++) {
                if(Tablero.getInstance().getCasilla(origenX, i).getPieza() != null)
                    ret = false;
            }
        }else if(origenY == destinoY){
            xInicio = origenX;
            xFin = destinoX;
            if(origenX > destinoX){
                xInicio = destinoX;
                xFin = origenX;                
            }

            for (int j = xInicio + 1; j < xFin; j++) {
                if(Tablero.getInstance().getCasilla(j, origenY).getPieza() != null)
                    ret = false;
            }
        }
        return ret;
    }
}