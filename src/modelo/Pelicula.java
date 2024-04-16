package modelo;

public class Pelicula {
  private int id;
  private String titulo;
  private Director director;
  private int año;
  private Genero genero;
  private boolean animacion;

  public Pelicula(int id, String titulo, Director director, int año, Genero genero, boolean animacion) {
    this.id = id;
    this.titulo = titulo;
    this.director = director;
    this.año = año;
    this.genero = genero;
    this.animacion = animacion;
  }

  public int getId() {
    return id;
  }

  public String getTitulo() {
    return titulo;
  }

  public Director getDirector() {
    return director;
  }

  public int getAño() {
    return año;
  }

  public Genero getGenero() {
    return genero;
  }

  public boolean isAnimacion() {
    return animacion;
  }

}