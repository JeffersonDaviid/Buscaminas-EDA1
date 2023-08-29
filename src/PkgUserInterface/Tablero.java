package PkgUserInterface;

import java.awt.GridLayout;

import javax.swing.JFrame;

import PkgUserInterface.UI_Component.CustomJPanel;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class Tablero extends JFrame {

    private boolean partidaFinalizada = false;
    private static int[][] tablero = new int[9][9];

    public Tablero(int filas, int columnas) {
        setTitle("BUSCAMINAS");
        // setBounds(0, 0, filas * 40, columnas * 42);
        setBounds(0, 0, 752, 434);
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
        // panel_1.setLayout(new GridLayout(filas, columnas, 1, 1));
        panel_1.setLayout(new GridLayout(9, 9, 1, 1));

        tablero[1][3] = -1;
        tablero[2][1] = -1;
        tablero[7][4] = -1;
        tablero[4][7] = -1;
        tablero[6][1] = -1;
        tablero[8][6] = -1;

        calcularValorCasilleroTablero(tablero, filas, columnas);

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                CustomJPanel celda = new CustomJPanel(tablero[i][j], "src/images/celda.png");
                panel_1.add(celda);
            }
        }
    }

    public static void main(String[] args) {
        tablero[1][3] = -1;
        tablero[2][1] = -1;
        tablero[7][4] = -1;
        tablero[4][7] = -1;
        tablero[6][1] = -1;
        tablero[8][6] = -1;

        Tablero game = new Tablero(9, 9);
        game.setVisible(true);
    }

    public static void calcularValorCasilleroTablero(int[][] tablero, int filas, int columnas) {
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
                if (tablero[i][j] != -1)
                    tablero[i][j] = contador;
            }
        }
    }

}
