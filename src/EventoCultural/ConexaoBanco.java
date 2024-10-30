package EventoCultural;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco {

    private String url = "jdbc:mysql://localhost:3306/db_eventoCultural?createDatabaseIfNotExist=true";
    private String usuario = "root";
    private String senha = "";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, usuario, senha);
    }

}
