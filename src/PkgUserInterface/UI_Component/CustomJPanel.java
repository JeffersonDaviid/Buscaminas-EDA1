package PkgUserInterface.UI_Component;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class CustomJPanel extends JPanel {

    // informacion de la celda
    private BufferedImage image;
    private int valorCelda = 0;
    private int fila;
    private int columna;
    private boolean esBandera = false;
    private boolean estaRevelado = false;

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    private boolean finPartida = false;
    private boolean partidaGanada = false;

    private int numeroBanderasColocadas = 0;

    private String pathCelda = "src/images/celda.png";
    private String pathCeldaDescubierta = "src/images/celdaDescubierta.png";
    private String pathBandera = "src/images/bandera.png";
    private String pathBomba = "src/images/bomba.png";

    /**
     * FUNCION PARA COLOCAR EL CASILLERO SIN SEVELAR
     * 
     * @param imagePath
     */
    public CustomJPanel(String imagePath) {
        try {
            // this.image = ImageIO.read(new File(imagePath));
            setImage(ImageIO.read(new File(imagePath)));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * FUNCION PARA PONER EL CASILLERO
     * 
     * @param etiqueta
     * @param imagePath
     */
    public CustomJPanel(int fila, int columna, int valor, String imagePath, int[] numBanderasRestantes,
            int[] numBombasRestantes) {
        this.fila = fila;
        this.columna = columna;
        this.valorCelda = valor;

        try {
            // this.image = ImageIO.read(new File(imagePath));
            setImage(ImageIO.read(new File(imagePath)));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        setLayout(new BorderLayout(0, 0));

        JLabel etiquetaCasillero = new JLabel(String.valueOf(valorCelda));
        etiquetaCasillero.setHorizontalAlignment(SwingConstants.CENTER);
        etiquetaCasillero.setVisible(false);
        add(etiquetaCasillero);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if (SwingUtilities.isLeftMouseButton(e)) {
                    if (esBandera == false && estaRevelado == false) {
                        if (valorCelda == -1) {
                            cambiarFondo(pathBomba);
                            setFinPartida(true);
                            setPartidaGanada(false);
                        } else {

                            if (valorCelda != 0) {
                                etiquetaCasillero.setVisible(true);
                                cambiarFondo(pathCeldaDescubierta);
                            }
                        }
                    }

                } else if (SwingUtilities.isRightMouseButton(e)) {
                    if (esBandera == false && estaRevelado == false && numBanderasRestantes[0] > 0) {
                        cambiarFondo(pathBandera);
                        setEsBandera(true);
                        numBanderasRestantes[0]--;

                        if (valorCelda == -1) {
                            numBombasRestantes[0]--;
                        }

                    } else if (esBandera == true && estaRevelado == false) {
                        cambiarFondo(pathCelda);
                        setEsBandera(false);
                        // setNumBanderasColocadas(0);<
                        numBanderasRestantes[0]++;

                        if (valorCelda == -1) {
                            numBombasRestantes[0]++;
                        }
                    }

                    if (numBombasRestantes[0] == 0 && numBanderasRestantes[0] == 0) {
                        setFinPartida(true);
                        setPartidaGanada(true);
                    }

                }

                System.out.println("numero de banderas restantes: " + numBanderasRestantes[0]);
                System.out.println("numero de bombas restantes: " + numBombasRestantes[0]);
                System.out.println();

            }
        });
    }

    public void cambiarFondo(String pathImage) {
        try {
            // image = ImageIO.read(new File(pathImage));
            setImage(ImageIO.read(new File(pathImage)));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public boolean getEsBandera() {
        return esBandera;
    }

    public void setEsBandera(boolean esBandera) {
        this.esBandera = esBandera;
    }

    public boolean getEstaRevelado() {
        return estaRevelado;
    }

    public void setEstaRevelado(boolean estaRevelado) {
        this.estaRevelado = estaRevelado;
    }

    public int getNumeroBanderasColocadas() {
        return numeroBanderasColocadas;
    }

    public void setNumeroBanderasColocadas(int numeroBanderasColocadas) {
        this.numeroBanderasColocadas = numeroBanderasColocadas;
    }

    public int getValorCelda() {
        return valorCelda;
    }

    public void setValorCelda(int valorCelda) {
        this.valorCelda = valorCelda;
    }

    public boolean isFinPartida() {
        return finPartida;
    }

    public void setFinPartida(boolean finPartida) {
        this.finPartida = finPartida;
    }

    public boolean isPartidaGanada() {
        return partidaGanada;
    }

    public void setPartidaGanada(boolean partidaGanada) {
        this.partidaGanada = partidaGanada;
    }

}
