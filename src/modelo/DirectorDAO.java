package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DirectorDAO {
  private String path;

  public DirectorDAO(String path) {
    this.path = path;

    Connection conn = new Utilidades().getConnection(path);
    if (conn != null) {
      System.out.println("Conexión establecida correctamente.");
      try {
        conn.close(); // cierro la conexion despues de verificar
      } catch (SQLException e) {
        e.printStackTrace();
      }
    } else {
      System.out.println("Error al establecer la conexión.");
    }
  }

  // metodo dameTodos:
  public ArrayList<Director> dameTodos() throws SQLException {
    String sql = "SELECT id, nombre FROM directores ORDER BY nombre";

    ArrayList<Director> directores = new ArrayList<>();
    Connection conn = new Utilidades().getConnection(path);

    Statement sentenciaSQL = conn.createStatement();

    ResultSet resultado = sentenciaSQL.executeQuery(sql);

    while (resultado.next()) {
      Director siguiente;
      siguiente = new Director(resultado.getInt("id"), resultado.getString("nombre"), resultado.getString("url_foto"),
          resultado.getString("url_web"));
      directores.add(siguiente);
    }
    return directores;
  }

  // metodo buscaPorId:
  public Director buscaPorId(int id) throws SQLException {
    String sql = "SELECT id FROM directores WHERE id = ?";

    Connection conn = new Utilidades().getConnection(path);
    PreparedStatement sentenciaSQL = conn.prepareStatement(sql);

    sentenciaSQL.setInt(1, id);

    ResultSet resultado = sentenciaSQL.executeQuery();

    if (resultado.next()) {
      return new Director(resultado.getInt("id"), resultado.getString("nombre"), resultado.getString("url_web"),
          resultado.getString("nombre"));
    } else {
      return null;
    }

  }

  // metodo buscaPorNombre:
  public Director buscaPorNombre(String nombre) throws SQLException {
    String sql = "SELECT nombre FROM directores WHERE nombre = ?";
    Connection conn = new Utilidades().getConnection(path);
    PreparedStatement sentenciaSQL = conn.prepareStatement(sql);

    sentenciaSQL.setString(1, nombre);

    ResultSet resultado = sentenciaSQL.executeQuery();

    if (resultado.next()) {
      return new Director(resultado.getInt("id"), resultado.getString("nombre"), resultado.getString("url_foto"),
          resultado.getString("url_web"));
    } else {
      return null;
    }
  }

  // metodo para borrar director:
  public void borra(int id) throws SQLException {
    String sql = "DELETE FROM directores WHERE id = ?";
    Connection conn = new Utilidades().getConnection(path);
    PreparedStatement sentenciaSQL = conn.prepareStatement(sql);

    sentenciaSQL.setInt(1, id);
    sentenciaSQL.executeUpdate();
  }

  // metodo para modificar director:
  public void modifica(Director director) throws SQLException {
    String sql = "UPDATE directores SET nombre = ? WHERE id = ?";
    Connection conn = new Utilidades().getConnection(path);
    PreparedStatement sentenciaSQL = conn.prepareStatement(sql);

    sentenciaSQL.setInt(1, director.getId());
    sentenciaSQL.setString(2, director.getNombre());
    sentenciaSQL.setString(3, director.getUrl_foto());
    sentenciaSQL.setString(4, director.getUrl_web());
    sentenciaSQL.executeUpdate();
  }
}