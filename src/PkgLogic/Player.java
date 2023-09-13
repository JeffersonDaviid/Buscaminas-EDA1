package PkgLogic;

public class Player {
    private String nombre;
    private int[] puntuacion = new int[3];

    public Player(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int[] getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int[] puntuacion) {
        this.puntuacion = puntuacion;
    }

}
