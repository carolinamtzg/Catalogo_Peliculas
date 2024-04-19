package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Utilidades {
  public Connection getConnection(String path) {
    Connection conn = null;
    try {
      conn = DriverManager.getConnection(String.format("jdbc:sqlite:%s", path));
      System.out.println("Conexión establecida correctamente.");
    } catch (SQLException e) {
      System.out.println("Error al establecer la conexión a la base de datos.");
      e.printStackTrace();
    }
    return conn;
  }

  // metodo para ordenar alfabeticamente los titulos de las pelis:
  public static List<Pelicula> ordenarPorPrimeraLetra(List<Pelicula> listaPeliculas) {
    Collections.sort(listaPeliculas, new Comparator<Pelicula>() {
      @Override
      public int compare(Pelicula n1, Pelicula n2) {
        String nombre1 = n1.getTitulo().toLowerCase();
        String nombre2 = n2.getTitulo().toLowerCase();

        int comparacion = Character.compare(nombre1.charAt(0), nombre2.charAt(0));

        if (comparacion == 0) {
          comparacion = nombre1.compareTo(nombre2);
        }
        return comparacion;
      }
    });
    return listaPeliculas;

  }
}
