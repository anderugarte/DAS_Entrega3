package com.example.das_entregagrupal.Modelo;

public abstract class Jugador {
    protected int id;
    protected ListaFichas fichas;

    public Jugador(int pId) {
        this.id = pId;
        this.fichas = new ListaFichas();
    }

    public ListaFichas getFichas() {
        return this.fichas;
    }

    public int getId() {
        return this.id;
    }

    public abstract boolean colocarFicha(int i);
}
