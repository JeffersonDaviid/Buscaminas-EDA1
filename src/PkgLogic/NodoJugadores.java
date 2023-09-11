package PkgLogic;

public class NodoJugadores {

    private String nombre;
    private float puntos;
    private NodoJugadores nodoLeft = null;
    private NodoJugadores nodoRight = null;

    public NodoJugadores(String nombre, float puntos) {
        this.nombre = nombre;
        this.puntos = puntos;
    }

    public static NodoJugadores insertarNodo(NodoJugadores ABB, String nombre, float puntos) {

        NodoJugadores hijo = new NodoJugadores(nombre, puntos);
        NodoJugadores aux = ABB;

        while (aux.getNodoRight() != null || aux.getNodoLeft() != null) {
            if (puntos > aux.getPuntos()) {
                if (aux.getNodoRight() == null)
                    break;
                aux = aux.getNodoRight();
            } else if (puntos <= aux.getPuntos()) {
                if (aux.getNodoLeft() == null)
                    break;
                aux = aux.getNodoLeft();
            }
        }

        if (puntos >= aux.getPuntos()) {
            aux.setNodoRight(hijo);
        }
        if (puntos < aux.getPuntos()) {
            aux.setNodoLeft(hijo);
        }

        return ABB;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPuntos() {
        return puntos;
    }

    public void setPuntos(float puntos) {
        this.puntos = puntos;
    }

    public NodoJugadores getNodoLeft() {
        return nodoLeft;
    }

    public void setNodoLeft(NodoJugadores nodoLeft) {
        this.nodoLeft = nodoLeft;
    }

    public NodoJugadores getNodoRight() {
        return nodoRight;
    }

    public void setNodoRight(NodoJugadores nodoRight) {
        this.nodoRight = nodoRight;
    }

    public static void main(String[] args) {

    }

}
