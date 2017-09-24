package unioeste.geral.manager;

import unioeste.geral.bo.endereco.*;
import unioeste.geral.dao.TipoLogradouroDAO;
import unioeste.geral.dao.impl.LogradouroDAOImpl;
import unioeste.geral.dao.impl.TipoLogradouroDAOImpl;
import unioeste.geral.exception.EnderecoException;
import unioeste.geral.util.EnderecoValidador;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UCEnderecoGeralServicos {

    public EnderecoEspecifico cadastrarEndereco(EnderecoEspecifico enderecoEspecifico) throws Exception {
        if(new EnderecoValidador().validarEndereco(enderecoEspecifico)) {
            Endereco endereco = enderecoEspecifico.getEndereco();
            //endereco.getLogradouro().setTipoLogradouro(new TipoLogradouroDAOImpl().consultar(endereco.getLogradouro().getTipoLogradouro()));
            Logradouro logradouro = new LogradouroDAOImpl().consultar(endereco.getLogradouro());

            if (logradouro == null) {
                new LogradouroDAOImpl().inserir(endereco.getLogradouro());
            }
        }
        else {
            throw new EnderecoException("Erro no cadastro de endereço");
        }

    }

	public EnderecoEspecifico excluirEndereco(EnderecoEspecifico endereco) throws EnderecoException {
        try {
            new EnderecoControle().removerEndereco(endereco);
            return endereco;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new EnderecoException("Erro na exclusão de endereço");
    }

    // TODO
//	public Endereco alterarEndereco(Endereco endereco) {
//	}
//	public Endereco obterEnderecoPorSite(String site) {
//	}

    public UCEnderecoGeralServicos() {
        this.connection = new Conector().getConnection(URL_BD, USUARIO_BD, SENHA_BD);
    }

    private Endereco popularEndereco(ResultSet rs) {
        try {
            if (rs.next()) {
                // Constrói objetos auxiliares para construção do objeto Endereco
                CidadeControle cidadeControle = new CidadeControle();
                Cidade cidade = cidadeControle.obterCidade(rs.getInt("Cidade_idCidade"));

                BairroControle bairroControle = new BairroControle();
                Bairro bairro = bairroControle.obterBairro(rs.getInt("Bairro_idBairro"));

                LogradouroControle logradouroControle = new LogradouroControle();
                Logradouro logradouro = logradouroControle.obterLogradouro(rs.getInt("Logradouro_idLogradouro"));

                // Constrói objeto Endereco com os objetos obtidos acima
                return new Endereco(rs.getInt("idEndereco"), rs.getString("cep"), logradouro, bairro, cidade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Endereco obterEnderecoPorCEP(String cep) throws EnderecoException {
        ResultSet rs;
        Endereco endereco = null;
        try {
            rs = new EnderecoDAO().buscarEnderecoPorCEP(cep, connection);
            endereco = popularEndereco(rs);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (endereco == null) {
            throw new EnderecoException("CEP não encontrado");
        }
        return endereco;
    }

    public Endereco obterEnderecoPorID(Endereco enderecoEntrada) throws EnderecoException {
        ResultSet rs;
        Endereco endereco = null;
        try {
            rs = new EnderecoDAO().buscarEnderecoPorID(enderecoEntrada.getIdEndereco(), connection);
            endereco = popularEndereco(rs);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (endereco == null) {
            throw new EnderecoException("ID não encontrado");
        }
        return endereco;
    }

    public Cidade obterCidade(Cidade cidadeEntrada) throws EnderecoException { // TODO: não tá sendo usado
        ResultSet rsCidade = new CidadeDAO().buscarCidadePorID(cidadeEntrada.getIdCidade(), connection);
        try {
            if (rsCidade.next()) {
                // Popula Pais a partir de Cidade
                ResultSet rsPais = new PaisDAO().buscarPaisPorID(rsCidade.getInt("UnidadeFederativa_Pais_idPais"), connection);
                rsPais.next();
                Pais pais = new Pais(rsPais.getInt("idPais"), rsPais.getString("nomePais"), rsPais.getString("siglaPais"));

                // Popula UF a partir de Cidade
                ResultSet rsUF = new UnidadeFederativaDAO().buscarUnidadeFederativaPorID(rsCidade.getString("UnidadeFederativa_idUnidadeFederativa"), connection);
                rsUF.next();
                UnidadeFederativa unidadeFederativa = new UnidadeFederativa(rsUF.getInt("idUnidadeFederativa"),
                        rsUF.getString("nomeUnidadeFederativa"), rsUF.getString("siglaUnidadeFederativa"), pais);

                return new Cidade(rsCidade.getInt("idCidade"), rsCidade.getString("nomeCidade"), unidadeFederativa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        throw new EnderecoException("ID da cidade não encontrado");
    }

}
