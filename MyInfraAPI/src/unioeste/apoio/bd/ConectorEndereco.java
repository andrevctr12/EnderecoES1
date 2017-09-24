package unioeste.apoio.bd;

import java.sql.Connection;

/**
 * @author André Victor
 * @author Cristopher Vidal
 * @author Khadije El Zein
 */
public class ConectorEndereco {
    private static final String USUARIO_BD = "root";
    private static final String SENHA_BD   = "";
    private static final String URL_BD     = "jdbc:mysql://localhost/enderecoDB";

    public Connection getConnection() {
        return new MySQLConnection().getConnection(URL_BD, USUARIO_BD, SENHA_BD);
    }
}
