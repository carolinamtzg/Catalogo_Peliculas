package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import modelo.Director;
import modelo.Genero;
import modelo.Pelicula;
import modelo.PeliculaDAO;

public class TestPeliculaDAO {
  private final String DB_PATH = "./data/create_database.sqlite";

  // test para comprobar la lista de peliculas:
  @Test
  public void testDameTodos() throws SQLException {
    PeliculaDAO peliculasDAO = new PeliculaDAO(DB_PATH);
    ArrayList<Pelicula> listaPeliculas = peliculasDAO.dameTodos();
    assertNotNull(listaPeliculas);
  }

  // test para buscar peliculas por Id:
  @Test
  public void testBuscaPeliculasPorId() throws SQLException {
    PeliculaDAO peliculaDAO = new PeliculaDAO(DB_PATH);
    int id = 2;

    Pelicula pelicula = peliculaDAO.buscaPorId(id);
    assertNotNull(pelicula);
    assertEquals(pelicula.getId(), id);
  }

  // test para buscar artista por titulo:
  @Test
  public void testBuscaPeliculaPorTitulo() throws SQLException {
    PeliculaDAO peliculaDAO = new PeliculaDAO(DB_PATH);
    String tituloPelicula = "Toy Story";

    // ejecucion del metodo a probar:
    Pelicula pelicula = peliculaDAO.buscaPorTitulo(tituloPelicula);
    assertNotNull(pelicula); // verifica que se haya devuelto una pelicula
    assertEquals(pelicula.getTitulo(), tituloPelicula);

    // verificar que la lista esta ordenada por nombre:
    ArrayList<Pelicula> listaPeliculas = peliculaDAO.dameTodos();
    assertNotNull(listaPeliculas); // verifica que se ha devuelto una lista de peliculas

    // itera sobre la lista y verifica que los nombres estén ordenados:
    for (int i = 0; i < listaPeliculas.size() - 1; i++) {
      String nombreActual = listaPeliculas.get(i).getTitulo();
      String nombreNext = listaPeliculas.get(i + 1).getTitulo();

      assertTrue(nombreActual.compareTo(nombreNext) <= 0);
    }
  }

  // test para insertar pelicula nueva:
  @Test
  public void testUpdate() throws SQLException {
    PeliculaDAO peliculaDAO = new PeliculaDAO(DB_PATH);
    int id = 11;

    // Insertar pelicula nueva:
    Director director = new Director(6, "James Cameron", "www.james_cameron.png", "www.james_cameron.com");
    Pelicula peliculaNuevo = new Pelicula(11, "Titanic", director, 1997, "www.titanic_caratula.png", Genero.ROMANCE,
        false);
    peliculaDAO.insertar(peliculaNuevo);
    // Comprobar insercion
    Pelicula pelicula = peliculaDAO.buscaPorId(id);
    assertEquals(peliculaNuevo.getId(), pelicula.getId());

    // Modificar
    String nuevoNombre = "Jennifer Lawrence";
    pelicula.setTitulo(nuevoNombre);
    peliculaDAO.modifica(pelicula);
    // Comprobar Modificacion
    Pelicula peliculaModificada = peliculaDAO.buscaPorId(id);
    assertEquals(nuevoNombre, peliculaModificada.getTitulo());

    // Eliminar
    peliculaDAO.borra(id);
    // Comprobar que no existe
    Pelicula peliculaEliminada = peliculaDAO.buscaPorId(id);
    assertNull(peliculaEliminada);
  }

}
