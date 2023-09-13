
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import PkgLogic.NodoNiveles;
import PkgLogic.PilaJugadores;
import PkgLogic.Player;
import PkgUserInterface.Tablero;
import PkgUserInterface.UI_Component.CustomJPanel;

public class Buscaminas extends JFrame {

    // JUGADORES
    private PilaJugadores jugadores = new PilaJugadores(10);
    private static NodoNiveles niveles = null;

    private String pathFondo = "src/images/fondoBuscaminas.jpg";
    private JTextField txtJugador;

    public Buscaminas() {
        setTitle("BUSCAMINAS");
        setSize(new Dimension(400, 275));
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setFont(new Font("Super Mario 256", Font.BOLD, 40));
        getContentPane().setLayout(new BorderLayout(0, 0));

        CustomJPanel fondoInicio = new CustomJPanel(pathFondo);
        // fondoInicio.setLayout(null);
        getContentPane().add(fondoInicio);
        fondoInicio.setLayout(null);

        JLabel lblTitulo = new JLabel("BUSCAMINAS");
        lblTitulo.setBounds(12, 12, 368, 50);
        lblTitulo.setForeground(Color.RED);
        lblTitulo.setFont(new Font("Super Mario 256", Font.BOLD, 40));
        lblTitulo.setHorizontalTextPosition(SwingConstants.CENTER);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        fondoInicio.add(lblTitulo);

        JLabel lblJugador = new JLabel("JUGADOR:");
        lblJugador.setBounds(22, 74, 60, 17);
        fondoInicio.add(lblJugador);

        txtJugador = new JTextField();
        txtJugador.setBounds(32, 103, 114, 21);
        fondoInicio.add(txtJugador);
        txtJugador.setColumns(10);

        JLabel lblDificultad = new JLabel("DIFICULTAD");
        lblDificultad.setBounds(22, 150, 104, 17);
        fondoInicio.add(lblDificultad);

        Object[] opciones = { "Fácil", "Medio", "Difícil" };
        JComboBox comboBox = new JComboBox(opciones);
        comboBox.setBounds(32, 179, 114, 26);
        fondoInicio.add(comboBox);

        // Botón para iniciar el juego
        JButton btnIniciar = new JButton("Iniciar Juego");
        btnIniciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                iniciarPartida();
            }
        });
        btnIniciar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
        });
        btnIniciar.setBounds(235, 97, 124, 27);
        fondoInicio.add(btnIniciar);

        // Botón para ver tabla de puntuaciones
        JButton btnPuntuaciones = new JButton("Puntuaciones");
        btnPuntuaciones.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
        });
        btnPuntuaciones.setBounds(235, 145, 124, 27);
        fondoInicio.add(btnPuntuaciones);

        // boton para salir de la aplicacion
        JButton btnSalir = new JButton("Salir");
        btnSalir.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0); // Cierra la aplicación
            }
        });
        btnSalir.setBounds(235, 196, 124, 27);
        fondoInicio.add(btnSalir);

    }

    public void iniciarPartida() {
        this.setVisible(false);
        Player jugador = new Player(txtJugador.getText(), 0);
        int dificultad = 0;
        int filas = 0, columnas = 0, bombas = 0;
        switch (dificultad) {
            case 0:
                filas = columnas = 10;
                bombas = 10;
                break;
            case 1:
                filas = columnas = 18;
                bombas = 40;
                break;
            case 2:
                filas = columnas = 24;
                bombas = 99;
                break;
        }
        NodoNiveles niveles = Tablero.generarNiveles(bombas, filas, columnas);
        // Tablero tabla = new Tablero();
        // tabla.iniciarJuego(jugador, niveles, filas, columnas);
        jugadores.insertarPila(jugador);

        NodoNiveles aux = niveles;
        while (aux != null) {
            Tablero game = new Tablero(aux, filas, columnas, jugador);
            game.setVisible(true);

            while (game.isVisible()) {
                // Verificar si se ha perdido
                for (int i = 0; i < filas; i++) {
                    for (int j = 0; j < columnas; j++) {
                        if (aux.getTablero()[i][j].isFinPartida()) {
                            if (aux.getTablero()[i][j].isPartidaGanada()) {
                                JOptionPane.showMessageDialog(game, "USTED HA GANADO!", "PUNTUACION",
                                        JOptionPane.INFORMATION_MESSAGE);
                                aux = aux.getNodoSiguiente();
                                game.setVisible(false);
                                this.setVisible(true);
                                break;
                            } else {
                                JOptionPane.showMessageDialog(game, "USTED HA PERDIDO :(", "PUNTUACION",
                                        JOptionPane.INFORMATION_MESSAGE);
                                game.setVisible(false);
                                this.setVisible(true);
                                return;
                            }

                        }
                    }
                }
            }
        }

    }

    public static void main(String[] args) {
        Buscaminas b = new Buscaminas();
        b.setVisible(true);
    }

}