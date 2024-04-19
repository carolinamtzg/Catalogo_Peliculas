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

  // metodo dameTodos:
  public ArrayList<Pelicula> dameTodos() throws SQLException {
    String sql = "SELECT p.id, p.titulo, p.anyo, p.es_animacion, d.nombre AS director_nombre, g.descripcion AS genero_nombre FROM peliculas p JOIN directores d ON p.id_director = d.id JOIN generos g ON p.id_genero = g.id ORDER BY g.descripcion, p.anyo DESC, p.titulo";

    ArrayList<Pelicula> peliculas = new ArrayList<>();
    Connection conn = new Utilidades().getConnection(path);

    Statement sentenciaSQL = conn.createStatement();

    ResultSet resultado = sentenciaSQL.executeQuery(sql);

    while (resultado.next()) {
      Director director = new Director(resultado.getInt("id"), resultado.getString("director_nombre"),
          resultado.getString("url_foto"), resultado.getString("url_web"));
      Genero genero = Genero.valueOf(resultado.getString("genero_nombre"));
      Pelicula pelicula = new Pelicula(resultado.getInt("id"), resultado.getString("titulo"), director,
          resultado.getInt("anyo"), resultado.getString("url_caratula"), genero, resultado.getBoolean("es_animacion"));
      peliculas.add(pelicula);
    }
    return peliculas;
  }

  // metodo buscaPorId:
  public Pelicula buscaPorId(int id) throws SQLException {
    String sql = "SELECT p.titulo, p.anyo, p.es_animacion, d.id AS director_id, d.nombre AS director_nombre, g.descripcion AS genero_nombre FROM peliculas p JOIN directores d ON p.id_director = d.id JOIN generos g ON p.id_genero = g.id WHERE p.id = ?";

    Connection conn = new Utilidades().getConnection(path);
    PreparedStatement sentenciaSQL = conn.prepareStatement(sql);

    sentenciaSQL.setInt(1, id);

    ResultSet resultado = sentenciaSQL.executeQuery();

    if (resultado.next()) {
      Director director = new Director(resultado.getInt("id_director"), resultado.getString("director_nombre"),
          resultado.getString("url_foto"), resultado.getString("url_web"));
      return new Pelicula(id, resultado.getString("titulo"), director, resultado.getInt("anyo"),
          resultado.getString("url_caratula"), Genero.valueOf(resultado.getString("genero")),
          resultado.getBoolean("es_animacion"));
    }
    return null;
  }

  // metodo buscar pelicula PorNombre:
  public Pelicula buscaPorNombre(String titulo) throws SQLException {
    String sql = "SELECT id, titulo, id_director, anyo, url_caratula, id_genero, es_animacion FROM peliculas WHERE titulo = ?";

    Connection conn = new Utilidades().getConnection(path);
    PreparedStatement sentenciaSQL = conn.prepareStatement(sql);

    sentenciaSQL.setString(1, titulo);

    ResultSet resultado = sentenciaSQL.executeQuery();

    if (resultado.next()) {
      int id = resultado.getInt("id");
      titulo = resultado.getString("titulo");
      int año = resultado.getInt("anyo");
      String url_caratula = resultado.getString("url_caratula");
      Boolean animacion = resultado.getBoolean("es_animacion");
      int generoId = resultado.getInt("id_genero");

      // obtener el nombre del director:
      DirectorDAO directorDAO = new DirectorDAO(path);
      Director director = directorDAO.buscaPorId(generoId);

      // instancio Genero para obtenerlo directamente del enum:
      Genero genero = Genero.values()[generoId - 1];

      return new Pelicula(id, titulo, director, año, url_caratula, genero, animacion);
    }
    return null;
  }

  // metodo para insertar pelicula:
  public void insertar(Pelicula pelicula) throws SQLException {
    String sql = "INSERT INTO peliculas (id, titulo, id_director, anyo, url_caratula, id_genero, es_animacion) VALUES (?, ?, ?, ?, ?, ?, ?)";

    Connection conn = new Utilidades().getConnection(path);
    PreparedStatement sentenciaSQL = conn.prepareStatement(sql);

    sentenciaSQL.setInt(1, pelicula.getId());
    sentenciaSQL.setString(2, pelicula.getTitulo());
    sentenciaSQL.setInt(3, pelicula.getDirector().getId());
    sentenciaSQL.setInt(4, pelicula.getAnyo());
    sentenciaSQL.setString(5, pelicula.getUrl_caratula());
    sentenciaSQL.setInt(6, pelicula.getGenero().getId());
    sentenciaSQL.setBoolean(7, pelicula.isAnimacion());

    sentenciaSQL.executeUpdate();
    conn.close();
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
    sentenciaSQL.setInt(3, pelicula.getAnyo());
    sentenciaSQL.setInt(4, pelicula.getGenero().ordinal() + 1); // el indice de enum comienza en 0, pero en la db
                                                                // comienza en 1.
    sentenciaSQL.setBoolean(5, pelicula.isAnimacion());
    sentenciaSQL.setInt(6, pelicula.getId());
    sentenciaSQL.executeUpdate();
  }
}
