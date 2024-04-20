package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import modelo.Pelicula;
import modelo.PeliculaDAO;
import vista.VentanaPrincipalPeliculas;

public class Controller implements ActionListener {
  private PeliculaDAO peliculaDAO;
  private VentanaPrincipalPeliculas ventana;

  public Controller() {
    peliculaDAO = new PeliculaDAO("./data/create_database.sqlite");
    ventana = new VentanaPrincipalPeliculas();
    ventana.setActionListener(this);
  }

  @Override

  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand().equals("Actualizar")) {
      actualizarCatalogo();
    }
  }

  private void actualizarCatalogo() {
    ventana.limpiarTabla();

    try {
      ArrayList<Pelicula> peliculas = peliculaDAO.dameTodos();
      for (Pelicula pelicula : peliculas) {
        ventana.agregarPeliculaTabla(pelicula);
      }
    } catch (SQLException e) {
      ventana.mostrarError("Error al obtener las películas: " + e.getMessage());
    }
  }
}
