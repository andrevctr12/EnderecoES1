package unioeste.geral.manager;

import unioeste.geral.bo.endereco.*;
import unioeste.geral.dao.BairroDAO;
import unioeste.geral.dao.CidadeDAO;
import unioeste.geral.dao.TipoLogradouroDAO;
import unioeste.geral.dao.impl.*;
import unioeste.geral.exception.EnderecoException;
import unioeste.geral.util.EnderecoValidador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class UCEnderecoGeralServicos {

    public EnderecoEspecifico cadastrarEndereco(EnderecoEspecifico enderecoEspecifico) throws EnderecoException {
        UCEnderecoInserirServicos inserirServicos = new UCEnderecoInserirServicos(enderecoEspecifico);
        if(new EnderecoValidador().validarEndereco(enderecoEspecifico)) {
            //endereco.getLogradouro().setTipoLogradouro(new TipoLogradouroDAOImpl().consultar(endereco.getLogradouro().getTipoLogradouro()));
            inserirServicos.cadastrarLogradouro();
            inserirServicos.cadastrarCidade();
            inserirServicos.cadastrarBairro();
            inserirServicos.cadastrarEndereco();
            new EnderecoEspecificoDAOImpl().inserir(enderecoEspecifico);
            return enderecoEspecifico;
        }
        throw new EnderecoException("Erro no cadastro de endereço");
    }

	public EnderecoEspecifico excluirEndereco(EnderecoEspecifico enderecoEspecifico) throws EnderecoException {

        throw new EnderecoException("Erro na exclusão de endereço");
    }

    // TODO
//	public Endereco alterarEndereco(Endereco endereco) {
//	}
//	public Endereco obterEnderecoPorSite(String site) {
//	}


    public List<EnderecoEspecifico> obterEnderecoPorCEP(String CEP) throws EnderecoException {
        List<EnderecoEspecifico> lista = new ArrayList<EnderecoEspecifico>();
        List<Endereco> listaEndereco = new EnderecoDAOImpl().consultar(CEP);

        for (Endereco endereco : listaEndereco) {
            List<EnderecoEspecifico> temp = new EnderecoEspecificoDAOImpl().consultarPorIdEndereco(endereco.getIdEndereco());
            lista.addAll(temp);
        }

        if (lista.isEmpty()) {
            throw new EnderecoException("Não há endereços cadastrados nesse CEP");
        }
        return lista;
    }

    public EnderecoEspecifico obterEnderecoPorID(EnderecoEspecifico endereco) throws EnderecoException {
        EnderecoEspecifico enderecoEspecifico = new EnderecoEspecificoDAOImpl().consultar(endereco.getIdEnderecoEspecifico());
        if (enderecoEspecifico == null) {
            throw new EnderecoException("ID não encontrado");
        }
        return endereco;
    }

    public Cidade obterCidade(Cidade cidade) throws EnderecoException { // TODO: não tá sendo usado

        throw new EnderecoException("ID da cidade não encontrado");
    }

}
