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
    private BufferedImage image;
    private boolean esBandera = false;
    private boolean estaRevelado = false;

    public boolean isEstaRevelado() {
        return estaRevelado;
    }

    public void setEstaRevelado(boolean estaRevelado) {
        this.estaRevelado = estaRevelado;
    }

    private int valorCelda = 0;

    /**
     * FUNCION PARA COLOCAR EL CASILLERO SIN SEVELAR
     * 
     * @param imagePath
     */
    public CustomJPanel(String imagePath) {
        try {
            this.image = ImageIO.read(new File(imagePath));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // private Color[] colors = { Color.RED, Color.GREEN, Color.BLUE };
    // private int currentColorIndex = 0;

    /**
     * FUNCION PARA PONER EL CASILLERO
     * 
     * @param etiqueta
     * @param imagePath
     */
    public CustomJPanel(String etiqueta, String imagePath, int valorCelda) {
        try {
            this.image = ImageIO.read(new File(imagePath));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        setLayout(new BorderLayout(0, 0));

        JLabel etiquetaCasillero = new JLabel(etiqueta);
        etiquetaCasillero.setHorizontalAlignment(SwingConstants.CENTER);
        etiquetaCasillero.setVisible(false);
        add(etiquetaCasillero);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    if (esBandera == false && estaRevelado == false) {
                        if (valorCelda == -1) {
                            cambiarFondo("src/images/bomba.png");
                        } else {
                            cambiarFondo("src/images/celda.png");
                            etiquetaCasillero.setVisible(true);
                        }
                    }
                    setEstaRevelado(true);

                } else if (SwingUtilities.isRightMouseButton(e)) {

                    if (esBandera == false && estaRevelado == false) {
                        cambiarFondo("src/images/bandera.png");
                        setEsBandera(true);
                    } else if (esBandera == true && estaRevelado == false) {
                        cambiarFondo("src/images/celda.png");
                        setEsBandera(false);
                    }
                }
            }
        });
    }

    private void cambiarFondo(String pathImage) {
        try {
            image = ImageIO.read(new File(pathImage));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        // currentColorIndex = (currentColorIndex + 1) % colors.length;
        // setBackground(image);
        repaint();
    }

    private void cambiarFondo(BufferedImage imagen) {
        image = imagen;
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

    public boolean isEsBandera() {
        return esBandera;
    }

    public void setEsBandera(boolean esBandera) {
        this.esBandera = esBandera;
    }

    public int getValorCelda() {
        return valorCelda;
    }

    public void setValorCelda(int valorCelda) {
        this.valorCelda = valorCelda;
    }
}
