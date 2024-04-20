package vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modelo.Pelicula;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

public class VentanaPrincipalPeliculas extends JFrame {
  private JTable tablaPeliculas;
  private DefaultTableModel modeloTabla;
  private JButton btnActualizar;

  public VentanaPrincipalPeliculas() {
    super("Catálogo de Películas");
    setSize(600, 400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    inicializarComponentes();
    setVisible(true);
  }

  private void inicializarComponentes() {
    // Crear tabla
    tablaPeliculas = new JTable();
    modeloTabla = new DefaultTableModel();
    modeloTabla.addColumn("Título");
    modeloTabla.addColumn("Año");
    modeloTabla.addColumn("Género");
    modeloTabla.addColumn("Director");
    tablaPeliculas.setModel(modeloTabla);

    // Botón actualizar
    btnActualizar = new JButton("Actualizar");

    // Panel
    JPanel panel = new JPanel(new BorderLayout());
    panel.add(new JScrollPane(tablaPeliculas), BorderLayout.CENTER);
    panel.add(btnActualizar, BorderLayout.SOUTH);

    add(panel, BorderLayout.CENTER);
  }

  public void setActionListener(ActionListener listener) {
    btnActualizar.addActionListener(listener);
  }

  public void limpiarTabla() {
    modeloTabla.setRowCount(0);
  }

  public void agregarPeliculaTabla(Pelicula pelicula) {
    Object[] fila = { pelicula.getTitulo(), pelicula.getAnyo(), pelicula.getGenero(),
        pelicula.getDirector().getNombre() };
    modeloTabla.addRow(fila);
  }

  public void mostrarError(String mensaje) {
    JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
  }
}
