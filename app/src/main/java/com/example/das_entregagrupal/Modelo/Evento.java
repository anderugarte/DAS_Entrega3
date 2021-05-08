package com.example.das_entregagrupal.Modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Evento {

    private static Evento mEvento;
    private ArrayList<Integer> probabilidades;

    private Evento(){

        this.probabilidades = new ArrayList<Integer>();
        for(int i = 0; i < 5; i++){
            this.probabilidades.add(1);
        }
        for(int i = 0; i < 20; i++){
            this.probabilidades.add(3);
        }
        for(int i = 0; i < 20; i++){
            this.probabilidades.add(4);
        }
        for(int i = 0; i < 15; i++){
            this.probabilidades.add(7);
        }
    }

    public static Evento getEvento(){
        if(mEvento==null){
            mEvento = new Evento();
        }
        return mEvento;
    }

    public void generarEvento(String pIdioma){
        Collections.shuffle(this.probabilidades);
        int i = this.probabilidades.get(0);
        if (i == 1){
            this.viruseses(pIdioma);
        }
        if (i == 3){
            this.rompecolumnas(pIdioma);
        }
        if (i == 4){
            Tablero.getTablero().piquete(pIdioma);
        }
        if(i == 7){
            this.comeplomo(pIdioma);
        }
    }

    private void viruseses(String idioma){
        Tablero.getTablero().resetear();
        if (idioma == "Espanol") {
            //JOptionPane.showMessageDialog(null, "Achuuuusss! Ups se me han volado las fichas, vais a tener que volver a empezar...");
        }
        else {
            //JOptionPane.showMessageDialog(null, "Achoooooo! Tockens have flown away, you must start again...");
        }
    }

    private void rompecolumnas(String idioma){
        Random rand = new Random();
        int num = rand.nextInt(6);
        Tablero.getTablero().romperColumna(num);
        if (idioma == "Espanol") {
            //JOptionPane.showMessageDialog(null, "Parece que alguien a abducido la comuna " + (num+1) + " entera...");
        } else {
            //JOptionPane.showMessageDialog(null, "Seems like someone has abduced the " + (num + 1) + " column...");
        }
    }

    private void comeplomo(String idioma) {
        Ficha p = new Ficha('C');// de comeplomo
        ListaJugadores.getListaJugadores().obtenerJugador(1).getFichas().anadirFicha(p);
        ListaJugadores.getListaJugadores().obtenerJugador(2).getFichas().anadirFicha(p);
        if (idioma == "Espanol") {
            //JOptionPane.showMessageDialog(null, "Para vosotros, jugadores. Una ficha comeplomo pa' los dos.");
        } else {
            //JOptionPane.showMessageDialog(null, "For you, players. A Comeplomo tocken for the both of you.");

        }
    }

}
