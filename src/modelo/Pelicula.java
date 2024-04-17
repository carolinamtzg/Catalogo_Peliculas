package modelo;

public class Pelicula {
  private int id;
  private String titulo;
  private Director director;
  private int anyo;
  private Genero genero;
  private boolean animacion;

  public Pelicula(int id, String titulo, Director director, int año, Genero genero, boolean animacion) {
    this.id = id;
    this.titulo = titulo;
    this.director = director;
    this.anyo = año;
    this.genero = genero;
    this.animacion = animacion;
  }

  // getters:
  public int getId() {
    return id;
  }

  public String getTitulo() {
    return titulo;
  }

  public Director getDirector() {
    return director;
  }

  public int getAnyo() {
    return anyo;
  }

  public Genero getGenero() {
    return genero;
  }

  public boolean isAnimacion() {
    return animacion;
  }

  // setters:
  public void setId(int id) {
    this.id = id;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public void setDirector(Director director) {
    this.director = director;
  }

  public void setAnyo(int anyo) {
    this.anyo = anyo;
  }

  public void setGenero(Genero genero) {
    this.genero = genero;
  }

  public void setAnimacion(boolean animacion) {
    this.animacion = animacion;
  }
}