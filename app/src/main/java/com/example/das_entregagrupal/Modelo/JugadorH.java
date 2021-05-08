package com.example.das_entregagrupal.Modelo;

public class JugadorH extends Jugador {

    private String nombre;

    public JugadorH(String pNombre, int pId) {
        super(pId);
        this.nombre = pNombre;
    }

    public String getNombre() {
        return this.nombre;
    }

    @Override
    public boolean colocarFicha(int i) {
        return Tablero.getTablero().colocarFicha(i, this.id);
    }
}
