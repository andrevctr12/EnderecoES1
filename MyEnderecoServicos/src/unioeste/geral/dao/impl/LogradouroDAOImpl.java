package unioeste.geral.dao.impl;

import unioeste.apoio.bd.ConectorEndereco;
import unioeste.geral.bo.endereco.Logradouro;
import unioeste.geral.bo.endereco.TipoLogradouro;
import unioeste.geral.dao.LogradouroDAO;
import unioeste.geral.dao.TipoLogradouroDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author André Victor
 * @author Cristopher Vidal
 * @author Khadije El Zein
 */
public class LogradouroDAOImpl implements LogradouroDAO {
        private Connection conexao;
        private java.sql.PreparedStatement statement;

    public void inserir(Logradouro objeto) {
        try {
            ConectorEndereco conexaoDB = new ConectorEndereco();
            conexao = conexaoDB.getConnection();
            conexao.setAutoCommit(false);
            String sql = "INSERT logradouro VALUES(NULL, '"
                    + objeto.getNome() + "',' "
                    + objeto.getTipoLogradouro().getIdTipoLogradouro() + "'" + ")";
            statement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                objeto.setIdLogradouro(rs.getInt(1));
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

    public void atualizar(Logradouro objeto) {
        try {
            ConectorEndereco conexaoDB = new ConectorEndereco();
            conexao = conexaoDB.getConnection();
            conexao.setAutoCommit(false);
            String sql = "UPDATE logradouro SET "
                    +" nome=" + objeto.getNome()
                    +" idTipoLogradouro=" + objeto.getTipoLogradouro().getIdTipoLogradouro()
                    +" where idLogradouro=" + objeto.getIdLogradouro();
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
            String sql = "DELETE FROM logradouro WHERE idLogradouro=?";
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

    public Logradouro consultar(Logradouro objeto) {
        try {
            ConectorEndereco conexaoDB = new ConectorEndereco();
            conexao = conexaoDB.getConnection();
            conexao.setAutoCommit(false);
            String sql = "select * from logradouro where nome='" + objeto.getNome() + "'";
            statement = conexao.prepareStatement(sql);
            ResultSet resultado = statement.executeQuery();
            if (resultado.next()) {
                Logradouro obj = new Logradouro(new TipoLogradouro());
                obj.setIdLogradouro(resultado.getInt("idLogradouro"));
                obj.setNome(resultado.getString("nome"));
                obj.getTipoLogradouro().setIdTipoLogradouro(resultado.getInt("idTipoLogradouro"));
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

    public Logradouro consultar(int id) {
        return null;
    }

    public List<Logradouro> consultarTodos() {
        try {
            ConectorEndereco conexaoDB = new ConectorEndereco();
            conexao = conexaoDB.getConnection();
            conexao.setAutoCommit(false);
            List<Logradouro> lista = new ArrayList<Logradouro>();
            String sql = "select * from tipoLogradouro";
            statement = conexao.prepareStatement(sql);
            ResultSet resultado = statement.executeQuery();
            while (resultado.next()) {
                Logradouro obj = new Logradouro(new TipoLogradouro());
                obj.setIdLogradouro(resultado.getInt("idLogradouro"));
                obj.setNome(resultado.getString("nome"));
                obj.getTipoLogradouro().setIdTipoLogradouro(resultado.getInt("idTipoLogradouro"));
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
