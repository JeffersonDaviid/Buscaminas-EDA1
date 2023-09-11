package PkgLogic;

public class Buscaminas {

    // Tamaño del tablero
    private static final int FILAS = 8;
    private static final int COLUMNAS = 8;

    // Tablero del juego
    private static char[][] tablero = new char[FILAS][COLUMNAS];

    // Función para inicializar el tablero
    private static void inicializarTablero() {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                tablero[i][j] = '*'; // '*' representa una casilla sin explorar
            }
        }
    }

    // Función para imprimir el tablero
    private static void imprimirTablero() {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Función recursiva para explorar las casillas sin bombas
    private static void explorar(int fila, int columna) {
        // Verificar límites del tablero
        if (fila < 0 || fila >= FILAS || columna < 0 || columna >= COLUMNAS) {
            return;
        }

        // Verificar si la casilla ya fue explorada
        if (tablero[fila][columna] != '*') {
            return;
        }

        // Marcar la casilla como explorada
        tablero[fila][columna] = ' ';

        // Verificar casillas adyacentes
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                explorar(fila + i, columna + j);
            }
        }
    }

    public static void main(String[] args) {
        inicializarTablero();
        imprimirTablero();

        // Simular una casilla sin bomba en la posición (3, 4)
        explorar(3, 4);

        System.out.println("Tablero después de explorar:");
        imprimirTablero();
    }
}
