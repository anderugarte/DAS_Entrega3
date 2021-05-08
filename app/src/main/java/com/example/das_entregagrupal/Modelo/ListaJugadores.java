package com.example.das_entregagrupal.Modelo;

import java.util.ArrayList;

public class ListaJugadores {

    private static ListaJugadores miListaJugadores;
    private ArrayList<Jugador> lista;

    private ListaJugadores() {
        this.lista = new ArrayList<Jugador>();
    }

    public static ListaJugadores getListaJugadores() {
        if (miListaJugadores == null) {
            miListaJugadores = new ListaJugadores();
        }
        return miListaJugadores;
    }

    public int obtenerNumJugadores() {
        return this.lista.size();
    }

    public Jugador obtenerJugador(int pId) {
        for (Jugador j : lista) {
            if (j.getId() == pId) {
                return j;
            }
        }
        return null;
    }

    public void anadirJugador(Jugador pJugador) {
        if (!this.lista.contains(pJugador)) {
            this.lista.add(pJugador);
        } else {
            System.out.println("Lo sentimos, ese jugador ya existe.");
        }
    }

    public void borrarComeplomos() {
        for (int i = 0; i < 2; i++) {
            this.lista.get(i).getFichas().eliminarComeplomo();
        }
    }

    // Este mÃ©todo solo lo hemos utilizado para el Test, por lo que no constarÃ¡ en
    // nuestro Diagrama de Clases
    public void resetear() {
        this.lista.clear();
    }

}

