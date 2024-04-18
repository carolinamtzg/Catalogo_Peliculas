package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import modelo.Director;
import modelo.DirectorDAO;

public class TestDirectorDAO {
  private final String DB_PATH = "./data/create_database.sqlite";

  // test para comprobar la lista de directores:
  @Test
  public void testDameTodos() throws SQLException {
    DirectorDAO directorDAO = new DirectorDAO(DB_PATH);
    ArrayList<Director> directores = directorDAO.dameTodos();
    assertNotNull(directores);
  }

  // test para buscaPorId:
  @Test
  public void testBuscaPorId() throws SQLException {
    DirectorDAO directorDAO = new DirectorDAO(DB_PATH);
    int id = 2;

    Director director = directorDAO.buscaPorId(id);
    assertNotNull(director);
    assertEquals(director.getId(), id);
  }

  // test para buscaPorNombre:
  @Test
  public void testBuscaPorNombre() throws SQLException {
    DirectorDAO directorDAO = new DirectorDAO(DB_PATH);
    String nombreDirector = "Steven Spielberg";

    // ejecucion del metodo a probar:
    Director director = directorDAO.buscaPorNombre(nombreDirector);
    assertNotNull(director); // verifica que se haya devuelto un director
    assertEquals(director.getNombre(), nombreDirector); // verifica que el nombre del director sea el esperado

    // verificar que la lista está ordenada por nombre:
    ArrayList<Director> directores = directorDAO.dameTodos();
    assertNotNull(directores); // verifica que se ha devuelto una lista de directores

    // itera sobre la lista y verifica que los nombres estén ordenados
    // alfabeticamente
    for (int i = 0; i < directores.size() - 1; i++) {
      String nombreActual = directores.get(i).getNombre();
      String nombreNext = directores.get(i + 1).getNombre();

      assertTrue(nombreActual.compareTo(nombreNext) <= 0);
    }
  }

  // test para insertar:
  @Test
  public void testUpdate() throws SQLException {
    DirectorDAO directorDAO = new DirectorDAO(DB_PATH);
    int id = 6;

    // Insertar
    Director directorNuevo = new Director(6, "Sofia Coppola",
        "www.sofia_coppola.png", "www.sofia_coppola.com");
    directorDAO.insertar(directorNuevo);
    // Comprobar insercion
    Director director = directorDAO.buscaPorId(id);
    assertEquals(directorNuevo.getId(), director.getId());

    // Modificar
    String nuevoNombre = "James Cameron";
    director.setNombre(nuevoNombre);
    directorDAO.modifica(director);
    // Comprobar Modificacion
    Director directorModificado = directorDAO.buscaPorId(id);
    assertEquals(nuevoNombre, directorModificado.getNombre());

    // Eliminar
    directorDAO.borra(id);
    // Comprobar que no existe
    Director directorEliminado = directorDAO.buscaPorId(id);
    assertNull(directorEliminado);
  }
}
