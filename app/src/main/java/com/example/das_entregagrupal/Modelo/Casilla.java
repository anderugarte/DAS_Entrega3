package com.example.das_entregagrupal.Modelo;

public class Casilla {
    private char estado;
    private int posicionX;

    public Casilla(char pEstado, int pPosicionX){
        this.estado = pEstado;			// un 1 si esa casilla esta ocupada por una ficha del jugador 1, un 2 en caso de que sea el jugador 2 o un V si esta vacia
        this.posicionX = pPosicionX;	// Fila
    }

    public char getEstado(){
        return this.estado;
    }

    public int getPosicionX(){
        return this.posicionX;
    }

    public void cambiarEstado(char pEstado){
        this.estado = pEstado;
    }
}
