package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import modelo.Director;

public class TestModelo {

  @Test
  public void testDirector() {
    int id = 1;
    String nombre = "Caro";
    String url_foto = "www.caro.foto";
    String url_web = "www.caro.web";
    Director director = new Director(id, nombre, url_foto, url_web);

    assertEquals(director.getId(), id);
    assertEquals(director.getNombre(), nombre);
    assertEquals(director.getUrl_foto(), url_foto);
    assertEquals(director.getUrl_web(), url_web);

    int new_id = 100;
    director.setId(new_id);
    assertEquals(director.getId(), new_id);

    String new_nombre = "Chemi";
    director.setNombre(new_nombre);
    assertEquals(director.getNombre(), new_nombre);

    String new_url_foto = "www.new.foto";
    director.setUrl_foto(new_url_foto);
    assertEquals(director.getUrl_foto(), new_url_foto);

    String new_url_web = "www.web.web";
    director.setUrl_web(new_url_web);
    assertEquals(director.getUrl_web(), new_url_web);
  }
}
