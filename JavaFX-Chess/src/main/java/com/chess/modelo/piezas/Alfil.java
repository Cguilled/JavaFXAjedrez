package com.chess.modelo.piezas;

import com.chess.modelo.Tablero;

/**
 *
 * @author Guiller
 */
public class Alfil extends Pieza{
    @Override
    public boolean mover(int origenX, int origenY, int destX, int destY){
        if(!analizaRecorrido(origenX, origenY, destX, destY))
            return false;
        
        if(Math.abs(origenX - destX) == Math.abs(origenY - destY))
            return true;
        else
            return false;
    }

    @Override
    public boolean analizaRecorrido(int origenX, int origenY, int destinoX, int destinoY){
        int xIni, xFin, yIni, yFin;
        if(Math.abs(origenX - destinoX) == Math.abs(origenY - destinoX)){
            xIni = origenX;
            xFin = destinoX;
            yIni = origenY;
            yFin = destinoY;
            if(origenX > destinoX){
                xIni = destinoX;
                xFin = origenX;                
            }
            if(origenY > destinoY){
                yIni = destinoY;
                yFin = origenY;                
            }
            
            //Al ser diagonal las coordenadas aumentan a la vez
            for (int j = xIni + 1, k = yIni + 1; j < xFin && k < yFin; j++, k++) {
                if(Tablero.getInstance().getCasilla(j, k).getPieza() != null)
                    return false;
            }
        }
        return true;
    }
}
