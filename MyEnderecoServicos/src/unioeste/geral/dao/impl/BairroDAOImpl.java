package unioeste.geral.dao.impl;

import unioeste.apoio.bd.ConectorEndereco;
import unioeste.geral.bo.endereco.Bairro;
import unioeste.geral.dao.BairroDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author André Victor
 * @author Cristopher Vidal
 * @author Khadije El Zein
 */
public class BairroDAOImpl implements BairroDAO {
    private Connection conexao;
    private java.sql.PreparedStatement statement;

    public void inserir(Bairro objeto) {
        try {
            ConectorEndereco conexaoDB = new ConectorEndereco();
            conexao = conexaoDB.getConnection();
            conexao.setAutoCommit(false);
            String sql = "INSERT bairro VALUES(NULL, '"
                    + objeto.getNome() + "'" + ")";
            statement = conexao.prepareStatement(sql);
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                objeto.setIdBairro(rs.getInt(1));
            }
            else {
                throw new SQLException("Nenhum ID obtido");
            }
        } catch (Exception ex) {
            try {
                conexao.rollback();
            } catch (Exception e) {
                System.out.println("Erro de conexão");
            }
            ex.printStackTrace();
            System.out.println("Erro de inserção");
        } finally {
            try {
                if (conexao != null) {
                    conexao.setAutoCommit(true);
                    statement.close();
                    conexao.close();
                }
            } catch (Exception ex) {
                System.out.println("Erro de consistencia");
            }
        }
    }

    public void atualizar(Bairro objeto) {
        try {
            ConectorEndereco conexaoDB = new ConectorEndereco();
            conexao = conexaoDB.getConnection();
            conexao.setAutoCommit(false);
            String sql = "UPDATE bairro SET "
                    +" nome=" + objeto.getNome()
                    +" where idTipoLogradouro=" + objeto.getIdBairro();
            statement = conexao.prepareStatement(sql);
            statement.executeUpdate();
        } catch (Exception ex) {
            try {
                conexao.rollback();
            } catch (Exception e) {
                System.out.println("Erro de conexão");
            }
            ex.printStackTrace();
            System.out.println("Erro de update");
        } finally {
            try {
                if (conexao != null) {
                    conexao.setAutoCommit(true);
                    statement.close();
                    conexao.close();
                }
            } catch (Exception ex) {
                System.out.println("Erro de consistencia");
            }
        }
    }

    public void excluir(int id) {
        try {
            ConectorEndereco conexaoDB = new ConectorEndereco();
            conexao = conexaoDB.getConnection();
            conexao.setAutoCommit(false);
            String sql = "DELETE FROM bairro WHERE idBairro=?";
            statement = conexao.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception ex) {
            try {
                conexao.rollback();
            } catch (Exception e) {
                System.out.println("Erro de conexão");
            }
            System.out.println("Erro de exclusão");
        } finally {
            try {
                if (conexao != null) {
                    conexao.setAutoCommit(true);
                    statement.close();
                    conexao.close();
                }
            } catch (Exception ex) {
                System.out.println("Erro de consistencia");
            }
        }
    }

    public Bairro consultar(Bairro objeto) {
        try {
            ConectorEndereco conexaoDB = new ConectorEndereco();
            conexao = conexaoDB.getConnection();
            conexao.setAutoCommit(false);
            String sql = "select * from bairro where nome='" + objeto.getNome() + "'";
            statement = conexao.prepareStatement(sql);
            ResultSet resultado = statement.executeQuery();
            if (resultado.next()) {
                Bairro obj = new Bairro();
                obj.setIdBairro(resultado.getInt("idBairro"));
                obj.setNome(resultado.getString("nome"));
                return obj;
            }
        } catch (Exception ex) {
            try {
                conexao.rollback();
            } catch (Exception e) {
                System.out.println("Erro de conexão");
            }
            System.out.println("Erro de consulta");
        } finally {
            try {
                if (conexao != null) {
                    conexao.setAutoCommit(true);
                    statement.close();
                    conexao.close();
                }
            } catch (Exception ex) {
                System.out.println("Erro de consistencia");
            }
        }
        return null;
    }

    public Bairro consultar(int id) {
        return null;
    }

    public List<Bairro> consultarTodos() {
        try {
            ConectorEndereco conexaoDB = new ConectorEndereco();
            conexao = conexaoDB.getConnection();
            conexao.setAutoCommit(false);

            String sql = "select * from bairro";
            statement = conexao.prepareStatement(sql);
            ResultSet resultado = statement.executeQuery();

            List<Bairro> lista = new ArrayList<Bairro>();
            while (resultado.next()) {
                Bairro obj = new Bairro();
                obj.setIdBairro(resultado.getInt("idBairro"));
                obj.setNome(resultado.getString("nome"));
                lista.add(obj);
            }
            return lista;
        } catch (Exception ex) {
            try {
                conexao.rollback();
            } catch (Exception e) {
                System.out.println("Erro de conexão");
            }
            System.out.println("Erro de consulta");
        } finally {
            try {
                if (conexao != null) {
                    conexao.setAutoCommit(true);
                    statement.close();
                    conexao.close();
                }
            } catch (Exception ex) {
                System.out.println("Erro de consistencia");
            }
        }
        return null;
    }
}
