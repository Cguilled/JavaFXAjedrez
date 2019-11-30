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
public class Caballo {
    public static Boolean Mover(int origenX, int origenY, int destX, int destY){
        if((Math.abs(origenX - destX) == 2 && Math.abs(origenY - destY) ==1) ||
                (Math.abs(origenX - destX) == 1 && Math.abs(origenY - destY) ==2))
            return true;
        else
            return false;
    }
}
