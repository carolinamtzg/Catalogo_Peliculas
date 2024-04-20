package vista;

import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import modelo.Pelicula;
import modelo.PeliculaDAO;

public class CatalogoPeliculasGUI extends JFrame {
  private JTable tablaPeliculas;
  private DefaultTableModel modeloTabla;
  private JButton btnActualizar;
  private PeliculaDAO peliculaDAO;

  public CatalogoPeliculasGUI() {
    super("Catalogo de peliculas");
    PeliculaDAO peliculaDAO = new PeliculaDAO(getName()); // inicializar el DAO de Peliculas

    // crear la tabla de peliculas:
    tablaPeliculas = new JTable();
    modeloTabla = new DefaultTableModel();
    modeloTabla.addColumn("Título");
    modeloTabla.addColumn("Año");
    modeloTabla.addColumn("Género");
    modeloTabla.addColumn("Director");
    tablaPeliculas.setModel(modeloTabla);

    // crear el boton de actualizar:
    btnActualizar = new JButton("Actualizar");
    btnActualizar.addActionListener(e -> actualizarCatalogo());

    // configuro el diseño de la ventana:
    JPanel panel = new JPanel(new BorderLayout());
    panel.add(new JScrollPane(tablaPeliculas), BorderLayout.CENTER);
    panel.add(btnActualizar, BorderLayout.SOUTH);
    add(panel);

    // configurar la ventana:
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(600, 400);
    setLocationRelativeTo(null);
    setVisible(true);

    // actualizar el catalogo:
    actualizarCatalogo();
  }

  private void actualizarCatalogo() {
    // limpiar la tabla:
    modeloTabla.setRowCount(0);

    // obtener las películas del DAO y agregarlas a la tabla:
    try {
      ArrayList<Pelicula> peliculas = peliculaDAO.dameTodos();
      for (Pelicula pelicula : peliculas) {
        Object[] fila = { pelicula.getTitulo(), pelicula.getAnyo(), pelicula.getGenero(), pelicula.getDirector() };
        modeloTabla.addRow(fila);
      }
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(this, "Error al obtener las películas: " + e.getMessage(), "Error",
          JOptionPane.ERROR_MESSAGE);
    }
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(CatalogoPeliculasGUI::new);
  }

}
