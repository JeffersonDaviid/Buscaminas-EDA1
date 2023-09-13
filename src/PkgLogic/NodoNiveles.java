package PkgLogic;

import PkgUserInterface.UI_Component.CustomJPanel;

public class NodoNiveles {

    private int filas;
    private int columnas;
    private int dificultad;

    private int[] numeroBombas = { 0 };
    private int[] numeroBanderas = { 0 };

    private CustomJPanel[][] tablero;
    private NodoNiveles nodoSiguiente;

    public NodoNiveles(CustomJPanel[][] tablero, NodoNiveles nodoSiguiente) {
        this.tablero = tablero;
        this.nodoSiguiente = nodoSiguiente;
    }

    public static NodoNiveles insertarAlFinal(NodoNiveles primerNodo, NodoNiveles nodoParaInsertar) {
        if (primerNodo == null) {
            primerNodo = nodoParaInsertar;
            return primerNodo;
        }

        NodoNiveles nodoUltimo;
        nodoUltimo = primerNodo;

        // guardamos la posición del ultimo nodo
        while (nodoUltimo.getNodoSiguiente() != null)
            nodoUltimo = nodoUltimo.getNodoSiguiente();

        nodoUltimo.setNodoSiguiente(nodoParaInsertar);
        // nodoUltimo = nuevoNodo;
        return primerNodo;
    }

    public int[] getNumeroBanderas() {
        return numeroBanderas;
    }

    public void setNumeroBanderas(int[] numeroBandera) {
        this.numeroBanderas = numeroBandera;
    }

    public CustomJPanel[][] getTablero() {
        return tablero;
    }

    public void setTablero(CustomJPanel[][] tablero) {
        this.tablero = tablero;
    }

    public NodoNiveles getNodoSiguiente() {
        return nodoSiguiente;
    }

    public void setNodoSiguiente(NodoNiveles nodoSiguiente) {
        this.nodoSiguiente = nodoSiguiente;
    }

    public int getFilas() {
        return filas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }

    public void setNumeroBombas(int[] numeroBombas) {
        this.numeroBombas = numeroBombas;
    }

    public int[] getNumeroBombas() {
        return numeroBombas;
    }

    public int getDificultad() {
        return dificultad;
    }

    public void setDificultad(int dificultad) {
        this.dificultad = dificultad;
    }
}
