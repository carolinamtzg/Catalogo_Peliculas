package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import modelo.Artistas;
import modelo.ArtistasDAO;

public class TestArtistasDAO {
  private final String DB_PATH = "./data/create_database.sqlite";

  // test para comprobar la lista de artistas:
  @Test
  public void testDameTodos() throws SQLException {
    ArtistasDAO artistasDAO = new ArtistasDAO(DB_PATH);
    ArrayList<Artistas> listaArtistas = artistasDAO.dameTodos();
    assertNotNull(listaArtistas);
  }

  // test para buscar artistas por Id:
  @Test
  public void testBuscaArtistasPorId() throws SQLException {
    ArtistasDAO artistasDAO = new ArtistasDAO(DB_PATH);
    int id = 2;

    Artistas artistas = artistasDAO.buscaPorId(id);
    assertNotNull(artistas);
    assertEquals(artistas.getId(), id);
  }

  // test para buscar artista por nombre:
  @Test
  public void testBuscaArtistaPorNombre() throws SQLException {
    ArtistasDAO artistasDAO = new ArtistasDAO(DB_PATH);
    String nombreArtista = "Tom Hanks";

    // ejecucion del metodo a probar:
    Artistas artistas = artistasDAO.buscaPorNombre(nombreArtista);
    assertNotNull(artistas); // verifica que se haya devuelto un artista
    assertEquals(artistas.getNombre(), nombreArtista);

    // verificar que la lista esta ordenada por nombre:
    ArrayList<Artistas> listaArtistas = artistasDAO.dameTodos();
    assertNotNull(artistas); // verifica que se ha devuelto una lista de artistas

    // itera sobre la lista y verifica que los nombres estén ordenados:
    for (int i = 0; i < listaArtistas.size() - 1; i++) {
      String nombreActual = listaArtistas.get(i).getNombre();
      String nombreNext = listaArtistas.get(i + 1).getNombre();

      assertTrue(nombreActual.compareTo(nombreNext) <= 0);
    }
  }

  // test para insertar artista:
  @Test
  public void testUpdate() throws SQLException {
    ArtistasDAO artistasDAO = new ArtistasDAO(DB_PATH);
    int id = 16;

    // Insertar:
    Artistas artistaNuevo = new Artistas(16, "Johnny Depp",
        "www.johnny_depp.png", "www.johnny_depp.com");
    artistasDAO.insertar(artistaNuevo);
    // Comprobar insercion
    Artistas artistas = artistasDAO.buscaPorId(id);
    assertEquals(artistaNuevo.getId(), artistas.getId());

    // Modificar
    String nuevoNombre = "Jennifer Lawrence";
    artistas.setNombre(nuevoNombre);
    artistasDAO.modifica(artistas);
    // Comprobar Modificacion
    Artistas artistaModificado = artistasDAO.buscaPorId(id);
    assertEquals(nuevoNombre, artistaModificado.getNombre());

    // Eliminar
    artistasDAO.borra(id);
    // Comprobar que no existe
    Artistas artistaEliminado = artistasDAO.buscaPorId(id);
    assertNull(artistaEliminado);
  }

}
