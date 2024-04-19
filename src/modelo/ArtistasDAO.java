package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ArtistasDAO {
  private String path;

  public ArtistasDAO(String path) {
    this.path = path;
    try {
      Connection conn = new Utilidades().getConnection(path);
      if (conn != null) {
        System.out.println("Conexión establecida correctamente.");
      }
      conn.close(); // cierro la conexion despues de verificar
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  // metodo dameTodos:
  public ArrayList<Artistas> dameTodos() throws SQLException {
    String sql = "SELECT id, nombre, url_foto, url_web FROM artistas ORDER BY nombre";

    ArrayList<Artistas> listaArtistas = new ArrayList<>();
    Connection conn = new Utilidades().getConnection(path);

    Statement sentenciaSQL = conn.createStatement();

    ResultSet resultado = sentenciaSQL.executeQuery(sql);

    while (resultado.next()) {
      Artistas siguiente;
      siguiente = new Artistas(resultado.getInt("id"), resultado.getString("nombre"), resultado.getString("url_foto"),
          resultado.getString("url_web"));
      listaArtistas.add(siguiente);
    }
    conn.close(); // cierro la conexion despues de la query
    return listaArtistas;
  }

  // metodo buscaPorId:
  public Artistas buscaPorId(int id) throws SQLException {
    Connection conn = new Utilidades().getConnection(path);
    String statement = "SELECT id, nombre, url_foto, url_web FROM artistas WHERE id = ?";
    PreparedStatement sentenciaSQL = conn.prepareStatement(statement);
    sentenciaSQL.setInt(1, id);

    ResultSet resultado = sentenciaSQL.executeQuery();

    if (resultado.next()) {
      Artistas artista = new Artistas(resultado.getInt("id"), resultado.getString("nombre"),
          resultado.getString("url_foto"),
          resultado.getString("url_web"));
      conn.close();
      return artista;
    }
    conn.close();
    return null;
  }

  // metodo buscaPorNombre:
  public Artistas buscaPorNombre(String nombre) throws SQLException {
    String sql = "SELECT id, nombre, url_foto, url_web FROM artistas WHERE nombre = ?";
    Connection conn = new Utilidades().getConnection(path);
    PreparedStatement sentenciaSQL = conn.prepareStatement(sql);

    sentenciaSQL.setString(1, nombre);

    ResultSet resultado = sentenciaSQL.executeQuery();

    if (resultado.next()) {
      Artistas artistas = new Artistas(resultado.getInt("id"), resultado.getString("nombre"),
          resultado.getString("url_foto"),
          resultado.getString("url_web"));
      conn.close();
      return artistas;
    } else {
      conn.close();
      return null;
    }
  }

  // metodo para insertar artista:
  public void insertar(Artistas artista) throws SQLException {
    String sql = "INSERT INTO artistas (id, nombre, url_foto, url_web) VALUES (?, ?, ?, ?)";

    Connection conn = new Utilidades().getConnection(path);
    PreparedStatement sentenciaSQL = conn.prepareStatement(sql);

    sentenciaSQL.setInt(1, artista.getId());
    sentenciaSQL.setString(2, artista.getNombre());
    sentenciaSQL.setString(3, artista.getUrl_foto());
    sentenciaSQL.setString(4, artista.getUrl_web());

    sentenciaSQL.executeUpdate();
    conn.close();
  }

  // metodo para borrar artista:
  public void borra(int id) throws SQLException {
    String sql = "DELETE FROM artistas WHERE id = ?";
    Connection conn = new Utilidades().getConnection(path);
    PreparedStatement sentenciaSQL = conn.prepareStatement(sql);

    sentenciaSQL.setInt(1, id);

    sentenciaSQL.executeUpdate();
    conn.close();
  }

  // metodo para modificar artista:
  public void modifica(Artistas artista) throws SQLException {
    // String sql = "UPDATE artistas SET nombre = ? WHERE id = ?";
    String sql = "UPDATE artistas SET nombre = ?, url_foto = ?, url_web = ?  WHERE id = ?";
    Connection conn = new Utilidades().getConnection(path);
    PreparedStatement sentenciaSQL = conn.prepareStatement(sql);

    sentenciaSQL.setString(1, artista.getNombre());
    sentenciaSQL.setString(2, artista.getUrl_foto());
    sentenciaSQL.setString(3, artista.getUrl_web());
    sentenciaSQL.setInt(4, artista.getId());

    sentenciaSQL.executeUpdate();
    conn.close();
  }

}
