package vista;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modelo.Pelicula;

public class DetallePeliculas extends JPanel {
  private JLabel lblTitulo;
  private JLabel lblAnyo;
  private JLabel lblGenero;
  private JLabel lblDirector;

  private JTextField txtTitulo;
  private JTextField txtAnyo;
  private JTextField txtGenero;
  private JTextField txtDirector;

  private JButton btn;

  private Pelicula pelicula;

  public void getDetallePeliculas(Pelicula pelicula) {
    System.out.println("me han pulsado debo pedir algo a la DB");

    if (pelicula == null) {
      System.out.println("no hay peliculas en la DB");
      txtTitulo.setText("");
      txtAnyo.setText("");
      txtGenero.setText("");
      txtDirector.setText("");
    } else {
      txtTitulo.setText(pelicula.getTitulo());
      txtAnyo.setText(String.format("%f", pelicula.getAnyo()));
      txtGenero.setText(pelicula.getGenero().toString());
      txtDirector.setText(pelicula.getDirector().getNombre());
    }
  }

  public void getDetallePeliculas() {
    setSize(400, 300);
    setLayout(new GridLayout(11, 4));

    lblTitulo = new JLabel("Titulo");
    txtTitulo = new JTextField();
    lblAnyo = new JLabel("Año");
    txtAnyo = new JTextField();
    lblGenero = new JLabel("Genero");
    txtGenero = new JTextField();
    lblDirector = new JLabel("Director");
    txtDirector = new JTextField();

    add(lblTitulo);
    add(txtTitulo);
    add(lblAnyo);
    add(txtAnyo);
    add(lblGenero);
    add(txtGenero);
    add(lblDirector);
    add(txtDirector);

    btn = new JButton("Actualizar");

    btn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent ev) {
        System.out.println("soy el boton de Actualizar" + ev.getActionCommand());
      }
    });

    add(btn);
  }

  public void setActionListener(ActionListener listener) {
    btn.addActionListener(listener);
  }
}
