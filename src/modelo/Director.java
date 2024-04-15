package modelo;

public class Director {
  private int id;
  private String nombre;

  public Director(int id, String nombre) {
    this.nombre = nombre;
    this.id = id;
  }

  // getters:
  public int getId() {
    return id;
  }

  public String getNombre() {
    return nombre;
  }

  // setters:
  public void setId(int id) {
    this.id = id;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

}
