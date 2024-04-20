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
import modelo.Genero;
import modelo.Pelicula;
import modelo.PeliculaDAO;
import modelo.Utilidades;

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

  // test para buscar artista por titulo ordenad:
  @Test
  public void testBuscaPeliculaPorTitulo() throws SQLException {
    PeliculaDAO peliculaDAO = new PeliculaDAO(DB_PATH);
    String tituloPelicula = "Toy Story";

    // ejecucion del metodo a probar:
    Pelicula pelicula = peliculaDAO.buscaPorTitulo(tituloPelicula);
    assertNotNull(pelicula); // verifica que se haya devuelto una pelicula
    assertEquals(pelicula.getTitulo(), tituloPelicula);

    // verificar que la lista esta ordenada por titulo:
    ArrayList<Pelicula> listaPeliculas = peliculaDAO.dameTodos();
    Utilidades.ordenarPorPrimeraLetra(listaPeliculas); // organizo la lista

    assertNotNull(listaPeliculas); // verifica que se ha devuelto una lista de peliculas

    Utilidades.ordenarPorPrimeraLetra(listaPeliculas);

    for (int i = 0; i < listaPeliculas.size() - 1; i++) {
      String nombre1 = listaPeliculas.get(i).getTitulo().toLowerCase();
      String nombre2 = listaPeliculas.get(i + 1).getTitulo().toLowerCase();

      assertTrue(nombre1.compareTo(nombre2) <= 0);
    }
  }

  // test para insertar pelicula nueva:
  @Test
  public void testUpdate() throws SQLException {
    PeliculaDAO peliculaDAO = new PeliculaDAO(DB_PATH);
    DirectorDAO directorDAO = new DirectorDAO(DB_PATH);
    int id_pelicula = 11;
    int id_director = 7;

    // Insertar pelicula con su respectivo director:
    Director directorNuevo = new Director(id_director, "James Cameron",
        "www.james_cameron.png",
        "www.james_cameron.com");
    Pelicula peliculaNueva = new Pelicula(id_pelicula, "Titanic", directorNuevo,
        1997,
        "www.titanic_caratula.png", Genero.ROMANCE,
        false);

    directorDAO.insertar(directorNuevo); // inserto director nuevo
    peliculaDAO.insertar(peliculaNueva); // inserto pelicula nueva

    // Comprobar insercion:
    Pelicula pelicula = peliculaDAO.buscaPorId(id_pelicula);
    Director director = directorDAO.buscaPorId(id_director);

    assertEquals(peliculaNueva.getId(), pelicula.getId());
    assertEquals(directorNuevo.getId(), director.getId());

    // Modificar:
    String modificoTituloPelicula = "Django Unchained";
    pelicula.setTitulo(modificoTituloPelicula);
    peliculaDAO.modifica(pelicula);
    // Comprobar Modificacion:
    Pelicula peliculaModificada = peliculaDAO.buscaPorId(id_pelicula);
    assertEquals(peliculaModificada.getTitulo(), pelicula.getTitulo());

    // Eliminar:
    peliculaDAO.borra(id_pelicula);
    directorDAO.borra(id_director);
    // Comprobar que no existe la pelicula:
    Pelicula peliculaEliminada = peliculaDAO.buscaPorId(id_pelicula);
    // Comprobar que no existe el director:
    Director directorEliminado = directorDAO.buscaPorId(id_director);
    assertNull(peliculaEliminada);
    assertNull(directorEliminado);
  }

}
