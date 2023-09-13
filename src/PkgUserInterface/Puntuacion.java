package PkgUserInterface;

import javax.swing.JFrame;

import PkgUserInterface.UI_Component.CustomJPanel;

import java.awt.Font;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import PkgLogic.PilaJugadores;

import java.awt.Color;

public class Puntuacion extends JFrame {

    private String pathFondo = "src/images/fondoGanadores.jpg";
    private String pathPrimerLugar = "src/images/primerLugar.png";

    public Puntuacion(PilaJugadores jugadores) {
        setTitle("BUSCAMINAS - PUNTUACION");
        setSize(new Dimension(900, 600));
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setFont(new Font("Super Mario 256", Font.BOLD, 40));
        CustomJPanel fondo = new CustomJPanel(pathFondo);
        getContentPane().add(fondo);
        fondo.setLayout(null);

        JLabel lblGanadores = new JLabel("GANADORES");
        lblGanadores.setForeground(Color.GREEN);
        lblGanadores.setFont(new Font("Super Mario 256", Font.BOLD, 40));
        lblGanadores.setHorizontalTextPosition(SwingConstants.CENTER);
        lblGanadores.setHorizontalAlignment(SwingConstants.CENTER);
        lblGanadores.setBounds(12, 10, 868, 55);
        fondo.add(lblGanadores);

        JLabel lblGanadores_1 = new JLabel("GANADORES");
        lblGanadores_1.setHorizontalTextPosition(SwingConstants.CENTER);
        lblGanadores_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblGanadores_1.setForeground(Color.WHITE);
        lblGanadores_1.setFont(new Font("Super Mario 256", Font.BOLD, 41));
        lblGanadores_1.setBounds(12, 11, 868, 55);
        fondo.add(lblGanadores_1);

        CustomJPanel gradas = new CustomJPanel(pathFondo);
        gradas.setOpaque(false);
        gradas.setLocation(189, 77);
        gradas.setSize(522, 344);
        fondo.add(gradas);
        gradas.setLayout(null);

        CustomJPanel panel = new CustomJPanel(pathPrimerLugar);
        panel.setBounds(68, 0, 29, 34);
        gradas.add(panel);

        JLabel lblJugador = new JLabel("jugador");
        lblJugador.setForeground(Color.RED);
        lblJugador.setFont(new Font("Droid Sans", Font.BOLD, 15));
        lblJugador.setBounds(118, 0, 114, 22);
        gradas.add(lblJugador);

        JLabel lblPuntuacion = new JLabel("puntuacion");
        lblPuntuacion.setForeground(Color.GREEN);
        lblPuntuacion.setFont(new Font("Fira Code Light", Font.BOLD, 15));
        lblPuntuacion.setBounds(334, 0, 100, 22);
        gradas.add(lblPuntuacion);

        for (int i = 0; i < jugadores.TOPE; i++) {

            JLabel lblPlayer = new JLabel(jugadores.Pila[i].getNombre());
            System.out.println(jugadores.Pila[i].getNombre());
            lblPlayer.setForeground(Color.WHITE);
            lblPlayer.setFont(new Font("Droid Sans", Font.ITALIC, 20));
            lblPlayer.setBounds(118, (i * 26) + 26, 114, 25);
            gradas.add(lblPlayer);

            JLabel lblScore = new JLabel(jugadores.Pila[i].getPuntuacion() + "");
            lblScore.setForeground(Color.WHITE);
            lblScore.setFont(new Font("Fira Code Light", Font.ITALIC, 20));
            lblScore.setBounds(334, (i * 26) + 26, 100, 25);
            gradas.add(lblScore);
        }
        System.out.println("hola");

    }

}
