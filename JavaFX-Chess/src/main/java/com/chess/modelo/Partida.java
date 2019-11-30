/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chess.modelo;

import com.chess.modelo.piezas.Peon;
import com.chess.modelo.piezas.Pieza;
import com.chess.modelo.piezas.Reina;
import com.chess.modelo.piezas.Rey;
import java.util.ArrayList;

/**
 *
 * @author Guiller
 */
public class Partida {

    private String idPartida = "p";
    ArrayList<Movimiento> movimientos = new ArrayList<>();
    ArrayList<Pieza> piezasComidas = new ArrayList<>();
    private String turno = "blanco";    //Empieza siempre en blanco
    private Integer contTurno = 0;      //Contador de turnos
    private boolean partidaFinalizada = false;
    private Tablero tablero;

    public boolean mover(Casilla casillaOrigen, Casilla casillaDestino/*, actualizarGrafico*/) {
        boolean exito = false;
        if (this.partidaFinalizada) {
            return exito;
        }

        Movimiento movimiento = new Movimiento(casillaOrigen, casillaDestino);
        Integer origenX = casillaOrigen.getFila();
        Integer origenY = casillaOrigen.getColumna();
        Integer destinoX = casillaDestino.getFila();
        Integer destinoY = casillaDestino.getColumna();

        //var recorridoDisponible = this.analizaRecorrido(origenX, origenY, destinoX, destinoY);
        //var puede = recorridoDisponible && movimiento.mueve();
        boolean puede = movimiento.mueve();
        System.out.println("Puede: " + puede);

        if (puede && casillaOrigen.getPieza().getColor().equals(this.turno)) {
            if (movimiento.isComePieza()) {
                piezasComidas.add(casillaDestino.getPieza());
                //Si se comen al rey
                if (casillaDestino.getPieza() instanceof Rey) {
                    System.out.println("ganaste");
                    this.partidaFinalizada = true;
                }

                System.out.println("Piezas comidas: " + this.piezasComidas);
                //Para pintar las piezas comidas
                //this.vista.pintarPiezasComidas(this.piezasComidas);
            }

            //mover graficamente
            /*if(actualizarGrafico) 
                this.vista.actualizarTableroMovimiento(casillaOrigen,casillaDestino);*/
            //Para pintar las piezas comidas
            //this.vista.pintarPiezasComidas(this.piezasComidas);
            //mover
            Pieza piezaOrigen = casillaOrigen.getPieza();
            casillaOrigen.setPieza(null);
            casillaDestino.setPieza(piezaOrigen);
            this.movimientos.add(movimiento);

            //Si la pieza que llega al final es peon
            if (piezaOrigen instanceof Peon) {
                //Se convierte en reina
                Reina reina = new Reina();

                if (piezaOrigen.getColor().equals("negro") && (destinoX == 7 || destinoX == 0)) {
                    reina.setLetra("w");
                    reina.setColor("negro");
                    casillaDestino.setPieza(reina);
                    //this.vista.pintarPieza(casillaDestino);
                } else if (piezaOrigen.getColor().equals("blanco") && (destinoX == 7 || destinoX == 0)) {
                    System.out.println("Peon se convierte en reina");
                    reina.setLetra("q");
                    reina.setColor("blanco");
                    casillaDestino.setPieza(reina);
                    //this.vista.pintarPieza(casillaDestino);
                }
            }

            //cambio turno
            if (this.turno.equals("blanco")) {
                this.turno = "negro";
            } else {
                this.turno = "blanco";
                this.contTurno++;
                System.out.println("Turno numero: " + this.contTurno);
            }

            //this.vista.actualizarTurno(this.contTurno, this.turno);
            System.out.println("Movimientos: " + this.movimientos);
            exito = true;
            //this.saveSessionStorage();

        }
        return exito;
    }

    public boolean puedeMover(Casilla casillaOrigen, Casilla casillaDestino) {
        boolean exito = false;
        if (this.partidaFinalizada) {
            return false;
        }

        Movimiento movimiento = new Movimiento(casillaOrigen, casillaDestino);
        Integer origenX = casillaOrigen.getFila();
        Integer origenY = casillaOrigen.getColumna();
        Integer destinoX = casillaDestino.getFila();
        Integer destinoY = casillaDestino.getColumna();

        //var recorridoDisponible = this.analizaRecorrido(origenX, origenY, destinoX, destinoY);
        //var puede = recorridoDisponible && movimiento.mueve();
        boolean puede = movimiento.mueve();

        if (puede && casillaOrigen.getPieza().getColor().equals(this.turno)) {
            exito = true;
        }

        return exito;
    }

    public boolean analizaRecorrido(Integer origenX, Integer origenY, Integer destinoX, Integer destinoY) {
        Integer xIni, xFin, yIni, yFin;
        if (origenX == destinoX) {
            yIni = origenY;
            yFin = destinoY;
            if (origenY > destinoY) {
                yIni = destinoY;
                yFin = origenY;
            }

            for (int i = yIni; i < yFin; i++) {
                if (this.tablero.getCasilla(origenX, i).getPieza() != null) {
                    return false;
                }
            }
        } else if (origenY == destinoY) {
            xIni = origenX;
            xFin = destinoX;
            if (origenX > destinoX) {
                xIni = destinoX;
                xFin = origenX;
            }

            for (int j = xIni + 1; j < xFin; j++) {
                if (this.tablero.getCasilla(j, origenY).getPieza() != null) {
                    return false;
                }
            }
        }
        return true;
    }

    //Function that saves the array movimientos in the SessionStorage
    //Habrá que empezar a programar en ingles en algun momento  
    /*this.saveSessionStorage  = function(){
        console.log("pActual: " + this);
        window.sessionStorage.setItem(this.idPartida, JSON.stringify(this));
    }*/
    public Tablero iniciarPartida(Integer orientacion) {
        String[][] letrasBlanco = {{"r", "b", "h", "q", "k", "h", "b", "r"}, {"p", "p", "p", "p", "p", "p", "p", "p"}};
        String[][] letrasNegro = {{"t", "n", "j", "w", "l", "j", "n", "t"}, {"o", "o", "o", "o", "o", "o", "o", "o"}};

        //Creo el tablero que creara las casillas
        Tablero tablero = new Tablero();
        tablero.iniciarTablero(orientacion);

        String color1 = "blanco", color2 = "negro";
        //Tendrian que ser arrays bidimensionales
        String[][] letrasColor1 = letrasBlanco;
        String[][] letrasColor2 = letrasNegro;

        if (orientacion == 1) {
            color1 = "negro";
            //letrasColor1 = letrasNegro;
            color2 = "blanco";
            //letrasColor2 = letrasBlanco;
        }

        //Creo las piezas
        for (int i = 0; i < 8; i++) { //recorro filas
            for (int j = 0; j < 8; j++) {
                String color, letra;
                Pieza pieza;
                if (i == 1 || i == 6) {
                    pieza = new Peon();
                    //pieza.setOrientacion(orientacion);
                    if (i == 1) {
                        color = color1;
                        letra = letrasColor1[1][j];
                    } else {
                        color = color2;
                        letra = letrasColor2[1][j];
                    }
                } else if (i == 0) {
                    color = color1;
                    letra = letrasColor1[0][j];
                } else if (i == 7) {
                    color = color2;
                    letra = letrasColor2[0][j];
                }

                /*if(i==0 || i==7) {
                    // ver columna
                    if(j==0 || j==7)	
                        p = new Torre();  

                    else if(j==1 || j==6)
                        var p = new Alfil();

                    else if(j==2 || j==5)
                        var p = new Caballo();

                    else if(j==4)
                        var p = new Rey();
                    else if(j==3)
                        var p = new Reina();
                }
				
                if (p != null) {
                    p.setLetra(letra);
                    p.setColor(color);
                    this.tablero.meterPiezaEnCasilla(i, j, p);
                } else 
                    this.tablero.meterPiezaEnCasilla(i, j, null);
                */
            }
        }
        //return this.tablero; //Devuelvo el tablero
        return null;
    }
    
    public void finalizarPartida(){
        this.partidaFinalizada = true;
    }
}
