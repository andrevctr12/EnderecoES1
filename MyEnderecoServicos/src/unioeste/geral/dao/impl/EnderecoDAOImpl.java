//package unioeste.geral.dao.impl;
//
//import unioeste.apoio.bd.ConectorEndereco;
//import unioeste.apoio.bd.MySQLConnection;
//import unioeste.geral.bo.endereco.Endereco;
//import unioeste.geral.dao.EnderecoDAO;
//
//
//import java.sql.*;
//
//public class EnderecoDAOImpl implements EnderecoDAO {
//    private Connection conexao;
//    private java.sql.PreparedStatement statement;
//
//    @Override
//    public void inserir(Endereco objeto) throws SQLException {
//        try {
//            ConectorEndereco conexaoDB = new ConectorEndereco();
//            conexao = conexaoDB.getConnection();
//            conexao.setAutoCommit(false);
//            String sql = "INSERT taxa VALUES(NULL, '"
//                    + obj.getNomeTaxa() + "', "
//                    + obj.getValorTaxa() + ", "
//                    + obj.getIdTipoTaxa() + ")";
//            statement = conexao.prepareStatement(sql);
//            statement.executeUpdate();
//        } catch (Exception ex) {
//            try {
//                conexao.rollback();
//            } catch (Exception e) {
//                System.out.println("Erro 1");
//            }
//            ex.printStackTrace();
//            System.out.println("Erro 2");
//        } finally {
//            try {
//                if (conexao != null) {
//                    conexao.setAutoCommit(true);
//                    statement.close();
//                    conexao.close();
//                }
//            } catch (Exception ex) {
//                System.out.println("Erro 3");
//            }
//        }
//    }
//
//	public void alterarEndereco(Endereco objeto, Connection connection) {
//        try {
//            String sql =
//                    "UPDATE Endereco " +
//					"SET " +
//                        "cep = ?, " +
//					"WHERE idEndereco = ?";
//
//            PreparedStatement stmt = connection.prepareStatement(sql);
//
//			int i = 1;
//            stmt.setString(i, objeto.getCep());
//
//            stmt.execute();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//        public void alterarEnderecoCEP(String objeto, Connection connection) {
//        try {
//            String sql =
//                    "UPDATE Endereco " +
//					"SET " +
//                        "cep = ?, " +
//                        "WHERE cep = ?";
//
//            PreparedStatement stmt = connection.prepareStatement(sql);
//            stmt.setString(1, objeto);
//
//            stmt.execute();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//	public void deletarEndereco(Endereco objeto, Connection connection) throws SQLException {
//        String sql =
//                "DELETE FROM Endereco " +
//                "WHERE idEndereco = ?";
//
//        PreparedStatement stmt = connection.prepareStatement(sql);
//
//        stmt.setInt(1, objeto.getIdEndereco());
//
//        stmt.execute();
//    }
//
//    public ResultSet buscarEnderecoPorID(int idEndereco, Connection connection) throws Exception {
//        String sql =
//                "SELECT * " +
//                "FROM Endereco " +
//                "WHERE idEndereco = ?";
//        try {
//            return new MySQLConnection().buscarDados(sql, connection, idEndereco);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        throw new Exception("ID não existente");
//    }
//
//    public ResultSet buscarEnderecoPorCEP(String cep, Connection connection) throws Exception {
//        String sql =
//                "SELECT * " +
//                "FROM Endereco " +
//                "WHERE cep = ?";
//        try {
//            return new MySQLConnection().buscarDados(sql, connection, cep);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        throw new Exception("ID não existente");
//    }
//
//    public ResultSet buscarTodosEnderecos(Connection connection) throws EnderecoException {
//        String sql =
//                "SELECT * " +
//                "FROM Endereco";
//        try {
//            return new MySQLConnection().buscarDados(sql, connection);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        throw new EnderecoException("Erro na busca de todos endereços");
//    }
//
//}
