package com.example.das_entregagrupal.Modelo;

import java.util.ArrayList;
import java.util.Iterator;

public class ListaFichas {

    private ArrayList<Ficha> lista;

    public ListaFichas(){
        this.lista = new ArrayList<Ficha>();
    }

    public int obtenerNumFichas(){
        return this.lista.size();
    }

    public void anadirFicha(Ficha pFicha){
        this.lista.add(pFicha);
    }

    public void eliminarComeplomo(){
        Iterator<Ficha> itr = this.lista.iterator();
        Ficha f;
        boolean encontrado = false;
        while(itr.hasNext()&&!encontrado) {
            f = itr.next();
            if(f.getTipo()=='C') {
                this.lista.remove(f);
                encontrado = true;
            }
        }
    }

    public Ficha obtenerPrimerFicha(){
        return this.lista.get(0);
    }

    public boolean hayComeplomo() {
        for(Ficha i:lista) {
            if(i.getTipo()=='C') {
                return true;
            }
        }
        return false;
    }

    public int obtenerTotalComeplomos() {
        Iterator<Ficha> itr = this.lista.iterator();
        Ficha f;
        int total = 0;
        while(itr.hasNext()) {
            f = itr.next();
            if(f.getTipo()=='C') {
                total++;
            }
        }
        return total;
    }

    public void resetear() {
        lista.clear();
    }
}

