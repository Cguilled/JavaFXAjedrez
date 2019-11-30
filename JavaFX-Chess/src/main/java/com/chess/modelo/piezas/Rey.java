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
public class Rey extends Pieza {

    @Override
    public boolean mover(int origenX, int origenY, int destX, int destY) {
        if(origenX == destX && Math.abs(origenY - destY) == 1)
            return true;
        else if(origenY == destY && Math.abs(origenX - destX) == 1)
            return true;
        else if((Math.abs(origenX - destX) == 1) && Math.abs(origenY - destY) == 1)
            return true;
        else
            return false;
    }

}
