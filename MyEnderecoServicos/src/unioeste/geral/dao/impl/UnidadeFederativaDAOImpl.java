package unioeste.geral.dao.impl;

import unioeste.apoio.bd.ConectorEndereco;
import unioeste.geral.bo.endereco.Pais;
import unioeste.geral.bo.endereco.UnidadeFederativa;
import unioeste.geral.dao.UnidadeFederativaDAO;

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
public class UnidadeFederativaDAOImpl implements UnidadeFederativaDAO {
    private Connection conexao;
    private java.sql.PreparedStatement statement;

    public void inserir(UnidadeFederativa objeto) {
        try {
            ConectorEndereco conexaoDB = new ConectorEndereco();
            conexao = conexaoDB.getConnection();
            conexao.setAutoCommit(false);
            String sql = "INSERT unidadeFederativa VALUES(NULL, '"
                    + objeto.getNome() + "','"
                    + objeto.getPais().getIdPais() + "'" + ")";
            statement = conexao.prepareStatement(sql);
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                objeto.setIdUnidadeFederativa(rs.getInt(1));
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

    public void atualizar(UnidadeFederativa objeto) {
        try {
            ConectorEndereco conexaoDB = new ConectorEndereco();
            conexao = conexaoDB.getConnection();
            conexao.setAutoCommit(false);
            String sql = "UPDATE unidadeFederativa SET "
                    +" nome=" + objeto.getNome()
                    +" idPais=" + objeto.getPais().getIdPais()
                    +" where idUnidadeFederativa=" + objeto.getIdUnidadeFederativa();
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
            String sql = "DELETE FROM unidadeFederativa WHERE idUnidadeFederativa=?";
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

    public UnidadeFederativa consultar(UnidadeFederativa objeto) {
        try {
            ConectorEndereco conexaoDB = new ConectorEndereco();
            conexao = conexaoDB.getConnection();
            conexao.setAutoCommit(false);
            String sql = "select * from unidadeFederativa where nome='" + objeto.getNome() + "'";
            statement = conexao.prepareStatement(sql);
            ResultSet resultado = statement.executeQuery();
            if (resultado.next()) {
                UnidadeFederativa obj = new UnidadeFederativa(new Pais());
                obj.setIdUnidadeFederativa(resultado.getInt("idUnidadeFederativa"));
                obj.setNome(resultado.getString("nome"));
                obj.getPais().setIdPais(resultado.getInt("idPais"));
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

    public UnidadeFederativa consultar(int id) {
        return null;
    }

    public List<UnidadeFederativa> consultarTodos() {
        try {
            ConectorEndereco conexaoDB = new ConectorEndereco();
            conexao = conexaoDB.getConnection();
            conexao.setAutoCommit(false);

            String sql = "select * from unidadeFederativa";
            statement = conexao.prepareStatement(sql);
            ResultSet resultado = statement.executeQuery();

            List<UnidadeFederativa> lista = new ArrayList<UnidadeFederativa>();
            while (resultado.next()) {
                UnidadeFederativa obj = new UnidadeFederativa(new Pais());
                obj.setIdUnidadeFederativa(resultado.getInt("idUnidadeFederativa"));
                obj.setNome(resultado.getString("nome"));
                obj.getPais().setIdPais(resultado.getInt("idPais"));
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
