package com.example.das_entregagrupal.Modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Tablero {

    private ArrayList<ListaCasillas> matriz;
    private static Tablero miTablero;

    private Tablero() {
        matriz = new ArrayList<ListaCasillas>();
    }

    public static Tablero getTablero() {
        if (miTablero == null) {
            miTablero = new Tablero();
        }
        return miTablero;
    }

    public boolean colocarFicha(int pColumna, int pIdJugador) {
        int fila = matriz.get(pColumna - 1).buscarHueco();
        if (fila != -1) {
            Casilla c = matriz.get(pColumna - 1).buscarCasilla(fila);
            if (pIdJugador == 1) {
                c.cambiarEstado('1');
            } else {
                c.cambiarEstado('2');
            }
            return true;
        } else {
//            JOptionPane.showMessageDialog(null, "Columna llena.");
            return false;
        }
    }

    public void colocarComeplomo(int pColumna) {
        int fila = matriz.get(pColumna - 1).buscarHueco();
        if (fila == 5) {
        } else if (fila == 4) {
            matriz.get(pColumna - 1).buscarCasilla(fila + 1).cambiarEstado('V');
        } else {
            matriz.get(pColumna - 1).buscarCasilla(fila + 1).cambiarEstado('V');
            matriz.get(pColumna - 1).buscarCasilla(fila + 2).cambiarEstado('V');
        }
    }

    public void inicializarTablero() {
        ListaCasillas l;
        for (int i = 0; i < 7; i++) {
            l = new ListaCasillas();
            for (int j = 0; j < 6; j++) {
                l.anadirCasilla();
            }
            l.revertir();
            this.matriz.add(l);
        }
    }

    public boolean hayHuecoEn(int i) {
        if (matriz.get(i - 1).buscarHueco() != -1) {
            return false;
        }
        return true;
    }

    public boolean noHayHueco() {
        boolean nohueco = true;
        for (int i = 0; i <= 6; i++) {
            if (this.matriz.get(i).buscarHueco() != -1) {
                nohueco = false;
            }
        }
        return nohueco;
    }

    public void resetear() {
        for (ListaCasillas l : this.matriz) {
            l.resetear();
        }
        Partida.getPartida().resetearNumTurno();
    }

    public boolean comprobarCuatro(int pId) {

        /////////////////////////////// VERTICAL ///////////////////////////////

        char id;
        if (pId == 1) {
            id = '1';
        } else {
            id = '2';
        }
        for (int i = 0; i < 7; i++) {
            if (matriz.get(i).comprobarCuatroV(id)) {
                return true;
            }
        }
        boolean salir = false;
        int i = 0;
        int j = 0;
        int cont = 0;

        //////////////////////////// HORIZONTAL ///////////////////////////////

        while (j < this.matriz.get(0).obtenerNumCasillas() && !salir) {
            while (i < this.matriz.size() && !salir) {
                if (this.matriz.get(i).buscarCasilla(j).getEstado() == id) {
                    cont++;
                    i++;
                    if (cont == 4) {
                        salir = true;
                    }
                } else {
                    cont = 0;
                    i++;
                }
            }
            i = 0;
            j++;
        }
        if (salir) {
            return true;
        }

        ///////////////////////////// DIAGONAL ////////////////////////////////

        salir = false;
        i = 0;
        int fila = 0;
        j = 0;
        int columna = 0;
        cont = 0;
        while (fila < this.matriz.get(0).obtenerNumCasillas() - 3 && !salir) {
            while (columna < this.matriz.size() - 3 && !salir) {
                if (this.matriz.get(i).buscarCasilla(j).getEstado() == id) {
                    cont++;
                    i++;
                    j++;
                    if (cont == 4) {
                        salir = true;
                    }
                } else {
                    cont = 0;
                    columna++;
                    i = columna;
                    j = fila;
                }
            }
            fila++;
            columna = 0;
            i = columna;
            j = fila;
        }
        if (salir) {
            return true;
        }
        salir = false;
        i = this.matriz.size() - 1;
        fila = 0;
        j = 0;
        columna = this.matriz.size() - 1;
        cont = 0;

        ////////////////////////////////////////////////////////////////////////

        while (fila < this.matriz.get(0).obtenerNumCasillas() - 3 && !salir) {
            while (columna > 2 && !salir) {
                if (this.matriz.get(i).buscarCasilla(j).getEstado() == id) {
                    cont++;
                    i--;
                    j++;
                    if (cont == 4) {
                        salir = true;
                    }
                } else {
                    cont = 0;
                    columna--;
                    i = columna;
                    j = fila;
                }
            }
            fila++;
            columna = this.matriz.size() - 1;
            i = columna;
            j = fila;
        }
        if (salir) {
            return true;
        }
        return false;
    }

    public void romperColumna(int pColumna) {
        this.matriz.get(pColumna).resetear();
    }

    public int piquete() {
        ArrayList<Integer> columnasDisp = new ArrayList<Integer>();
        for (int i = 0; i < 7; i++) {
            columnasDisp.add(i);
        }
        boolean salir = false;
        int i = 0;
        Random rand = new Random();
        while (!salir) {
            i = rand.nextInt(columnasDisp.size() - 1);
            if (this.matriz.get(i).buscarHueco() != -1) {
                salir = true;
            } else {
                columnasDisp.remove(i);
            }
        }
        int desde = this.matriz.get(i).buscarHueco();
        int aleat;
        if (desde == 0) {
            aleat = 0;
        } else {
            aleat = rand.nextInt(desde);
        }
        this.matriz.get(i).buscarCasilla(aleat).cambiarEstado('P');
//        if (idioma == "Espanol") {
////            JOptionPane.showMessageDialog(null, "¡Vaya! Alguien ha bloqueado la casilla (" + (7 - (aleat + 1)) + ", "
////                    + (i + 1) + " ). Espero que no os haya molestado.");
//        } else {
////            JOptionPane.showMessageDialog(null, "¡Damn! Someone has blocked the (" + (7 - (aleat + 1)) + ", " + (i + 1)
////                    + " ) square. Hope it hasn't bothered you.");
//        }
        return 4;
    }

    private int[] probabilidadDeFichaEnColumnas(char jugador) {
        // matriz de casillas para manejo más cómodo
        char[][] m = new char[7][6];
        char[][] mInv = new char[7][6];

        // inicializarla
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                m[i][j] = matriz.get(i).buscarCasilla(j).getEstado();
                mInv[6 - i][j] = matriz.get(i).buscarCasilla(j).getEstado();
            }
        }

        // puntuación de cada casilla en base a 4 criterios
        int[] puntuacionVertical = probabilidadesVertical(m, jugador);
        int[] puntuacionHorizontal = probabilidadesHorizontal(m, jugador);
        int[] puntuacionDiagonal1 = probabilidadesDiagonal(m, jugador);
        int[] puntuacionDiagonal2 = probabilidadesDiagonal(mInv, jugador);

        // la suma de todos
        int[] suma = new int[7];
        for (int i = 0; i < 7; i++) {
            suma[i] = puntuacionVertical[i] + puntuacionHorizontal[i] + puntuacionDiagonal1[i]
                    + puntuacionDiagonal2[6 - i];
        }

        return suma;
    }

    private int[] probabilidadesVertical(char[][] matriz, char jugador) {
        int[] p = new int[7];

        for (int i = 0; i < 7; i++) {
            p[i] = 10 * fichasSeguidas(matriz[i], jugador); // la puntuación es 10 x las fichas que tenga
        }
        return p;
    }

    private int[] probabilidadesHorizontal(char[][] matriz, char jugador) {
        int[] p = new int[7];

        for (int i = 0; i < 7; i++) {
            int posFichaPosible = this.matriz.get(i).buscarHueco(); // coger el primer hueco de la columna

            if (posFichaPosible == -1) { // si no hay hueco, pasar a la siguiente
                p[i] = 0;
                continue;
            }

            // array desde esa posición a la izquierda
            ArrayList<Character> arrayIzq = new ArrayList<>();
            for (int i2 = i; i2 >= 0; i2--) {
                arrayIzq.add(new Character(matriz[i2][posFichaPosible]));
            }

            // array desde esa posición a la derecha
            ArrayList<Character> arrayDer = new ArrayList<>();
            for (int i2 = i; i2 < 7; i2++) {
                arrayDer.add(new Character(matriz[i2][posFichaPosible]));
            }

            // rellenar la probabilidad
            p[i] = 10 * (fichasSeguidas(arrayC2arrayc(arrayIzq), jugador)
                    + fichasSeguidas(arrayC2arrayc(arrayDer), jugador));
        }

        return p;
    }

    private int[] probabilidadesDiagonal(char[][] matriz, char jugador) {
        int[] p = new int[7];

        for (int i = 0; i < 7; i++) {
            int posFichaPosible = this.matriz.get(i).buscarHueco(); // coger el primer hueco de la columna

            if (posFichaPosible == -1) { // si no hay hueco, pasar a la siguiente
                p[i] = 0;
                continue;
            }

            // array desde esa posición a la izquierda + arriba
            ArrayList<Character> arrayIzqAr = new ArrayList<>();
            for (int offset = 1; i - offset >= 0 && posFichaPosible - offset >= 0; offset++) {
                arrayIzqAr.add(new Character(matriz[i - offset][posFichaPosible - offset]));
            }

            // array desde esa posición a la derecha + abajo
            ArrayList<Character> arrayDerAb = new ArrayList<>();
            for (int offset = 1; i + offset < 7 && posFichaPosible + offset < 6; offset++) {
                arrayDerAb.add(new Character(matriz[i + offset][posFichaPosible + offset]));
            }

            // rellenar la probabilidad
            p[i] = 10 * (fichasSeguidas(arrayC2arrayc(arrayIzqAr), jugador)
                    + fichasSeguidas(arrayC2arrayc(arrayDerAb), jugador));
        }

        return p;
    }

    private int fichasSeguidas(char[] array, char jugador) { // array desde la primera ficha posible puesta hasta la
        if (array.length == 0)
            return 0; // última puesta
        if (array[0] != 'V') // si no hay hueco, devolver 0
            return 0;

        // buscar el hueco más cercano disponible
        int posHueco = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] == 'V')
                posHueco++;
            else
                break;
        }

        // a partir de ahí, contar las fichas seguidas
        int fichasSeguidas = 0;
        for (int i = posHueco + 1; i < array.length; i++) {
            if (array[i] == jugador)
                fichasSeguidas++;
            else
                break;
        }

        return fichasSeguidas;
    }

    private char[] arrayC2arrayc(ArrayList<Character> array) {
        char[] resultado = new char[array.size()];

        for (int i = 0; i < array.size(); i++) {
            resultado[i] = array.get(i).charValue();
        }

        return resultado;
    }

    public int buscarHuecoMaquina(char jugador) {
        List<Integer> listaPuntuaciones = Arrays.stream(probabilidadDeFichaEnColumnas(jugador)).boxed()
                .collect(Collectors.toList()); // convertir a arraylist uwu
        return listaPuntuaciones.indexOf(Collections.max(listaPuntuaciones));
    }

    public int puedeGanar() {
        return mejorPuntuacionPosibleDelSiguienteTurno('2') >= 30 ? buscarHuecoMaquina('2') : -1;
    }

    public int puedeGanarEnemigo() {
        return mejorPuntuacionPosibleDelSiguienteTurno('1') >= 30 ? buscarHuecoMaquina('1') : -1;
    }

    private int mejorPuntuacionPosibleDelSiguienteTurno(char jugador) {
        List<Integer> listaPuntuaciones = Arrays.stream(probabilidadDeFichaEnColumnas(jugador)).boxed()
                .collect(Collectors.toList()); // convertir a arraylist uwu
        return Collections.max(listaPuntuaciones);
    }

    public int mereceComeplomo() {
        // matriz de casillas para manejo más cómodo
        char[][] m = new char[7][6];

        // inicializarla
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                m[i][j] = matriz.get(i).buscarCasilla(j).getEstado();
            }
        }

        // puntuación solo vertical
        int[] puntuacionVertical = probabilidadesVertical(m, '1');

        List<Integer> puntuacionLista = Arrays.stream(puntuacionVertical).boxed().collect(Collectors.toList());
        if (puntuacionLista.indexOf(30) != -1)
            return puntuacionLista.indexOf(30);
        else
            return puntuacionLista.indexOf(20);
    }

    public char getEstado(int fila, int columna) {
        return matriz.get(columna).buscarCasilla(fila).getEstado();
    }

}
