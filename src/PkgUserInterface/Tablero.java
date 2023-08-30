package PkgUserInterface;

import java.awt.GridLayout;
import java.util.Random;

import javax.swing.JFrame;

import PkgUserInterface.UI_Component.CustomJPanel;
import java.awt.BorderLayout;
import javax.swing.JPanel;

import PkgLogic.NodoNiveles;

import javax.swing.JLabel;

public class Tablero extends JFrame {

    private boolean partidaFinalizada = false;
    private boolean partidaIniciada = false;
    private static int numeroBombas;
    private static NodoNiveles niveles = null;

    public Tablero(int[][] tablero, int filas, int columnas) {
        setTitle("BUSCAMINAS");
        setBounds(0, 0, filas * 30, columnas * 32);
        // setBounds(0, 0, 752, 434);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        getContentPane().setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.NORTH);
        // ---------->>>>>>>>>AQUI AGREGAR CONTADOR
        JLabel lblContador = new JLabel("CONTADOR");
        panel.add(lblContador);

        JPanel panel_1 = new JPanel();
        getContentPane().add(panel_1, BorderLayout.CENTER);
        panel_1.setLayout(new GridLayout(filas, columnas, 1, 1));
        // panel_1.setLayout(new GridLayout(9, 9, 1, 1));

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                CustomJPanel celda = new CustomJPanel(tablero[i][j], "src/images/celda.png");
                panel_1.add(celda);
            }
        }

    }

    public static void main(String[] args) {
        int dificultad = 10;
        int filas = 0, columnas = 0;

        // 10 facil con 10B , 18 medio con 40B, 24 con 99B dificil
        if (dificultad <= 10) {
            filas = columnas = 10;
        } else if (10 < dificultad && dificultad <= 30) {
            filas = columnas = 18;
        } else if (dificultad >= 70) {
            filas = columnas = 24;
        }
        generarNiveles(dificultad, filas, columnas);
        NodoNiveles aux = niveles;

        // while (aux != null) {
        Tablero game = new Tablero(aux.getTablero(), filas, columnas);
        game.setVisible(true);
        aux = aux.getNodoSiguiente();
        // }

    }

    public static void generarNiveles(int dificultad, int filas, int columnas) {

        int[][] tablero = new int[filas][columnas];

        // GENERAR LAS BOMBAS
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < dificultad + (i * 3); j++) {
                tablero[generateRandomNumber(0, filas - 1)][generateRandomNumber(0, columnas - 1)] = -1;
            }
            // GENERAR LAS TABLAS
            calcularValorCasilleroTablero(tablero, filas, columnas);

            // INSERTAR LOS NIVELES EN UNA LISTA
            NodoNiveles nivel = new NodoNiveles(tablero, null);
            nivel.setNumeroBombas(numeroBombas);
            niveles = niveles.insertarAlFinal(niveles, nivel);
        }

        // muestra las tablas
        NodoNiveles aux = niveles;
        while (aux != null) {
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    System.out.print(aux.getTablero()[i][j] + " | ");
                }
                System.out.println();
            }
            aux = aux.getNodoSiguiente();
            System.out.println();
            System.out.println();
        }

    }

    public static int generateRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    // 10 facil con 10 , 18 medio con 40, 24 con 99 dificil
    public static void calcularValorCasilleroTablero(int[][] tablero, int filas, int columnas) {
        numeroBombas = 0;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {

                // calculo casillero
                int auxFila = i - 1, auxColumna = j - 1, contador = 0;

                for (int k = 0; k < 3; k++) {
                    for (int k2 = 0; k2 < 3; k2++) {

                        // Tiene que estar dentro del limite del tablero
                        if (auxFila >= 0 && auxColumna >= 0 && auxFila < tablero.length
                                && auxColumna < tablero.length)
                            // contamos las minas alrededor del casillero actual
                            if (tablero[auxFila][auxColumna] == -1)
                                contador++;

                        auxColumna++;
                    }
                    auxColumna = j - 1;
                    auxFila++;
                }
                // guardamos el contador en la posicion que no es una mina, para no quitar las
                // minas
                if (tablero[i][j] != -1) {
                    tablero[i][j] = contador;
                } else {
                    numeroBombas++;
                }
            }
        }
    }

}
