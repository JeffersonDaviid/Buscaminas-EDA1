
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.Random;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import PkgLogic.NodoNiveles;
import PkgLogic.Player;
import UI_Component.CustomJPanel;

import javax.swing.JLabel;

public class Tablero extends JFrame {

    // Items cabecera
    private static Timer contador;
    private JLabel lblContador;
    private JLabel lblBanderas;

    private int segundos;
    private int minutos;
    private int horas;

    public Tablero() {
    }

    public Tablero(NodoNiveles nivel, int filas, int columnas, Player player) {
        setIconImage(new javax.swing.ImageIcon("src/images/bandera1.png").getImage());
        setTitle("PARTIDA DE " + player.getNombre().toUpperCase());
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
        comenzarContador();
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

        // Iniciar el temporizador
        contador.start();
    }

    private void actualizarContador() {
        String tiempo = String.format("%02d:%02d:%02d", horas, minutos, segundos);
        lblContador.setText(tiempo);
    }

    /**
     * Funcion para generar los 3 niveles dependiendo del grado de dificultad
     * 
     * @param dificultad
     * @param filas
     * @param columnas
     */
    public static NodoNiveles generarNiveles(int dificultad, int filas, int columnas) {
        NodoNiveles niveles = null;
        // GENERAR LAS BOMBAS
        for (int i = 0; i < 3; i++) {
            CustomJPanel[][] tablero = new CustomJPanel[filas][columnas];
            NodoNiveles nivel = new NodoNiveles(tablero, null);

            for (int k1 = 0; k1 < filas; k1++) {
                for (int k2 = 0; k2 < columnas; k2++) {
                    tablero[k1][k2] = new CustomJPanel(k1, k2, 0, "src/images/celda.png", nivel.getNumeroBombas(),
                            nivel.getNumeroBanderas(), nivel);

                }
            }
            // dificultad + (i * 3), aumento de bombas dependiendo de la dificultad
            for (int j = 0; j < dificultad + (i * 3); j++) {
                tablero[generateRandomNumber(0, filas - 1)][generateRandomNumber(0, columnas - 1)].setValorCelda(-1);
            }
            // GENERAR LAS TABLAS

            calcularValorCasilleroTablero(nivel, filas, columnas);

            // INSERTAR LOS NIVELES EN UNA LISTA
            niveles = nivel.insertarAlFinal(niveles, nivel);
        }

        return niveles;
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
                            nivel.getNumeroBombas(), nivel.getNumeroBanderas(), nivel);
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
            if (tablero[fila][columna].getValorCelda() != 0) {
                tablero[fila][columna].setEstaRevelado(true);
                tablero[fila][columna].getEtiquetaCasillero().setVisible(true);
            }
            return;
        }

        // Marcar la casilla como explorada
        tablero[fila][columna].setEstaRevelado(true);

        revelarVacio(tablero, fila, columna + 1);
        revelarVacio(tablero, fila + 1, columna);
        revelarVacio(tablero, fila, columna - 1);
        revelarVacio(tablero, fila - 1, columna);

    }

    public static int[] sumarTiempo(int hora, int minuto, int segundo, int horasASumar, int minutosASumar,
            int segundosASumar) {
        // Sumar segundos
        int nuevoSegundo = segundo + segundosASumar;
        int carrySegundos = nuevoSegundo / 60;
        nuevoSegundo %= 60;

        // Sumar minutos y tener en cuenta el carry de los segundos
        int nuevoMinuto = minuto + minutosASumar + carrySegundos;
        int carryMinutos = nuevoMinuto / 60;
        nuevoMinuto %= 60;

        // Sumar horas y tener en cuenta el carry de los minutos
        int nuevaHora = hora + horasASumar + carryMinutos;
        nuevaHora %= 24; // Asegurarse de que la hora esté en el rango de 0 a 23

        System.out.println("Hora actual: " + hora + ":" + minuto + ":" + segundo);
        System.out.println("Tiempo a sumar: " + horasASumar + " horas, " + minutosASumar + " minutos, " + segundosASumar
                + " segundos");
        System.out.println("Nueva hora: " + nuevaHora + ":" + nuevoMinuto + ":" + nuevoSegundo);

        int[] tiempo = { nuevaHora, nuevoMinuto, nuevoSegundo };
        return tiempo;

    }

    public JLabel getLblBanderas() {
        return lblBanderas;
    }

    public void setLblBanderas(JLabel lblBanderas) {
        this.lblBanderas = lblBanderas;
    }

    public int getSegundos() {
        return segundos;
    }

    public int getMinutos() {
        return minutos;
    }

    public int getHoras() {
        return horas;
    }
}
