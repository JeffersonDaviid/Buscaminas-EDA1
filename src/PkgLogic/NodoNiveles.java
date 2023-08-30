package PkgLogic;

import javax.swing.JOptionPane;

public class NodoNiveles {

    private int filas;
    private int columnas;
    private int numeroBombas;

    private int[][] tablero;
    private NodoNiveles nodoSiguiente;

    public NodoNiveles(int[][] tablero, NodoNiveles nodoSiguiente) {
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

    public int[][] getTablero() {
        return tablero;
    }

    public void setTablero(int[][] tablero) {
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

    public int getNumeroBombas() {
        return numeroBombas;
    }

    public void setNumeroBombas(int numeroBombas) {
        this.numeroBombas = numeroBombas;
    }
}
