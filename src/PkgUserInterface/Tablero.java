package PkgUserInterface;

import java.awt.GridLayout;
import java.awt.event.*;
import java.util.Random;
import javax.swing.Timer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import PkgUserInterface.UI_Component.CustomJPanel;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import PkgLogic.NodoJugadores;
import PkgLogic.NodoNiveles;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Tablero extends JFrame {

    // private boolean partidaFinalizada = false;
    private boolean partidaIniciada = false;
    private static NodoNiveles niveles = null;

    // Items cabecera
    private static Timer contador;
    private JLabel lblContador;
    private JLabel lblBanderas;

    public JLabel getLblBanderas() {
        return lblBanderas;
    }

    public void setLblBanderas(JLabel lblBanderas) {
        this.lblBanderas = lblBanderas;
    }

    private int segundos;
    private int minutos;
    private int horas;

    public Tablero(NodoNiveles nivel, int filas, int columnas, NodoJugadores player) {
        setTitle("BUSCAMINAS - BIENVENIDO " + player.getNombre().toUpperCase());
        setBounds(0, 0, filas * 30, columnas * 32);
        // setBounds(0, 0, 752, 434);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        getContentPane().setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.NORTH);
        // ---------->>>>>>>>>AQUI AGREGAR CONTADOR

        ImageIcon clockIcon = new ImageIcon("src/images/reloj.png");
        JLabel clockLabel = new JLabel(clockIcon);
        panel.add(clockLabel);

        lblContador = new JLabel("00:00:00");
        panel.add(lblContador);

        ImageIcon flagIcon = new ImageIcon("src/images/bandera1.png");
        JLabel flagLabel = new JLabel(flagIcon);
        panel.add(flagLabel);

        // Contador de banderas
        lblBanderas = new JLabel(String.valueOf(nivel.getNumeroBanderas()[0]));
        panel.add(lblBanderas);

        JPanel panel_1 = new JPanel();
        getContentPane().add(panel_1, BorderLayout.CENTER);
        panel_1.setLayout(new GridLayout(filas, columnas, 1, 1));
        // panel_1.setLayout(new GridLayout(9, 9, 1, 1));

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                panel_1.add(nivel.getTablero()[i][j]);
            }
        }
        segundos = 0;
        minutos = 0;
        horas = 0;
        panel_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!partidaIniciada) {
                    comenzarContador();
                    partidaIniciada = true;
                }
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                getLblBanderas().setText(String.valueOf(nivel.getNumeroBombas()[0]));
            }
        });

    }

    private void comenzarContador() {
        contador = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                segundos++;
                if (segundos == 60) {
                    segundos = 0;
                    minutos++;
                    if (minutos == 60) {
                        minutos = 0;
                        horas++;
                    }
                }
                actualizarContador();
            }
        });
        contador.start();

    }

    private void actualizarContador() {
        String tiempo = String.format("%02d:%02d:%02d", horas, minutos, segundos);
        lblContador.setText(tiempo);
    }

    public static void main(String[] args) {
        int dificultad = 10;
        int filas = 0, columnas = 0;

        // 10 facil con 10B , 18 medio con 40B, 24 con 99B dificil
        if (dificultad <= 10) {
            filas = columnas = 10;
        } else if (10 < dificultad && dificultad < 70) {
            filas = columnas = 18;
        } else if (dificultad >= 70) {
            filas = columnas = 24;
        }

        generarNiveles(dificultad, filas, columnas);
        NodoNiveles aux = niveles;
        NodoJugadores p = new NodoJugadores("Pepe", 0);

        while (aux != null) {
            Tablero game = new Tablero(aux, filas, columnas, p);
            game.setVisible(true);

            while (game.isVisible()) {
                // Verificar si se ha perdido
                for (int i = 0; i < filas; i++) {
                    for (int j = 0; j < columnas; j++) {
                        if (aux.getTablero()[i][j].isFinPartida()) {
                            if (aux.getTablero()[i][j].isPartidaGanada()) {
                                JOptionPane.showMessageDialog(game, "USTED HA GANADO!", "PUNTUACION",
                                        JOptionPane.INFORMATION_MESSAGE);
                                aux = aux.getNodoSiguiente();
                                game.setVisible(false);
                                break;
                            } else {
                                JOptionPane.showMessageDialog(game, "USTED HA PERDIDO :(", "PUNTUACION",
                                        JOptionPane.INFORMATION_MESSAGE);
                                game.setVisible(false);
                                return;
                            }

                        }
                    }
                }
            }
        }

    }

    /**
     * Funcion para generar los 3 niveles dependiendo del grado de dificultad
     * 
     * @param dificultad
     * @param filas
     * @param columnas
     */
    public static void generarNiveles(int dificultad, int filas, int columnas) {

        // GENERAR LAS BOMBAS
        for (int i = 0; i < 3; i++) {
            CustomJPanel[][] tablero = new CustomJPanel[filas][columnas];
            NodoNiveles nivel = new NodoNiveles(tablero, null);

            for (int k1 = 0; k1 < filas; k1++) {
                for (int k2 = 0; k2 < columnas; k2++) {
                    tablero[k1][k2] = new CustomJPanel(k1, k2, 0, "src/images/celda.png", nivel.getNumeroBombas(),
                            nivel.getNumeroBanderas());

                }
            }
            // dificultad + (i * 3), aumento de bombas dependiendo de la dificultad
            for (int j = 0; j < dificultad + (i * 3); j++) {
                tablero[generateRandomNumber(0, filas - 1)][generateRandomNumber(0, columnas - 1)].setValorCelda(-1);
            }
            // GENERAR LAS TABLAS

            calcularValorCasilleroTablero(nivel, filas, columnas);

            // INSERTAR LOS NIVELES EN UNA LISTA
            niveles = niveles.insertarAlFinal(niveles, nivel);
        }
    }

    public static int generateRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    /**
     * Funcion para calcular el numero de bombas cercanas a cada casillero
     * 
     * @param nivel
     * @param filas
     * @param columnas
     */
    public static void calcularValorCasilleroTablero(NodoNiveles nivel, int filas, int columnas) {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {

                // calculo casillero
                int auxFila = i - 1, auxColumna = j - 1, contadorDeMinas = 0;

                // vamos iterar alrededor de cada casillero
                for (int k = 0; k < 3; k++) {
                    for (int k2 = 0; k2 < 3; k2++) {

                        // Tiene que estar dentro del limite del tablero
                        if (auxFila >= 0 && auxColumna >= 0 && auxFila < filas && auxColumna < columnas)
                            // contamos las minas alrededor del casillero actual
                            if (nivel.getTablero()[auxFila][auxColumna].getValorCelda() == -1)
                                contadorDeMinas++;
                        // iteramos columnas
                        auxColumna++;
                    }
                    // restauramos el puntero de columnas
                    auxColumna = j - 1;
                    // iteramos las filas
                    auxFila++;
                }

                // guardamos el contadorDeMinas en la posicion que no es una mina, para no
                // quitar las minas
                if (nivel.getTablero()[i][j].getValorCelda() != -1) {
                    CustomJPanel celda = new CustomJPanel(i, j, contadorDeMinas, "src/images/celda.png",
                            nivel.getNumeroBombas(), nivel.getNumeroBanderas());
                    nivel.getTablero()[i][j] = celda;

                    int fila = i;
                    int columna = j;
                    nivel.getTablero()[i][j].addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {

                            if (SwingUtilities.isLeftMouseButton(e)) {
                                // vamos mostrar todos los casilleros sin bombas adyacentes
                                if (nivel.getTablero()[fila][columna].getValorCelda() == 0)
                                    revelarVacio(nivel.getTablero(), fila, columna);
                            }

                        }
                    });

                    // vamos a contar el total de bombas en toda la tabla
                } else if (nivel.getTablero()[i][j].getValorCelda() == -1) {
                    nivel.getNumeroBombas()[0]++;
                    nivel.getNumeroBanderas()[0]++;
                }
            }
        }
    }

    /**
     * Función recursiva para explorar las casillas sin bombas
     * 
     * @param tablero
     * @param fila
     * @param columna
     */
    private static void revelarVacio(CustomJPanel[][] tablero, int fila, int columna) {
        // Verificar límites del tablero
        if (fila < 0 || fila >= tablero.length || columna < 0 || columna >= tablero.length) {
            return;
        }

        // Verificar si la casilla ya fue explorada
        if (tablero[fila][columna].getEstaRevelado() || tablero[fila][columna].getValorCelda() != 0) {
            tablero[fila][columna].cambiarFondo("src/images/celdaDescubierta.png");
            if (tablero[fila][columna].getValorCelda() != 0)
                tablero[fila][columna].getEtiquetaCasillero().setVisible(true);
            return;
        }

        // Marcar la casilla como explorada
        tablero[fila][columna].setEstaRevelado(true);

        revelarVacio(tablero, fila, columna + 1);
        revelarVacio(tablero, fila + 1, columna);
        revelarVacio(tablero, fila, columna - 1);
        revelarVacio(tablero, fila - 1, columna);

    }

}
