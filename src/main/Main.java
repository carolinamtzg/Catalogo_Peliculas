package main;

public class Main {

  public static void main(String[] args) {

    int num = 1;

    String statement = String.format("SELECT id, nombre, url_foto, url_web FROM directores WHERE id = %d", num);
    System.out.println(statement); // Output: Hello Friend 1

    // String sql = "SELECT id, nombre, url_foto, url_web FROM directores WHERE id =
    // 1";

    // Connection conn = new
    // Utilidades().getConnection("./data/create_database.sqlite");

    // try {

    // Statement sentenciaSQL = conn.createStatement();

    // ResultSet resultado = sentenciaSQL.executeQuery(sql);
    // System.out.println(resultado);
    // if (resultado.next()) {
    // System.out.println(resultado.getString("nombre"));
    // }
    // System.out.println("Done");

    // } catch (SQLException e) {
    // System.out.println("Error");
    // e.printStackTrace();
    // }
  }
}
