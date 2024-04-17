package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Test;

import modelo.Utilidades;

public class TestDAO {
  @Test
  public void testIfJDBCExists() {

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

    String DB_PATH = "./data/create_database.sqlite";
    Connection conn = utils.getConnection(DB_PATH);
    assertNotNull(conn);
    conn.close();
  }
}