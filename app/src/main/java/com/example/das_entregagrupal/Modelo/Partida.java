package com.example.das_entregagrupal.Modelo;

public class Partida {

    private int numTurno;
    private static Partida mPartida;

    private Partida() {
        this.numTurno = 0;
    }

    public static Partida getPartida() {
        if (mPartida == null) {
            mPartida = new Partida();
        }
        return mPartida;
    }

    public void inicializarJugadores(String nombre1, String nombre2) {
        ListaJugadores lista = ListaJugadores.getListaJugadores();

        JugadorH j1 = new JugadorH(nombre1, 1);
        JugadorH j2 = new JugadorH(nombre2, 2);
        lista.anadirJugador(j1);
        lista.anadirJugador(j2);
        Ficha f = new Ficha('N');// n de ficha tipo normal
        for (int i = 0; i < 42; i++) {
            j1.getFichas().anadirFicha(f);
            j2.getFichas().anadirFicha(f);
        }
    }

    public void inicializarJugadorYMaq(String nombre1, int dif) {
        ListaJugadores lista = ListaJugadores.getListaJugadores();
        JugadorH j1 = new JugadorH(nombre1, 1);
        JugadorIA j2 = new JugadorIA(2, dif);
        lista.anadirJugador(j1);
        lista.anadirJugador(j2);
        Ficha f = new Ficha('N');
        for (int i = 0; i < 42; i++) {
            j1.getFichas().anadirFicha(f);
            j2.getFichas().anadirFicha(f);
        }
    }

    public void resetearNumTurno() {
        this.numTurno = 0;
    }

}

