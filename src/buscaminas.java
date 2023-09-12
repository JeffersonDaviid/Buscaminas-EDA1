
import java.awt.GridLayout;

import javax.swing.JFrame;
import PkgUserInterface.UI_Component.CustomJPanel;

public class buscaminas extends JFrame {

    public buscaminas() {
        setLayout(new GridLayout(9, 9, 1, 1));
        setBounds(0, 0, 9 * 40, 9 * 40);

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setTitle("BUSCAMINAS");
        setBounds(0, 0, 752, 434);
        setResizable(false);
        setLocationRelativeTo(null);

        // JLabel lblNewLabel_1 = new JLabel("New label");
        // lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        // add(lblNewLabel_1);

        tablero[1][3] = -1;
        tablero[2][1] = -1;
        tablero[7][4] = -1;
        tablero[4][7] = -1;
        tablero[6][1] = -1;
        tablero[8][6] = -1;

        calcularValorCasilleroTablero(tablero);
        imprimirTablero(tablero);

        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {

                // CustomJPanel celda = new CustomJPanel(tablero[i][j], "src/images/celda.png");
                // add(celda);

            }
            // JOptionPane.showMessageDialog(panel, "hola");
        }
    }

    private static int[][] tablero = new int[9][9];

    public static void main(String[] args) {
        
    }

    public static void imprimirTablero(int[][] tablero) {
        System.out.println("TABLERO - BUSCAMINAS\n");

        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {

                System.out.print(tablero[i][j] + " | ");
            }
            System.out.println();
        }
    }

    public static void calcularValorCasilleroTablero(int[][] tablero) {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {

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