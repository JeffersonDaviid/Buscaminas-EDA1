package PkgUserInterface;

import java.awt.GridLayout;
import java.util.Random;

import javax.swing.JFrame;

import PkgUserInterface.UI_Component.CustomJPanel;
import java.awt.BorderLayout;
import javax.swing.JPanel;

import PkgLogic.NodoNiveles;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Tablero extends JFrame {

    private boolean partidaFinalizada = false;
    private boolean partidaIniciada = false;
    private static int numeroBanderas;
    private static NodoNiveles niveles = null;

    public Tablero(CustomJPanel[][] tablero, int filas, int columnas) {
        setTitle("BUSCAMINAS");
        setBounds(0, 0, filas * 30, columnas * 32);
        // setBounds(0, 0, 752, 434);
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
                panel_1.add(tablero[i][j]);
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

        while (aux != null) {
            Tablero game = new Tablero(aux.getTablero(), filas, columnas);
            game.setVisible(true);

            while (game.isVisible()) {
                // Verificar si se ha perdido
                for (int i = 0; i < filas; i++) {
                    for (int j = 0; j < columnas; j++) {
                        if (aux.getTablero()[i][j].getFinPartida()) {
                            JOptionPane.showMessageDialog(game, "USTED HA PERDIDO :(", "PUNTUACION",
                                    JOptionPane.INFORMATION_MESSAGE);
                            game.setVisible(false);
                            return;
                        }
                    }
                }

                // Verificar si se ha ganado
                if (aux.getNumeroBombas()[0] == 0) {
                    int contador = 0;

                    for (int i = 0; i < filas; i++) {
                        for (int j = 0; j < columnas; j++) {
                            if (aux.getTablero()[i][j].getValorCelda() == -1) {
                                contador++;
                                if (aux.getTablero()[i][j].getEsBandera()) {
                                    aux.getNumeroBombas()[0]++;
                                }
                            }
                        }
                    }

                    if (contador == aux.getNumeroBombas()[0]) {
                        JOptionPane.showMessageDialog(game, "USTED HA GANADO!", "PUNTUACION",
                                JOptionPane.INFORMATION_MESSAGE);
                        aux = aux.getNodoSiguiente();
                        break;
                    }
                }
            }
        }

    }

    public static void generarNiveles(int dificultad, int filas, int columnas) {

        // GENERAR LAS BOMBAS
        for (int i = 0; i < 3; i++) {
            CustomJPanel[][] tablero = new CustomJPanel[filas][columnas];
            NodoNiveles nivel = new NodoNiveles(tablero, null);

            for (int k1 = 0; k1 < filas; k1++) {
                for (int k2 = 0; k2 < columnas; k2++) {
                    tablero[k1][k2] = new CustomJPanel(0, "src/images/cel da.png", nivel.getNumeroBombas());
                }
            }
            for (int j = 0; j < dificultad + (i * 3); j++) {
                tablero[generateRandomNumber(0, filas - 1)][generateRandomNumber(0, columnas - 1)].setValorCelda(-1);
            }
            // GENERAR LAS TABLAS
            nivel.setNumeroBombas(calcularValorCasilleroTablero(nivel, filas, columnas));

            // INSERTAR LOS NIVELES EN UNA LISTA
            niveles = niveles.insertarAlFinal(niveles, nivel);
        }

        // muestra las tablas
        NodoNiveles aux = niveles;
        while (aux != null) {
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    System.out.print(aux.getTablero()[i][j].getValorCelda() + " | ");
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
    public static int[] calcularValorCasilleroTablero(NodoNiveles nivel, int filas, int columnas) {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {

                // calculo casillero
                int auxFila = i - 1, auxColumna = j - 1, contador = 0;

                for (int k = 0; k < 3; k++) {
                    for (int k2 = 0; k2 < 3; k2++) {

                        // Tiene que estar dentro del limite del tablero
                        if (auxFila >= 0 && auxColumna >= 0 && auxFila < filas && auxColumna < columnas)
                            // contamos las minas alrededor del casillero actual
                            if (nivel.getTablero()[auxFila][auxColumna].getValorCelda() == -1)
                                contador++;

                        auxColumna++;
                    }
                    auxColumna = j - 1;
                    auxFila++;
                }

                // guardamos el contador en la posicion que no es una mina, para no quitar las
                // minas
                if (nivel.getTablero()[i][j].getValorCelda() != -1) {
                    CustomJPanel celda = new CustomJPanel(contador, "src/images/celda.png", nivel.getNumeroBombas());
                    nivel.getTablero()[i][j] = celda;

                } else if (nivel.getTablero()[i][j].getValorCelda() == -1) {
                    nivel.getNumeroBombas()[0]++;
                }
            }
        }
        return nivel.getNumeroBombas();
    }

}
