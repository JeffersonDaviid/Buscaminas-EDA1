
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
import javax.swing.SwingWorker;

import PkgLogic.NodoNiveles;
import PkgLogic.PilaJugadores;
import PkgLogic.Player;
import UI_Component.CustomJPanel;

public class Buscaminas extends JFrame {

    // JUGADORES
    private PilaJugadores jugadores = new PilaJugadores(50);
    private static NodoNiveles niveles = null;
    int filas = 0, columnas = 0, bombas = 0;

    private String pathFondo = "src/images/fondoBuscaminas.jpg";
    private JTextField txtJugador;
    JComboBox comboBox;

    public Buscaminas() {
        setIconImage(new javax.swing.ImageIcon("src/images/bandera1.png").getImage());
        setTitle("BUSCAMINAS - EDA 1 - JSSF");
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

        JLabel lblTitulo1 = new JLabel("BUSCAMINAS");
        lblTitulo1.setBounds(13, 13, 368, 50);
        lblTitulo1.setForeground(Color.BLACK);
        lblTitulo1.setFont(new Font("Super Mario 256", Font.BOLD, 41));
        lblTitulo1.setHorizontalTextPosition(SwingConstants.CENTER);
        lblTitulo1.setHorizontalAlignment(SwingConstants.CENTER);
        fondoInicio.add(lblTitulo1);

        JLabel lblJugador = new JLabel("JUGADOR:");
        lblJugador.setForeground(Color.magenta);
        lblJugador.setFont(new Font("Super Mario 256", Font.BOLD, 12));
        lblJugador.setBounds(22, 74, 90, 17);
        fondoInicio.add(lblJugador);

        txtJugador = new JTextField();
        txtJugador.setBounds(32, 103, 114, 21);
        fondoInicio.add(txtJugador);
        txtJugador.setColumns(10);

        JLabel lblDificultad = new JLabel("DIFICULTAD:");
        lblDificultad.setBounds(22, 150, 104, 17);
        lblDificultad.setForeground(Color.magenta);
        lblDificultad.setFont(new Font("Super Mario 256", Font.BOLD, 12));
        fondoInicio.add(lblDificultad);

        Object[] opciones = { "Fácil", "Medio", "Difícil" };
        comboBox = new JComboBox(opciones);
        comboBox.setBounds(32, 179, 114, 26);
        fondoInicio.add(comboBox);

        // Botón para iniciar el juego
        JButton btnIniciar = new JButton("Iniciar Juego");
        btnIniciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // iniciarPartida();
                startGame();
            }
        });
        btnIniciar.setBounds(235, 97, 124, 27);
        fondoInicio.add(btnIniciar);

        // Botón para ver tabla de puntuaciones
        JButton btnPuntuaciones = new JButton("Puntuaciones");
        btnPuntuaciones.setForeground(Color.darkGray);
        btnPuntuaciones.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                verPuntuacion();
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

    private void startGame() {
        this.setVisible(false);

        Player jugador = new Player(txtJugador.getText());
        int dificultad = comboBox.getSelectedIndex();

        switch (dificultad) {
            case 0:
                filas = columnas = 10;
                bombas = 10;
                break;
            case 1:
                filas = columnas = 16;
                bombas = 40;
                break;
            case 2:
                filas = columnas = 22;
                bombas = 99;
                break;
        }

        niveles = Tablero.generarNiveles(bombas, filas, columnas);
        jugadores.insertarPila(jugador);

        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                NodoNiveles nivel = niveles;

                while (nivel != null) {
                    Tablero game = new Tablero(nivel, filas, columnas, jugador);
                    game.setVisible(true);

                    while (game.isVisible()) {
                        if (nivel.isFinPartida()) {
                            if (nivel.isPartidaGanada()) {
                                JOptionPane.showMessageDialog(game, "USTED HA GANADO!", "PUNTUACION",
                                        JOptionPane.INFORMATION_MESSAGE);

                                int[] puntos = game.sumarTiempo(jugador.getPuntuacion()[0], jugador.getPuntuacion()[1],
                                        jugador.getPuntuacion()[2], game.getHoras(), game.getMinutos(),
                                        game.getSegundos());
                                jugador.setPuntuacion(puntos);
                            } else {
                                JOptionPane.showMessageDialog(game, "USTED HA PERDIDO :(", "PUNTUACION",
                                        JOptionPane.INFORMATION_MESSAGE);
                            }

                            game.setVisible(false);

                            if (!nivel.isPartidaGanada()) {
                                setVisible(true);
                                return null;
                            }
                            nivel = nivel.getNodoSiguiente();
                        }
                    }
                }
                setVisible(true);
                return null;
            }
        };

        worker.execute(); // Ejecuta el SwingWorker en un hilo secundario
    }

    public void iniciarJuego() {
        this.setVisible(false);
        Player jugador = new Player(txtJugador.getText());
        int dificultad = comboBox.getSelectedIndex();
        System.out.print(dificultad);
        int filas = 0, columnas = 0, bombas = 0;
        switch (dificultad) {
            case 0:
                filas = columnas = 10;
                bombas = 10;
                break;
            case 1:
                filas = columnas = 16;
                bombas = 40;
                break;
            case 2:
                filas = columnas = 22;
                bombas = 99;
        }

        NodoNiveles niveles = Tablero.generarNiveles(bombas, filas, columnas);
        jugadores.insertarPila(jugador);

        NodoNiveles nivel = niveles;
        while (nivel != null) {
            Tablero game = new Tablero(nivel, filas, columnas, jugador);
            game.setVisible(true);

            while (game.isVisible()) {

                if (nivel.isFinPartida()) {
                    if (nivel.isPartidaGanada()) {
                        JOptionPane.showMessageDialog(game, "USTED HA GANADO!", "PUNTUACION",
                                JOptionPane.INFORMATION_MESSAGE);
                        nivel = nivel.getNodoSiguiente();
                    } else {
                        JOptionPane.showMessageDialog(game, "USTED HA PERDIDO :(", "PUNTUACION",
                                JOptionPane.INFORMATION_MESSAGE);
                    }

                    game.setVisible(false);
                    this.setVisible(true);

                    if (!nivel.isPartidaGanada()) {
                        return;
                    }
                }
            }

        }

    }

    public void verPuntuacion() {
        Puntuacion p = new Puntuacion(jugadores);
        p.setVisible(true);

    }

    public static void main(String[] args) {
        Buscaminas b = new Buscaminas();
        b.setVisible(true);
    }

}