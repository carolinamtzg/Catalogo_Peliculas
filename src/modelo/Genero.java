package modelo;

public enum Genero {
  ACCION(1),
  AVENTURA(2),
  COMEDIA(3),
  DRAMA(4),
  TERROR(5),
  CIENCIA_FICCION(6),
  MUSICAL_DANZA(7),
  SUSPENSO(8),
  WESTERN(9),
  DOCUMENTAL(10),
  BIOGRAFICO(11),
  ROMANCE(12);

  private final int id;

  Genero(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }
}