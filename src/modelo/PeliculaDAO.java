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
    String sql = "SELECT p.id AS id_pelicula, p.titulo AS titulo_pelicula, p.anyo AS anyo_pelicula, p.es_animacion AS es_animacion, p.url_caratula AS url_caratula, d.nombre AS nombre_director, d.id AS id_director, d.url_foto AS url_foto_director, d.url_web AS url_web_director, g.descripcion AS nombre_genero FROM peliculas p JOIN directores d ON p.id_director = d.id JOIN generos g ON p.id_genero = g.id ORDER BY g.descripcion, p.anyo DESC, p.titulo";
    ArrayList<Pelicula> peliculas = new ArrayList<>();
    Connection conn = new Utilidades().getConnection(path);

    Statement sentenciaSQL = conn.createStatement();

    ResultSet resultado = sentenciaSQL.executeQuery(sql);

    while (resultado.next()) {
      Director director = new Director(resultado.getInt("id_director"), resultado.getString("nombre_director"),
          resultado.getString("url_foto_director"), resultado.getString("url_web_director"));

      Genero genero = Genero.valueOf(resultado.getString("nombre_genero"));

      Pelicula pelicula = new Pelicula(resultado.getInt("id_pelicula"), resultado.getString("titulo_pelicula"),
          director,
          resultado.getInt("anyo_pelicula"), resultado.getString("url_caratula"), genero,
          resultado.getBoolean("es_animacion"));
      peliculas.add(pelicula);
    }
    conn.close();

    return peliculas;
  }

  // metodo buscaPorId:
  public Pelicula buscaPorId(int id) throws SQLException {

    String sql = "SELECT d.id AS id_director, d.nombre AS nombre_director, d.url_foto AS url_foto_director, d.url_web AS url_web_director, p.titulo AS titulo_pelicula, p.anyo AS anyo_pelicula, p.url_caratula AS url_caratula_pelicula, p.es_animacion AS es_animacion_pelicula, g.descripcion AS genero_pelicula FROM peliculas p LEFT JOIN directores d ON p.id_director = d.id LEFT JOIN generos g ON p.id_genero = g.id WHERE p.id = ?";
    Connection conn = new Utilidades().getConnection(path);
    PreparedStatement sentenciaSQL = conn.prepareStatement(sql);

    sentenciaSQL.setInt(1, id);

    ResultSet resultado = sentenciaSQL.executeQuery();

    if (resultado.next()) {
      Director director = new Director(resultado.getInt("id_director"), resultado.getString("nombre_director"),
          resultado.getString("url_foto_director"), resultado.getString("url_web_director"));

      return new Pelicula(id, resultado.getString("titulo_pelicula"), director, resultado.getInt("anyo_pelicula"),
          resultado.getString("url_caratula_pelicula"), Genero.valueOf(resultado.getString("genero_pelicula")),
          resultado.getBoolean("es_animacion_pelicula"));
    }
    conn.close();

    return null;
  }

  // metodo buscar pelicula por titulo:
  public Pelicula buscaPorTitulo(String titulo) throws SQLException {
    String sql = "SELECT p.id AS id_pelicula, p.titulo AS titulo_pelicula, p.id_director AS id_director, p.anyo AS anyo_pelicula, p.url_caratula AS url_caratula, p.id_genero AS id_genero_pelicula, p.es_animacion AS es_animacion FROM peliculas p WHERE titulo = ?";

    Connection conn = new Utilidades().getConnection(path);
    PreparedStatement sentenciaSQL = conn.prepareStatement(sql);

    sentenciaSQL.setString(1, titulo);

    ResultSet resultado = sentenciaSQL.executeQuery();

    if (resultado.next()) {
      int id = resultado.getInt("id_pelicula");
      titulo = resultado.getString("titulo_pelicula");
      int año = resultado.getInt("anyo_pelicula");
      String url_caratula = resultado.getString("url_caratula");
      Boolean animacion = resultado.getBoolean("es_animacion");
      int generoId = resultado.getInt("id_genero_pelicula");

      // obtener el nombre del director:
      DirectorDAO directorDAO = new DirectorDAO(path);
      Director director = directorDAO.buscaPorId(generoId);

      // instancio Genero para obtenerlo directamente del enum:
      Genero genero = Genero.values()[generoId - 1];

      return new Pelicula(id, titulo, director, año, url_caratula, genero, animacion);
    }
    conn.close();

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
    conn.close();
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
    conn.close();
  }
}
