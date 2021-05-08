package com.example.das_entregagrupal.Modelo;

import java.util.ArrayList;
import java.util.Collections;

public class ListaCasillas {

    private ArrayList<Casilla> lista;

    public ListaCasillas(){
        this.lista = new ArrayList<Casilla>();
    }

    public int buscarHueco(){
        for (Casilla c : this.lista) {
            if (c.getEstado()=='V') {return c.getPosicionX();}
        }
        return -1;
    }

    public void resetear(){
        for (Casilla c : this.lista) {
            c.cambiarEstado('V');
        }
    }

    public int obtenerNumCasillas(){
        return this.lista.size();
    }

    public Casilla buscarCasilla(int pX){
        for (Casilla c : this.lista) {
            if (c.getPosicionX() == pX) {return c;}
        }
        return null;
    }

    public void anadirCasilla(){
        Casilla c = new Casilla ('V',this.lista.size());
        this.lista.add(c);
    }

    public void revertir() {
        Collections.reverse(this.lista);
    }

    public boolean comprobarCuatroV(char pId) {
        int cont=0;
        for(Casilla c:lista) {
            if(c.getEstado()==pId) {
                cont++;
                if(cont==4) {
                    return true;
                }
            }
            else {
                cont=0;
            }
        }
        return false;
    }

}
