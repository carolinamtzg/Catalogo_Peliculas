package modelo;

public class Repartos {
  private int id_pelicula;
  private int id_artista;

  public Repartos(int id_pelicula, int id_artista) {
    this.id_pelicula = id_pelicula;
    this.id_artista = id_artista;
  }

  // setters:
  public void setId_pelicula(int id_pelicula) {
    this.id_pelicula = id_pelicula;
  }

  public void setId_artista(int id_artista) {
    this.id_artista = id_artista;
  }
}
