package unioeste.geral.dao.impl;

import unioeste.apoio.bd.ConectorEndereco;
import unioeste.geral.bo.endereco.Bairro;
import unioeste.geral.bo.endereco.Cidade;
import unioeste.geral.bo.endereco.Endereco;
import unioeste.geral.bo.endereco.Logradouro;
import unioeste.geral.dao.EnderecoDAO;

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
public class EnderecoDAOImpl implements EnderecoDAO {
    private Connection conexao;
    private java.sql.PreparedStatement statement;

    public void inserir(Endereco objeto) {
        try {
            ConectorEndereco conexaoDB = new ConectorEndereco();
            conexao = conexaoDB.getConnection();
            conexao.setAutoCommit(false);
            String sql = "INSERT endereco VALUES(NULL, '"
                    + objeto.getCEP() + "', '"
                    + objeto.getCidade().getIdCidade() + "', "
                    + objeto.getBairro().getIdBairro() + "', "
                    + objeto.getLogradouro().getIdLogradouro() + "'" + ")";
            statement = conexao.prepareStatement(sql);
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                objeto.setIdEndereco(rs.getInt(1));
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

    public void atualizar(Endereco objeto) {
        try {
            ConectorEndereco conexaoDB = new ConectorEndereco();
            conexao = conexaoDB.getConnection();
            conexao.setAutoCommit(false);
            String sql = "UPDATE endereco SET "
                    +" CEP=" + objeto.getCEP()
                    +" idCidade=" + objeto.getCidade().getIdCidade()
                    +" idBairro=" + objeto.getBairro().getIdBairro()
                    +" idLogradouro=" + objeto.getLogradouro().getIdLogradouro()
                    +" where idEndereco=" + objeto.getIdEndereco();
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
            String sql = "DELETE FROM endereco WHERE idEndereco=?";
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

    public List<Endereco> consultar(String CEP) {
        try {
            ConectorEndereco conexaoDB = new ConectorEndereco();
            conexao = conexaoDB.getConnection();
            conexao.setAutoCommit(false);
            String sql = "select * from endereco where CEP='" + CEP + "'";
            statement = conexao.prepareStatement(sql);
            ResultSet resultado = statement.executeQuery();

            List<Endereco> lista = new ArrayList<Endereco>();
            while (resultado.next()) {
                Endereco obj = new Endereco(new Cidade(), new Bairro(), new Logradouro());
                obj.setIdEndereco(resultado.getInt("idEndereco"));
                obj.setCEP(resultado.getString("CEP"));
                obj.getCidade().setIdCidade(resultado.getInt("idCidade"));
                obj.getBairro().setIdBairro(resultado.getInt("idBairro"));
                obj.getLogradouro().setIdLogradouro(resultado.getInt("idLogradouro"));
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

    public Endereco consultar(int id) {
        return null;
    }

    public Endereco consultar(Endereco endereco) {
        try {
            ConectorEndereco conexaoDB = new ConectorEndereco();
            conexao = conexaoDB.getConnection();
            conexao.setAutoCommit(false);
            String sql = "select * from endereco where idBairro='" + endereco.getBairro().getIdBairro() + "'"
                    + "and idCidade='" + endereco.getCidade().getIdCidade() + "' and idLogradouro='" + endereco.getLogradouro().getIdLogradouro() + "'";
            statement = conexao.prepareStatement(sql);
            ResultSet resultado = statement.executeQuery();

            while (resultado.next()) {
                Endereco obj = new Endereco(new Cidade(), new Bairro(), new Logradouro());
                obj.setIdEndereco(resultado.getInt("idEndereco"));
                obj.setCEP(resultado.getString("CEP"));
                obj.getCidade().setIdCidade(resultado.getInt("idCidade"));
                obj.getBairro().setIdBairro(resultado.getInt("idBairro"));
                obj.getLogradouro().setIdLogradouro(resultado.getInt("idLogradouro"));
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

    public List<Endereco> consultarTodos() {
        try {
            ConectorEndereco conexaoDB = new ConectorEndereco();
            conexao = conexaoDB.getConnection();
            conexao.setAutoCommit(false);
            List<Endereco> lista = new ArrayList<Endereco>();
            String sql = "select * from endereco";
            statement = conexao.prepareStatement(sql);
            ResultSet resultado = statement.executeQuery();
            while (resultado.next()) {
                Endereco obj = new Endereco(new Cidade(), new Bairro(), new Logradouro());
                obj.setIdEndereco(resultado.getInt("idEndereco"));
                obj.setCEP(resultado.getString("CEP"));
                obj.getCidade().setIdCidade(resultado.getInt("idCidade"));
                obj.getBairro().setIdBairro(resultado.getInt("idBairro"));
                obj.getLogradouro().setIdLogradouro(resultado.getInt("idLogradouro"));
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
