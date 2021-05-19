package com.example.das_entregagrupal.Modelo;

public class Partida {

    private int numTurno;
    private int turnoJugador;
    private int marcadorJ1;
    private int marcadorJ2;
    private static Partida mPartida;

    private Partida() {
        this.numTurno = 0;
        this.turnoJugador = 1;
        this.marcadorJ1 = 0;
        this.marcadorJ2 = 0;
    }

    public static Partida getPartida() {
        if (mPartida == null) {
            mPartida = new Partida();
        }
        return mPartida;
    }

    public boolean partidaAcabada() {
        if (Tablero.getTablero().comprobarCuatro(this.turnoJugador) || Tablero.getTablero().noHayHueco()) {
            return true;
        }
        return false;
    }

    public void inicializarTablero() {
        Tablero.getTablero().inicializarTablero();
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

    // Estos 2 mÃ©todos solo se utilizarÃ¡n para las pruebas por lo que no
    // aparecerÃ¡n en nuestro Diagrama de Clases.
    public int getNumTurno() {
        return this.numTurno;
    }

    public void aumentarNumTurno() {
        this.numTurno++;
    }

}

