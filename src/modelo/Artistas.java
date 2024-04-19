package modelo;

public class Artistas {
  private int id;
  private String nombre;
  private String url_foto;
  private String url_web;

  public Artistas(int id, String nombre, String url_foto, String url_web) {
    this.id = id;
    this.nombre = nombre;
    this.url_foto = url_foto;
    this.url_web = url_web;
  }

  // getters:
  public int getId() {
    return id;
  }

  public String getNombre() {
    return nombre;
  }

  public String getUrl_foto() {
    return url_foto;
  }

  public String getUrl_web() {
    return url_web;
  }

  // setters:
  public void setId(int id) {
    this.id = id;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public void setUrl_foto(String url_foto) {
    this.url_foto = url_foto;
  }

  public void setUrl_web(String url_web) {
    this.url_web = url_web;
  }

}
