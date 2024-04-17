package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Utilidades {
  public Connection getConnection(String path) {
    Connection conn = null;
    try {
      conn = DriverManager.getConnection(String.format("jdbc:sqlite:%s", path));
      System.out.println("Conexión establecida correctamente.");
    } catch (SQLException e) {
      System.out.println("Error al establecer la conexión a la base de datos.");
      e.printStackTrace();
    }
    return conn;
  }
}
