package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import modelo.Director;
import modelo.Genero;
import modelo.Pelicula;

public class TestModelo {

  @Test
  public void testDirector() {
    int id = 1;
    String nombre = "Guillermo del Toro";
    String url_foto = "www.guillermo_del_toro.png";
    String url_web = "www.guillermo_del_toro.com";

    Director director = new Director(id, nombre, url_foto, url_web);

    assertEquals(director.getId(), id);
    assertEquals(director.getNombre(), nombre);
    assertEquals(director.getUrl_foto(), url_foto);
    assertEquals(director.getUrl_web(), url_web);

    int new_id = 2;
    director.setId(new_id);
    assertEquals(director.getId(), new_id);

    String new_nombre = "Wes Anderson";
    director.setNombre(new_nombre);
    assertEquals(director.getNombre(), new_nombre);

    String new_url_foto = "www.wes_anderson.png";
    director.setUrl_foto(new_url_foto);
    assertEquals(director.getUrl_foto(), new_url_foto);

    String new_url_web = "www.wes_anderson.com";
    director.setUrl_web(new_url_web);
    assertEquals(director.getUrl_web(), new_url_web);
  }

  @Test
  public void testPelicula() {
    int id = 1;
    String titulo = "The Grand Budapest Hotel";
    Director director = new Director(id, "Wes Anderson", "www.wes_anderson.png", "www.wes_anderson.com");
    int anyo = 2014;
    Genero genero = Genero.COMEDIA;
    boolean animacion = false;

    Pelicula pelicula = new Pelicula(id, titulo, director, anyo, genero, animacion);

    assertEquals(pelicula.getId(), id);
    assertEquals(pelicula.getTitulo(), titulo);
    assertEquals(pelicula.getDirector(), director);
    assertEquals(pelicula.getAnyo(), anyo);
    assertEquals(pelicula.getGenero(), genero);
    assertEquals(pelicula.isAnimacion(), false);

    int new_id = 2;
    pelicula.setId(new_id);
    assertEquals(pelicula.getId(), new_id);

    String new_titulo = "El laberinto del fauno";
    pelicula.setTitulo(new_titulo);
    assertEquals(pelicula.getTitulo(), new_titulo);

    Director director2 = new Director(id, "Guillermo del Toro", "www.guillermo_del_toro.png",
        "www.guillermo_del_toro.com");

    pelicula.setDirector(director2);

    assertEquals(pelicula.getDirector(), director2);

    int new_anyo = 2006;
    pelicula.setAnyo(new_anyo);
    assertEquals(pelicula.getAnyo(), new_anyo);

    Genero newGenero = Genero.DRAMA;
    pelicula.setGenero(newGenero);
    assertEquals(pelicula.getGenero(), newGenero);

    boolean new_animacion = true;
    pelicula.setAnimacion(new_animacion);
    assertEquals(pelicula.isAnimacion(), new_animacion);
  }

  @Test
  public void testGenero() {
    assertEquals(Genero.ACCION, Genero.valueOf("ACCION"));
    assertEquals(Genero.AVENTURA, Genero.valueOf("AVENTURA"));
    assertEquals(Genero.BIOGRAFICO, Genero.valueOf("BIOGRAFICO"));
    assertEquals(Genero.CIENCIA_FICCION, Genero.valueOf("CIENCIA_FICCION"));
    assertEquals(Genero.COMEDIA, Genero.valueOf("COMEDIA"));
    assertEquals(Genero.DOCUMENTAL, Genero.valueOf("DOCUMENTAL"));
    assertEquals(Genero.DRAMA, Genero.valueOf("DRAMA"));
    assertEquals(Genero.MUSICAL_DANZA, Genero.valueOf("MUSICAL_DANZA"));
    assertEquals(Genero.ROMANCE, Genero.valueOf("ROMANCE"));
    assertEquals(Genero.SUSPENSO, Genero.valueOf("SUSPENSO"));
    assertEquals(Genero.TERROR, Genero.valueOf("TERROR"));
    assertEquals(Genero.WESTERN, Genero.valueOf("WESTERN"));
  }
}
