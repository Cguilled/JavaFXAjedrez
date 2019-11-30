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
public class Peon extends Pieza{
    private int orientacion;

    public int getOrientacion() {
        return orientacion;
    }

    public void setOrientacion(int orientacion) {
        this.orientacion = orientacion;
    }
    
    @Override
    public boolean mover(int origenX, int origenY, int destX, int destY) {
 
        if(this.orientacion==0) {
            int distancia = 1;
            if(origenX==1 && destX==3)
                distancia=2;
            if(getColor().equals("negro")) {
                distancia = -1;
                if(origenX==6 && destX==4)
                    distancia=-2;
            }
                 
            if(origenY==destY && (destX-origenX)==distancia)
                return true;
            else if(Math.abs(destY-origenY)==1 && (destX-origenX)==distancia && Math.abs(distancia)==1)
                return true;

            else
                return false;
            
        }else {
            int distancia = -1;
            if(origenX==6 && destX==4)
                distancia=-2;
            if(getColor().equals("negro")) {
                distancia = 1;
                if(origenX==1 && destX==3)
                    distancia=2;
            }
            
            if(origenY==destY && (destX-origenX)==distancia)
                return true;
            else if(Math.abs(destY-origenY)==1 && (destX-origenX)==distancia && Math.abs(distancia)==1)
                return true;
            else
                return false;
        }
    }
    
    public Boolean esMovimientoDiagonal(int origenX, int origenY, int destX, int destY) {
        int distancia;
        if(orientacion == 0){
            //Variable para identificar si el peon se mueve hacia arriba o abajo
            distancia = 1;
            if(getColor().equals(NEGRO))
                distancia = -1;
            
            if(Math.abs(destY-origenY)==1 && (destX-origenX)==distancia)
                return true;
            else
                return false;
        }else {
            distancia = -1;
            if(getColor().equals(NEGRO))
                distancia = 1;

            if(Math.abs(destY-origenY)==1 && (destX-origenX)==distancia)
                return true;
            else
                return false;
        }
    }
}
