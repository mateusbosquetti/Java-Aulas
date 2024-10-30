package EventoCultural;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco {

    private static String url = "jdbc:mysql://localhost:3306/db_eventoCultural";
    private static String usuario = "root";
    private static String senha = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, usuario, senha);
    }

}
