// package PkgUserInterface;

// import java.awt.*;
// import java.awt.event.*;
// import javax.swing.*;

// import PkgLogic.PilaJugadores;

// public class PantallaInicio extends JFrame {

// private PilaJugadores p;

// // Variable para almacenar la dificultad seleccionada
// private int dificultad;

// // Constructor de la pantalla de inicio
// public PantallaInicio(JFrame frame) {

// p = this.p;
// setLayout(new GridLayout(2, 1));

// // Botón para iniciar el juego
// JButton btnIniciar = new JButton("Iniciar Juego");
// btnIniciar.addActionListener(new ActionListener() {
// public void actionPerformed(ActionEvent e) {
// mostrarSelectorDificultad(frame);
// }
// });

// // Botón para salir
// JButton btnSalir = new JButton("Salir");
// btnSalir.addActionListener(new ActionListener() {
// public void actionPerformed(ActionEvent e) {
// System.exit(0); // Cierra la aplicación
// }
// });

// // Agregamos el botón a la pantalla de inicio
// add(btnIniciar);
// add(btnSalir);
// }

// // Método para mostrar el selector de dificultad
// int mostrarSelectorDificultad(JFrame frame) {
// // Creamos un diálogo de selección de dificultad
// Object[] opciones = { "Fácil", "Medio", "Difícil" };
// dificultad = JOptionPane.showOptionDialog(frame, "Seleccione la dificultad:",
// "Selector de Dificultad",
// JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones,
// opciones[0]);

// // Si se selecciona una dificultad, pedimos el nombre del jugador
// if (dificultad >= 0) {
// return pedirNombre(frame); // Retorna la dificultad
// } else {
// // Manejo de caso cuando no se selecciona ninguna dificultad
// return -1; // O el valor que corresponda en tu lógica
// }
// }

// private int pedirNombre(JFrame frame) {
// String nombre = JOptionPane.showInputDialog(frame, "Ingrese su nombre:");
// if (nombre != null && !nombre.isEmpty()) {
// // Inicia el juego con la dificultad y el nombre del jugador
// int dificultad = iniciarJuego(nombre); // Obtener dificultad seleccionada
// // Aquí puedes hacer lo que necesites con la dificultad
// } else {
// // Si el nombre es nulo o está vacío, se puede manejar según tu lógica
// JOptionPane.showMessageDialog(frame, "Nombre inválido. El juego no se
// iniciará.");
// }
// return dificultad;
// }

// // Método para iniciar el juego con la dificultad y nombre del jugador
// private int iniciarJuego(String nombre) {
// // Dependiendo de la dificultad seleccionada (guardada en la variable
// // dificultad)
// // puedes hacer algo con ella aquí

// int valorEspecifico;
// switch (dificultad) {
// case 0:
// valorEspecifico = 10;
// break;
// case 1:
// valorEspecifico = 40;
// break;
// case 2:
// valorEspecifico = 90;
// break;
// default:
// valorEspecifico = 0;
// }

// // Actualizar el nombre del jugador en la instancia de PilaJugadores
// p.insertarPila();

// return valorEspecifico;
// }
// }
