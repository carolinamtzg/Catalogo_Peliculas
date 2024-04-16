package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PeliculaDAO {
  private String path;

  public PeliculaDAO(String path) {
    this.path = path;
  }

  // metodo dateTodos:
  public ArrayList<Pelicula> dameTodos() throws SQLException {
    String sql = "SELECT p.id, p.titulo, p.anyo, p.es_animacion, d.nombre AS director_nombre, g.descripcion AS genero_nombre FROM peliculas p JOIN directores d ON p.id_director = d.id JOIN generos g ON p.id_genero = g.id ORDER BY g.descripcion, p.anyo DESC, p.titulo";

    ArrayList<Pelicula> peliculas = new ArrayList<>();
    Connection conn = new Utilidades().getConnection(path);

    Statement sentenciaSQL = conn.createStatement();

    ResultSet resultado = sentenciaSQL.executeQuery(sql);

    while (resultado.next()) {
      Director director = new Director(resultado.getInt("id"), resultado.getString("director_nombre"));
      Genero genero = new Genero.valueOf(resultado.getString("genero_nombre"));
      Pelicula pelicula = new Pelicula(resultado.getInt("id"), resultado.getString("titulo"), director,
          resultado.getInt("anyo"), genero, resultado.getBoolean("es_animacion"));

      peliculas.add(pelicula);
    }
    return peliculas;
  }

  // metodo buscaPorId:
  public Pelicula buscaPorId(int id) throws SQLException {
    String sql = "SELECT titulo, id_director, anyo, id_genero, es_animacion FROM peliculas WHERE id = ?";

    Connection conn = new Utilidades().getConnection(path);
    PreparedStatement sentenciaSQL = conn.prepareStatement(sql);

    sentenciaSQL.setInt(1, id);

    ResultSet resultado = sentenciaSQL.executeQuery();

    if (resultado.next()) {
      Director director = new Director(resultado.getInt("id"), resultado.getString("director_nombre"));
      return new Pelicula(id, resultado.getString("titulo"), director, resultado.getInt("anyo"),
          Genero.valueOf(resultado.getString("genero")), resultado.getBoolean("es_animacion"));
    }
    return null;
  }

  // metodo buscaPorNombre:
  public Pelicula buscaPorNombre(String nombre) throws SQLException {
    String sql = "SELECT titulo, id_director, anyo, id_genero, es_animacion FROM peliculas WHERE nombre = ?";
    Connection conn = new Utilidades().getConnection(path);
    PreparedStatement sentenciaSQL = conn.prepareStatement(sql);

    sentenciaSQL.setString(1, nombre);

    ResultSet resultado = sentenciaSQL.executeQuery();

    if (resultado.next()) {
      int id = resultado.getInt("id");
      int año = resultado.getInt("anyo");
      Boolean animacion = resultado.getBoolean("es_animacion");
      String genero_nombre = resultado.getString("genero_nombre");
      String director_nombre = resultado.getString("director_nombre");

      Director director = new Director(resultado.getInt("id"), resultado.getString("director_nombre"));
      Genero genero = Genero.valueOf(genero_nombre);

      return new Pelicula(id, director_nombre, director, año, genero, animacion);
    }
    return null;
  }

  // metodo para borrar pelicula:
  public void borra(int id) throws SQLException {
    String sql = "DELETE FROM peliculas WHERE id = ?";
    Connection conn = new Utilidades().getConnection(path);
    PreparedStatement sentenciaSQL = conn.prepareStatement(sql);

    sentenciaSQL.setInt(1, id);
    sentenciaSQL.executeUpdate();
  }

  // metodo para modificar la pelicula:
  public void modifica(Pelicula pelicula) throws SQLException {
    String sql = "UPDATE peliculas SET titulo = ?, id_director = ?, anyo = ?, id_genero = ?, es_animacion = ? WHERE id = ?";
    Connection conn = new Utilidades().getConnection(path);
    PreparedStatement sentenciaSQL = conn.prepareStatement(sql);

    sentenciaSQL.setString(1, pelicula.getTitulo());
    sentenciaSQL.setInt(2, pelicula.getDirector().getId());
    sentenciaSQL.setInt(3, pelicula.getAño());
    sentenciaSQL.setInt(4, pelicula.getGenero().ordinal() + 1); // el indice de enum comienza en 0, pero en la db
                                                                // comienza en 1.
    sentenciaSQL.setBoolean(5, pelicula.isAnimacion());
    sentenciaSQL.setInt(6, pelicula.getId());
    sentenciaSQL.executeUpdate();
  }
}
