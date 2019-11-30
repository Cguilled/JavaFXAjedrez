/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chess.modelo.piezas;

import com.chess.modelo.Tablero;

/**
 *
 * @author Guiller
 */
public class Torre extends Pieza{
    @Override
    public boolean mover(int origenX, int origenY, int destX, int destY){
        if(!this.analizaRecorrido(origenX, origenY, destX, destY))
            return false;
        
        if(origenX==destX || origenY==destY)
            return true;
        else
            return false;
    }
    
    @Override
    public boolean analizaRecorrido(int origenX, int origenY, int destinoX, int destinoY){
        int xIni, xFin, yIni, yFin;
        if(origenX == destinoX){
            yIni = origenY;
            yFin = destinoY;
            if(origenY > destinoY){
                yIni = destinoY;
                yFin = origenY;                
            }

            for (int i = yIni + 1; i < yFin; i++) {
                if(Tablero.getInstance().getCasilla(origenX, i).getPieza() != null)
                    return false;
            }
        }else if(origenY == destinoY){
            xIni = origenX;
            xFin = destinoX;
            if(origenX > destinoX){
                xIni = destinoX;
                xFin = origenX;                
            }

            for (int j = xIni + 1; j < xFin; j++) {
                if(Tablero.getInstance().getCasilla(j, origenY).getPieza() != null)
                    return false;
            }
        }
        return true;
    }
}
