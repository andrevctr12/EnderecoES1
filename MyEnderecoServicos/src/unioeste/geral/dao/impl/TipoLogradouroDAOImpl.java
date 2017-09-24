package unioeste.geral.dao.impl;

import unioeste.apoio.bd.ConectorEndereco;
import unioeste.geral.bo.endereco.TipoLogradouro;
import unioeste.geral.dao.TipoLogradouroDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author André Victor
 * @author Cristopher Vidal
 * @author Khadije El Zein
 */
public class TipoLogradouroDAOImpl implements TipoLogradouroDAO {
    private Connection conexao;
    private java.sql.PreparedStatement statement;

    public void inserir(TipoLogradouro objeto) {
        try {
            ConectorEndereco conexaoDB = new ConectorEndereco();
            conexao = conexaoDB.getConnection();
            conexao.setAutoCommit(false);
            String sql = "INSERT tipoLogradouro VALUES(NULL, '"
                    + objeto.getTipo() + "'" + ")";
            statement = conexao.prepareStatement(sql);
            statement.executeUpdate();
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

    public void atualizar(TipoLogradouro objeto) {
        try {
            ConectorEndereco conexaoDB = new ConectorEndereco();
            conexao = conexaoDB.getConnection();
            conexao.setAutoCommit(false);
            String sql = "UPDATE tipoLogradouro SET "
                    +" tipo=" + objeto.getTipo()
                    +" where idTipoLogradouro=" + objeto.getIdTipoLogradouro();
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
            String sql = "DELETE FROM tipoLogradouro WHERE idTipoLogradouro=?";
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

    public TipoLogradouro consultar(TipoLogradouro objeto) throws Exception {
        try {
            ConectorEndereco conexaoDB = new ConectorEndereco();
            conexao = conexaoDB.getConnection();
            conexao.setAutoCommit(false);
            String sql = "select * from tipoLogradouro where tipo='" + objeto.getTipo() + "'";
            statement = conexao.prepareStatement(sql);
            ResultSet resultado = statement.executeQuery();
            if (resultado.next()) {
                TipoLogradouro obj = new TipoLogradouro();
                obj.setIdTipoLogradouro(resultado.getInt("idTipoLogradouro"));
                obj.setTipo(resultado.getString("tipo"));
                return obj;
            }
        } catch (Exception ex) {
            try {
                conexao.rollback();
            } catch (Exception e) {
                System.out.println("Erro de conexão");
            }
            System.out.println("Erro de consulta");
            throw new Exception(ex);
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

    public TipoLogradouro consultar(int id) {
        try {
            ConectorEndereco conexaoDB = new ConectorEndereco();
            conexao = conexaoDB.getConnection();
            conexao.setAutoCommit(false);
            String sql = "select * from tipoLogradouro where idTipoLogradouro=" + id;
            statement = conexao.prepareStatement(sql);
            ResultSet resultado = statement.executeQuery();
            if (resultado.next()) {
                TipoLogradouro obj = new TipoLogradouro();
                obj.setIdTipoLogradouro(resultado.getInt("idTipoLogradouro"));
                obj.setTipo(resultado.getString("tipo"));
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

    public List<TipoLogradouro> consultarTodos() {
        try {
            ConectorEndereco conexaoDB = new ConectorEndereco();
            conexao = conexaoDB.getConnection();
            conexao.setAutoCommit(false);
            List<TipoLogradouro> lista = new ArrayList<TipoLogradouro>();
            String sql = "select * from tipoLogradouro";
            statement = conexao.prepareStatement(sql);
            ResultSet resultado = statement.executeQuery();
            while (resultado.next()) {
                TipoLogradouro tipoLogradouro = new TipoLogradouro();
                tipoLogradouro.setIdTipoLogradouro(resultado.getInt("idTipoLogradouro"));
                tipoLogradouro.setTipo(resultado.getString("tipo"));
                lista.add(tipoLogradouro);
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

//    public static void main(String[] args) {
//        TipoLogradouroDAO bd = new TipoLogradouroDAOImpl();
//        TipoLogradouro tipoLogradouro = new TipoLogradouro();
//        tipoLogradouro.setTipo("Avenida");
//        bd.inserir(tipoLogradouro);
//        tipoLogradouro = bd.consultar(tipoLogradouro);
//        //bd.consultar(1);
//        System.out.println(tipoLogradouro.getTipo());
//        //bd.excluir();
//
//    }
}

