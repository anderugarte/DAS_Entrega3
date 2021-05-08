package com.example.das_entregagrupal.Modelo;

import java.util.Random;

public class JugadorIA extends Jugador {
    private int dificultad;

    public JugadorIA(int pId, int dif) {
        super(pId);
        dificultad = dif;
    }

    @Override
    public boolean colocarFicha(int i) {
        if (dificultad == 0) {
            colocarIATonta();
        } else {
            colocarIALista();
        }
        return true;

    }

    private void colocarIALista() {
        int i = Tablero.getTablero().puedeGanar();
        if (i != -1) {
            Tablero.getTablero().colocarFicha(i + 1, this.id);
        } else {
            i = Tablero.getTablero().puedeGanarEnemigo();
            if (i != -1) {
                Tablero.getTablero().colocarFicha(i + 1, this.id);
            } else {
                if (!this.fichas.hayComeplomo()) {
                    i = Tablero.getTablero().buscarHuecoMaquina('2');
                    Tablero.getTablero().colocarFicha(i + 1, this.id);
                } else {
                    i = Tablero.getTablero().mereceComeplomo();
                    if (i != -1) {
                        Tablero.getTablero().colocarComeplomo(i + 1);
                        ListaJugadores.getListaJugadores().obtenerJugador(2).getFichas().eliminarComeplomo();
                    } else {
                        i = Tablero.getTablero().buscarHuecoMaquina('2');
                        Tablero.getTablero().colocarFicha(i + 1, this.id);
                    }
                }
            }
        }

    }

    private void colocarIATonta() {
        Random ran = new Random();
        int i;
        do {
            i = (int) (ran.nextDouble() * 7 + 1);
        } while (Tablero.getTablero().hayHuecoEn(i));
        Tablero.getTablero().colocarFicha(i, this.id);

    }

}

