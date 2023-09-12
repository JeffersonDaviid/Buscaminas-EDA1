package PkgLogic;

import javax.swing.JOptionPane;

public class PilaJugadores {

    // Creamos el arreglo Pila
    int[] Pila;
    // Creamos la variable auxiliar TOPE
    int TOPE;
    // Creamos la variable MAX
    int MAX;
    // Creamos un arreglo para almacenar los nombres
    String[] Nombres;

    // Constructor
    public PilaJugadores(int MAX) {
        this.TOPE = 0;
        this.MAX = MAX;
        this.Pila = new int[this.MAX + 1];
        this.Nombres = new String[this.MAX + 1]; // Inicializamos el arreglo de nombres
    }

    // Vaciamos la Pila regresando el TOPE a 0
    public void vaciarPila() {
        if (this.esPilaVacia()) {
            JOptionPane.showMessageDialog(null, "LA PILA ESTA VACIA");
        }
        this.TOPE = 0;
    }

    // Verificamos si la pila esta llena cuando el MAX es igual al TOPE y se verifica un desbordamiento
    public boolean esPilaLlena() {
        return this.MAX == this.TOPE;
    }

    // Verificamos si la pila esta vacia cuando el TOPE es igual a 0 y se verifica un subdesbordamiento
    public boolean esPilaVacia() {
        return this.TOPE == 0;
    }

    // INSERTAR PILA
    public void insertarPila() {
        if (this.esPilaLlena()) {
            JOptionPane.showMessageDialog(null, "LA PILA ESTA LLENA");
        } else {
            int DATO;
            String nombre; // Variable para almacenar el nombre
            DATO = Integer.parseInt(JOptionPane.showInputDialog(null, "INGRESAR EL DATO"));
            nombre = JOptionPane.showInputDialog(null, "INGRESAR EL NOMBRE DEL JUGADOR"); // Pedimos el nombre
            this.TOPE++;
            this.Pila[this.TOPE] = DATO;
            this.Nombres[this.TOPE] = nombre; // Almacenamos el nombre

            JOptionPane.showMessageDialog(null, "EL DATO " + DATO + " SE INSERTO EN LA PILA");
        }
    }

    // Método para obtener el nombre del jugador
    public String getNombre() {
        if (this.esPilaVacia()) {
            JOptionPane.showMessageDialog(null, "LA PILA ESTA VACIA");
            return null; // Si la pila está vacía, no hay nombre que obtener
        } else {
            return this.Nombres[this.TOPE];
        }
    }

}

