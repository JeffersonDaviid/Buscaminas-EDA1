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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class CustomJPanel extends JPanel {
    private BufferedImage image;
    private boolean esBandera = false;
    private boolean estaRevelado = false;
    private String pathCelda = "src/images/celda.png";
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
    public CustomJPanel(int valorCelda, String imagePath) {
        try {
            // this.image = ImageIO.read(new File(imagePath));
            setImage(ImageIO.read(new File(imagePath)));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        setLayout(new BorderLayout(0, 0));

        JLabel etiquetaCasillero = new JLabel(valorCelda + "");
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
                            JOptionPane.showMessageDialog(etiquetaCasillero, "HAS PERDIDO!");
                        } else {
                            cambiarFondo(pathCelda);
                            etiquetaCasillero.setVisible(true);
                        }
                    }
                    setEstaRevelado(true);

                } else if (SwingUtilities.isRightMouseButton(e)) {
                    if (esBandera == false && estaRevelado == false) {
                        cambiarFondo(pathBandera);
                        setEsBandera(true);
                    } else if (esBandera == true && estaRevelado == false) {
                        cambiarFondo(pathCelda);
                        setEsBandera(false);
                    }
                }
            }
        });
    }

    private void cambiarFondo(String pathImage) {
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

    public boolean isEsBandera() {
        return esBandera;
    }

    public void setEsBandera(boolean esBandera) {
        this.esBandera = esBandera;
    }

    public boolean isEstaRevelado() {
        return estaRevelado;
    }

    public void setEstaRevelado(boolean estaRevelado) {
        this.estaRevelado = estaRevelado;
    }
}
