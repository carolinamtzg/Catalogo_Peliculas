package tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import modelo.Director;
import modelo.DirectorDAO;

public class TestDirectorDAO {
  private final String PATH = "./data/kakebo_tests.sqlite";
  private Connection conn;

  // test para comprobar la lista de directores ordenar por nombre:
  @Test
  public void testDameTodos() throws SQLException {
    // crear un directorDAO:
    DirectorDAO directorDAO = new DirectorDAO(PATH);

    // obtener la lista de todos los directores:
    ArrayList<Director> directores = directorDAO.dameTodos();

    // veirificar que la lista no esta nula:
    assertNotNull(directores);

    // verificar que la lista esta ordenada por nombre:
    for (int i = 0; i < directores.size() - 1; i++) {
      String nombreActual = directores.get(i).getNombre();
      String nombreNext = directores.get(i + 1).getNombre();
      assertTrue(nombreActual.compareTo(nombreNext) <= 0);
    }
  }
}
