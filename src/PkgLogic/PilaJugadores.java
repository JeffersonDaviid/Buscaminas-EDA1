package PkgLogic;

import javax.swing.JOptionPane;

public class PilaJugadores {

    // Creamos el arreglo Pila
   public Player[] Pila;
    // Creamos la variable auxiliar TOPE
    public int TOPE;
    // Creamos la variable MAX
    public int MAX;

    // Constructor
    public PilaJugadores(int MAX) {
        this.TOPE = 0;
        this.MAX = MAX;
        this.Pila = new Player[this.MAX + 1];
    }

    // Vaciamos la Pila regresando el TOPE a 0
    public void vaciarPila() {
        if (this.esPilaVacia()) {
            JOptionPane.showMessageDialog(null, "LA PILA ESTA VACIA");
        }
        this.TOPE = 0;
    }

    // Verificamos si la pila esta llena cuando el MAX es igual al TOPE y se
    // verifica un desbordamiento
    public boolean esPilaLlena() {
        return this.MAX == this.TOPE;
    }

    // Verificamos si la pila esta vacia cuando el TOPE es igual a 0 y se verifica
    // un subdesbordamiento
    public boolean esPilaVacia() {
        return this.TOPE == 0;
    }

    // INSERTAR PILA
    public void insertarPila(Player player) {
        if (this.esPilaLlena()) {
            JOptionPane.showMessageDialog(null, "LA PILA ESTA LLENA");
        } else {
            this.TOPE++;
            this.Pila[this.TOPE] = player;
            JOptionPane.showMessageDialog(null, "EL DATO " + player.getNombre().toUpperCase() + " SE INSERTO EN LA PILA");
        }
    }

}
