package unioeste.apoio.bd;

import java.sql.*;

public class MySQLConnection {
    private static final String DRIVER_BD  = "com.mysql.jdbc.Driver";

	public Connection getConnection(String urlBD, String usuarioBD, String senhaBD) {
        try {
            Class.forName(DRIVER_BD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            return DriverManager.getConnection(urlBD, usuarioBD, senhaBD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

	public ResultSet buscarDados(String tabela, String atributo, Object valor, Connection connection) throws Exception {
		String sql =
			" SELECT *" +
			" FROM " + tabela + 
			" WHERE " + atributo + " = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setObject(1, valor);
			return stmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		throw new Exception("Erro na busca de dados");
	}

    public ResultSet buscarDados(String sql, Connection connection, Object chave) throws Exception {
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setObject(1, chave);
            return stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new Exception("Erro na busca de dados");
    }

    public ResultSet buscarDados(String sql, Connection connection) throws Exception {
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            return stmt.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new Exception("Erro na busca de dados");
    }
}
