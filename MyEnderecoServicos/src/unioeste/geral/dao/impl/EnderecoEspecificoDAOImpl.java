package unioeste.geral.dao.impl;

import unioeste.apoio.bd.ConectorEndereco;
import unioeste.geral.bo.endereco.Endereco;
import unioeste.geral.bo.endereco.EnderecoEspecifico;
import unioeste.geral.bo.endereco.Logradouro;
import unioeste.geral.bo.endereco.TipoLogradouro;
import unioeste.geral.dao.EnderecoEspecificoDAO;

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
public class EnderecoEspecificoDAOImpl implements EnderecoEspecificoDAO {
    private Connection conexao;
    private java.sql.PreparedStatement statement;

    public void inserir(EnderecoEspecifico objeto) {
        try {
            ConectorEndereco conexaoDB = new ConectorEndereco();
            conexao = conexaoDB.getConnection();
            conexao.setAutoCommit(false);
            String sql = "INSERT enderecoEspecifico VALUES(NULL, '"
                    + objeto.getNumero() + "', '"
                    + objeto.getComplemento() + "', "
                    + objeto.getEndereco().getIdEndereco() + "'" + ")";
            statement = conexao.prepareStatement(sql);
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                objeto.setIdEnderecoEspecifico(rs.getInt(1));
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

    public void atualizar(EnderecoEspecifico objeto) {
        try {
            ConectorEndereco conexaoDB = new ConectorEndereco();
            conexao = conexaoDB.getConnection();
            conexao.setAutoCommit(false);
            String sql = "UPDATE enderecoEspecifico SET "
                    +" numero=" + objeto.getNumero()
                    +" complemento=" + objeto.getComplemento()
                    +" idEndereco=" + objeto.getEndereco().getIdEndereco()
                    +" where idEnderecoEspecifico=" + objeto.getIdEnderecoEspecifico();
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
            String sql = "DELETE FROM enderecoEspecifico WHERE idEnderecoEspecifico=?";
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

    public List<EnderecoEspecifico> consultarPorNumero(int numero) {
        try {
            ConectorEndereco conexaoDB = new ConectorEndereco();
            conexao = conexaoDB.getConnection();
            conexao.setAutoCommit(false);
            String sql = "select * from enderecoEspecifico where numero='" + numero + "'";

            statement = conexao.prepareStatement(sql);
            ResultSet resultado = statement.executeQuery();

            List<EnderecoEspecifico> lista = new ArrayList<EnderecoEspecifico>();
            while (resultado.next()) {
                EnderecoEspecifico obj = new EnderecoEspecifico(new Endereco());
                obj.setIdEnderecoEspecifico(resultado.getInt("idEnderecoEspecifico"));
                obj.setNumero(resultado.getInt("numero"));
                obj.setComplemento(resultado.getString("complemento"));
                obj.getEndereco().setIdEndereco(resultado.getInt("idEndereco"));
                lista.add(obj);
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

    public List<EnderecoEspecifico> consultarPorIdEndereco(int idEndereco) {
        try {
            ConectorEndereco conexaoDB = new ConectorEndereco();
            conexao = conexaoDB.getConnection();
            conexao.setAutoCommit(false);
            String sql = "select * from enderecoEspecifico where idEndereco='" + idEndereco + "'";

            statement = conexao.prepareStatement(sql);
            ResultSet resultado = statement.executeQuery();

            List<EnderecoEspecifico> lista = new ArrayList<EnderecoEspecifico>();
            while (resultado.next()) {
                EnderecoEspecifico obj = new EnderecoEspecifico(new Endereco());
                obj.setIdEnderecoEspecifico(resultado.getInt("idEnderecoEspecifico"));
                obj.setNumero(resultado.getInt("numero"));
                obj.setComplemento(resultado.getString("complemento"));
                obj.getEndereco().setIdEndereco(resultado.getInt("idEndereco"));
                lista.add(obj);
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

    public EnderecoEspecifico consultar(int id) {
        try {
            ConectorEndereco conexaoDB = new ConectorEndereco();
            conexao = conexaoDB.getConnection();
            conexao.setAutoCommit(false);
            String sql = "select * from enderecoEspecifico where idEnderecoEspecifico=" + id;
            statement = conexao.prepareStatement(sql);
            ResultSet resultado = statement.executeQuery();
            if (resultado.next()) {
                EnderecoEspecifico obj = new EnderecoEspecifico(new Endereco());
                obj.setIdEnderecoEspecifico(resultado.getInt("idEnderecoEspecifico"));
                obj.setNumero(resultado.getInt("numero"));
                obj.setComplemento(resultado.getString("complemento"));
                obj.getEndereco().setIdEndereco(resultado.getInt("idEndereco"));
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

    public List<EnderecoEspecifico> consultarTodos() {
        try {
            ConectorEndereco conexaoDB = new ConectorEndereco();
            conexao = conexaoDB.getConnection();
            conexao.setAutoCommit(false);
            List<EnderecoEspecifico> lista = new ArrayList<EnderecoEspecifico>();
            String sql = "select * from enderecoEspecifico";
            statement = conexao.prepareStatement(sql);
            ResultSet resultado = statement.executeQuery();
            while (resultado.next()) {
                EnderecoEspecifico obj = new EnderecoEspecifico(new Endereco());
                obj.setIdEnderecoEspecifico(resultado.getInt("idEnderecoEspecifico"));
                obj.setNumero(resultado.getInt("numero"));
                obj.setComplemento(resultado.getString("complemento"));
                obj.getEndereco().setIdEndereco(resultado.getInt("idEndereco"));
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
