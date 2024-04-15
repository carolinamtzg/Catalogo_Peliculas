package tests;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import modelo.Utilidades;

public class TestDAO {
  @Test
  public void testJDBCConecta() {

    boolean laClaseJDBCExiste = false;
    try {
      Class.forName("org.sqlite.JDBC");
      System.out.println("Se ha conectado correctamente");
      laClaseJDBCExiste = true;
    } catch (ClassNotFoundException err) {
      System.out.println("SQLite JDBC no encontrado");
      err.printStackTrace();
    }

    assertTrue(laClaseJDBCExiste);
  }

  @Test
  public void testCrearConexion() throws SQLException {
    Utilidades utils = new Utilidades();

    Connection conn = utils.getConnection("./data/create_database.sqlite");
    assertNotNull(conn);
    conn.close();

    conn = utils.getConnection("./data/create_database.sqlite");
    assertNull(conn);
  }
}
